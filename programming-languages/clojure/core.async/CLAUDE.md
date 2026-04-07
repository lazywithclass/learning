# Technical Spike: Clojure core.async Data Pipeline

## Context
We are building a data ingestion system that interacts with a legacy medical records API. The source provides metadata in XML format and must be polled at a specific interval ($N$ minutes). Each XML contains document IDs that must be used to download binary medical reports/images.

## Objective
Implement a robust, production-grade spike in **Clojure** using **core.async** to demonstrate efficient handling of I/O-bound tasks and throttled polling logic.

---

## 1. Architectural Requirements
The spike must implement a multi-stage pipeline using channels (`chan`):

* **The Ticker (Producer):** * An infinite loop using `(timeout ...)` or a recurring `ticker`.
    * **Constraint:** Must handle backpressure. If the previous fetch/download cycle is still active, the next pulse should be skipped or queued to prevent resource exhaustion.
* **XML Ingestion & Parsing:**
    * Mock or real HTTP call to retrieve XML.
    * Extract a collection of `DocumentIDs`.
* **ID Dispatcher (Pipeline):**
    * Distribute IDs into a worker pool.
    * Demonstrate usage of `clojure.core.async/pipeline` or `pipeline-async` to manage concurrency (e.g., max 5-10 concurrent downloads).
* **Document Downloader (Consumer):**
    * Simulate binary download for each ID.
    * Inject random `(timeout ...)` to simulate real-world network latency.
* **Error Handling:**
    * Implement a dedicated error channel. Ensure a single failed XML parse or document download does not crash the entire process.

---

## 2. Technical Guidelines for Implementation
* **Parking vs. Blocking:** Strictly prefer "parking" operations (`>!`, `<!`) within `go` blocks to ensure we don't saturate the fixed thread pool.
* **State:** Keep the system as functional as possible. Use `atoms` only for tracking global telemetry (e.g., "Total bytes downloaded" or "Failed IDs").
* **XML Library:** Use `org.clojure/data.xml`.
* **Graceful Shutdown:** Implement a "Kill Pill" or a `stop-channel` pattern to allow for a clean exit of all `go-loops`.
* **Logging:** Provide meaningful logs for each stage (Polling started, IDs found, Download started/finished).

---

## 3. Deliverables
Please provide a complete, runnable project structure:
1.  **deps.edn**: Include `org.clojure/core.async`, `org.clojure/data.xml`, and `clj-http`.
2.  **core.clj**: The implementation of the pipeline.
3.  **Runner**: A `-main` function that starts the spike with an $N$ minute interval.

---

## 4. Specific Challenge
"Claude, please pay special attention to the **backpressure** between the Ticker and the Downloader. If the XML returns 1,000 IDs and the downloader is limited to 5 concurrent workers, show how core.async manages that buffer without crashing the JVM."
