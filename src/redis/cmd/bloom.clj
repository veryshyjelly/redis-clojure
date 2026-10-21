(ns redis.cmd.bloom)

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