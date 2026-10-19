(ns redis.cmd.transaction)

(defn discard!
  "DISCARD flushes all previously queued commands in a transaction and restores the connection state to normal.
   If WATCH was used, DISCARD also unwatches all keys that were being watched by the connection.

   ```
   DISCARD
   ```

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn exec!
  "Executes all previously queued commands in a transaction and restores the connection state to normal.
   When used together with WATCH, EXEC executes the queued commands only if none of the watched keys were modified, providing a check-and-set mechanism.
   In clustered Redis environments, the behavior of this command may vary.

   ```
   EXEC
   ```

   Array reply: each element being the reply to each of the commands in the atomic transaction.
   Null reply: the transaction was aborted because a WATCHed key was touched."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn multi!
  "Marks the start of a transaction block.
   Subsequent commands are queued for atomic execution and will be run when EXEC is called.
   In clustered Redis environments the behavior of this command varies; see multi-key operations for details.

   ```
   MULTI
   ```

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn unwatch!
  "Flushes all the previously watched keys for a transaction.
   If EXEC or DISCARD is called, there is no need to manually call UNWATCH.

   ```
   UNWATCH
   ```

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn watch!
  "Marks the given keys to be watched for conditional execution of a transaction.

   ```
   WATCH key [key ...]
   ```

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))
