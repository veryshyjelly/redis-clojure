(ns redis.cmd.list)

(defn blmove!
  "BLMOVE is the blocking variant of LMOVE.
   When the source list contains elements, it pops an element from the specified end of the source and pushes it to the specified end of the destination, behaving exactly like LMOVE.
   When the source list is empty, the connection is blocked until another client pushes an element to it or until the timeout is reached.
   A timeout of zero blocks indefinitely.
   When used inside a MULTI/EXEC block or a Lua script, the command behaves exactly like LMOVE and does not block.

   ```
   BLMOVE source destination <LEFT | RIGHT> <LEFT | RIGHT> timeout
   ```

   source: the key of the source list.
   destination: the key of the destination list.
   LEFT | RIGHT: the end of source to pop the element from, LEFT (head) or RIGHT (tail).
   LEFT | RIGHT: the end of destination to push the element to, LEFT (head) or RIGHT (tail).
   timeout: the maximum time to block in seconds, a timeout of 0 blocks indefinitely.

   Bulk string reply: the element being popped from the source and pushed to the destination.
   Null reply: the operation timed-out."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn blmovem!
  "BLMOVEM is the blocking variant of LMOVEM.
   It moves elements from the source list to the destination list.
   When the source holds enough elements to satisfy the request,
   the command behaves exactly like LMOVEM.
   Otherwise, Redis blocks the connection until another client
   pushes the required elements to the source list
   or until the timeout is reached.
   A timeout of 0 blocks indefinitely.

   ```
   BLMOVEM source destination <LEFT | RIGHT> <LEFT | RIGHT> timeout
     [<COUNT count | EXACTLY exactly> <OBO | BULK>]
   ```

   source: the key of the source list.
   destination: the key of the destination list.
   LEFT | RIGHT (source): the end of source to pop elements from,
     LEFT for head or RIGHT for tail.
   LEFT | RIGHT (destination): the end of destination to push elements to,
     LEFT for head or RIGHT for tail.
   timeout: the maximum time to block in seconds,
     0 blocks indefinitely.
   COUNT count | EXACTLY exactly: the number of elements to move;
     COUNT moves up to count elements,
     EXACTLY moves exactly that many and blocks if source holds fewer.
   OBO | BULK: the order in which elements are pushed onto destination;
     OBO moves elements one by one,
     BULK moves all at once preserving relative order.

   Array reply: the moved elements, in destination order.
   Null reply: the operation timed out."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn blmpop!
  "BLMPOP is the blocking variant of LMPOP.
   When any of the specified lists contains elements, it behaves exactly like LMPOP.
   When used inside a MULTI/EXEC block or a Lua script, it also behaves like LMPOP.
   When all lists are empty, the connection is blocked until another client pushes to one of them or until the timeout elapses.
   A timeout of zero blocks indefinitely.
   In clustered Redis environments, behaviour varies for multi-key operations.

   ```
   BLMPOP timeout numkeys key [key ...] <LEFT | RIGHT> [COUNT count]
   ```

   timeout: the maximum time to block, in seconds, a timeout of 0 blocks indefinitely.
   numkeys: the number of keys that follow.
   key [key ...]: one or more keys of lists to operate on.
   LEFT | RIGHT: the end of the list to pop elements from, LEFT (head) or RIGHT (tail).
   COUNT count: the number of elements to pop, defaults to 1.

   Null reply: when no element could be popped and the timeout is reached.
   Array reply: a two-element array with the first element being the name of the key from which elements were popped, and the second element being an array of the popped elements."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn blpop!
  "BLPOP is a blocking list pop primitive.
   It blocks the connection when there are no elements to pop from any of the given lists.
   An element is popped from the head of the first non-empty list, with the given keys being checked in the order they are provided.
   A timeout of 0 means the command blocks indefinitely until an element becomes available.

   ```
   BLPOP key [key ...] timeout
   ```

   key [key ...]: one or more keys of lists to operate on.
   timeout: the maximum time to block, in seconds, a timeout of 0 blocks indefinitely.

   Null reply: no element could be popped and the timeout expired
   Array reply: the key from which the element was popped and the value of the popped element."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn brpop!
  "BRPOP is a blocking list pop primitive that removes and returns the last element of the first non-empty list among the given keys.
   If none of the specified lists contain elements, the connection is blocked until an element becomes available or the timeout expires.
   Keys are checked in the order they are provided, and the element is always popped from the tail of the list.
   A timeout of zero means the connection blocks indefinitely.

   ```
   BRPOP key [key ...] timeout
   ```

   key [key ...]: one or more keys of lists to operate on.
   timeout: the maximum time to block, in seconds, a timeout of 0 blocks indefinitely.

   Null reply: no element could be popped and the timeout expired.
   Array reply: the key from which the element was popped and the value of the popped element."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn brpoplpush!
  "BRPOPLPUSH is the blocking variant of RPOPLPUSH.
   It pops the rightmost element from the source list and pushes it to the left of the destination list.
   When source contains elements, the command behaves exactly like RPOPLPUSH.
   When used inside a MULTI/EXEC block or a Lua script, it also behaves like RPOPLPUSH.
   When source is empty, Redis blocks the connection until another client pushes to it or until the timeout is reached.
   A timeout of zero blocks indefinitely.

   ```
   BRPOPLPUSH source destination timeout
   ```

   source: the key of the source list.
   destination: the key of the destination list.
   timeout: the maximum time to block, in seconds, a timeout of 0 blocks indefinitely.

   Bulk string reply: the element being popped from source and pushed to destination.
   Null reply: the timeout is reached."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn lindex!
  "Returns the element at the given index in the list stored at key.
   The index is zero-based: 0 is the first element, 1 the second, and so on.
   Negative indices count from the tail of the list, where -1 is the last element, -2 the second-to-last, and so forth.
   If the key does not exist or the index is out of range, a null reply is returned.
   If the value at key is not a list, an error is returned.

   ```
   LINDEX key index
   ```

   key: the name of the key that holds the list.
   index: the zero-based index of the element to return, negative indexes count from the tail (-1 is the last element).

   Null reply: when index is out of range.
   Bulk string reply: the requested element."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn linsert!
  "Inserts element into the list stored at key, placing it either before or after the reference value pivot.
   If key does not exist, it is treated as an empty list and no operation is performed.
   An error is returned if key exists but does not hold a list value.

   ```
   LINSERT key <BEFORE | AFTER> pivot element
   ```

   key: the name of the key that holds the list.
   BEFORE | AFTER: whether to insert element before or after pivot.
   pivot: the existing element to insert relative to.
   element: the value to insert.

   Integer reply: the list length after a successful insert operation.
   Integer reply: 0 when the key does not exist.
   Integer reply: -1 when the pivot was not found."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn llen!
  "Returns the length of the list stored at key.
   If the key does not exist, it is treated as an empty list and 0 is returned.
   An error is returned if the value at key is not a list.

   ```
   LLEN key
   ```

   key: the name of the key that holds the list.

   Integer reply: the length of the list."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn lmove!
  "Atomically pops an element from one end of the list at source and pushes it to one end of the list at destination.
   The wherefrom argument selects which end of source to pop from, and the whereto argument selects which end of destination to push to.
   If source does not exist, nil is returned and no operation is performed.
   If source and destination are the same key, the operation acts as a list rotation: the element is removed from one end and pushed to the other.
   If wherefrom and whereto are the same, the operation is a no-op.

   ```
   LMOVE source destination <LEFT | RIGHT> <LEFT | RIGHT>
   ```

   source: the key of the source list.
   destination: the key of the destination list.
   LEFT | RIGHT: the end of source to pop the element from, LEFT for head or RIGHT for tail.
   LEFT | RIGHT: the end of destination to push the element to, LEFT for head or RIGHT for tail.

   Bulk string reply: the element being popped and pushed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn lmovem!
  "Atomically moves one or more elements from the list stored at source to the list stored at destination and returns the moved elements.
   Without the optional how-many block, it behaves exactly like LMOVE but returns the moved element as a one-element array.
   The how-many block controls how many elements are moved and in what order.
   COUNT moves up to the given number of elements, moving all if fewer are available.
   EXACTLY moves exactly the given number of elements, or nothing at all if the source list is too short.
   OBO pushes elements one by one, while BULK pushes them all at once preserving their relative order.

   ```
   LMOVEM source destination <LEFT | RIGHT> <LEFT | RIGHT>
     [<COUNT count | EXACTLY exactly> <OBO | BULK>]
   ```

   source: the key of the source list.
   destination: the key of the destination list.
   LEFT | RIGHT: the end of source to pop elements from, LEFT for head or RIGHT for tail.
   LEFT | RIGHT: the end of destination to push elements to, LEFT for head or RIGHT for tail.
   COUNT count | EXACTLY exactly: the number of elements to move; COUNT moves up to count elements (all if fewer available), EXACTLY moves exactly that many or nothing if source is too short.
   OBO | BULK: the order in which elements are pushed onto destination; OBO moves elements one by one, BULK moves them all at once preserving relative order.

   Array reply: the moved elements, in destination order.
   Null reply: if EXACTLY was used and source did not hold enough elements."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn lmpop!
  "Pops one or more elements from the first non-empty list key among the provided key names.
   Elements are removed from either the left (head) or right (tail) end of that list, as specified by the direction argument.
   The number of elements actually popped is the lesser of the non-empty list length and the count argument, which defaults to 1.
   If none of the provided keys hold a non-empty list, no element is popped and a null reply is returned.

   ```
   LMPOP numkeys key [key ...] <LEFT | RIGHT> [COUNT count]
   ```

   numkeys: the number of keys that follow.
   key [key ...]: one or more keys of lists to operate on.
   LEFT | RIGHT: the end of the list to pop elements from, LEFT for head or RIGHT for tail.
   COUNT count: the number of elements to pop, defaults to 1.

   Null reply: if no element could be popped.
   Array reply: a two-element array with the first element being the name of the key from which elements were popped and the second element being an array of elements."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn lpop!
  "Removes and returns the first elements of the list stored at key.
   By default, a single element is popped from the beginning of the list.
   When the count argument is provided, up to count elements are returned, depending on the list length.
   If the key does not exist, a null reply is returned.

   ```
   LPOP key [count]
   ```

   key: the name of the key that holds the list.
   count: the number of elements to pop, without it a single element is popped.

   Null reply: if the key does not exist.
   Bulk string reply: when called without the count argument, the value of the first element.
   Array reply: when called with the count argument, a list of popped elements."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn lpos!
  "Returns the zero-based index of matching elements inside a Redis list.
   By default the list is scanned from head to tail and the first match of element is returned.
   If no match is found, nil is returned.
   The RANK option sets the starting direction: 1 searches from the head, -1 from the tail.
   The COUNT option limits how many matches are returned; 0 means all matches.
   The MAXLEN option caps the number of elements compared; 0 (the default) scans the whole list.

   ```
   LPOS key element [RANK rank] [COUNT num-matches] [MAXLEN len]
   ```

   key: the name of the key that holds the list.
   element: the value to search for.
   RANK rank: the rank of the first match to return, 1 is the first from the head, -1 the first from the tail.
   COUNT num-matches: the number of matches to return, 0 returns all matches.
   MAXLEN len: compare at most len elements, 0 (the default) scans the whole list.

   Null reply: if there is no matching element.
   Integer reply: an integer representing the matching element.
   Array reply: if the COUNT option is given, an array of integers representing the matching elements (or an empty array if there are no matches)."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn lpush!
  "Inserts all the specified values at the head of the list stored at key.
   If key does not exist, it is created as an empty list before the push operations are performed.
   If key holds a value that is not a list, an error is returned.
   Multiple elements can be pushed in a single call by specifying several arguments.
   Elements are inserted one after the other at the head of the list, from the leftmost argument to the rightmost.
   For example, pushing a, b, c in that order results in c as the first element, b as the second, and a as the third.

   ```
   LPUSH key element [element ...]
   ```

   key: the name of the key that holds the list.
   element [element ...]: one or more values to prepend to the list.

   Integer reply: the length of the list after the push operation."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn lpushx!
  "LPUSHX inserts one or more elements at the head of the list stored at key.
   It only performs the operation when the key already exists and holds a list.
   If the key does not exist, no operation is performed, unlike LPUSH which would create the list.

   ```
   LPUSHX key element [element ...]
   ```

   key: the name of the key that holds the list.
   element [element ...]: one or more values to prepend to the list, only if it already exists.

   Integer reply: the length of the list after the push operation."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn lrange!
  "Returns the elements of the list stored at key within the specified range.
   Offsets are zero-based, with 0 being the first element (the head of the list) and 1 the next.
   Negative offsets count from the end of the list, so -1 is the last element and -2 the penultimate.
   If the key does not exist, an empty array is returned.

   ```
   LRANGE key start stop
   ```

   key: the name of the key that holds the list.
   start: the zero-based start index, negative indexes count from the tail.
   stop: the zero-based stop index (inclusive), negative indexes count from the tail.

   Array reply: a list of elements in the specified range, or an empty array if the key doesn't exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn lrem!
  "Removes occurrences of a given element from the list stored at key.
   The count argument controls both the number of elements to remove and the direction:
   a positive value removes from the head of the list,
   a negative value removes from the tail,
   and zero removes all matching elements.
   If the key does not exist, it is treated as an empty list and the command returns 0.

   ```
   LREM key count element
   ```

   key: the name of the key that holds the list.
   count: the number of matching elements to remove and the direction,
   positive removes from the head, negative from the tail, and 0 removes all matches.
   element: the value to remove.

   Integer reply: the number of removed elements."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn lset!
  "Sets the list element at the given index to the new value.
   The index is zero-based; negative indexes count from the tail of the list.
   An error is returned if the index is out of range.

   ```
   LSET key index element
   ```

   key: the name of the key that holds the list.
   index: the zero-based index of the element to set, negative indexes count from the tail.
   element: the new value.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ltrim!
  "Trims an existing list so that it contains only the elements within the specified range.
   Both start and stop are zero-based indexes, where 0 is the first element (the head) and 1 the next.
   Negative indexes indicate offsets from the end of the list, where -1 is the last element and -2 the penultimate.
   Out of range indexes do not produce an error.
   If start is larger than the end of the list, the result is an empty list and the key is removed.
   If stop is larger than the end of the list, it is treated as the last element of the list.

   ```
   LTRIM key start stop
   ```

   key: the name of the key that holds the list.
   start: the zero-based start index, negative indexes count from the tail.
   stop: the zero-based stop index (inclusive), negative indexes count from the tail.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn rpop!
  "Removes and returns the last elements of the list stored at key.
   By default, a single element is popped from the end of the list.
   When the optional count argument is provided, up to count elements are returned, depending on the list length.
   If the key does not exist, a null reply is returned.

   ```
   RPOP key [count]
   ```

   key: the name of the key that holds the list.
   count: the number of elements to pop, without it a single element is popped.

   Null reply: if the key does not exist.
   Bulk string reply: when called without the count argument, the value of the last element.
   Array reply: when called with the count argument, a list of popped elements."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn rpoplpush!
  "Atomically removes the last element (tail) of the list stored at source and pushes it as the first element (head) of the list stored at destination.
   If source does not exist, nil is returned and no operation is performed.
   If source and destination are the same key, the operation is equivalent to a list rotation: the last element is moved to the front.

   ```
   RPOPLPUSH source destination
   ```

   source: the key of the source list.
   destination: the key of the destination list.

   Bulk string reply: the element being popped and pushed.
   Null reply: if the source list is empty."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn rpush!
  "Inserts all specified elements at the tail of the list stored at key.
   If key does not exist, it is created as an empty list before the push operation.
   If key holds a value that is not a list, an error is returned.
   Multiple elements can be pushed in a single call; they are appended in order from the leftmost to the rightmost element.

   ```
   RPUSH key element [element ...]
   ```

   key: the name of the key that holds the list.
   element [element ...]: one or more values to append to the list.

   Integer reply: the length of the list after the push operation."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn rpushx!
  "Inserts the specified elements at the tail of the list stored at key.
   Only performs the operation if key already exists and holds a list.
   If the key does not exist, no operation is performed.

   ```
   RPUSHX key element [element ...]
   ```

   key: the name of the key that holds the list.
   element [element ...]: one or more values to append to the list, only if it already exists.

   Integer reply: the length of the list after the push operation."
  [_state _msg]
  (throw (Exception. "Not Implemented")))
