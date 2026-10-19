(ns redis.cmd.uncategorized)

(defn bf-add!
  "Adds an item to a Bloom filter.
   If the key does not exist, a new Bloom filter is created with default error rate, capacity, and expansion.
   This command is similar to BF.MADD except that only one item can be added.

   ```
   BF.ADD key item
   ```

   key: the key name for a Bloom filter to add the item to; if the key does not exist, a new Bloom filter is created with default error rate, capacity, and expansion.
   item: the item to add.

   Boolean reply: true for successfully adding an item, or false if there is a probability that the item was already added to the filter.
   Simple error reply in these cases: invalid arguments, wrong key type, or when the filter is full."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn bf-card!
  "Returns the cardinality of a Bloom filter.
   The cardinality is the number of items that were added and detected as unique,
   meaning items that caused at least one bit to be set in at least one sub-filter.
   If the key does not exist, the command returns 0.
   An error is returned for invalid arguments, wrong key type, or when the filter is full.
   When the key exists, the returned value is the same as BF.INFO key ITEMS.

   ```
   BF.CARD key
   ```

   key: the key name for a Bloom filter.

   Integer reply: the number of items detected as unique that were added to the
   Bloom filter (items that caused at least one bit to be set in at least one
   sub-filter), or 0 when the given key does not exist.
   Simple error reply in these cases: invalid arguments, wrong key type, or when
   the filter is full.
   Note: when key exists, BF.CARD returns the same value as BF.INFO key ITEMS."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn bf-exists!
  "Determines whether a given item was added to a Bloom filter.
   This command is similar to BF.MEXISTS, except that only one item can be checked at a time.

   ```
   BF.EXISTS key item
   ```

   key: the key name for a Bloom filter.
   item: the item to check.

   Boolean reply: true means the item was probably already added to the filter, false means the item was definitely not added, the key does not exist, or the key contains a value of the wrong type.
   Simple error reply if invalid arguments are passed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn bf-info!
  "Returns information about a Bloom filter stored at the given key.
   If no property is specified, all properties are returned together.
   A specific property can be requested by passing one of the supported option keywords.
   A simple error is returned when the arguments are invalid, the key holds a value of the wrong type, or the key does not exist.

   ```
   BF.INFO key [CAPACITY | SIZE | FILTERS | ITEMS | EXPANSION]
   ```

   key: the key name for a Bloom filter.
   CAPACITY: return the number of unique items that can be stored in this Bloom filter before scaling would be required, including already added items.
   SIZE: return the memory size, in bytes, allocated for this Bloom filter.
   FILTERS: return the number of sub-filters.
   ITEMS: return the number of items that were added to this Bloom filter and detected as unique, meaning items that caused at least one bit to be set in at least one sub-filter.
   EXPANSION: return the expansion rate.

   Simple error reply in these cases: invalid arguments, wrong key type, or when the key does not exist.
   Map reply with simple string and integer pairs."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn bf-insert!
  "Creates a new Bloom filter under the given key if it does not already exist, using the specified error rate, capacity, and expansion, then adds all specified items to the filter.
   If the key already exists, the existing filter is used as-is and the items are simply added to it.
   This command is a convenience combination of BF.RESERVE and BF.MADD, allowing the filter parameters to be set at creation time.

   ```
   BF.INSERT key [CAPACITY capacity] [ERROR error]
     [EXPANSION expansion] [NOCREATE] [NONSCALING] ITEMS item [item
     ...]
   ```

   key: the key name for a Bloom filter to add items to; if the key does not exist, a new Bloom filter is created.
   ITEMS item...: one or more items to add to the filter.
   NOCREATE: do not create the filter if it does not already exist; an error is returned instead, and it is an error to specify NOCREATE together with CAPACITY or ERROR.
   CAPACITY capacity: the desired capacity for a newly created filter; ignored if the filter already exists, and if absent the module-level capacity is used.
   ERROR error: the error ratio for a newly created filter; if absent the module-level error rate is used.
   NONSCALING: prevent the filter from creating additional sub-filters when initial capacity is reached; the filter returns an error when full.
   EXPANSION expansion: the multiplier for the size of a new sub-filter when capacity is reached, specified as a positive integer; defaults to 2.

   Array reply, where each element is one of the following:
   Boolean reply true for successfully adding an item, or false if there is a probability that the item was already added to the filter.
   Simple string reply when the item cannot be added because the filter is full.
   Simple error reply when the number of arguments or key type is wrong, and also when NOCREATE is specified and key does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn bf-loadchunk!
  "Restores a Bloom filter previously saved using BF.SCANDUMP.
   Each call loads one chunk of data into the filter identified by key.
   The iterator and data values must be taken from a prior BF.SCANDUMP response.

   ```
   BF.LOADCHUNK key iterator data
   ```

   key: key name for a Bloom filter to restore.
   iterator: iterator value associated with data, returned by BF.SCANDUMP.
   data: current data chunk, returned by BF.SCANDUMP.

   Simple string reply: OK if executed correctly.
   Simple error reply in these cases: invalid arguments, wrong key type, or when invalid data was passed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn bf-madd!
  "Adds one or more items to a Bloom filter.
   If the key does not exist, a new Bloom filter is created
   with default error rate, capacity, and expansion.
   Unlike BF.INSERT, the error rate, capacity, and expansion
   cannot be specified.

   ```
   BF.MADD key item [item ...]
   ```

   key: the key name for a Bloom filter to add the items to;
     if the key does not exist, a new Bloom filter is created
     with default error rate, capacity, and expansion.
   item...: one or more items to add.

   Array reply where each element is either a boolean reply,
   where true means the item has been added successfully,
   and false means there is a probability that the item was
   already added to the filter, or a simple error reply when
   the item cannot be added because the filter is full.
   Simple error reply in these cases: invalid arguments,
   key not found, wrong key type, or when the filter is full."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn bf-mexists!
  "Determines whether one or more items were added to a Bloom filter.
   This command is similar to BF.EXISTS, except that more than one item can be checked in a single call.
   If the key does not exist or contains a value of the wrong type, the result for each item is false.

   ```
   BF.MEXISTS key item [item ...]
   ```

   key: the key name for a Bloom filter.
   item...: one or more items to check.

   An array of booleans, where each element corresponds to an item in the request. A value of true means the item was probably already added to the filter. A value of false means the item was definitely not added, key does not exist, or key contains a value of the wrong type.
   Simple error reply if invalid arguments are passed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn bf-reserve!
  "Creates an empty Bloom filter under the given key with a single sub-filter
   sized for the specified capacity and an upper-bound false-positive rate.
   By default the filter auto-scales: when the current sub-filter reaches
   capacity, a new sub-filter is created whose size is the previous sub-filter
   size multiplied by the expansion factor.
   It is recommended to reserve the estimated required capacity up front,
   because each additional sub-filter consumes extra memory for its bits and
   hash functions and adds CPU overhead compared to a filter created with the
   right capacity from the start.
   The optimal number of hash functions is ceil(-ln(error_rate) / ln(2)),
   and the total bits needed is capacity * -ln(error_rate) / ln(2)^2.

   ```
   BF.RESERVE key error_rate capacity [EXPANSION expansion]
     [NONSCALING]
   ```

   key: the key name for the Bloom filter to be created.
   error_rate: the desired probability for false positives, a decimal value
     between 0 and 1.
   capacity: the number of entries intended to be added to the filter;
     performance degrades linearly with the number of sub-filters once
     this limit is exceeded.
   NONSCALING: prevents the filter from creating additional sub-filters
     when initial capacity is reached; the filter returns an error when
     capacity is reached.
   EXPANSION expansion: the multiplier for the size of each new sub-filter,
     specified as a positive integer, defaults to 2.

   Simple string reply: OK if the filter was created successfully.
   Simple error reply: invalid arguments or the key already exists."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn bf-scandump!
  "Begins an incremental save of a Bloom filter.
   This is useful for large Bloom filters that cannot fit into the DUMP and RESTORE model.
   The first call must use iterator 0.
   Successive calls return iterator-data pairs until the iterator is 0 and data is null, indicating completion.
   Each iterator-data pair should be passed to BF.LOADCHUNK when restoring the filter.

   ```
   BF.SCANDUMP key iterator
   ```

   key: key name for a Bloom filter to save.
   iterator: iterator value, either 0 or the iterator from a previous invocation of this command.

   Array reply: a two-element array of an Integer reply (Iterator) and a Bulk string reply (Data).
   Simple error reply in these cases: invalid arguments, the key was not found, or the key is of the wrong type.
   The Iterator is passed as input to the next invocation of BF.SCANDUMP.
   If Iterator is 0, then it means iteration has completed.
   The iterator-data pair should also be passed to BF.LOADCHUNK when restoring the filter."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

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

(defn ft-sugadd!
  "Adds a suggestion string to an auto-complete suggestion dictionary.
   The suggestion dictionary is disconnected from the index definitions, and creating and updating suggestion dictionaries is left to the user.
   If the suggestion already exists, its score is replaced by the new value unless the INCR option is used.
   The INCR option increments the existing entry by the given score instead of replacing it, which is useful for updating the dictionary based on user queries in real time.
   The PAYLOAD option saves an extra payload with the suggestion, which can be fetched by adding the WITHPAYLOADS argument to FT.SUGGET.

   ```
   FT.SUGADD key string score [INCR] [PAYLOAD payload]
   ```

   key: the suggestion dictionary key.
   string: the suggestion string to index.
   score: the floating point number representing the suggestion string weight.
   INCR: increments the existing entry by the given score instead of replacing it.
   PAYLOAD payload: saves an extra payload with the suggestion, fetchable via the WITHPAYLOADS argument to FT.SUGGET.

   Integer reply: number of elements added to the suggestion dictionary."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-sugdel!
  "Deletes a string from a suggestion index.
   If the string is not found in the index, the command returns 0 without raising an error.

   ```
   FT.SUGDEL key string
   ```

   key: the suggestion dictionary key.
   string: the suggestion string to delete.

   Integer reply: 1 if the suggestion was deleted, 0 if it was not found."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-sugget!
  "Retrieves completion suggestions for a given prefix from a suggestion dictionary.
   The key must reference an existing suggestion dictionary.
   The FUZZY option enables a fuzzy prefix search, matching prefixes at Levenshtein distance of 1 from the given prefix.
   The MAX option limits the number of results returned, defaulting to 5.
   The WITHSCORES option appends a score after each suggestion, useful for merging results across multiple instances.
   The WITHPAYLOADS option includes any payload saved with each suggestion; if a suggestion has no payload, a null is returned for that entry.

   ```
   FT.SUGGET key prefix 
     [FUZZY] 
     [WITHSCORES] 
     [WITHPAYLOADS] 
     [MAX max]
   ```

   key: the suggestion dictionary key.
   prefix: the prefix to complete on.
   FUZZY: performs a fuzzy prefix search, including prefixes at Levenshtein distance of 1 from the prefix sent.
   MAX num: limits the results to a maximum of num, defaults to 5.
   WITHSCORES: also returns the score of each suggestion, useful for merging results from multiple instances.
   WITHPAYLOADS: returns optional payloads saved along with the suggestions, returns a null reply if no payload is present for an entry.

   Array of the top suggestions matching the prefix, optionally with a score after each entry."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-suglen!
  "Retrieves the number of entries stored in an auto-complete suggestion dictionary.
   The key must reference an existing suggestion dictionary; if it does not exist, the command returns an error.

   ```
   FT.SUGLEN key
   ```

   key: the suggestion dictionary key.

   Integer reply: number of suggestions in the dictionary."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-numpowby!
  "Raises the number value stored at path to the power of value.
   If the key does not exist, or if the result is out of range, a simple error is returned.
   If the matching value at a given path is not a number, that element in the reply is null.
   If no path matches, the returned array is empty.

   ```
   JSON.NUMPOWBY key path value
   ```

   key: the key to modify.
   path: the JSONPath to specify.
   value: the number value to use as the exponent.

   Simple error reply if the key does not exist or the result is out of range.
   Array reply with one element per matching path: an integer reply or double reply containing the new value, or a null reply if the matching value is not a number. The array is empty if no path matches."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-add!
  "Adds one or more observations to a t-digest sketch.
   The key must reference an existing t-digest sketch; if it does not exist, an error is returned.
   Each value must be a valid floating-point number; an incorrect type results in an error.

   ```
   TDIGEST.ADD key value [value ...]
   ```

   key: the key name for an existing t-digest sketch.
   value: the floating-point value of an observation.

   Simple string reply OK if executed correctly.
   Simple error reply if the given key does not exist or the value parameter is of the incorrect type."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-byrank!
  "Returns a floating-point estimation of the value at each given rank in a t-digest sketch.
   Multiple ranks can be requested in a single call, and the results are returned in the same order as the input ranks.
   A rank of 0 corresponds to the smallest observation and a rank of n-1 to the largest, where n is the total number of observations in the sketch.

   ```
   TDIGEST.BYRANK key rank [rank ...]
   ```

   key: the key name of an existing t-digest sketch.
   rank: ranks for which the values should be retrieved, where 0 is the rank of the smallest observation and n-1 is the rank of the largest observation, with n being the number of observations added to the sketch.

   Array of doubles as floating-points, populated with value_1, value_2, ..., value_R:
   an accurate result when rank is 0, the value of the smallest observation.
   an accurate result when rank is n-1, the value of the largest observation, where n denotes the number of observations added to the sketch.
   inf when rank is equal to n or larger than n.
   nan for all ranks when the given sketch is empty.
   Simple error reply in these cases: the given key does not exist or is of an incorrect type, rank parsing errors, or incorrect number of arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-byrevrank!
  "Returns, for each input reverse rank, an estimation of the floating-point value with that reverse rank.
   Multiple estimations can be retrieved in a single call.
   A reverse rank of 0 corresponds to the largest observation,
   and n-1 corresponds to the smallest observation,
   where n is the number of observations added to the sketch.
   If the key does not exist or is of an incorrect type, a simple error is returned.
   Rank parsing errors or an incorrect number of arguments also produce a simple error.

   ```
   TDIGEST.BYREVRANK key reverse_rank [reverse_rank ...]
   ```

   key: the key name of an existing t-digest sketch.
   revrank: reverse ranks for which the values should be retrieved,
     where 0 is the reverse rank of the largest observation
     and n-1 is the reverse rank of the smallest observation,
     where n denotes the number of observations added to the sketch.

   Array of doubles as floating-points, populated with value_1, value_2, ..., value_R:
   an accurate result when rank is 0, the value of the largest observation.
   an accurate result when rank is n-1, the value of the smallest observation,
     where n denotes the number of observations added to the sketch.
   inf when rank is equal to n or larger than n.
   nan for all ranks when the given sketch is empty.
   Simple error reply in these cases: the given key does not exist or is of an incorrect type,
     rank parsing errors, or an incorrect number of arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-cdf!
  "Returns, for each input value, an estimation of the floating-point fraction of observations smaller than the given value plus half the observations equal to the given value.
   Multiple fractions can be retrieved in a single call by passing multiple values.

   ```
   TDIGEST.CDF key value [value ...]
   ```

   key: the key name for an existing t-digest sketch.
   value: the values for which the CDF (Cumulative Distribution Function) should be retrieved.

   Array of doubles populated with fraction_1, fraction_2, ..., fraction_N.
   All values are nan if the given sketch is empty.
   Simple error reply in these cases: the given key does not exist or is of an incorrect type, value parsing errors, or an incorrect number of arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-create!
  "Allocates memory and initializes a new t-digest sketch under the given key.
   The compression parameter controls the tradeoff between accuracy and memory usage, defaulting to 100 if not specified.
   Higher compression values such as 1000 yield more accurate estimates at the cost of more memory.

   ```
   TDIGEST.CREATE key [COMPRESSION compression]
   ```

   key: the key name for this new t-digest sketch.
   COMPRESSION compression: a controllable tradeoff between accuracy and memory consumption, defaults to 100.

   Simple string reply OK if executed correctly.
   Simple error reply in these cases: incorrect key type or incorrect keyword."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-info!
  "Returns information and statistics about a t-digest sketch stored at the given key.
   The reply includes details such as compression, capacity, merged and unmerged node
   counts and weights, total observations, total compressions, and memory usage.
   If the key does not exist, is of an incorrect type, or the number of arguments is
   wrong, a simple error is returned.

   ```
   TDIGEST.INFO key
   ```

   key: the key name for an existing t-digest sketch.

   Map reply with information about the given sketch as name-value pairs:
   Compression: Integer reply, the compression (controllable trade-off between
     accuracy and memory consumption) of the sketch
   Capacity: Integer reply, size of the buffer used for storing the centroids and
     for the incoming unmerged observations
   Merged nodes: Integer reply, number of merged observations
   Unmerged nodes: Integer reply, number of buffered nodes (uncompressed observations)
   Merged weight: Integer reply, weight of values of the merged nodes
   Unmerged weight: Integer reply, weight of values of the unmerged nodes
     (uncompressed observations)
   Observations: Integer reply, number of observations added to the sketch
   Total compressions: Integer reply, number of times this sketch compressed data together
   Memory usage: Integer reply, number of bytes allocated for the sketch
   Simple error reply if the given key does not exist, is of an incorrect type,
     or an incorrect number of arguments is provided."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-max!
  "Returns the maximum observation value from a t-digest sketch.
   The result is always accurate.
   nan is returned if the sketch is empty.
   An error is returned if the number of arguments is incorrect or the key type is wrong.

   ```
   TDIGEST.MAX key
   ```

   key: the key name for an existing t-digest sketch.

   Double reply representing the maximum observation value from the given sketch. The result is always accurate. nan is returned if the sketch is empty.
   Simple string reply in these cases: incorrect number of arguments or incorrect key type."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-merge!
  "Merges multiple t-digest sketches into a single sketch.
   If the destination key does not exist, a new sketch is created.
   If the destination key already exists, its values are merged with the values of the source keys.
   Use OVERRIDE to overwrite the destination key contents instead of merging.

   When COMPRESSION is not specified, the resulting compression is set to the maximum value among all source sketches if the destination does not exist or OVERRIDE is used.
   If the destination already exists and OVERRIDE is not specified, its compression is left unchanged.

   ```
   TDIGEST.MERGE destination-key numkeys source-key [source-key ...]
     [COMPRESSION compression] [OVERRIDE]
   ```

   destination-key: the key name for a t-digest sketch to merge observation values into; if it does not exist a new sketch is created, and if it exists its values are merged with the source keys unless OVERRIDE is used.
   numkeys: the number of source sketches to merge from, one or more.
   source-key: a key name for a t-digest sketch from which to merge observation values.
   COMPRESSION compression: a tradeoff between accuracy and memory consumption; 100 is the default and a common value, 1000 is more accurate; when not specified the compression is set to the maximum among all source sketches if the destination does not exist or OVERRIDE is used, and left unchanged if the destination exists and OVERRIDE is not used.
   OVERRIDE: if the destination key already exists, it is overwritten instead of merged.

   Simple string reply OK if successful.
   Simple error reply in the following cases: incorrect key type, incorrect keyword, or incorrect number of arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-min!
  "Returns the minimum observation value from a t-digest sketch.
   The result is always accurate.
   If the sketch is empty, nan is returned.
   An error is returned if the number of arguments is incorrect or the key type is wrong.

   ```
   TDIGEST.MIN key
   ```

   key: the key name for an existing t-digest sketch.

   Double reply representing the minimum observation value from the given sketch. The result is always accurate. nan is returned if the sketch is empty.
   Simple string reply in these cases: incorrect number of arguments or incorrect key type."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-quantile!
  "Returns a floating-point estimate for each given quantile fraction, representing the value below which the specified fraction of observations falls.
   Multiple quantiles can be requested in a single call.
   When the quantile is 0, the result is the exact smallest observation in the sketch.
   When the quantile is 1, the result is the exact largest observation in the sketch.
   If the sketch is empty, nan is returned for every quantile.
   An error is returned if the key does not exist, the key holds a value of the wrong type, a quantile cannot be parsed, or the number of arguments is incorrect.

   ```
   TDIGEST.QUANTILE key quantile [quantile ...]
   ```

   key: the key name for an existing t-digest sketch.
   quantile: the input fraction between 0 and 1 inclusively.

   Array of double replies as estimates, populated with value_1, value_2, ..., value_N.
   an accurate result when quantile is 0, the value of the smallest observation.
   an accurate result when quantile is 1, the value of the largest observation.
   nan for all quantiles when the given sketch is empty.
   Simple error reply in these cases: the given key does not exist or is of an incorrect type, quantile parsing errors, or incorrect number of arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-rank!
  "Returns the estimated rank for each floating-point input value against a t-digest sketch.
   The rank is the number of observations in the sketch that are smaller than the value, plus half the number of observations that are equal to the value.
   Multiple values can be queried in a single call.
   If the key does not exist or holds a value of an incorrect type, a simple error is returned.
   If the sketch is empty, all returned ranks are -2.

   ```
   TDIGEST.RANK key value [value ...]
   ```

   key: the key name for an existing t-digest sketch.
   value: the input value for which the rank should be estimated.

   Array of integers populated with rank_1, rank_2, ..., rank_V.
   -1 when value is smaller than the value of the smallest observation.
   The number of observations when value is larger than the value of the largest observation.
   Otherwise, an estimation of the number of (observations smaller than value + half the observations equal to value).
   0 is the rank of the value of the smallest observation.
   n-1 is the rank of the value of the largest observation, where n denotes the number of observations added to the sketch.
   All values are -2 if the sketch is empty.
   Simple error reply: the given key does not exist or is of an incorrect type, quantile parsing errors, or incorrect number of arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-reset!
  "Resets a t-digest sketch by emptying it and re-initializing it to its default state.
   The key must reference an existing t-digest sketch; otherwise an error is returned.

   ```
   TDIGEST.RESET key
   ```

   key: the key name for an existing t-digest sketch.

   Simple string reply OK if executed correctly.
   Simple error reply in these cases: key does not exist or is of the wrong type, or incorrect number of arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-revrank!
  "Returns the estimated reverse rank for each floating-point input value.
   The reverse rank is the number of observations in the sketch that are
   larger than the value, plus half the number of observations equal to the value.
   Multiple reverse ranks can be retrieved in a single call.
   A simple error is returned if the key does not exist, is of an incorrect
   type, or if there are parsing errors or an incorrect number of arguments.

   ```
   TDIGEST.REVRANK key value [value ...]
   ```

   key: the key name for an existing t-digest sketch.
   value: the input value for which the reverse rank should be estimated.

   Array of integers populated with revrank_1, revrank_2, ..., revrank_V:
   -1 when value is larger than the value of the largest observation.
   The number of observations when value is smaller than the value of the
   smallest observation.
   Otherwise, an estimation of the number of (observations larger than value
   + half the observations equal to value).
   0 is the reverse rank of the value of the largest observation.
   n-1 is the rank of the value of the smallest observation, where n denotes
   the number of observations added to the sketch.
   All values are -2 if the sketch is empty.
   Simple error reply in these cases: the given key does not exist or is of
   an incorrect type, quantile parsing errors, or incorrect number of arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-trimmed-mean!
  "Returns an estimation of the mean value from a t-digest sketch.
   Observation values outside the specified low and high cutoff quantiles are excluded from the calculation.
   If the sketch is empty, the result is nan.
   An error is returned if the key does not exist, is of an incorrect type,
   the quantiles are out of range, or the number of arguments is incorrect.

   ```
   TDIGEST.TRIMMED_MEAN key low_cut_quantile high_cut_quantile
   ```

   key: the key name for an existing t-digest sketch.
   low_cut_quantile: a floating-point value in the range [0..1],
     must be lower than high_cut_quantile;
     when 0 no low cut is applied,
     when greater than 0 observation values lower than this quantile are excluded.
   high_cut_quantile: a floating-point value in the range [0..1],
     must be higher than low_cut_quantile;
     when less than 1 observation values greater than or equal to this quantile are excluded,
     when 1 no high cut is applied.

   Double reply as an estimation of the mean value.
   nan if the sketch is empty.
   Simple error reply in these cases: the given key does not exist or is of an incorrect type,
   quantiles out of range [0..1], or incorrect number of arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

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
