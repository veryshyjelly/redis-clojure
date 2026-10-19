(ns redis.cmd.array)

(defn arcount!
  "Returns the number of non-empty elements in an array.
   If the key does not exist, the command returns 0.

   ```
   ARCOUNT key
   ```

   key: the name of the key that holds the array.

   Integer reply: the number of non-empty elements, or 0 if key does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ardel!
  "Deletes elements at the specified indices in an array stored under the given key.
   Array is a data type currently in preview and may be subject to change.
   Indices are zero-based integers.
   Deleting an index that does not exist counts as zero elements deleted
   and does not modify the array.

   ```
   ARDEL key index [index ...]
   ```

   key: the name of the key that holds the array.
   index: one or more zero-based integer indices of the elements to delete,
   deleting a non-existent index counts as zero elements deleted
   and does not modify the array.

   Integer reply: number of elements deleted."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ardelrange!
  "Deletes elements in one or more ranges from an array.
   If start is greater than end for a given pair, the range is processed in ascending order regardless.
   Multiple pairs may overlap; each element is counted at most once.

   ```
   ARDELRANGE key start end [start end ...]
   ```

   key: the name of the key that holds the array.
   range: one or more start end pairs, each defining an inclusive range of indices to delete, if start is greater than end the range is processed in ascending order, multiple pairs may overlap and each element is counted at most once.

   Integer reply: number of elements deleted."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn arget!
  "Gets the value at an index in an array.
   Array is a new data type currently in preview and may be subject to change.

   ```
   ARGET key index
   ```

   key: the name of the key that holds the array.
   index: the zero-based integer index of the element to retrieve.

   Bulk string reply: The value at the given index.
   Null reply: if key or index does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn argetrange!
  "Gets values in a range of indices from an array stored at the given key.
   Array is a new data type currently in preview and may be subject to change.
   If start is greater than end, elements are returned in reverse index order.

   ```
   ARGETRANGE key start end
   ```

   key: the name of the key that holds the array.
   start: the zero-based integer index of the first element to return, if start is greater than end elements are returned in reverse index order.
   end: the zero-based integer index of the last element to return (inclusive), if end is less than start elements are returned in reverse index order.

   Array reply"
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn argrep!
  "Searches array elements within a specified index range using one or more textual predicates.
   Returns the indices of the elements that match.
   Empty slots in the range are skipped.
   When start is greater than end, matches are returned in reverse index order.
   An empty array is returned when the key does not exist or no element matches.

   ```
   ARGREP key start end
     <EXACT string | MATCH string | GLOB pattern | RE pattern [...]>
     [AND | OR | LIMIT limit | WITHVALUES | NOCASE [...]]
   ```

   key: the name of the key that holds the array.
   start: the zero-based integer index at which to begin searching, the special value - denotes the first index of the array.
   end: the zero-based integer index at which to stop searching (inclusive), the special value + denotes the last index of the array.
   predicate: one or more textual predicates to evaluate against each non-empty element in the range, each being EXACT string, MATCH string, GLOB pattern, or RE pattern.
   AND: combine multiple predicates with logical AND, an element matches only if every predicate matches.
   OR: combine multiple predicates with logical OR, an element matches if any predicate matches, this is the default.
   LIMIT limit: stop after limit matches have been collected, limit must be a positive integer.
   WITHVALUES: in addition to each matching index, return the matching value as a two-element array.
   NOCASE: perform case-insensitive comparisons for EXACT, MATCH, GLOB, and RE.

   Array reply: indices of the matching elements, in the same order in which the range is traversed (ascending when start <= end, descending when start > end).
   When WITHVALUES is given, an array of index-value pairs, each a two-element array.
   An empty array is returned when the key does not exist or no element matches."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn arinfo!
  "Returns metadata about the array stored at the given key.
   The FULL option, when present, includes per-slice statistics in the reply:
   the number of dense and sparse slices and their average sizes and fill rates.
   Using FULL raises the time complexity from O(1) to O(N), where N is the number of slices.

   ```
   ARINFO key [FULL]
   ```

   key: the name of the key that holds the array.
   FULL: when present, includes per-slice statistics in the reply,
   such as the number of dense and sparse slices and their average sizes and fill rates,
   raising the complexity from O(1) to O(N) where N is the number of slices.

   Map reply"
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn arinsert!
  "Inserts one or more values into an array at consecutive indices.
   The values are placed starting at the current insert cursor position, and the cursor advances by one for each value inserted.
   Array is a data type currently in preview and may be subject to change.

   ```
   ARINSERT key value [value ...]
   ```

   key: the name of the key that holds the array.
   value: one or more string values to insert at consecutive indices, beginning at the current insert cursor position. The cursor advances by one for each value inserted. Use ARNEXT to inspect the current cursor position and ARSEEK to reposition it.

   Integer reply: The last index where a value was inserted."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn arlastitems!
  "Returns the most recently inserted elements from the array stored at key.
   If the array contains fewer elements than count, all elements are returned.
   By default, elements are returned in oldest-first order.

   ```
   ARLASTITEMS key count [REV]
   ```

   key: the name of the key that holds the array.
   count: the maximum number of most recently inserted elements to return,
     if the array contains fewer elements than count all elements are returned.
   REV: when present, returns elements in reverse chronological order
     (most recent first) instead of the default oldest-first order.

   Array reply"
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn arlen!
  "Returns the length of an array, defined as the maximum index plus one.
   If the key does not exist, the command returns 0.
   The array data type is in preview and may be subject to change.

   ```
   ARLEN key
   ```

   key: the name of the key that holds the array.

   Integer reply: the length of the array (max index + 1), or 0 if key does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn armget!
  "Gets values at multiple indices in an array.
   The array data type is currently in preview and may be subject to change.
   The reply preserves the order of the requested indices and returns nil for any index that is not set.

   ```
   ARMGET key index [index ...]
   ```

   key: the name of the key that holds the array.
   index: one or more zero-based integer indices of the elements to retrieve, the reply preserves the order of the requested indices and returns nil for any index that is not set.

   Array reply"
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn armset!
  "Sets multiple index-value pairs in an array.
   Array is a new data type currently in preview and may be subject to change.

   ```
   ARMSET key index value [index value ...]
   ```

   key: the name of the key that holds the array.
   data: one or more index value pairs, each index is a zero-based integer specifying where to write and each value is the string to store at that position, pairs may be non-contiguous and in any order.

   Integer reply: Number of new slots that were set (previously empty)."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn arnext!
  "Returns the next index that ARINSERT would use for the array stored at the given key.
   If the key does not exist or no insert has happened yet, the command returns 0.
   If the insertion cursor is exhausted, meaning the next insert would overflow, the command returns null.

   ```
   ARNEXT key
   ```

   key: the name of the key that holds the array.

   Integer reply: The next index ARINSERT would use. Returns 0 for missing keys or when no insert happened yet.
   Null reply: Null when the insertion cursor is exhausted (next insert would overflow)."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn arop!
  "Performs aggregate operations on array elements within a specified index range.
   The array data type is currently in preview and may be subject to change.
   The command always scans from the lower to the higher index regardless of argument order.
   SUM, MIN, and MAX return nil when no numeric elements are present in the range.
   AND, OR, and XOR return nil when the range is empty.
   For bitwise operations, floats are truncated toward zero before the operation is applied.

   ```
   AROP key start end <SUM | MIN | MAX | AND | OR | XOR | MATCH value |
     USED>
   ```

   key: the name of the key that holds the array.
   start: the zero-based integer index of the first element in the range to aggregate.
   end: the zero-based integer index of the last element in the range to aggregate (inclusive); the command always scans from the lower to the higher index regardless of argument order.
   operation: the aggregate function to apply to all non-empty elements in [start, end]; one of SUM, MIN, MAX, AND, OR, XOR, MATCH value, or USED.

   Bulk string reply: Result of the operation.
   Integer reply: Integer result for MATCH, USED, AND, OR, XOR.
   Null reply: Null if no elements match the operation."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn arring!
  "Inserts one or more values into a ring buffer stored under the given key.
   Each value is placed at the next position in the ring and the cursor advances accordingly.
   When the buffer is full, newer values overwrite older ones.
   If the specified size is smaller than the current window, the array is truncated to fit.
   Array is a preview data type and may be subject to change.

   ```
   ARRING key size value [value ...]
   ```

   key: the name of the key that holds the array.
   size: the size of the ring buffer window; each value is inserted at insert_idx % size,
     wrapping back to index 0 when the end of the window is reached; when the buffer is full
     newer values overwrite older ones, and if size is smaller than the current window the
     array is truncated to fit.
   value: one or more string values to insert into the ring buffer; each value is placed at
     the next position in the ring and the cursor advances accordingly.

   Integer reply: The last index where a value was inserted."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn arscan!
  "Iterates existing elements in an array stored under the given key, returning index-value pairs within the specified range.
   If start is greater than end, elements are returned in reverse index order.
   Empty slots within the range are not included in the output.

   ```
   ARSCAN key start end [LIMIT limit]
   ```

   key: the name of the key that holds the array.
   start: the zero-based integer index at which to begin scanning; if start is greater than end, elements are returned in reverse index order.
   end: the zero-based integer index at which to stop scanning (inclusive).
   LIMIT: the maximum number of index-value pairs to return; when omitted, all elements in the range are returned; unlike ARGETRANGE, empty slots are not included in the output, so LIMIT caps the number of existing elements returned.

   Array reply: Array of index-value pairs, each a two-element array: [[idx1, val1], [idx2, val2], ...]"
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn arseek!
  "Sets the ARINSERT or ARRING cursor to a specific index on an array.
   If the key does not exist, the command returns 0 and no cursor is set.

   ```
   ARSEEK key index
   ```

   key: the name of the key that holds the array.
   index: the zero-based integer index to set as the new insert cursor position for subsequent ARINSERT calls.

   Integer reply: 1 if the cursor was set, 0 if the key does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn arset!
  "Sets one or more contiguous values starting at a given index in an array.
   When multiple values are provided, they are written to consecutive indices beginning at the specified index.
   The command returns the count of previously empty slots that were filled.

   ```
   ARSET key index value [value ...]
   ```

   key: the name of the key that holds the array.
   index: the zero-based integer index at which to start writing.
   value: one or more string values to store at consecutive indices beginning at index.

   Integer reply: number of new slots that were set (previously empty)."
  [_state _msg]
  (throw (Exception. "Not Implemented")))
