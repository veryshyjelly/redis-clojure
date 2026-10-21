(ns redis.cmd.cms)

(defn cms-incrby!
  "Increases the count of one or more items in a Count-Min Sketch by the specified increment values.
   Multiple item-increment pairs can be provided in a single call.
   If the key does not exist, the key is the wrong type, the arguments are invalid, or an overflow occurs, a simple error is returned.

   ```
   CMS.INCRBY key item increment [item increment ...]
   ```

   key: the name of the sketch.
   item increment [item increment ...]: one or more item-increment pairs, each item's counter is increased by its corresponding increment.

   Array of integer replies representing updated min-counts of each of the provided items in the sketch.
   Simple error reply in these cases: invalid arguments, missing key, overflow, or wrong key type."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cms-info!
  "Returns the width, depth, and total count of a Count-Min Sketch.
   If the key does not exist, or the key holds a value that is not a Count-Min Sketch, a simple error is returned.

   ```
   CMS.INFO key
   ```

   key: the name of the sketch.

   Map reply of simple string reply and integer reply pairs containing sketch information.
   Simple error reply in these cases: invalid arguments, missing key, or wrong key type."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cms-initbydim!
  "Initializes a Count-Min Sketch data structure at the given key with user-specified dimensions.
   The width and depth parameters control the accuracy of the sketch: a larger width reduces the error size, while a larger depth reduces the probability of an error of a certain size.
   If the key already exists, the command returns an error.

   ```
   CMS.INITBYDIM key width depth
   ```

   key: the name of the sketch.
   width: number of counters in each array, reduces the error size.
   depth: number of counter-arrays, reduces the probability for an error of a certain size (percentage of total count).

   Simple string reply OK if executed correctly.
   Simple error reply if the given key already exists."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cms-initbyprob!
  "Initializes a Count-Min Sketch data structure under the given key.
   The sketch is sized to meet the requested error tolerance and false-positive probability.
   If the key already exists, the command returns an error.

   ```
   CMS.INITBYPROB key error probability
   ```

   key: the name of the sketch.
   error: estimate of the error as a percent of total counted items, affects the width of the sketch.
   probability: desired probability for inflated count, a decimal between 0 and 1, affects the depth of the sketch; the closer to zero, the greater the memory consumption per item and CPU usage per operation.

   Simple string reply OK if executed correctly.
   Simple error reply if the given key already exists."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cms-merge!
  "Merges several Count-Min Sketches into a single destination sketch.
   All source sketches must have the same width and depth as the destination.
   Weights can be specified to multiply individual sketches before merging; the default weight is 1.
   The destination sketch must already be initialized.
   A simple error is returned if any key does not exist or if the width or depth of a source sketch does not match the destination.

   ```
   CMS.MERGE destination numKeys source [source ...] [WEIGHTS weight
     [weight ...]]
   ```

   destination: the name of the destination sketch, must be initialized.
   numKeys: number of sketches to be merged.
   source [source ...]: names of source sketches to be merged.
   WEIGHTS weight [weight ...]: multiple of each sketch, default is 1.

   Simple string reply OK if executed correctly.
   Simple error reply in these cases: non-existent key or destination key is not of the same width and/or depth."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cms-query!
  "Returns the count for one or more items in a Count-Min Sketch.
   If the key does not exist or holds a value of the wrong type, a simple error is returned.

   ```
   CMS.QUERY key item [item ...]
   ```

   key: the name of the sketch.
   item [item ...]: one or more items for which to return the count.

   Array of integer replies representing the min-counts of each of the provided items in the sketch.
   Simple error reply in these cases: invalid arguments, missing key, or wrong key type."
  [_state _msg]
  (throw (Exception. "Not Implemented")))