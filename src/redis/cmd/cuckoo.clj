(ns redis.cmd.cuckoo)

(defn cf-add!
  "Adds an item to the cuckoo filter stored at the given key.
   If the key does not exist, a new cuckoo filter is created first.
   Cuckoo filters can contain the same item multiple times, and each addition is treated as a separate entry.
   Use CF.ADDNX to add an item only if it does not already exist.

   ```
   CF.ADD key item
   ```

   key: the key name for the cuckoo filter to add the item to; if the key does not exist a new cuckoo filter is created.
   item: the item to add to the filter.

   Boolean reply: true for successfully adding an item to the filter.
   Simple error reply in these cases: invalid arguments, wrong key type, or when the filter is full."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cf-addnx!
  "Adds an item to a cuckoo filter only if the item is not already present.
   If the item fingerprint already exists in the filter, nothing is added.
   This is equivalent to checking existence first and then adding, but done atomically.
   If the key does not exist, a new cuckoo filter is created before the item is added.

   ```
   CF.ADDNX key item
   ```

   key: the key name for a cuckoo filter to add items to, a new cuckoo filter is created if the key does not exist.
   item: the item to add.

   Boolean reply: true for successfully adding an item to the filter or false if the item fingerprint already exists in the filter.
   Simple error reply in these cases: invalid arguments, wrong key type, or when the filter is full."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cf-count!
  "Returns an estimation of the number of times a given item was added to a cuckoo filter.
   A positive value is an estimation; an overestimation is possible but not an underestimation.
   A value of 0 means the key does not exist or the item was never added to the filter.
   If you only need to check whether an item exists, use CF.EXISTS instead.

   ```
   CF.COUNT key item
   ```

   key: the key name for a cuckoo filter.
   item: the item to check.

   Integer reply: a positive value is an estimation of the number of times item was added to the filter, an overestimation is possible but not an underestimation, 0 means that key does not exist or that item had not been added to the filter.
   Simple error: invalid arguments or wrong key type."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cf-del!
  "Deletes an item once from a cuckoo filter.
   If the item exists only once, it is removed from the filter.
   If the item was added multiple times, it remains present after one deletion.
   Deleting an item that was not previously added may corrupt the filter and cause false negatives, so only delete items you are certain were added.

   ```
   CF.DEL key item
   ```

   key: the key name for a cuckoo filter.
   item: an item to delete.

   Boolean reply true for successfully deleting an item, or false if no such item was found in the filter.
   Simple error reply in these cases: invalid arguments or wrong key type."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cf-exists!
  "Determines whether a given item was added to a cuckoo filter.
   This command is similar to CF.MEXISTS, except that only one item can be checked.

   ```
   CF.EXISTS key item
   ```

   key: key name for a cuckoo filter.
   item: an item to check.

   Boolean reply, where a value of true means the item was probably already added to the filter. A value of false means the item was definitely not added, key does not exist, or key contains a value of the wrong type.
   Simple error reply if invalid arguments are passed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cf-info!
  "Returns information about a cuckoo filter stored at the given key.
   If the key does not exist, or the key holds a value that is not a cuckoo filter,
   an error is returned.

   ```
   CF.INFO key
   ```

   key: the key name for a cuckoo filter.

   Simple error reply if invalid arguments are passed, key does not exist,
   or key is not of the correct type.
   Map reply with argument name (Simple string reply) and value (Integer reply) pairs."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cf-insert!
  "Adds one or more items to a cuckoo filter.
   If the key does not exist, a new cuckoo filter is created automatically unless NOCREATE is specified.
   This command is similar to CF.ADD, except that multiple items can be added in a single call and the capacity of a new filter can be specified.

   ```
   CF.INSERT key [CAPACITY capacity] [NOCREATE] ITEMS item [item ...]
   ```

   key: the key name for a cuckoo filter to add items to; if the key does not exist a new cuckoo filter is created.
   ITEMS item...: one or more items to add to the filter.
   CAPACITY capacity: specifies the desired capacity of the new filter if it does not exist yet; ignored if the filter already exists; if not specified the filter is created with the module-level default capacity of 1024.
   NOCREATE: prevents automatic filter creation if the filter does not exist, returning an error instead; mutually exclusive with CAPACITY.

   Simple error reply when the number of arguments or key type is incorrect, and also when NOCREATE is specified and key does not exist.
   Array reply, where each element is either a boolean reply of true for successfully adding an item, or an integer reply of -1 when the item cannot be added because the filter is full."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cf-insertnx!
  "Adds one or more items to a cuckoo filter, but only if their fingerprints do not already exist in the filter.
   If the key does not exist, a new cuckoo filter is created automatically unless NOCREATE is specified.
   This command is similar to CF.ADDNX but supports adding multiple items at once and allows specifying a custom capacity for the new filter.

   ```
   CF.INSERTNX key [CAPACITY capacity] [NOCREATE] ITEMS item [item ...]
   ```

   key: the key name for the cuckoo filter to add items to; if the key does not exist a new filter is created.
   ITEMS item...: one or more items to add to the filter.
   CAPACITY capacity: the desired capacity of the new filter if it does not exist yet; ignored if the filter already exists; defaults to 1024 if not specified.
   NOCREATE: prevents automatic filter creation if the filter does not exist, returning an error instead; mutually exclusive with CAPACITY.

   Simple error reply when the number of arguments or key type is incorrect, and also when NOCREATE is specified and key does not exist.
   Array reply, where each element is an integer reply: 0 means that the item fingerprint already exists in the filter, 1 for successfully adding an item, or -1 when the item cannot be added because the filter is full."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cf-loadchunk!
  "Restores a cuckoo filter that was previously saved using CF.SCANDUMP.
   The command takes the iterator and data chunk produced by CF.SCANDUMP and uses them to rebuild the filter under the given key.
   An error is returned if the arguments are invalid, the key holds a different type, or the data chunk is not valid.

   ```
   CF.LOADCHUNK key iterator data
   ```

   key: the key name for the cuckoo filter to restore.
   iterator: the iterator value associated with the data chunk, as returned by CF.SCANDUMP.
   data: the current data chunk, as returned by CF.SCANDUMP.

   Simple string reply: OK if executed correctly.
   Simple error reply: if the arguments are invalid, the key type is wrong, or invalid data was passed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cf-mexists!
  "Determines whether one or more items were added to a cuckoo filter.
   This command is similar to CF.EXISTS, except that more than one item can be checked in a single call.
   A value of true means the item was probably already added to the filter.
   A value of false means the item was definitely not added, the key does not exist, or the key contains a value of the wrong type.

   ```
   CF.MEXISTS key item [item ...]
   ```

   key: the key name for a cuckoo filter.
   item...: one or more items to check.

   An array of booleans, where each element corresponds to an item in the request.
   A value of true means the item was probably already added to the filter.
   A value of false means the item was definitely not added, key does not exist, or key contains a value of the wrong type.
   Simple error reply if invalid arguments are passed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cf-reserve!
  "Creates an empty cuckoo filter with a single sub-filter for the initial specified capacity.
   The filter is likely to declare itself full before reaching its full capacity, so the fill rate will rarely reach 100 percent.
   Using a larger bucket size improves the fill rate at the cost of a higher error rate.
   When the filter declares itself full, it auto-expands by generating additional sub-filters, which reduces performance and increases the error rate.
   Each new sub-filter is sized as the previous sub-filter multiplied by the expansion factor.
   The minimal false positive error rate is approximately 0.78 percent when bucket size is 1, and larger buckets increase the error rate linearly.
   Unused capacity in prior sub-filters is automatically used when possible.
   The filter can grow up to 32 times.

   ```
   CF.RESERVE key capacity [BUCKETSIZE bucketsize]
     [MAXITERATIONS maxiterations] [EXPANSION expansion]
   ```

   key: the key name for the cuckoo filter to be created.
   capacity: estimated capacity for the filter, rounded to the next power of 2; the filter will likely not fill up to 100 percent of it, so reserve extra capacity to avoid expansions.
   BUCKETSIZE bucketsize: number of items in each bucket, an integer between 1 and 255, defaults to 2; a higher value improves fill rate but increases error rate and slightly slows performance.
   MAXITERATIONS maxiterations: number of attempts to swap items between buckets before declaring the filter full, an integer between 1 and 65535, defaults to 20; a low value favors performance while a high value favors fill rate.
   EXPANSION expansion: the size of a new sub-filter is the current filter size multiplied by this value, an integer between 0 and 32768, defaults to 1, rounded to the next power of 2.

   Simple string reply: OK if the filter was created successfully.
   Simple error reply in these cases: invalid arguments or the key already exists."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cf-scandump!
  "Begins an incremental save of a cuckoo filter.
   This is useful for large cuckoo filters that cannot fit into the DUMP and RESTORE model.
   On the first call the iterator must be 0; subsequent calls pass back the iterator returned by the previous call.
   The command returns successive iterator-data pairs until the iterator is 0 and the data is NULL, indicating completion.
   Each iterator-data pair should also be passed to CF.LOADCHUNK when restoring the filter.

   ```
   CF.SCANDUMP key iterator
   ```

   key: key name for a cuckoo filter to save.
   iterator: iterator value, either 0 or the iterator from a previous invocation of this command.

   Array reply: a two-element array of an Integer reply (Iterator) and a Bulk string reply (Data).
   Simple error reply in these cases: invalid arguments, the key was not found, or the key is of the wrong type."
  [_state _msg]
  (throw (Exception. "Not Implemented")))