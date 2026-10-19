(ns redis.cmd.pubsub)

(defn psubscribe!
  "Subscribes the client to one or more glob-style patterns.
   Messages published to any channel whose name matches a subscribed pattern are delivered to the client.
   Once the client enters the subscribed state it is not supposed to issue any other commands, except for additional SUBSCRIBE, SSUBSCRIBE, PSUBSCRIBE, UNSUBSCRIBE, SUNSUBSCRIBE, PUNSUBSCRIBE, PING, RESET and QUIT commands.
   When using the extended protocol, a client may issue any commands while in a subscribed state.

   ```
   PSUBSCRIBE pattern [pattern ...]
   ```

   pattern [pattern ...]: one or more glob-style patterns to subscribe to, messages published to any channel whose name matches a pattern are delivered to the client, supported patterns include h?llo matching hello hallo and hxllo, h*llo matching hllo and heeeello, h[ae]llo matching hello and hallo but not hillo, special characters can be escaped to match them verbatim.

   When successful, this command does not return anything.
   Instead, for each pattern, one message with the first element being the string psubscribe is pushed as a confirmation that the command succeeded."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn publish!
  "Posts a message to the given channel.
   All clients subscribed to that channel receive the message.
   In a Redis Cluster, clients can publish to any node and the cluster forwards the message as needed, so subscribers connected to any node will still receive it.

   ```
   PUBLISH channel message
   ```

   channel: the channel to publish to.
   message: the message to publish.

   Integer reply: the number of clients that the message was sent to.
   Note that in a Redis Cluster, only clients that are connected to the same node as the publishing client are included in the count."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn pubsub-channels!
  "Lists the currently active Pub/Sub channels.
   An active channel is one that has at least one subscriber; clients subscribed only to patterns do not count.
   If no pattern is specified, all active channels are returned.
   If a pattern is specified, only channels whose names match the given glob-style pattern are returned.
   In a Redis Cluster, the reply reflects only the local node Pub/Sub context rather than the entire cluster.

   ```
   PUBSUB CHANNELS [pattern]
   ```

   pattern: list only active channels whose names match the given glob-style pattern, if omitted all active channels are listed.

   Array reply: a list of active channels, optionally matching the specified pattern."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn pubsub-numpat!
  "Returns the number of unique patterns that clients have subscribed to via PSUBSCRIBE.
   This is the total count of distinct patterns across all clients, not the number of clients subscribed to patterns.
   In a Redis Cluster, the reply only reflects the Pub/Sub context of the local node, not the entire cluster.

   ```
   PUBSUB NUMPAT
   ```

   Integer reply: the number of patterns all the clients are subscribed to."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn pubsub-numsub!
  "Returns the number of subscribers for the specified channels.
   Clients subscribed to patterns are not counted.
   It is valid to call this command without any channels, in which case an empty list is returned.
   In a Redis Cluster, the reply only reflects the Pub/Sub context of the local node, not the entire cluster.

   ```
   PUBSUB NUMSUB [channel [channel ...]]
   ```

   channel [channel ...]: one or more channels to report the subscriber count for.

   Array reply: the number of subscribers per channel, each even element (including the 0th) is channel name, each odd element is the number of subscribers."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn pubsub-shardchannels!
  "Lists the currently active shard channels.
   An active shard channel is a Pub/Sub shard channel that has one or more subscribers.
   If no pattern is specified, all active shard channels are listed;
   otherwise only those matching the given glob-style pattern are returned.
   The information returned is at the shard level, not at the cluster level.

   ```
   PUBSUB SHARDCHANNELS [pattern]
   ```

   pattern: list only active shard channels whose names match the given glob-style pattern,
   if omitted all active shard channels are listed.

   Array reply: a list of active channels, optionally matching the specified pattern."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn pubsub-shardnumsub!
  "Returns the number of subscribers for the specified shard channels.
   It is valid to call this command without any channels, in which case it returns an empty list.
   In a Redis Cluster, the replies only report information from the node's Pub/Sub context,
   rather than the entire cluster.

   ```
   PUBSUB SHARDNUMSUB [shardchannel [shardchannel ...]]
   ```

   shardchannel [shardchannel ...]: one or more shard channels to report the subscriber count for.

   Array reply: the number of subscribers per shard channel, each even element (including the 0th)
   is channel name, each odd element is the number of subscribers."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn punsubscribe!
  "Unsubscribes the client from the specified patterns.
   If no patterns are specified, the client is unsubscribed from all previously subscribed patterns.
   For each pattern the client unsubscribes from, a confirmation message is pushed to the client.

   ```
   PUNSUBSCRIBE [pattern [pattern ...]]
   ```

   pattern [pattern ...]: one or more patterns to unsubscribe from,
     if omitted the client is unsubscribed from all patterns.

   When successful, this command does not return anything.
   Instead, for each pattern, one message with the first element being
     the string punsubscribe is pushed as a confirmation that the command succeeded."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn spublish!
  "Posts a message to the given shard channel.
   In a Redis Cluster, shard channels are assigned to slots using the same hashing algorithm as keys.
   The message must be sent to the node that owns the slot the shard channel is hashed to.
   The cluster forwards the published shard message to all nodes in the shard,
   so a client can subscribe to a shard channel by connecting to any node in that shard.

   ```
   SPUBLISH shardchannel message
   ```

   shardchannel: the shard channel to publish to.
   message: the message to publish.

   Integer reply: the number of clients that the message was sent to.
   Note that in a Redis Cluster, only clients that are connected to the same node as the publishing client are included in the count."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ssubscribe!
  "Subscribes the client to the specified shard channels.
   In Redis Cluster, shard channels are assigned to slots using the same algorithm Redis uses to assign keys to slots.
   To receive messages published to a shard channel, subscribe to a node, either a primary or replica, that serves the channel slot.
   All shard channels in a single SSUBSCRIBE call must belong to the same slot.
   To subscribe to shard channels across different slots, use separate SSUBSCRIBE calls.

   ```
   SSUBSCRIBE shardchannel [shardchannel ...]
   ```

   shardchannel [shardchannel ...]: one or more shard channels to subscribe to.

   When successful, this command does not return anything.
   Instead, for each shard channel, one message with the first element being the string ssubscribe is pushed as a confirmation that the command succeeded.
   Note that this command can also return a -MOVED redirect."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn subscribe!
  "Subscribes the client to one or more channels.
   Once the client enters the subscribed state, it may only issue additional SUBSCRIBE, SSUBSCRIBE, PSUBSCRIBE, UNSUBSCRIBE, SUNSUBSCRIBE, PUNSUBSCRIBE, PING, RESET, and QUIT commands.
   When the extended protocol is in use, a subscribed client may issue any command while in the subscribed state.

   ```
   SUBSCRIBE channel [channel ...]
   ```

   channel: one or more channels to subscribe to.

   When successful, this command does not return anything.
   Instead, for each channel, one message with the first element being the string subscribe is pushed as a confirmation that the command succeeded."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn sunsubscribe!
  "Unsubscribes the client from the specified shard channels.
   If no shard channels are specified, the client is unsubscribed from all previously subscribed shard channels.
   The client receives one message for each shard channel it unsubscribes from.
   Global channels and shard channels must be unsubscribed from separately.

   ```
   SUNSUBSCRIBE [shardchannel [shardchannel ...]]
   ```

   shardchannel [shardchannel ...]: one or more shard channels to unsubscribe from, if omitted the client is unsubscribed from all shard channels.

   When successful, this command does not return anything.
   Instead, for each shard channel, one message with the first element being the string sunsubscribe is pushed as a confirmation that the command succeeded."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn unsubscribe!
  "Unsubscribes the client from the specified channels.
   If no channels are specified, the client is unsubscribed from all previously subscribed channels.
   The client receives one push message for each channel it unsubscribes from.

   ```
   UNSUBSCRIBE [channel [channel ...]]
   ```

   channel [channel ...]: one or more channels to unsubscribe from, if omitted the client is unsubscribed from all channels.

   When successful, this command does not return anything.
   Instead, for each channel, one message with the first element being the string unsubscribe is pushed as a confirmation that the command succeeded."
  [_state _msg]
  (throw (Exception. "Not Implemented")))
