(ns redis.cmd.cluster)

(defn asking!
  "Sent to the target node immediately before a command that was redirected via an -ASK redirect.
   This is normally handled automatically by cluster clients.
   If an -ASK redirect is received during a transaction, a single ASKING command is sufficient before sending the complete transaction to the target node.

   ```
   ASKING
   ```

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-addslots!
  "Assigns a set of hash slots to the node receiving the command.
   If the command is successful, the node maps the specified hash slots to itself and starts broadcasting the new configuration.
   An error is returned if the command cannot be completed.

   ```
   CLUSTER ADDSLOTS slot [slot ...]
   ```

   slot [slot ...]: one or more hash slots to assign to the current node.

   Simple string reply: OK if the command was successful.
   Otherwise an error is returned."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-addslotsrange!
  "Assigns one or more inclusive hash slot ranges to the current node.
   It is similar to CLUSTER ADDSLOTS, but instead of listing individual slots it accepts ranges specified by a start and an end slot.

   ```
   CLUSTER ADDSLOTSRANGE start-slot end-slot [start-slot end-slot ...]
   ```

   start-slot end-slot [start-slot end-slot ...]: one or more inclusive ranges of hash slots to assign to the current node.

   Simple string reply: OK if the command was successful.
   Otherwise an error is returned."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-bumpepoch!
  "Advances the cluster config epoch on the connected node.
   The epoch is incremented if the node's config epoch is zero, or if it is less than the cluster's greatest epoch.
   Config epoch management is normally performed internally by the cluster and relies on obtaining a consensus of nodes.
   This command attempts to increment the config epoch without getting that consensus, so using it may violate the last failover wins rule.
   Use it with caution.

   ```
   CLUSTER BUMPEPOCH
   ```

   Bulk string reply: BUMPED if the epoch was incremented.
   Bulk string reply: STILL if the node already has the greatest configured epoch in the cluster."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-count-failure-reports!
  "Returns the number of active failure reports for the specified node.
   In Redis Cluster, failure reports are used to promote a node from PFAIL to FAIL.
   PFAIL means the node is unreachable from one node's point of view, while FAIL means a majority of primary nodes agreed within a time window that the node is unreachable.
   Only reports that have not expired are counted; a report expires after twice the node timeout.
   The count includes only reports received from other nodes and does not include the receiving node's own view of the specified node.
   This command is useful for debugging cluster failure detection when it does not behave as expected.

   ```
   CLUSTER COUNT-FAILURE-REPORTS node-id
   ```

   node-id: the ID of the node to count failure reports for.

   Integer reply: the number of active failure reports for the node."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-countkeysinslot!
  "Returns the number of keys stored in the specified Redis Cluster hash slot.
   The command only queries the local data set, so contacting a node that is not
   serving the specified hash slot will always result in a count of zero.
   During atomic slot migration operations, keys being imported or trimmed are
   filtered out from the results.

   ```
   CLUSTER COUNTKEYSINSLOT slot
   ```

   slot: the hash slot to count the keys in.

   Integer reply: the number of keys in the specified hash slot, or an error if the hash slot is invalid."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-delslots!
  "In Redis Cluster, each node tracks which master is responsible for a given hash slot.
   CLUSTER DELSLOTS instructs the current node to forget the master assignment for one or more specified hash slots.

   ```
   CLUSTER DELSLOTS slot [slot ...]
   ```

   slot [slot ...]: one or more hash slots to unassign from the current node.

   Simple string reply: OK if the command was successful.
   Otherwise an error is returned."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-delslotsrange!
  "Removes one or more inclusive hash slot ranges from the current node.
   Each range is specified by a start slot and an end slot, both inclusive.
   Multiple ranges can be provided in a single call.

   ```
   CLUSTER DELSLOTSRANGE start-slot end-slot [start-slot end-slot ...]
   ```

   start-slot end-slot [start-slot end-slot ...]: one or more inclusive ranges of hash slots to unassign from the current node.

   Simple string reply: OK if the command was successful.
   Otherwise an error is returned."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-failover!
  "Forces a replica node to initiate a manual failover of its primary instance.
   The command can only be sent to a node that is currently a replica; sending it to a primary will result in an error.
   The failover is performed safely: the replica waits until it has processed the full replication stream from the primary before it takes over, and clients are moved to the new primary only after that point.
   This is useful when you want to replace the current primary with one of its replicas even though the primary has not failed.

   ```
   CLUSTER FAILOVER [FORCE | TAKEOVER]
   ```

   FORCE | TAKEOVER: FORCE starts the failover without coordinating with the primary, for use when the primary is unreachable. TAKEOVER additionally bypasses the cluster consensus needed to authorize the failover.

   Simple string reply: OK if the command was accepted and a manual failover is going to be attempted.
   An error if the operation cannot be executed, for example if the client is connected to a node that is already a primary."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-flushslots!
  "Deletes all slot information from the connected node.
   It can only be called when the database is empty.

   ```
   CLUSTER FLUSHSLOTS
   ```

   Simple string reply: OK"
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-forget!
  "Removes the specified node from the set of known nodes in the cluster node that receives the command.
   The node is dropped from the internal nodes table of the receiving node.
   To fully remove a node from the cluster, the command must be sent to every remaining node, whether master or replica.
   The command also adds the node to a ban list so that it cannot be re-added as a side effect of processing gossip heartbeat packets from other nodes.
   The ban list is propagated through cluster gossip ping and pong messages, so in most cases sending the command to one or more nodes is enough for it to reach the rest of the cluster.

   ```
   CLUSTER FORGET node-id
   ```

   node-id: the ID of the node to remove from the current node's view of the cluster.

   Simple string reply: OK if the command was executed successfully.
   Otherwise an error is returned."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-getkeysinslot!
  "Returns key names stored on the contacted node that hash to the specified slot.
   The count argument limits the number of keys returned, enabling batch processing.
   This command is mainly used when resharding cluster slots from one node to another.
   During atomic slot migration operations, keys being imported or trimmed are filtered out from the results.

   ```
   CLUSTER GETKEYSINSLOT slot count
   ```

   slot: the hash slot to return key names from.
   count: the maximum number of key names to return.

   Array reply: an array with up to count elements."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-info!
  "Provides INFO-style information about the vital parameters of a Redis Cluster node.
   The reply always includes a set of core fields describing the cluster state.
   Atomic slot migration fields may also be present in the reply.
   Message-related fields are included only when their value is not zero; each message type reports the number of messages sent and received.

   ```
   CLUSTER INFO
   ```

   Bulk string reply: A map between named fields and values in the form of <field>:<value> lines separated by newlines composed by the two bytes CRLF.
   Bulk string reply: A map between named fields and values in the form of : lines separated by newlines composed by the two bytes CRLF."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-keyslot!
  "Returns the hash slot number for the given key.
   The command applies the full Redis Cluster hashing algorithm, including hash tag support.
   If the key contains a valid hash tag, only the substring between the opening and closing curly braces is hashed.
   Hash tags can be used to force multiple keys into the same slot so they are handled by the same node.

   ```
   CLUSTER KEYSLOT key
   ```

   key: the key name to compute the hash slot for.

   Integer reply: The hash slot number for the specified key"
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-links!
  "Each node in a Redis Cluster maintains a pair of long-lived TCP links with every peer:
   one for sending outbound messages and one for receiving inbound messages.
   CLUSTER LINKS returns information about all such peer links.
   Each link is represented as a map containing its attributes and their values.

   ```
   CLUSTER LINKS
   ```

   Array reply: an array of Map reply where each map contains various attributes
   and their values of a cluster link."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-meet!
  "CLUSTER MEET connects a given node to the local node, adding it to the cluster.
   By default, nodes do not trust each other and are considered unknown, so this command is the mechanism by which a node is explicitly accepted into the cluster.
   The cluster requires a full mesh topology, but you do not need to issue CLUSTER MEET for every pair of nodes.
   Sending enough CLUSTER MEET commands so that every node is reachable through a chain of known nodes is sufficient, because gossip information exchanged in heartbeat packets will fill in the remaining links automatically.
   The command is not reciprocal: sending CLUSTER MEET from node A to node B does not require a matching command from B to A.
   If the optional cluster-bus-port is omitted, it defaults to the client port number plus 10,000.

   ```
   CLUSTER MEET ip port [cluster-bus-port]
   ```

   ip: the IP address of the node to add to the cluster.
   port: the client port of the node to add.
   cluster-bus-port: the cluster bus port of the node, defaults to the client port number plus 10,000 if omitted.

   Simple string reply: OK if the command was successful.
   Error reply: if the address or port specified are invalid."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-migration!
  "CLUSTER MIGRATION provides atomic slot migration functionality for Redis Cluster.
   It allows importing slots from other nodes, monitoring the progress of migration tasks, and canceling ongoing migrations.
   The IMPORT subcommand is executed on the destination master and accepts one or more slot ranges to migrate atomically, returning a task ID for tracking.
   The CANCEL subcommand cancels an ongoing migration task by its ID, or all tasks if ALL is specified.
   Cancelling a task on the source node does not stop the migration on the destination node, which will continue retrying until it is also cancelled there.
   The STATUS subcommand displays the status of current and completed atomic slot migration tasks, either for a specific task ID or for all tasks.

   ```
   CLUSTER MIGRATION <IMPORT start-slot end-slot
     [start-slot end-slot ...] | CANCEL <ID task-id | ALL> |
     STATUS <[ID task-id] | [ALL]>>
   ```

   subcommand: the operation to perform, one of IMPORT, CANCEL, or STATUS.
   IMPORT accepts one or more slot ranges and triggers atomic migration on the destination master, returning a task ID.
   CANCEL cancels a task by ID or all tasks if ALL is given.
   STATUS displays the status of current and completed migration tasks, optionally filtered by task ID.

   For the IMPORT subcommand: Bulk string reply: task ID on success, or error message on failure.
   For the CANCEL subcommand: Integer reply: number of cancelled tasks.
   For the STATUS subcommand: Array reply: a list of migration task details.
   Each task is represented as an array containing field-value pairs:
   id: Task identifier
   slots: Slot range being migrated
   source: Source node ID
   dest: Destination node ID
   operation: Operation type (typically migrate)
   state: Current state (completed, in_progress, etc.)
   last_error: Last error message (empty if none)
   retries: Number of retry attempts
   create_time: Task creation timestamp
   start_time: Task start timestamp
   end_time: Task completion timestamp (if completed)
   write_pause_ms: Write pause duration in milliseconds"
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-myid!
  "Returns the unique, auto-generated identifier
   of the connected cluster node.

   ```
   CLUSTER MYID
   ```

   Bulk string reply: the node ID."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-myshardid!
  "Returns the unique, auto-generated shard ID of the shard to which the connected cluster node belongs.

   ```
   CLUSTER MYSHARDID
   ```

   Bulk string reply: the node's shard ID."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-nodes!
  "Returns the current cluster configuration as seen by the node being contacted.
   The configuration includes the set of known nodes, the state of connections to them, their flags, properties, and assigned hash slots.
   The output uses the same serialization format that Redis Cluster uses to persist cluster state on disk, though the on-disk version appends a few extra fields.
   For clients that only need the mapping between hash slots and node addresses, CLUSTER SHARDS is the preferred command.
   CLUSTER NODES is intended for administrative tasks, debugging, and configuration inspection.

   ```
   CLUSTER NODES
   ```

   Bulk string reply: the serialized cluster configuration."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-replicas!
  "Returns the list of replica nodes that are replicating from the specified master node.
   The list is provided in the same format used by CLUSTER NODES.
   The command fails if the specified node is not known to the receiving node, or if it is not a master according to the node table of that node.
   If a replica has been recently added, moved, or removed and the configuration update has not yet propagated to the queried node, the result may be stale; all nodes will eventually agree on the set of replicas for a given master.

   ```
   CLUSTER REPLICAS node-id
   ```

   node-id: the ID of the master node to list the replicas of.

   Array reply: a list of replica nodes replicating from the specified master node provided in the same format used by CLUSTER NODES."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-replicate!
  "Reconfigures the current node as a replica of the specified master node.
   If the node receiving the command is an empty master, its role is changed from master to replica as a side effect.
   Once the node becomes a replica, heartbeat packets exchanged between cluster nodes propagate the new configuration automatically, so there is no need to inform other nodes explicitly.
   A replica will always accept the command.
   If the node is a master, the command succeeds and converts it into a replica only if additional conditions are met.
   On success, the new replica immediately attempts to contact its master in order to begin replication.

   ```
   CLUSTER REPLICATE node-id
   ```

   node-id: the ID of the master node that the current node should replicate.

   Simple string reply: OK if the command was successful.
   Otherwise an error is returned."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-reset!
  "Resets a Redis Cluster node.
   SOFT, the default, resets the node but keeps its existing node ID.
   HARD also assigns a new node ID and clears additional state.
   Before resetting a primary node, all keys must be removed from it, for example with FLUSHALL;
   Redis will not reset a primary node that still contains keys.
   The command is mainly useful for reprovisioning a node so it can be used in a new, different cluster.

   ```
   CLUSTER RESET [HARD | SOFT]
   ```

   HARD | SOFT: SOFT (the default) resets the node but keeps its node ID;
   HARD also assigns a new node ID and clears additional state.

   Simple string reply: OK if the command was successful.
   Otherwise an error is returned."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-saveconfig!
  "Forces the node to save its nodes.conf configuration file to disk.
   Before returning, the command calls fsync(2) to guarantee the configuration is flushed to the physical disk.
   This is useful when the nodes.conf file has been lost or deleted and needs to be regenerated from the in-memory state.
   It can also be used to persist configuration changes made through other CLUSTER commands, although those commands normally schedule persistence automatically when it is important for correctness across restarts.

   ```
   CLUSTER SAVECONFIG
   ```

   Simple string reply: OK if the command was successful.
   Otherwise an error is returned."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-set-config-epoch!
  "Sets a specific configuration epoch on the current node.
   This command only works when the node is in a fresh state.
   Manually altering the configuration epoch is normally unsafe because the node with the higher epoch value wins when claiming hash slot ownership during failover.
   The main use case is when creating a new cluster from scratch, where you can assign a different progressive configuration epoch to each node before joining them together, avoiding the slow collision resolution process that occurs when all nodes start with the same epoch.

   ```
   CLUSTER SET-CONFIG-EPOCH config-epoch
   ```

   config-epoch: the configuration epoch to set for the current node.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-setslot!
  "CLUSTER SETSLOT changes the state of a hash slot in the receiving node.
   It is used to start and end cluster live resharding operations by setting a hash slot to migrating state in the source node and importing state in the destination node.
   The subcommand determines the effect: IMPORTING marks the slot as being imported from the given node, MIGRATING marks it as being migrated to the given node, NODE assigns the slot to the given node, and STABLE clears any importing or migrating state.

   ```
   CLUSTER SETSLOT slot <IMPORTING node-id | MIGRATING node-id |
     NODE node-id | STABLE>
   ```

   slot: the hash slot to configure.
   IMPORTING node-id | MIGRATING node-id | NODE node-id | STABLE: the slot state to set, IMPORTING to import the slot from the given node, MIGRATING to migrate it to the given node, NODE to assign the slot to the given node, or STABLE to clear any importing or migrating state.

   Simple string reply: all the sub-commands return OK if the command was successful.
   Otherwise an error is returned."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-shards!
  "CLUSTER SHARDS returns details about the shards of the cluster.
   A shard is a collection of nodes that serve the same set of slots and replicate from each other.
   A shard has at most one master at a time, but may have multiple or zero replicas.
   A shard can exist without serving any slots while still having replicas.
   The command returns an array of shards, each containing two fields: slots and nodes.
   The slots field is a list of slot ranges, stored as pairs of integers representing inclusive start and end slots.
   The nodes field contains a list of all nodes in the shard, where each node is a map of attributes.
   Some attributes are optional and more may be added in the future.
   A NULL endpoint means the node has an unknown endpoint and the client should connect to the same endpoint it used to send the command, but with the port returned by the command.
   An empty string for the endpoint or ip field means the node does not know its own address, which can happen in a single-node cluster or before a node has joined the cluster.
   The value ? is returned if the node is configured to use announced hostnames but no hostname is set.
   Clients may treat an empty string the same as NULL, using the current endpoint, while ? should be treated as an unknown node not necessarily the same one serving the current command.

   ```
   CLUSTER SHARDS
   ```

   Array reply: a nested list of Map reply of hash ranges and shard nodes describing individual shards."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-slaves!
  "Lists the replica nodes that are replicating from the specified master node.
   The result is provided in the same format used by CLUSTER NODES.
   The command fails if the specified node is not known to the node receiving the command, or if that node is not a master according to the local node table.
   If a replica has been added, moved, or removed and the configuration update has not yet propagated to the queried node, the result may be stale; all nodes converge within seconds absent network partitions.
   This command is deprecated; use CLUSTER REPLICAS instead.

   ```
   CLUSTER SLAVES node-id
   ```

   node-id: the ID of the master node to list the replicas of.

   Array reply: a list of replica nodes replicating from the specified master node provided in the same format used by CLUSTER NODES."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-slot-stats!
  "Retrieves slot usage statistics for the slots assigned to the current shard.
   The results can be filtered to a specific range of slots or sorted by a chosen metric.
   This is useful for understanding overall slot usage, identifying hot or cold slots,
   planning slot migrations to balance load, or refining key distribution logic.
   All metrics except KEY-COUNT require that cluster-slot-stats-enabled
   is set to yes in the configuration.

   ```
   CLUSTER SLOT-STATS <SLOTSRANGE start-slot end-slot | ORDERBY metric
     [LIMIT limit] [ASC | DESC]>
   ```

   SLOTSRANGE start-slot end-slot: limits results to a specific inclusive range of slots,
   always sorted by slot number in ascending order.
   ORDERBY metric: sorts the slot statistics by the specified metric,
   using slot number as an ascending tiebreaker.
   LIMIT limit: limits the number of results returned when using ORDERBY.
   ASC | DESC: specifies ascending or descending sort order when using ORDERBY.
   metric: one of KEY-COUNT, CPU-USEC, MEMORY-BYTES, NETWORK-BYTES-IN,
   or NETWORK-BYTES-OUT.

   Array reply: a nested list of slot usage statistics.
   Simple error otherwise."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn cluster-slots!
  "Returns details about which cluster slots map to which Redis instances.
   This command is intended for client libraries to retrieve or update the mapping of cluster hash slots to node network information, so that commands can be routed to the correct instance for the specified keys.
   For each node, the preferred endpoint and port define where clients should send requests for a given slot.
   A NULL endpoint means the node has an unknown endpoint and the client should connect to the same endpoint used to send this command, but with the returned port.
   An empty string for the endpoint or IP field indicates the node does not know its own address, which can occur in a single-node cluster or when a node has not yet joined the cluster.
   The value ? is returned if the node is configured to use announced hostnames but no hostname is set.
   Clients may treat an empty string the same as NULL, while ? should be treated as an unknown node, not necessarily the same node serving the current command.
   Additional networking metadata is provided as a map on the fourth argument for each node.

   ```
   CLUSTER SLOTS
   ```

   Array reply: nested list of slot ranges with networking information."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn readonly!
  "Enables read queries on a connection to a Redis Cluster replica node.
   Normally a replica node redirects the client to the authoritative master for the hash slot involved in a given command.
   READONLY tells the replica that the client is willing to accept possibly stale data and does not intend to issue write queries.
   While the connection is in readonly mode, the cluster sends a redirection only when the operation involves keys that are not served by the replica master node.

   ```
   READONLY
   ```

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn readwrite!
  "Disables read queries for a connection to a Redis Cluster replica node.
   Read queries against a Redis Cluster replica node are disabled by default.
   The READONLY command can change this behavior on a per-connection basis.
   READWRITE resets the readonly mode flag of a connection back to readwrite.

   ```
   READWRITE
   ```

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))
