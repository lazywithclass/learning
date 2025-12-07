import os
import google.generativeai as genai
from github import Github

# 1. Setup Configuration
GEMINI_API_KEY = os.getenv("GEMINI_API_KEY")
GITHUB_TOKEN = os.getenv("GITHUB_TOKEN")
REPO_NAME = os.getenv("REPO_NAME")
COMMIT_SHA = os.getenv("COMMIT_SHA")

# Configure Gemini
genai.configure(api_key=GEMINI_API_KEY)
model = genai.GenerativeModel("gemini-flash-latest")

def get_commit_changes():
    """Fetches the specific changes (diffs) for the current commit."""
    g = Github(GITHUB_TOKEN)
    repo = g.get_repo(REPO_NAME)
    commit = repo.get_commit(COMMIT_SHA)
    
    diff_data = ""
    file_count = 0
    
    for file in commit.files:
        # Optional: Filter again to ensure we only review specific extensions or folders
        # if not file.filename.startswith("src/"): continue 
        
        if file.patch:  # Only include files that have text changes (not binary files)
            diff_data += f"\n\n--- File: {file.filename} ---\n"
            diff_data += file.patch
            file_count += 1
            
    return repo, commit, diff_data, file_count

def generate_review(diff_text):
    """Sends the diff to Gemini for review."""
    prompt = f"""
    You are a senior code reviewer. Review the following code changes (git diff).
    
    Instructions:
    1. Focus on bugs, security vulnerabilities, and code clarity.
    2. Be concise. Do not summarize what the code does; purely critique it.
    3. If the code looks good, just say "LGTM".
    4. Use Markdown formatting.
    
    Code Changes:
    {diff_text}
    """
    
    response = model.generate_content(prompt)
    return response.text

def main():
    if not GEMINI_API_KEY:
        print("Error: GEMINI_API_KEY is missing.")
        return

    print(f"Fetching commit {COMMIT_SHA}...")
    repo, commit, diff_data, file_count = get_commit_changes()
    
    if file_count == 0:
        print("No reviewable text changes found.")
        return

    print(f"Sending {len(diff_data)} characters of code to Gemini...")
    review = generate_review(diff_data)
    
    print("Posting review to GitHub...")
    # This posts a comment directly on the commit SHA
    commit.create_comment(f"## 🤖 Gemini AI Review\n\n{review}")
    print("Done!")

if __name__ == "__main__":
    main()
