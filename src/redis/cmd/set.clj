(ns redis.cmd.set)

(defn sadd!
  "Adds the specified members to the set stored at key.
   If key does not exist, a new set is created before adding the members.
   Members that are already present in the set are silently ignored.
   An error is returned if the value stored at key is not a set.

   ```
   SADD key member [member ...]
   ```

   key: the name of the key that holds the set.
   member [member ...]: one or more members to add to the set.

   Integer reply: the number of elements that were added to the set,
   not including all the elements already present in the set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn scard!
  "Returns the cardinality (number of elements) of the set stored at key.
   If the key does not exist, the command returns 0.
   If the key holds a value that is not a set, an error is returned.

   ```
   SCARD key
   ```

   key: the name of the key that holds the set.

   Integer reply: the cardinality (number of elements) of the set, or 0 if the key does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn sdiff!
  "Returns the members of the set resulting from the difference between the first set and all the successive sets.
   Keys that do not exist are considered to be empty sets.

   ```
   SDIFF key [key ...]
   ```

   key [key ...]: one or more set keys, the result is the members of the first set that are not present in any of the subsequent sets.

   Set reply: a set with the members of the resulting set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn sdiffcard!
  "Returns the cardinality of the difference between the first set and all the successive sets.
   It is the count-only counterpart of SDIFF, returning just the number of elements in the difference rather than the members themselves.
   In clustered Redis environments, behavior varies for multi-key operations.

   ```
   SDIFFCARD numkeys key [key ...] [LIMIT limit]
   ```

   numkeys: the number of keys that follow.
   key [key ...]: one or more set keys, the result counts the members of the first set that are not present in any of the subsequent sets.
   LIMIT limit: stop counting once the cardinality reaches limit, 0 (the default) means no limit.

   Integer reply: the number of elements in the resulting difference."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn sdiffstore!
  "Computes the difference of one or more sets and stores the resulting set in the destination key.
   The difference consists of the members that are present in the first set but not in any of the subsequent sets.
   If the destination key already exists, its value is overwritten.

   ```
   SDIFFSTORE destination key [key ...]
   ```

   destination: the key to store the resulting set in.
   key [key ...]: one or more set keys, the difference is the members of the first set that are not present in any of the subsequent sets.

   Integer reply: the number of elements in the resulting set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn sinter!
  "Returns the members of the set resulting from the intersection of all the given sets.
   Keys that do not exist are considered to be empty sets.
   If any of the given sets is empty, the resulting set is also empty.

   ```
   SINTER key [key ...]
   ```

   key [key ...]: one or more set keys to intersect.

   Set reply: a set with the members of the resulting set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn sintercard!
  "Returns the cardinality of the set that would result from intersecting all the given sets.
   Keys that do not exist are treated as empty sets, so if any of the given keys is missing or empty the result is zero.
   By default the command computes the full intersection cardinality.
   When the optional LIMIT argument is provided and the running count reaches that limit, the computation stops early and returns the limit value, which can significantly speed up queries where the limit is lower than the actual intersection size.

   ```
   SINTERCARD numkeys key [key ...] [LIMIT limit]
   ```

   numkeys: the number of keys that follow.
   key [key ...]: one or more set keys to intersect.
   LIMIT limit: stop counting once the cardinality reaches limit, 0 (the default) means no limit.

   Integer reply: the number of elements in the resulting intersection."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn sinterstore!
  "Computes the intersection of one or more sets and stores the resulting set in the destination key.
   If the destination key already exists, it is overwritten.
   In clustered Redis environments, the behavior of this command varies for multi-key operations.

   ```
   SINTERSTORE destination key [key ...]
   ```

   destination: the key to store the resulting set in.
   key [key ...]: one or more set keys to intersect.

   Integer reply: the number of elements in the result set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn sismember!
  "Checks whether a given member is part of the set stored at key.
   If the key does not exist, the member is treated as not present.

   ```
   SISMEMBER key member
   ```

   key: the name of the key that holds the set.
   member: the member to check for.

   Integer reply: 0 if the element is not a member of the set, or when the key does not exist.
   Integer reply: 1 if the element is a member of the set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn smembers!
  "Returns all the members of the set value stored at key.
   This has the same effect as running SINTER with one argument key.

   ```
   SMEMBERS key
   ```

   key: the name of the key that holds the set.

   Array reply: an array with all the members of the set.
   Set reply: a set with all the members of the set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn smismember!
  "Checks whether each given member is present in the set stored at key.
   For every member, 1 is returned if the value is a member of the set, or 0 if the element is not a member.
   If the key does not exist, 0 is returned for every member; no error is raised.

   ```
   SMISMEMBER key member [member ...]
   ```

   key: the name of the key that holds the set.
   member [member ...]: one or more members to check for.

   Array reply: a list representing the membership of the given elements,
   in the same order as they are requested."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn smove!
  "Moves a member from the set at source to the set at destination.
   The operation is atomic, so at any given moment the element appears to be a member of either source or destination for other clients.
   If the source set does not exist or does not contain the specified member, no operation is performed.
   Otherwise, the member is removed from the source set and added to the destination set.
   If the member already exists in the destination set, it is only removed from the source set.

   ```
   SMOVE source destination member
   ```

   source: the key of the source set.
   destination: the key of the destination set.
   member: the member to move from the source set to the destination set.

   Integer reply: 1 if the element is moved.
   Integer reply: 0 if the element is not a member of source and no operation was performed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn spop!
  "Removes and returns one or more random members from the set stored at key.
   By default, a single member is popped from the set.
   When the optional count argument is provided, up to count members are removed, depending on the set cardinality.
   This is similar to SRANDMEMBER, which returns random members without removing them.

   ```
   SPOP key [count]
   ```

   key: the name of the key that holds the set.
   count: the number of members to pop, without it a single member is popped.

   Null reply: if the key does not exist.
   Bulk string reply: when called without the count argument, the removed member.
   Array reply: when called with the count argument, a list of the removed members."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn srandmember!
  "Returns a random element from the set stored at key.
   If the key does not exist, a null reply is returned.
   When a positive count is given, the command returns an array of distinct members,
   with a length equal to the smaller of count and the set cardinality.
   When a negative count is given, the command may return the same member more than once,
   and the number of elements returned is the absolute value of count.
   If the key does not exist and a count is given, an empty array is returned.

   ```
   SRANDMEMBER key [count]
   ```

   key: the name of the key that holds the set.
   count: the number of members to return, a positive count returns up to that many
   distinct members, a negative count returns exactly |count| members and may repeat them.

   Bulk string reply: without the additional count argument, the command returns
   a randomly selected member, or a Null reply when key does not exist.
   Array reply: when the optional count argument is passed, the command returns
   an array of members, or an empty array when key does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn srem!
  "Removes the specified members from the set stored at key.
   Members that are not part of the set are silently ignored.
   If the key does not exist, it is treated as an empty set and the command returns 0.
   An error is returned if the value stored at key is not a set.

   ```
   SREM key member [member ...]
   ```

   key: the name of the key that holds the set.
   member [member ...]: one or more members to remove from the set.

   Integer reply: Number of members that were removed from the set, not including non existing members."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn sscan!
  "SSCAN iterates over the members of a set stored at key.
   Iteration is cursor-based: start with cursor 0, and pass the cursor returned by each call as the argument to the next call.
   The MATCH option restricts results to members matching a glob-style pattern.
   The COUNT option provides a hint for the number of members to return per iteration; the default is 10.

   ```
   SSCAN key cursor [MATCH pattern] [COUNT count]
   ```

   key: the name of the key that holds the set.
   cursor: the cursor value, start an iteration with 0, then use the cursor returned by each call as the argument to the next call.
   MATCH pattern: only return members matching the given glob-style pattern.
   COUNT count: a hint for the number of members to return per iteration, the default is 10.

   Array reply: specifically, an array with two elements:
   The first element is a Bulk string reply that represents an unsigned 64-bit number, the cursor.
   The second element is an Array reply with the names of scanned members."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn sunion!
  "Returns the members of the set resulting from the union of all the given sets.
   Keys that do not exist are considered to be empty sets.

   ```
   SUNION key [key ...]
   ```

   key [key ...]: one or more set keys to union.

   Set reply: a set with the members of the resulting set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn sunioncard!
  "Returns the cardinality of the union of the given sets.
   It is the count-only counterpart of SUNION: it returns just the number of distinct elements in the union, not the members themselves.
   In clustered Redis environments, the behavior of this multi-key command varies; see the multi-key operations documentation for details.

   ```
   SUNIONCARD numkeys key [key ...] [APPROX] [LIMIT limit]
   ```

   numkeys: the number of keys that follow.
   key [key ...]: one or more set keys to union.
   APPROX: return an approximate cardinality instead of an exact count.
   LIMIT limit: stop counting once the cardinality reaches limit, 0 (the default) means no limit.

   Integer reply: the number of elements in the resulting union."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn sunionstore!
  "Computes the union of one or more sets and stores the resulting set in the destination key.
   If the destination key already exists, it is overwritten.
   In clustered Redis environments, the behavior of this command varies; see the multi-key operations documentation for details.

   ```
   SUNIONSTORE destination key [key ...]
   ```

   destination: the key to store the resulting set in.
   key [key ...]: one or more set keys to union.

   Integer reply: Number of the elements in the resulting set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))
