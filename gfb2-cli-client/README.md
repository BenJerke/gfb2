This is a test/beta client for GFB2, a time-tracking application. (The browser interface needs work.)
- Implements user login & signup.
- Fetches user tasks & tags from the GFB2 server. 
- Allows users to create new tasks and tags 
- Allows users to run working sessions; logging time to their tasks & updating statuses

Data is saved to an in-memory H2 database and written to a CSV on logout.