(ns redis.cmd.stream)

(defn xack!
  "Removes one or more messages from the Pending Entries List (PEL) of a stream consumer group.
   A message becomes pending when it is delivered to a consumer, typically as a side effect of XREADGROUP, or when a consumer takes ownership of it via XCLAIM.
   While pending, the message will be returned by subsequent XREADGROUP calls (for instance using an ID of 0) and will be listed by XPENDING.
   Once a consumer has successfully processed a message, it should call XACK so the message is not processed again and the PEL entry is purged, releasing server memory.
   Message IDs that are no longer part of the PEL, for example because they were already acknowledged, are not counted as successfully acknowledged.

   ```
   XACK key group id [id ...]
   ```

   key: the stream key.
   group: the consumer group name.
   id [id ...]: one or more message IDs to acknowledge.

   Integer reply: the number of messages successfully acknowledged.
   Certain message IDs may no longer be part of the PEL (for example because they have already been acknowledged), and XACK will not count them as successfully acknowledged."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xackdel!
  "Acknowledges and conditionally deletes one or multiple entries for a stream consumer group at the specified key.
   It combines the functionality of acknowledging entries in a consumer group with deleting those entries from the stream in a single atomic operation.
   If the key does not exist, -1 is returned for each requested ID.
   The optional mode controls how consumer group references are handled when entries are deleted.

   ```
   XACKDEL key group [KEEPREF | DELREF | ACKED] IDS numids id [id ...]
   ```

   key: the name of the stream key.
   group: the name of the consumer group.
   IDS numids id [id ...]: the IDS block specifying which entries to acknowledge and delete, where numids is the number of IDs that follow and id [id ...] are one or more stream entry IDs.
   KEEPREF | DELREF | ACKED: specifies how to handle consumer group references when acknowledging and deleting entries; KEEPREF (default) preserves existing references in all consumer groups PEL, DELREF removes all references from all consumer groups pending entry lists, and ACKED only deletes entries that were read and acknowledged by all consumer groups.

   Array reply: -1 for each requested ID when the given key does not exist.
   Array reply: for each ID, an integer reply.
   Integer reply: 1 if the entry was acknowledged and deleted from the stream.
   Integer reply: -1 if no such ID exists in the provided stream key.
   Integer reply: 2 if the entry was acknowledged but not deleted, as there are still dangling references (ACKED option)."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xadd!
  "Appends the specified stream entry to the stream stored at the given key.
   If the key does not exist, a new stream key is created as a side effect of the command.
   The NOMKSTREAM option suppresses this automatic key creation.
   Each entry consists of one or more field-value pairs, stored in the order provided.
   Commands that read the stream, such as XRANGE or XREAD, return fields and values in exactly the order they were added.
   XADD is the only command that can add data to a stream; other commands like XDEL and XTRIM can remove data.

   ```
   XADD key [NOMKSTREAM] [KEEPREF | DELREF | ACKED]
     [IDMPAUTO producer-id | IDMP producer-id idempotent-id]
     [<MAXLEN | MINID> [= | ~] threshold [LIMIT count]] <* | id>
     field value [field value ...]
   ```

   key: the name of the stream key.
   id: the stream entry ID, use * to auto-generate a unique ID or specify a well-formed ID in the format <ms>-<seq>.
   field value [field value ...]: one or more field-value pairs that make up the stream entry, at least one pair is required.
   NOMKSTREAM: prevents the creation of a new stream if the key does not exist.
   IDMPAUTO producer-id | IDMP producer-id idempotent-id: enables idempotent message processing to prevent duplicate entries, only usable when the entry ID is *.
   KEEPREF | DELREF | ACKED: specifies how to handle consumer group references when trimming, defaults to KEEPREF, has no effect if there are no consumer groups.
   MAXLEN | MINID [= | ~] threshold [LIMIT count]: trims the stream to maintain a specific size or remove old entries, MAXLEN evicts entries while the stream length exceeds the threshold, MINID evicts entries with IDs lower than the threshold, = is exact trimming (default), ~ is approximate trimming, LIMIT count caps the number of entries examined during trimming.

   Bulk string reply: the ID of the added entry, auto-generated if * was passed as the id argument, or the same ID specified by the user; when using IDMP and a duplicate is detected, returns the ID of the original entry.
   Null reply: if the NOMKSTREAM option is given and the key does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xautoclaim!
  "Transfers ownership of pending stream entries to a specified consumer, using SCAN-like semantics.
   It is conceptually equivalent to calling XPENDING followed by XCLAIM, but provides a more straightforward way to handle message delivery failures.
   The command operates on the stream at key within the context of the given consumer group, claiming messages that have been idle for at least min-idle-time milliseconds and whose ID is greater than or equal to start.
   The count argument, which defaults to 100, sets the upper limit of entries the command attempts to claim per call.
   Internally the command scans the consumer group Pending Entries List from start and filters out entries whose idle time is less than or equal to min-idle-time.
   The maximum number of pending entries scanned is count multiplied by 10, so the number of entries actually claimed may be less than the specified count.
   When the PEL is exhausted the command returns the special 0-0 ID as the cursor, signalling completion, but it is still valid to call the command again with 0-0 as start because enough time may have passed for older entries to become eligible.
   Claiming a message resets its idle time, ensuring that only a single consumer can successfully claim a given pending message at a specific instant.
   Messages released back to the group via XNACK are immediately claimable because their delivery time is set to zero.
   If the command encounters a message that no longer exists in the stream (trimmed or deleted), it does not claim it and removes it from the PEL; such message IDs are included in the reply.
   Claiming a message also increments its attempted deliveries count, unless the JUSTID option is used.

   ```
   XAUTOCLAIM key group consumer min-idle-time start [COUNT count]
     [JUSTID]
   ```

   key: the stream key.
   group: the consumer group name.
   consumer: the name of the consumer that will own the claimed messages.
   min-idle-time: claim only messages that have been idle for at least this long, in milliseconds.
   start: the message ID to start scanning from, use 0 to start at the beginning of the PEL.
   COUNT count: the number of messages to attempt to claim per call, defaults to 100.
   JUSTID: return only the IDs of the claimed messages without their fields, and do not increment their retry counters.

   A stream ID to be used as the start argument for the next call to XAUTOCLAIM.
   An Array reply containing all the successfully claimed messages in the same format as XRANGE.
   An Array reply containing message IDs that no longer exist in the stream, and were deleted from the PEL in which they were found."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xcfgset!
  "Sets the IDMP (Idempotent Message Processing) configuration parameters for a stream.
   It controls how long idempotent IDs are retained and the maximum number of idempotent IDs tracked per producer.
   The stream must already exist; the command operates on an existing stream key.

   ```
   XCFGSET key [IDMP-DURATION idmp-duration]
     [IDMP-MAXSIZE idmp-maxsize]
   ```

   key: the name of the stream key, which must already exist.
   IDMP-DURATION idmp-duration: sets the duration in seconds for which each idempotent ID is retained in the stream IDMP map, valid range 1 to 86400 seconds, default 100 seconds.
   IDMP-MAXSIZE idmp-maxsize: sets the maximum number of most recent idempotent IDs kept for each producer in the stream IDMP map, valid range 1 to 10000 entries, default 100 entries.

   Simple string reply: OK if the configuration was set successfully."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xclaim!
  "Changes the ownership of a pending message in a stream consumer group so that the specified consumer becomes the new owner.
   The message is claimed only if its idle time exceeds the min-idle-time threshold; as a side effect the idle time is reset, so two consumers attempting to claim the same message simultaneously will never both succeed.
   Messages previously released back to the group via XNACK are immediately claimable because their delivery time is set to zero, satisfying any minimum idle time requirement.
   Unless JUSTID is specified, the delivery attempt counter is incremented each time a message is claimed, allowing the system to detect messages that repeatedly fail to be processed.
   The reply array may be shorter than the number of IDs supplied if a message could not be claimed; in one such case the message is also removed from the pending entries list.

   ```
   XCLAIM key group consumer min-idle-time id [id ...] [IDLE ms]
     [TIME unix-time-milliseconds] [RETRYCOUNT count] [FORCE] [JUSTID]
     [LASTID lastid]
   ```

   key: the stream key.
   group: the consumer group name.
   consumer: the name of the consumer that will own the claimed messages.
   min-idle-time: claim only messages that have been idle for at least this long, in milliseconds.
   id [id ...]: one or more message IDs to claim.
   IDLE ms: set the idle time of the message; if not specified, an idle time of 0 is assumed.
   TIME unix-time-milliseconds: set the idle time to a specific Unix time in milliseconds instead of a relative amount.
   RETRYCOUNT count: set the retry counter to the specified value; if not set, the counter is incremented on each delivery.
   FORCE: create the pending message entry in the PEL even if the ID is not already in the PEL, provided the message exists in the stream.
   JUSTID: return only an array of IDs of successfully claimed messages; the retry counter is not incremented.
   LASTID lastid: update the consumer group last-delivered ID to the given ID.

   Array reply: when the JUSTID option is specified, an array of IDs of messages successfully claimed.
   Array reply: an array of stream entries, each of which contains an array of two elements, the entry ID and the entry data itself."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xdel!
  "Removes the specified entries from a stream and returns the number of entries actually deleted.
   The returned count may be less than the number of IDs passed if some of those IDs do not exist in the stream.
   Although streams are normally treated as append-only, entries can be deleted from them.

   ```
   XDEL key id [id ...]
   ```

   key: the stream key.
   id [id ...]: one or more message IDs to delete.

   Integer reply: the number of entries that were deleted."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xdelex!
  "Deletes one or multiple entries from the stream stored at the given key.
   It extends the basic XDEL behaviour by letting the caller control what happens to consumer group references when entries are removed.
   If the key does not exist, the command returns -1 for every requested ID.
   If an entry ID is not found in the stream, -1 is returned for that ID.

   ```
   XDELEX key [KEEPREF | DELREF | ACKED] IDS numids id [id ...]
   ```

   key: the name of the stream key.
   IDS numids id [id ...]: the IDS block specifying which entries to delete, where numids is the number of IDs that follow and id [id ...] are one or more stream entry IDs to delete.
   KEEPREF | DELREF | ACKED: specifies how to handle consumer group references when deleting entries; KEEPREF (default) preserves existing references in all consumer groups pending entry lists, DELREF also removes all references from all consumer groups pending entry lists, and ACKED only deletes entries that were read and acknowledged by all consumer groups.

   Array reply: -1 for each requested ID when the given key does not exist.
   Array reply: for each ID:
   Integer reply: -1 if no such ID exists in the provided stream key.
   Integer reply: 1 if the entry was deleted from the stream.
   Integer reply: 2 if the entry was not deleted and there are still existing references (ACKED option); also, if there are no consumer groups."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xgroup-create!
  "Creates a new consumer group for the stream stored at key.
   The group is uniquely identified by its name within that stream; attempting to create a group with a name that already exists returns a -BUSYGROUP error.
   The id argument sets the starting position for the group: $ means the last entry currently in the stream, while 0 means the group will read from the very beginning.
   By default the command requires the stream to already exist and returns an error otherwise.
   The MKSTREAM option causes an empty stream to be created if the key does not yet exist.
   The ENTRIESREAD option sets the group initial entries-read counter, which is used to compute the consumer group lag.

   ```
   XGROUP CREATE key group <id | $> [MKSTREAM]
     [ENTRIESREAD entries-read]
   ```

   key: the stream key.
   group: the name of the consumer group to create.
   id | $: the ID of the last delivered message the group starts reading after, use $ for the last message currently in the stream or 0 to read from the start.
   MKSTREAM: create the stream as an empty stream if it does not already exist.
   ENTRIESREAD entries-read: set the group initial entries-read counter, used to compute its lag.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xgroup-createconsumer!
  "Creates a consumer with the given name in the specified consumer group of the stream stored at key.
   Consumers are also created automatically when an operation such as XREADGROUP references a consumer that does not yet exist, but this only applies to XREADGROUP and only when there is data in the stream.

   ```
   XGROUP CREATECONSUMER key group consumer
   ```

   key: the stream key.
   group: the consumer group name.
   consumer: the name of the consumer to create.

   Integer reply: the number of created consumers, either 0 or 1."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xgroup-delconsumer!
  "Deletes a consumer from a consumer group on a stream.
   Any pending messages that belonged to the consumer become unclaimable once the consumer is removed.
   It is recommended to claim or acknowledge pending messages before deleting the consumer.

   ```
   XGROUP DELCONSUMER key group consumer
   ```

   key: the stream key.
   group: the consumer group name.
   consumer: the name of the consumer to delete.

   Integer reply: the number of pending messages the consumer had before it was deleted."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xgroup-destroy!
  "Completely destroys a consumer group on a stream.
   The group is destroyed even if there are active consumers or pending messages, so this should only be called when it is truly needed.

   ```
   XGROUP DESTROY key group
   ```

   key: the stream key.
   group: the name of the consumer group to destroy.

   Integer reply: the number of destroyed consumer groups, either 0 or 1."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xgroup-setid!
  "Sets the last delivered ID for a consumer group on a stream.
   This allows changing the group's last-delivered ID without deleting and recreating the group.
   The ID can be set to 0 to make consumers re-process all messages, or to $ to target the last message currently in the stream.
   The optional ENTRIESREAD argument sets the group's entries-read counter, which is used to compute its lag.
   This is useful when you know exactly how many entries lie between an arbitrary ID and the stream's last entry, allowing you to set entries-read to the stream's entries_added minus that count.

   ```
   XGROUP SETID key group <id | $> [ENTRIESREAD entries-read]
   ```

   key: the stream key.
   group: the consumer group name.
   id | $: the ID to set as the group's last-delivered ID, use $ for the last message currently in the stream or 0 to reset to the start.
   ENTRIESREAD entries-read: set the group's entries-read counter, used to compute its lag.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xidmprecord!
  "XIDMPRECORD is an internal command that users should not call directly.
   It sets IDMP (idempotency) metadata on a stream, which is then replayed during AOF loading.
   The command records a mapping between a producer ID and an idempotency ID to a specific stream message ID.
   If the pid/iid pair already maps to the same stream ID, the command is idempotent and succeeds.
   It returns an error if the key does not exist, the key is not a stream, the stream ID does not refer to an existing entry, pid or iid are empty, or the pid/iid pair already maps to a different stream ID.

   ```
   XIDMPRECORD key pid iid stream-id
   ```

   key: the name of a stream key.
   pid: a producer ID.
   iid: an IDMP (idempotency) ID.
   stream-id: the ID of an existing stream message.

   Simple string reply: OK when the provided pid/iid pair already maps to the same stream ID, that is, the command is idempotent.
   Simple error reply: the key does not exist, the key does not refer to a stream, the stream ID refers to a non-existent or deleted entry, pid and/or iid are empty, or the pid/iid pair already maps to a different stream ID."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xinfo-consumers!
  "Returns the list of consumers belonging to a named consumer group of a stream.
   For each consumer in the group, a set of attributes is provided.

   ```
   XINFO CONSUMERS key group
   ```

   key: the stream key.
   group: the consumer group name.

   Array reply: a list of consumers and their attributes."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xinfo-groups!
  "Returns the list of all consumer groups of the stream stored at key.

   ```
   XINFO GROUPS key
   ```

   key: the stream key.

   Array reply: a list of consumer groups."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xinfo-stream!
  "Returns information about the stream stored at key.
   Without the FULL option, a summary of the stream is provided.
   With FULL, the full stream state is returned, including all entries and detailed pending-entry-list information.

   ```
   XINFO STREAM key [FULL [COUNT count]]
   ```

   key: the stream key.
   FULL: return the full stream state, including all entries and detailed PEL information, instead of a summary.
   COUNT count: with FULL, the number of entries to return, 0 returns all entries, the default is 10.

   Map reply: when the FULL argument was not given, a list of information about a stream in summary form.
   Map reply: when the FULL argument was given, a list of information about a stream in extended form."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xlen!
  "Returns the number of entries in the stream stored at key.
   If the key does not exist, the command returns zero, as if the stream were empty.
   Note that unlike other Redis types, zero-length streams are possible, so you should call TYPE or EXISTS to distinguish a missing key from an empty stream.
   Streams are not auto-deleted once they have no entries, because they may have consumer groups associated with them.

   ```
   XLEN key
   ```

   key: the stream key.

   Integer reply: the number of entries of the stream at key."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xnack!
  "In the context of a stream consumer group, XNACK allows a consumer to explicitly release pending messages back to the group Pending Entries List without acknowledging them.
   Released messages become immediately available for re-delivery to other consumers in the group, eliminating the idle-timeout delay normally required for message recovery.
   The command provides three modes that control how the delivery counter is adjusted: SILENT decrements the counter by one, FAIL leaves it unchanged, and FATAL sets it to the maximum value, marking the message as permanently failed.
   This is useful for graceful consumer shutdown, consumer-side failures, resource constraints, or poison message detection.

   ```
   XNACK key group <SILENT | FAIL | FATAL> IDS numids id [id ...]
     [RETRYCOUNT count] [FORCE]
   ```

   key: name of the stream.
   group: name of the consumer group.
   mode: controls the delivery counter adjustment; must be SILENT, FAIL, or FATAL.
   ids: block specifying the message IDs to release, where numids is the count and id [id ...] are the stream entry IDs to be released back to the group.
   RETRYCOUNT count: directly sets the delivery counter to the specified value, overriding the mode-based adjustment.
   FORCE: creates new unowned PEL entries for IDs not already in the group PEL; each entry must exist in the stream.

   Integer reply: the number of messages successfully released back to the group PEL.
   Messages that are not in the consumer group PEL will not be counted."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xpending!
  "XPENDING inspects the Pending Entries List (PEL) of a consumer group on a stream.
   Pending entries are messages that were delivered to a consumer but never acknowledged.
   Without the optional range arguments the command returns a summary of the PEL;
   with start, end, and count it returns a detailed per-message listing.
   It is the primary way to observe which consumers are active,
   which messages are still pending, and which have been idle for a long time.

   If the key does not exist or does not hold a stream, an error is returned.
   If the group does not exist on the stream, an error is returned.

   ```
   XPENDING key group [[IDLE min-idle-time] start end count [consumer]]
   ```

   key: the stream key.
   group: the consumer group name.
   IDLE min-idle-time: filter to messages that have been idle for at least this long, in milliseconds.
   start: the start of the ID range to inspect, inclusive, use - for the smallest ID.
   end: the end of the ID range to inspect, inclusive, use + for the greatest ID.
   count: the maximum number of messages to return.
   consumer: filter to messages owned by the given consumer.

   Array reply: different data depending on the way XPENDING is called, as explained on this page."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xrange!
  "Returns the stream entries whose IDs fall within a specified range.
   The range is a closed interval defined by a minimum and a maximum ID, so entries with an ID equal to either bound are included.
   A reciprocal command, XREVRANGE, returns the same entries in reverse order.

   ```
   XRANGE key start end [COUNT count]
   ```

   key: the stream key.
   start: the start of the ID range, inclusive, use - for the smallest ID.
   end: the end of the ID range, inclusive, use + for the greatest ID.
   COUNT count: the maximum number of entries to return.

   Array reply: a list of stream entries with IDs matching the specified range."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xread!
  "Reads entries from one or more streams, returning only those with an ID
   greater than the last ID reported by the caller.
   The command can optionally block waiting for new entries,
   similar to BRPOP or BZPOPMIN.
   If no stream can be served and the BLOCK option is not given,
   a null reply is returned.

   ```
   XREAD [COUNT count] [MAXCOUNT maxcount] [MAXSIZE maxsize]
     [BLOCK milliseconds] STREAMS key [key ...] id [id ...]
   ```

   STREAMS key [key ...] id [id ...]: the keys to read from, followed by an ID
     for each key; entries with an ID greater than the given one are returned,
     use $ to read only entries added after the call.
   COUNT count: the maximum number of entries to return per stream.
   MAXCOUNT maxcount: the maximum number of entries to return in total across
     all streams, must be a positive integer and greater than or equal to COUNT
     when both are given.
   MAXSIZE maxsize: the maximum size in bytes of the reply across all streams,
     must be a positive integer, at least one entry is always returned so a
     single entry larger than MAXSIZE is still returned.
   BLOCK milliseconds: block for up to this many milliseconds if no entries are
     available, 0 blocks indefinitely.

   Map reply: a map of key-value elements where each element is composed of the
     key name and the entries reported for that key, the entries are full stream
     entries having IDs and the list of all the fields and values, fields and
     values are guaranteed to be reported in the same order they were added by
     XADD.
   Null reply: if the BLOCK option is given and a timeout occurs, or if there is
     no stream that can be served."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xreadgroup!
  "XREADGROUP reads entries from one or more streams on behalf of a named consumer within a consumer group.
   It is the consumer-group variant of XREAD, so the same stream and ID semantics apply.
   The ID for each key is either the special value > to read entries never delivered to any consumer in the group, or a specific entry ID to read from this consumer's pending entries list.
   When NOACK is used, read entries are not added to the consumer's pending entries list and are treated as acknowledged immediately.
   The CLAIM option allows claiming entries that have been idle for at least the given number of milliseconds before reading them.

   ```
   XREADGROUP GROUP group consumer [COUNT count] [MAXCOUNT maxcount]
     [MAXSIZE maxsize] [BLOCK milliseconds] [CLAIM min-idle-time]
     [NOACK] STREAMS key [key ...] id [id ...]
   ```

   GROUP group consumer: the consumer group and the consumer name to read as.
   STREAMS key [key ...] id [id ...]: the keys to read from, followed by an ID for each key; use > to read messages never delivered to any other consumer, or a specific ID to read from this consumer's pending list.
   COUNT count: the maximum number of entries to return per stream.
   MAXCOUNT maxcount: the maximum number of entries to return in total across all streams; unlike COUNT which limits per stream, MAXCOUNT applies a single cumulative budget for the whole command; must be a positive integer and greater than or equal to COUNT when both are given; when COUNT is omitted, MAXCOUNT alone bounds the total; works for both new reads and pending entries list reads.
   MAXSIZE maxsize: the maximum size in bytes of the reply across all streams; must be a positive integer; at least one entry is always returned so a single entry larger than MAXSIZE is still returned; the limit is checked before delivering the next entry so a skipped entry is neither sent nor added to the consumer's pending entries list.
   BLOCK milliseconds: block for up to this many milliseconds if no entries are available; 0 blocks indefinitely.
   CLAIM min-idle-time: claim messages that have been idle for at least this long in milliseconds before reading.
   NOACK: do not add the read messages to the pending entries list; treat them as acknowledged immediately.

   Map reply: a map of key-value elements where each element is composed of the key name and the entries reported for that key; the entries are full stream entries, having IDs and the list of all the fields and values; fields and values are guaranteed to be reported in the same order they were added by XADD.
   Null reply: if the BLOCK option is given and a timeout occurs, or if there is no stream that can be served."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xrevrange!
  "XREVRANGE returns stream entries in reverse order, from the highest matching ID down to the lowest.
   It is the reverse-order counterpart of XRANGE: the end ID is specified first, then the start ID, and both endpoints are inclusive.
   Use + to denote the greatest ID and - to denote the smallest ID.
   Each returned entry is complete, containing its ID and all fields in the order they were added by XADD.

   ```
   XREVRANGE key end start [COUNT count]
   ```

   key: the stream key.
   end: the end of the ID range, inclusive, entries are returned in reverse order, use + for the greatest ID.
   start: the start of the ID range, inclusive, use - for the smallest ID.
   COUNT count: the maximum number of entries to return.

   Array reply: the command returns the entries with IDs matching the specified range.
   The returned entries are complete, which means that the ID and all the fields they are composed of are returned.
   Moreover, the entries are returned with their fields and values in the same order as XADD added them."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xsetid!
  "XSETID is an internal command that users should not call directly.
   It is used by Redis primary shards to replicate the last delivered entry ID of a stream.

   ```
   XSETID key last-id [ENTRIESADDED entries-added]
     [MAXDELETEDID max-deleted-id]
   ```

   key: the stream key.
   last-id: the ID to set as the stream's last-generated ID.
   ENTRIESADDED entries-added: set the stream's entries-added counter.
   MAXDELETEDID max-deleted-id: set the stream's maximum deleted entry ID.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn xtrim!
  "XTRIM trims a stream by evicting older entries, that is entries with lower IDs, if needed.
   It supports two strategies: MAXLEN evicts entries as long as the stream length exceeds the threshold, and MINID evicts entries whose IDs are lower than the threshold.
   By default, or when the = operator is given, trimming is exact; the ~ operator enables approximate trimming, which is more efficient but may leave slightly more entries than the threshold.
   When consumer groups exist, the KEEPREF, DELREF, or ACKED option controls how references in consumer group PELs are handled.
   If no consumer group option is specified, KEEPREF is used by default.

   ```
   XTRIM key <MAXLEN | MINID> [= | ~] threshold [LIMIT count] [KEEPREF | DELREF | ACKED]
   ```

   key: the name of the stream key.
   MAXLEN | MINID: the trimming strategy; MAXLEN evicts entries as long as the stream length exceeds the threshold, MINID evicts entries with IDs lower than the threshold.
   threshold: for MAXLEN, a non-negative integer specifying the maximum number of entries that may remain after trimming; for MINID, a stream ID below which all entries are trimmed and at or above which entries are kept.
   = | ~: the trimming operator; = for exact trimming (default), ~ for approximate trimming which is more efficient but may leave slightly more entries than the threshold.
   LIMIT count: limits the number of entries to examine during trimming; when not specified, Redis uses a default of 100 times the number of entries in a macro node; specifying 0 disables the limiting mechanism entirely.
   KEEPREF | DELREF | ACKED: specifies how to handle consumer group references; KEEPREF (default) preserves PEL references to trimmed entries, DELREF removes those references, ACKED only removes entries acknowledged by all consumer groups.

   Integer reply: the number of entries deleted from the stream."
  [_state _msg]
  (throw (Exception. "Not Implemented")))
