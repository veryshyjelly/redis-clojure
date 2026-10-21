(ns redis.cmd.pf)

(defn pfadd!
  "Adds all the given elements to the HyperLogLog data structure stored at the specified key.
   If the key does not exist, an empty HyperLogLog structure is created automatically.
   The HyperLogLog internals may be updated to reflect a different estimation of the number of unique items added so far.
   The command can be called with only the key and no elements; in that case it is a no-op if the key already exists, or it simply creates the data structure if the key does not exist.

   ```
   PFADD key [element [element ...]]
   ```

   key: the name of the HyperLogLog key.
   element [element ...]: one or more elements to add to the HyperLogLog, with no elements the command only creates the key if it does not already exist.

   Integer reply: 1 if at least one HyperLogLog internal register was altered.
   Integer reply: 0 if no HyperLogLog internal registers were altered."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn pfcount!
  "Returns the approximated cardinality of the HyperLogLog data structure stored at the given key.
   If the key does not exist, the result is 0.
   When multiple keys are supplied, the command internally merges the HyperLogLogs into a temporary one and returns the approximated cardinality of their union.
   The HyperLogLog structure uses a fixed 12k bytes of memory per key, and the returned cardinality carries a standard error of 0.81%.
   As a side effect, the last 8 bytes of the HyperLogLog may be updated to cache the latest computed cardinality, making PFCOUNT technically a write command.

   ```
   PFCOUNT key [key ...]
   ```

   key [key ...]: one or more HyperLogLog keys; with multiple keys the command returns the approximated cardinality of their union.

   Integer reply: the approximated number of unique elements observed via PFADD."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn pfdebug!
  "PFDEBUG is an internal command.
   It is meant to be used for developing and testing Redis.

   ```
   PFDEBUG subcommand key
   ```"
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn pfmerge!
  "Merges multiple HyperLogLog values into a single HyperLogLog that approximates the cardinality of the union of all observed sets.
   The merged result is stored in the destination key, which is created as an empty HyperLogLog if it does not already exist.
   If the destination key already exists, its value is treated as one of the source sets and its cardinality is included in the computed union.

   ```
   PFMERGE destkey [sourcekey [sourcekey ...]]
   ```

   destkey: the destination HyperLogLog key in which to store the merged result.
   sourcekey [sourcekey ...]: one or more source HyperLogLog keys to merge into destkey, destkey itself is also included in the merge.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn pfselftest!
  "PFSELFTEST is an internal command.
   It is meant to be used for developing and testing Redis.

   ```
   PFSELFTEST
   ```

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))
