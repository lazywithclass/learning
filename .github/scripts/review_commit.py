import os
import sys
import base64
import google.generativeai as genai
from github import Github

GEMINI_API_KEY = os.getenv("GEMINI_API_KEY")
GITHUB_TOKEN = os.getenv("GITHUB_TOKEN")
REPO_NAME = os.getenv("REPO_NAME")
COMMIT_SHA = os.getenv("COMMIT_SHA")

SCRIPT_DIR = os.path.dirname(os.path.abspath(__file__))
default_prompt_path = os.path.join(SCRIPT_DIR, "prompt.md")
PROMPT_FILE = os.getenv("PROMPT_FILE", default_prompt_path)

CONTEXT_DIRECTORY = "algorithms/using-clojure"

genai.configure(api_key=GEMINI_API_KEY)
model = genai.GenerativeModel("gemini-flash-latest")

def load_prompt_instructions(filepath):
    """Reads the custom prompt instructions from a file."""
    if not os.path.exists(filepath):
        print(f"Error: Prompt file '{filepath}' not found.")
        sys.exit(1)
        
    try:
        with open(filepath, "r", encoding="utf-8") as f:
            return f.read().strip()
    except Exception as e:
        print(f"Error reading prompt file: {e}")
        sys.exit(1)

def get_project_context(repo, commit_sha):
    """
    Fetches the full content of all files in the CONTEXT_DIRECTORY
    at the state of the current commit.
    """
    print(f"Fetching project context from: {CONTEXT_DIRECTORY}...")
    context_data = ""
    file_count = 0
    
    try:
        tree = repo.get_git_tree(commit_sha, recursive=True)
        
        for element in tree.tree:
            if element.path.startswith(CONTEXT_DIRECTORY) and element.type == "blob":
                valid_extensions = ('.clj', '.edn', '.md', '.txt', '.xml', '.yml')
                if not element.path.endswith(valid_extensions):
                    continue

                blob = repo.get_git_blob(element.sha)
                if blob.encoding == "base64":
                    content = base64.b64decode(blob.content).decode('utf-8', errors='replace')
                else:
                    content = blob.content

                context_data += f"\n\n--- Context File: {element.path} ---\n"
                context_data += content
                file_count += 1
                
    except Exception as e:
        print(f"Warning: Could not fetch project context: {e}")
    
    print(f"  - Loaded {file_count} files for context.")
    return context_data

def get_commit_changes(repo, commit):
    """Fetches the specific changes (diffs) for the current commit."""
    diff_data = ""
    file_count = 0
    
    for file in commit.files:
        if file.patch:  # Only include files that have text changes
            diff_data += f"\n\n--- Diff: {file.filename} ---\n"
            diff_data += file.patch
            file_count += 1
            
    return diff_data, file_count

def generate_review(diff_text, project_context, base_instructions):
    """
    Sends the diff + full project context to Gemini.
    """
    final_prompt = f"""
    {base_instructions}

    =========================================
    PART 1: PROJECT CONTEXT
    (Use these files to understand definitions, imports, and architecture. Do not review these files unless they are also in the diff.)
    
    {project_context}

    =========================================
    PART 2: CODE CHANGES TO REVIEW
    (Focus your critique/review specifically on these changes)
    
    {diff_text}
    """
    
    # Calculate rough token count (4 chars ~= 1 token) to avoid surprises, 
    # though Gemini Flash handles 1M+ tokens easily.
    print(f"Sending prompt (~{len(final_prompt)//4} tokens)...")
    
    response = model.generate_content(final_prompt)
    return response.text

def main():
    if not GEMINI_API_KEY:
        print("Error: GEMINI_API_KEY is missing.")
        return

    g = Github(GITHUB_TOKEN)
    repo = g.get_repo(REPO_NAME)
    commit = repo.get_commit(COMMIT_SHA)

    print(f"Loading prompt from {PROMPT_FILE}...")
    base_instructions = load_prompt_instructions(PROMPT_FILE)

    print(f"Fetching commit changes {COMMIT_SHA}...")
    diff_data, diff_file_count = get_commit_changes(repo, commit)
    
    if diff_file_count == 0:
        print("No reviewable text changes found.")
        return

    project_context = get_project_context(repo, COMMIT_SHA)
    review = generate_review(diff_data, project_context, base_instructions)
    print("Posting review to GitHub...")
    commit.create_comment(f"## 🤖 Gemini AI Review\n\n{review}")
    print("Done!")

if __name__ == "__main__":
    main()
