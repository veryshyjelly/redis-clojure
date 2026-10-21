(ns redis.cmd.topk)

(defn topk-add!
  "Adds one or more items to a Top-k sketch stored at the given key.
   If an item enters the Top-K sketch, the item that is expelled (if any) is returned.
   This enables dynamic heavy-hitter detection of items being entered or expelled from the sketch.

   ```
   TOPK.ADD key items [items ...]
   ```

   key: the name of the sketch where items are added.
   item [item ...]: the items to be added.

   Array of bulk string replies containing either dropped elements or null.
   Simple error reply in these cases: incorrect number of arguments, non-existant key, or key of the incorrect type."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn topk-count!
  "Returns the approximate count for each specified item in a Top-K sketch.
   Multiple items can be queried in a single call.
   The returned count is an upper bound on the true frequency and will typically be lower than the actual number of appearances.
   The count value should not be treated as an exact representation of how many times an item has appeared.

   ```
   TOPK.COUNT key item [item ...]
   ```

   key: the name of the sketch where items are to be counted.
   item [item ...]: the items to be counted.

   Array of integer replies representing the count of each specified item. For non-existant items, 0 is returned.
   Simple error reply in these cases: incorrect number of arguments, non-existant key, or key of the incorrect type."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn topk-incrby!
  "Increases the score of one or more items in a Top-K sketch by the specified increment.
   Multiple item-increment pairs can be supplied to update several items in a single call.
   When an item enters the Top-K list, the item that is expelled (if any) is returned.
   The increment value must be at least 1 and at most 100,000 to avoid server freeze.

   ```
   TOPK.INCRBY key item increment [item increment ...]
   ```

   key: the name of the sketch.
   item increment [item increment ...]: one or more item-increment pairs, each item is incremented by its corresponding increment, the increment must be between 1 and 100,000.

   Array of bulk string replies containing either dropped elements or null.
   Simple error reply in these cases: incorrect number of arguments, non-existant key, key of the incorrect type, or an incorrect increment (less than 0 or greater than 100,000)."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn topk-info!
  "Returns the configuration parameters of a Top-K sketch stored at the given key.
   The returned values include the number of required items (k), the width, the depth, and the decay factor.
   If the key does not exist or holds a value of the wrong type, a simple error is returned.

   ```
   TOPK.INFO key
   ```

   key: the name of the sketch.

   A map of simple string and integer pairs.
   For decay, a double reply is used to represent the floating point value.
   Simple error reply in these cases: incorrect number of arguments, non-existant key, or key of the incorrect type."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn topk-list!
  "Returns the full list of items stored in a Top-K sketch.
   The items are sorted by decreased count estimation.
   When the WITHCOUNT option is given, the count of each element is returned alongside its name.
   A simple error is returned for invalid arguments, a non-existent key, or a key of the incorrect type.

   ```
   TOPK.LIST key [WITHCOUNT]
   ```

   key: the name of the sketch.
   WITHCOUNT: the count of each element is also returned.

   Array of bulk string replies representing the names of items in the given sketch. If WITHCOUNT is requested, an array of bulk string reply and integer reply pairs, representing the names of the items in the sketch together with their counts.
   Simple error reply in these cases: invalid arguments, non-existant key, or key of the incorrect type."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn topk-query!
  "Checks whether one or more items are among the Top-K items stored in a sketch.
   If the key does not exist or holds a value of the wrong type, a simple error is returned.

   ```
   TOPK.QUERY key item [item ...]
   ```

   key: the name of the sketch.
   item [item ...]: the items to be queried.

   Simple error reply: non-existant key or key of the incorrect type.
   Array of boolean replies: true if an item is in the Top-K or false otherwise."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn topk-reserve!
  "Initializes a Top-K sketch data structure under the given key.
   The sketch is configured with the specified parameters and is ready to receive items.
   If the key already exists, the command returns an error.

   ```
   TOPK.RESERVE key topk [width depth decay]
   ```

   key: the name of the Top-K sketch.
   topk: the number of top occurring items to keep.
   width: number of counters kept in each array, defaults to 8.
   depth: number of arrays, defaults to 7.
   decay: the probability of reducing a counter in an occupied bucket,
          computed as decay raised to the power of the bucket counter,
          defaults to 0.9.

   Simple string reply OK if executed correctly.
   Simple error reply in these cases: incorrect number of arguments,
   invalid decay value, or key already exists."
  [_state _msg]
  (throw (Exception. "Not Implemented")))