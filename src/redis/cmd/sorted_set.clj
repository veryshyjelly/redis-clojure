(ns redis.cmd.sorted-set)

(defn bzmpop!
  "BZMPOP is the blocking variant of ZMPOP.
   It pops and removes the members with the lowest or highest scores from one or more sorted sets.
   When any of the sorted sets contains elements, the command behaves exactly like ZMPOP.
   When used inside a MULTI/EXEC block or a Lua script, it also behaves like ZMPOP.
   When all sorted sets are empty, the connection is blocked until another client adds members to one of the keys or until the timeout elapses.
   A timeout of zero blocks indefinitely.
   In clustered Redis environments the command behavior varies; see the multi-key operations documentation for details.

   ```
   BZMPOP timeout numkeys key [key ...] <MIN | MAX> [COUNT count]
   ```

   timeout: the maximum time to block, in seconds, a timeout of 0 blocks indefinitely.
   numkeys: the number of keys that follow.
   key [key ...]: one or more sorted-set keys to pop from.
   MIN | MAX: pop the members with the lowest scores (MIN) or the highest scores (MAX).
   COUNT count: the number of members to pop, defaults to 1.

   Null reply: when no element could be popped.
   Array reply: a two-element array with the first element being the name of the key from which elements were popped, and the second element is an array of the popped elements. Every entry in the elements array is also an array that contains the member and its score."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn bzpopmax!
  "BZPOPMAX is the blocking variant of the ZPOPMAX command.
   It pops the member with the highest score from the first non-empty sorted set among the given keys, checking keys in the order they are provided.
   If none of the given sorted sets contain any members, the connection is blocked until a member becomes available or the timeout expires.
   The timeout is specified as a double value in seconds; a timeout of zero blocks indefinitely.
   In a clustered Redis environment, this command behavior varies; see the multi-key operations documentation for details.

   ```
   BZPOPMAX key [key ...] timeout
   ```

   key [key ...]: one or more sorted-set keys to pop from.
   timeout: the maximum time to block, in seconds, a timeout of 0 blocks indefinitely.

   Null reply: when no element could be popped and the timeout expired.
   Array reply: the keyname, popped member, and its score."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn bzpopmin!
  "BZPOPMIN is the blocking variant of ZPOPMIN.
   It pops the member with the lowest score from the first non-empty sorted set among the given keys, checking them in the order provided.
   If none of the given sorted sets contain members, the connection is blocked until a member becomes available or the timeout expires.
   The timeout is specified as a double value in seconds; a timeout of zero blocks indefinitely.
   In clustered Redis environments, the behavior of multi-key operations may vary.

   ```
   BZPOPMIN key [key ...] timeout
   ```

   key [key ...]: one or more sorted-set keys to pop from.
   timeout: the maximum time to block, in seconds, a timeout of 0 blocks indefinitely.

   Null reply: when no element could be popped and the timeout expired.
   Array reply: the keyname, popped member, and its score."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zadd!
  "Adds all the specified members with their scores to the sorted set stored at key.
   If a member already exists in the set, its score is updated and the element is repositioned to maintain correct ordering.
   If key does not exist, a new sorted set is created with the specified members as its sole members.
   If the key exists but does not hold a sorted set, an error is returned.
   Scores must be the string representation of a double-precision floating-point number; +inf and -inf are valid values.
   NX and XX are mutually exclusive, as are GT and LT.

   ```
   ZADD key [NX | XX] [GT | LT] [CH] [INCR] score member [score member
     ...]
   ```

   key: the name of the key that holds the sorted set.
   score member [score member ...]: one or more score-member pairs to add or update, where score is a double-precision floating-point number.
   NX: only add new members, do not update existing members.
   XX: only update existing members, do not add new members.
   GT: only update an existing member if the new score is greater than the current score, does not prevent adding new members.
   LT: only update an existing member if the new score is less than the current score, does not prevent adding new members.
   CH: change the return value from the number of added members to the number of changed members (added plus updated).
   INCR: increment the member score by score instead of setting it, only one score-member pair may be given.

   Null reply: if the operation was aborted because of a conflict with one of the XX/NX/LT/GT options.
   Integer reply: the number of new members when the CH option is not used.
   Integer reply: the number of new or updated members when the CH option is used.
   Double reply: the updated score of the member when the INCR option is used."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zcard!
  "Returns the number of elements in the sorted set stored at key.
   If the key does not exist, the command returns 0.

   ```
   ZCARD key
   ```

   key: the name of the key that holds the sorted set.

   Integer reply: the cardinality (number of members) of the sorted set, or 0 if the key doesn't exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zcount!
  "Returns the number of elements in the sorted set at key with a score between min and max.
   The min and max arguments follow the same semantics as described for ZRANGEBYSCORE.
   The command runs in O(log(N)) time because it uses element ranks to determine the range,
   so it does not need to iterate over the members in the range.

   ```
   ZCOUNT key min max
   ```

   key: the name of the key that holds the sorted set.
   min: the minimum score, inclusive unless prefixed with (, use -inf for no lower bound.
   max: the maximum score, inclusive unless prefixed with (, use +inf for no upper bound.

   Integer reply: the number of members in the specified score range."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zdiff!
  "ZDIFF computes the difference of one or more sorted sets.
   The result consists of the members present in the first set that are not present in any of the subsequent sets.
   Unlike ZDIFFSTORE, the resulting set is returned to the client rather than stored under a key.
   When the WITHSCORES option is used, the score of each member is included in the reply.

   ```
   ZDIFF numkeys key [key ...] [WITHSCORES]
   ```

   numkeys: the number of keys that follow.
   key [key ...]: one or more sorted-set keys, the result is the members of the first set that are not present in any of the subsequent sets.
   WITHSCORES: also return the score of each member.

   Array reply: the result of the difference including, optionally, scores when the WITHSCORES option is used."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zdiffstore!
  "Computes the difference between the first sorted set and all subsequent sorted sets,
   and stores the result in the destination key.
   The total number of input keys is specified by numkeys.
   Keys that do not exist are treated as empty sets.
   If destination already exists, it is overwritten.

   ```
   ZDIFFSTORE destination numkeys key [key ...]
   ```

   destination: the key to store the resulting sorted set in.
   numkeys: the number of keys that follow.
   key [key ...]: one or more sorted-set keys, the difference is the members of the first set that are not present in any of the subsequent sets.

   Integer reply: the number of members in the resulting sorted set at destination."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zincrby!
  "Increments the score of member in the sorted set stored at key by the given increment.
   If member does not already exist in the sorted set, it is added with the increment value as its score, as if its previous score was 0.0.
   If key does not exist, a new sorted set is created with the specified member as its sole member.
   An error is returned if key exists but does not hold a sorted set.
   The score is a double precision floating point number, and a negative increment can be used to decrement the score.

   ```
   ZINCRBY key increment member
   ```

   key: the name of the key that holds the sorted set.
   increment: the amount to add to the member's current score.
   member: the member whose score to increment, added with the increment as its score if it does not exist.

   Double reply: the new score of member."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zinter!
  "ZINTER computes the intersection of one or more sorted sets and returns the resulting members to the client.
   It behaves like ZINTERSTORE except that the result is not stored under a key.
   Members that do not appear in every input set are excluded from the result.
   The WEIGHTS option multiplies each set scores by a per-set factor before aggregation, defaulting to 1 for every set.
   The AGGREGATE option controls how scores of members present in multiple sets are combined: SUM (the default), MIN, MAX, or COUNT.
   The WITHSCORES option causes the score of each returned member to be included in the reply.

   ```
   ZINTER numkeys key [key ...] [WEIGHTS weight [weight ...]]
     [AGGREGATE <SUM | MIN | MAX | COUNT>] [WITHSCORES]
   ```

   numkeys: the number of keys that follow.
   key [key ...]: one or more sorted-set keys to intersect.
   WEIGHTS weight [weight ...]: a multiplication factor for each input set,
     each set scores are multiplied by its weight before aggregation,
     defaults to 1 for every set.
   AGGREGATE SUM | MIN | MAX | COUNT: how to combine the scores of members
     that exist in multiple sets, SUM (the default), MIN, MAX, or COUNT.
   WITHSCORES: also return the score of each member.

   Array reply: the result of the intersection including, optionally,
     scores when the WITHSCORES option is used."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zintercard!
  "ZINTERCARD returns the cardinality of the intersection of one or more sorted sets,
   without returning the actual member list.
   Keys that do not exist are treated as empty sets, so if any of the given keys
   is missing or empty the result is zero.
   By default the full intersection is computed.
   When the optional LIMIT argument is provided and the running count reaches that
   value, the algorithm stops early and returns the limit as the cardinality,
   which can significantly speed up queries where the limit is lower than the
   actual intersection size.

   ```
   ZINTERCARD numkeys key [key ...] [LIMIT limit]
   ```

   numkeys: the number of keys that follow.
   key [key ...]: one or more sorted-set keys to intersect.
   LIMIT limit: stop counting once the cardinality reaches limit, 0 (the default) means no limit.

   Integer reply: the number of members in the resulting intersection."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zinterstore!
  "Computes the intersection of the sorted sets identified by the given keys and stores the result in destination.
   The number of input keys (numkeys) must be provided before the key list and any optional arguments.
   By default, the score of each resulting member is the sum of its scores across all input sets, which means every member ends up with a score equal to the number of input sets.
   If destination already exists, it is overwritten.

   ```
   ZINTERSTORE destination numkeys key [key ...] [WEIGHTS weight
     [weight ...]] [AGGREGATE <SUM | MIN | MAX | COUNT>]
   ```

   destination: the key to store the resulting sorted set in.
   numkeys: the number of keys that follow.
   key [key ...]: one or more sorted-set keys to intersect.
   WEIGHTS weight [weight ...]: a multiplication factor for each input set, each set scores are multiplied by its weight before aggregation, defaults to 1 for every set.
   AGGREGATE SUM | MIN | MAX | COUNT: how to combine the scores of members that exist in multiple sets, SUM is the default, MIN, MAX, or COUNT.

   Integer reply: the number of members in the resulting sorted set at the destination."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zlexcount!
  "ZLEXCOUNT returns the number of elements in a sorted set whose values fall within a given lexicographic range.
   It is intended for use when all members in the sorted set share the same score, so that lexicographical ordering is the effective ordering.
   The min and max arguments follow the same conventions as ZRANGEBYLEX: prefix with [ for an inclusive bound or ( for an exclusive bound; use - for the lowest possible value and + for the highest.
   The command runs in O(log(N)) time because it uses element ranks to determine the range, so it does not iterate over the members in the range.

   ```
   ZLEXCOUNT key min max
   ```

   key: the name of the key that holds the sorted set.
   min: the minimum member, compared lexicographically; prefix with [ for an inclusive bound or ( for an exclusive bound, use - for the lowest possible value.
   max: the maximum member, compared lexicographically; prefix with [ for an inclusive bound or ( for an exclusive bound, use + for the highest possible value.

   Integer reply: the number of members in the specified score range."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zmpop!
  "Pops one or more member-score pairs from the first non-empty sorted set among the provided keys.
   When the MIN modifier is used, the elements with the lowest scores are popped; MAX pops those with the highest scores.
   The number of elements actually popped is the minimum of the sorted set cardinality and the COUNT value.
   If none of the provided keys hold a non-empty sorted set, the command returns a null reply.

   ```
   ZMPOP numkeys key [key ...] <MIN | MAX> [COUNT count]
   ```

   numkeys: the number of keys that follow.
   key [key ...]: one or more sorted-set keys to pop from.
   MIN | MAX: pop the members with the lowest scores (MIN) or the highest scores (MAX).
   COUNT count: the number of members to pop, defaults to 1.

   Null reply: when no element could be popped.
   Array reply: a two-element array with the first element being the name of the key from which elements were popped, and the second element is an array of the popped elements. Every entry in the elements array is also an array that contains the member and its score."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zmscore!
  "Returns the scores associated with the specified members in the sorted set stored at key.
   For every member that does not exist in the sorted set, a nil value is returned.

   ```
   ZMSCORE key member [member ...]
   ```

   key: the name of the key that holds the sorted set.
   member [member ...]: one or more members whose scores to return.

   Null reply: if the member does not exist in the sorted set.
   Array reply: a list of Double reply member scores as double-precision floating point numbers."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zpopmax!
  "Removes and returns up to count members with the highest scores from the sorted set stored at key.
   If count is not specified, the default is 1.
   Specifying a count greater than the sorted set cardinality does not produce an error.
   When multiple elements are returned, the one with the highest score comes first, followed by elements with lower scores.

   ```
   ZPOPMAX key [count]
   ```

   key: the name of the key that holds the sorted set.
   count: the number of members to pop, defaults to 1 if omitted.

   Array reply: a list of popped elements and scores."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zpopmin!
  "Removes and returns up to count members with the lowest scores from the sorted set stored at key.
   When count is not specified, the default is 1.
   Specifying a count greater than the sorted set cardinality does not produce an error.
   When multiple elements are returned, the one with the lowest score comes first, followed by elements with greater scores.

   ```
   ZPOPMIN key [count]
   ```

   key: the name of the key that holds the sorted set.
   count: the number of members to pop, without it a single member is popped.

   Array reply: a list of popped elements and scores."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zrandmember!
  "Returns a random element from the sorted set stored at key.
   If the key does not exist, a null reply is returned.
   When a positive count is given, the command returns an array of distinct members,
   with the array length being the minimum of count and the set cardinality.
   When a negative count is given, the command returns exactly the absolute value
   of count members, and the same member may appear more than once.
   The optional WITHSCORES modifier, which can only be used together with count,
   causes the reply to include the score of each returned member.

   ```
   ZRANDMEMBER key [count [WITHSCORES]]
   ```

   key: the name of the key that holds the sorted set.
   count: the number of members to return, a positive count returns up to that many
   distinct members while a negative count returns exactly the absolute value of
   count members and may repeat them.
   WITHSCORES: also return the score of each returned member,
   can only be used together with count.

   Bulk string reply: without the additional count argument, the command returns
   a randomly selected member, or Null reply when key does not exist.
   Array reply: when the additional count argument is passed, the command returns
   an array of members, or an empty array when key does not exist.
   If the WITHSCORES modifier is used, the reply is a list of members and their
   scores from the sorted set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zrange!
  "Returns the specified range of elements from the sorted set stored at key.
   The range can be specified by index (rank), by score, or by lexicographical order.
   By default, start and stop are zero-based indices, where negative values count from the end of the set.
   When BYSCORE is given, start and stop are interpreted as score bounds.
   When BYLEX is given, start and stop are interpreted as lexicographical bounds.
   The stop bound is always inclusive.
   REV reverses the ordering so results are returned from high to low.
   LIMIT offset count skips offset matching members and returns up to count of them; it requires BYSCORE or BYLEX, and a negative count returns all remaining members.
   WITHSCORES causes the score of each member to be included in the reply.

   ```
   ZRANGE key start stop [BYSCORE | BYLEX] [REV] [LIMIT offset count]
     [WITHSCORES]
   ```

   key: the name of the key that holds the sorted set.
   start: the start of the range, by default a zero-based index where negatives count from the end, or a score bound with BYSCORE, or a lexicographical bound with BYLEX.
   stop: the end of the range, inclusive, interpreted the same way as start.
   BYSCORE | BYLEX: interpret the range as a score range or a lexicographical range instead of an index range.
   REV: reverse the ordering so results are returned from high to low.
   LIMIT offset count: skip offset matching members and return up to count of them, requires BYSCORE or BYLEX, a negative count returns all remaining members.
   WITHSCORES: also return the score of each member.

   Array reply: a list of members in the specified range with, optionally, their scores when the WITHSCORES option is given."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zrangebylex!
  "Returns the elements in the sorted set at key whose values fall between min and max, ordered lexicographically.
   This command is intended for sorted sets where all members share the same score; if the members have different scores, the set of returned elements is unspecified.
   Elements are compared byte-by-byte, and a longer string is considered greater than a shorter one when the common prefix is identical.
   The optional LIMIT clause skips a number of matching members and then returns up to a given count; a negative count returns all remaining members from the offset.
   A large offset forces traversal of that many elements before returning results, which can add up to O(N) time complexity.

   ```
   ZRANGEBYLEX key min max [LIMIT offset count]
   ```

   key: the name of the key that holds the sorted set.
   min: the minimum member, compared lexicographically, prefix with [ for an inclusive bound or ( for an exclusive bound, use - for the lowest possible value.
   max: the maximum member, compared lexicographically, prefix with [ for an inclusive bound or ( for an exclusive bound, use + for the highest possible value.
   LIMIT offset count: skip offset matching members and return up to count of them, a negative count returns all remaining members.

   Array reply: a list of elements in the specified score range."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zrangebyscore!
  "Returns all elements in the sorted set at key whose score falls between min and max, inclusive.
   Elements are ordered from low to high scores.
   Members sharing the same score are returned in lexicographical order.
   The optional LIMIT clause restricts the result to a sub-range of matching elements, similar to SQL LIMIT.
   A negative count returns all elements starting from the offset.
   A large offset requires traversing that many elements first, which can add up to O(N) time complexity.
   The optional WITHSCORES flag causes each member to be returned together with its score.

   ```
   ZRANGEBYSCORE key min max [WITHSCORES] [LIMIT offset count]
   ```

   key: the name of the key that holds the sorted set.
   min: the minimum score, inclusive unless prefixed with (, use -inf for no lower bound.
   max: the maximum score, inclusive unless prefixed with (, use +inf for no upper bound.
   WITHSCORES: also return the score of each member.
   LIMIT offset count: skip offset matching members and return up to count of them, a negative count returns all remaining members.

   Array reply: a list of the members with, optionally, their scores in the specified score range."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zrangestore!
  "ZRANGESTORE works like ZRANGE but stores the resulting elements in a destination key instead of returning them.
   If the source key does not exist or holds a non-sorted-set value, the command returns an error.
   If the destination key already exists, its contents are overwritten with the result.
   The range is specified by min and max, which by default are zero-based indexes (negative values count from the end).
   Using BYSCORE interprets min and max as score bounds, and BYLEX interprets them as lexicographical bounds.
   REV reverses the ordering so members are returned from high to low.
   LIMIT offset count skips the first offset matching members and returns at most count of them; it requires BYSCORE or BYLEX, and a negative count returns all remaining members.

   ```
   ZRANGESTORE dst src min max [BYSCORE | BYLEX] [REV] [LIMIT offset
     count]
   ```

   dst: the key to store the resulting sorted set in.
   src: the source sorted-set key.
   min: the start of the range, by default a zero-based index (negatives count from the end), a score bound with BYSCORE, or a lexicographical bound with BYLEX.
   max: the end of the range, inclusive, interpreted the same way as min.
   BYSCORE | BYLEX: interpret the range as a score range (BYSCORE) or a lexicographical range (BYLEX) instead of an index range.
   REV: reverse the ordering so results are returned from high to low.
   LIMIT offset count: skip offset matching members and return up to count of them, requires BYSCORE or BYLEX, a negative count returns all remaining members.

   Integer reply: the number of elements in the resulting sorted set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zrank!
  "Returns the rank of a member in the sorted set stored at key, where ranks are ordered from the lowest score to the highest.
   The rank is 0-based, so the member with the lowest score has rank 0.
   If the key does not exist or the member is not in the sorted set, the command returns null.
   The optional WITHSCORE argument causes the score of the member to be included in the reply alongside its rank.

   ```
   ZRANK key member [WITHSCORE]
   ```

   key: the name of the key that holds the sorted set.
   member: the member whose rank to return.
   WITHSCORE: also return the member's score along with its rank.

   Null reply: if the key does not exist or the member does not exist in the sorted set.
   Integer reply: the rank of the member when WITHSCORE is not used.
   Array reply: the rank and score of the member when WITHSCORE is used."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zrem!
  "Removes the specified members from the sorted set stored at key.
   Non-existing members are silently ignored.
   An error is returned when the key exists but does not hold a sorted set.

   ```
   ZREM key member [member ...]
   ```

   key: the name of the key that holds the sorted set.
   member [member ...]: one or more members to remove from the sorted set.

   Integer reply: the number of members removed from the sorted set, not including non-existing members."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zremrangebylex!
  "Removes all elements in the sorted set stored at key that fall within the lexicographical range specified by min and max.
   This command is intended for use when all elements in the sorted set share the same score, so that ordering is purely lexicographical.
   The min and max bounds work the same way as in ZRANGEBYLEX, and the command removes exactly the elements that ZRANGEBYLEX would return for the same arguments.

   ```
   ZREMRANGEBYLEX key min max
   ```

   key: the name of the key that holds the sorted set.
   min: the minimum member, compared lexicographically, prefix with [ for an inclusive bound or ( for an exclusive bound, use - for the lowest possible value.
   max: the maximum member, compared lexicographically, prefix with [ for an inclusive bound or ( for an exclusive bound, use + for the highest possible value.

   Integer reply: Number of members removed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zremrangebyrank!
  "Removes all elements in the sorted set stored at key whose rank falls between start and stop, inclusive.
   Both start and stop are zero-based indexes, where 0 refers to the element with the lowest score.
   Negative indexes count from the opposite end: -1 is the element with the highest score, -2 the second highest, and so on.

   ```
   ZREMRANGEBYRANK key start stop
   ```

   key: the name of the key that holds the sorted set.
   start: the start index, zero-based; negative values count from the end of the sorted set (-1 is the member with the highest score).
   stop: the stop index, zero-based and inclusive; negative values count from the end of the sorted set.

   Integer reply: Number of members removed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zremrangebyscore!
  "Removes all elements in the sorted set stored at key whose score falls between min and max, inclusive.
   If the key does not exist, the command returns zero as if the set were empty.
   If the key holds a value that is not a sorted set, an error is returned.

   ```
   ZREMRANGEBYSCORE key min max
   ```

   key: the name of the key that holds the sorted set.
   min: the minimum score, inclusive unless prefixed with (, use -inf for no lower bound.
   max: the maximum score, inclusive unless prefixed with (, use +inf for no upper bound.

   Integer reply: Number of members removed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zrevrange!
  "Returns the specified range of elements in the sorted set stored at key.
   Elements are ordered from the highest to the lowest score.
   Descending lexicographical order is used for elements with equal score.
   Apart from the reversed ordering, ZREVRANGE is similar to ZRANGE.

   ```
   ZREVRANGE key start stop [WITHSCORES]
   ```

   key: the name of the key that holds the sorted set.
   start: the start index, zero-based, negative values count from the end of the sorted set (-1 is the member with the highest score).
   stop: the stop index, zero-based and inclusive, negative values count from the end of the sorted set.
   WITHSCORES: also return the score of each member.

   Array reply: a list of members in the specified range, optionally with their scores if WITHSCORE was used."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zrevrangebylex!
  "Returns all elements in the sorted set at key with a value between max and min,
   in reversed lexicographical order.
   The command is intended for use when all elements in the sorted set share the same score,
   so that ordering is determined lexicographically rather than by score.
   It behaves like ZRANGEBYLEX but returns the matching members in reverse order.

   ```
   ZREVRANGEBYLEX key max min [LIMIT offset count]
   ```

   key: the name of the key that holds the sorted set.
   max: the maximum member, compared lexicographically, prefix with [ for an inclusive bound or ( for an exclusive bound, use + for the highest possible value.
   min: the minimum member, compared lexicographically, prefix with [ for an inclusive bound or ( for an exclusive bound, use - for the lowest possible value.
   LIMIT offset count: skip offset matching members and return up to count of them, a negative count returns all remaining members.

   Array reply: List of the elements in the specified score range."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zrevrangebyscore!
  "Returns all elements in the sorted set at key whose scores fall between max and min, inclusive.
   Elements are returned in descending score order, from highest to lowest.
   Members that share the same score are returned in reverse lexicographical order.
   Apart from the reversed ordering, the behaviour mirrors ZRANGEBYSCORE.

   ```
   ZREVRANGEBYSCORE key max min [WITHSCORES] [LIMIT offset count]
   ```

   key: the name of the key that holds the sorted set.
   max: the maximum score, inclusive unless prefixed with (, use +inf for no upper bound.
   min: the minimum score, inclusive unless prefixed with (, use -inf for no lower bound.
   WITHSCORES: also return the score of each member.
   LIMIT offset count: skip offset matching members and return up to count of them, a negative count returns all remaining members.

   Array reply: a list of the members and, optionally, their scores in the specified score range."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zrevrank!
  "Returns the rank of a member in a sorted set, where the highest score has rank 0.
   The rank is 0-based and reflects the ordering from high to low scores.
   If the key does not exist or the member is not in the sorted set, a null reply is returned.
   The optional WITHSCORE argument includes the member's score in the reply alongside its rank.

   ```
   ZREVRANK key member [WITHSCORE]
   ```

   key: the name of the key that holds the sorted set.
   member: the member whose rank to return.
   WITHSCORE: also return the member's score along with its rank.

   Null reply: if the key does not exist or the member does not exist in the sorted set.
   Integer reply: the rank of the member when WITHSCORE is not used.
   Array reply: the rank and score of the member when WITHSCORE is used."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zscan!
  "Iterates over the members and scores of a sorted set stored at key using an incremental cursor-based approach.
   Start the iteration with cursor 0, then pass the cursor returned by each call as the argument to the next call.

   ```
   ZSCAN key cursor [MATCH pattern] [COUNT count]
   ```

   key: the name of the key that holds the sorted set.
   cursor: the cursor value, start an iteration with 0 and use the cursor returned by each call as the argument to the next call.
   MATCH pattern: only return members matching the given glob-style pattern.
   COUNT count: a hint for the number of members to return per iteration, the default is 10.

   Array reply: cursor and scan response in array form."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zscore!
  "Returns the score associated with a given member in a sorted set.
   If the member is not present in the sorted set, or if the key does not exist, a nil reply is returned.

   ```
   ZSCORE key member
   ```

   key: the name of the key that holds the sorted set.
   member: the member whose score to return.

   Double reply: the score of the member (a double-precision floating point number).
   Nil reply: if member does not exist in the sorted set, or the key does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zunion!
  "ZUNION computes the union of one or more sorted sets and returns the result to the client.
   Unlike ZUNIONSTORE, the resulting set is not stored under any key.
   Each member that appears in multiple input sets has its score combined according to the AGGREGATE option, and each set can be scaled by a per-set weight before aggregation.

   ```
   ZUNION numkeys key [key ...] [WEIGHTS weight [weight ...]]
     [AGGREGATE <SUM | MIN | MAX | COUNT>] [WITHSCORES]
   ```

   numkeys: the number of keys that follow.
   key [key ...]: one or more sorted-set keys to union.
   WEIGHTS weight [weight ...]: a multiplication factor for each input set, each set scores are multiplied by its weight before aggregation, defaults to 1 for every set.
   AGGREGATE SUM | MIN | MAX | COUNT: how to combine the scores of members that exist in multiple sets, SUM is the default, MIN, MAX, or COUNT.
   WITHSCORES: also return the score of each member.

   Array reply: the result of the union with, optionally, their scores when WITHSCORES is used."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn zunionstore!
  "Computes the union of the specified sorted sets and stores the result in the destination key.
   If destination already exists, it is overwritten.
   By default, the score of each element in the result is the sum of its scores across the input sets where it appears.
   The WEIGHTS option allows specifying a multiplication factor per input set, so each element score is scaled by its set weight before aggregation; when omitted, all weights default to 1.
   The AGGREGATE option controls how scores are combined: SUM (the default) adds them, MIN takes the smallest, MAX takes the largest, and COUNT ignores the original scores entirely and instead counts how many input sets contain the element, optionally scaled by WEIGHTS.

   ```
   ZUNIONSTORE destination numkeys key [key ...] [WEIGHTS weight
     [weight ...]] [AGGREGATE <SUM | MIN | MAX | COUNT>]
   ```

   destination: the key to store the resulting sorted set in.
   numkeys: the number of keys that follow.
   key [key ...]: one or more sorted-set keys to union.
   WEIGHTS weight [weight ...]: a multiplication factor for each input set, applied before aggregation, defaults to 1 for every set.
   AGGREGATE SUM | MIN | MAX | COUNT: how to combine scores of members present in multiple sets, SUM is the default.

   Integer reply: the number of elements in the resulting sorted set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))
