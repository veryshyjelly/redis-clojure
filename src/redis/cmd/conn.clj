(ns redis.cmd.conn)

(defn auth!
  "AUTH authenticates the current connection.
   It accepts either a single password or a username and password pair.
   When only a password is provided, it is checked against the password set by requirepass.
   When a username and password are provided, they are checked against the ACL user list.
   If the credentials are valid, the server replies OK and begins accepting commands from the connection.
   If the credentials are invalid, an error is returned and the client must retry with a different password.
   In ACL mode, if only a password is given, the username is assumed to be default.

   ```
   AUTH [username] password
   ```

   password: the password to authenticate with, matching the requirepass setting or the ACL user password.
   username: the ACL username to authenticate as, defaults to the default user if omitted.

   Simple string reply: OK, or an error if the password, or username/password pair, is invalid."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn client
  "Dispatcher for client commands."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn client-caching!
  "Controls whether the keys accessed by the next command on this connection are tracked for client-side caching invalidation.
   This command is only meaningful when client tracking has been enabled in OPTIN or OPTOUT mode via CLIENT TRACKING.
   In OPTIN mode, keys are not tracked by default; issuing CLIENT CACHING YES before a command enables tracking for that single command.
   In OPTOUT mode, keys are tracked by default; issuing CLIENT CACHING NO before a command suppresses tracking for that single command.
   The state is set on the connection and applies only to the immediately following command execution.

   ```
   CLIENT CACHING <YES | NO>
   ```

   YES | NO: whether to cache keys for the next command (YES) or not (NO) when tracking is in OPTIN or OPTOUT mode.

   Simple string reply: OK or an error if the argument is not yes or no."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn client-getname!
  "Returns the name of the current connection as set by CLIENT SETNAME.
   If no name was assigned to the connection, nil is returned.

   ```
   CLIENT GETNAME
   ```

   Bulk string reply: the connection name of the current connection.
   Null reply: the connection name was not set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn client-getredir!
  "Returns the client ID to which tracking notifications are being redirected.
   This redirection is set when CLIENT TRACKING is used to enable tracking.
   The command lets client libraries check later whether redirection is active and towards which client ID, without having to remember the ID themselves.

   ```
   CLIENT GETREDIR
   ```

   Integer reply: 0 when not redirecting notifications to any client.
   Integer reply: -1 if client tracking is not enabled.
   Integer reply: the ID of the client to which notification are being redirected."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn client-id!
  "Returns the ID of the current connection.
   Every connection is assigned a unique ID when it is established.
   This command is especially useful in combination with CLIENT UNBLOCK,
   which allows unblocking a specific client by its ID.

   ```
   CLIENT ID
   ```

   Integer reply: the ID of the client."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn client-info!
  "Returns information and statistics about the current client connection in a mostly human readable format.
   The reply format is identical to that of CLIENT LIST, and the content consists only of information about the current client.

   ```
   CLIENT INFO
   ```

   Bulk string reply: a unique string for the current client, as described at the CLIENT LIST page."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn client-kill!
  "Closes a given client connection.
   The command supports two formats: a legacy form that takes an ip:port pair
   matching the addr field from CLIENT LIST, and a filter-based form that allows
   selecting clients by various attributes.
   In the filter-based form, multiple filters can be combined and are evaluated
   with logical AND.
   When the legacy ip:port form is used, the command returns OK on success or an
   error if no matching client is found.
   When the filter-based form is used, the command returns the number of clients
   killed, which may be zero.

   ```
   CLIENT KILL <ip:port | <[ID client-id] | [TYPE <NORMAL | MASTER |
     SLAVE | REPLICA | PUBSUB>] | [USER username] | [ADDR ip:port] |
     [LADDR ip:port] | [SKIPME <YES | NO>] | [MAXAGE maxage]
     [[ID client-id] | [TYPE <NORMAL | MASTER | SLAVE | REPLICA |
     PUBSUB>] | [USER username] | [ADDR ip:port] | [LADDR ip:port] |
     [SKIPME <YES | NO>] | [MAXAGE maxage] ...]>>
   ```

   ip:port: close the client at the given address, the legacy single-argument form.
   ID client-id: close only the client with the given unique ID.
   TYPE NORMAL | MASTER | SLAVE | REPLICA | PUBSUB: close only clients of the given type.
   USER username: close only clients authenticated as the given ACL user.
   ADDR ip:port: close only clients connected from the given address.
   LADDR ip:port: close only clients connected to the given local (server) address.
   SKIPME YES | NO: whether to skip the calling client (YES, the default) or allow it to be closed too (NO).
   MAXAGE maxage: close only clients older than the given age, in seconds.

   Simple string reply: OK when called in 3 argument format and the connection has been closed.
   Integer reply: when called in filter/value format, the number of clients killed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn client-list!
  "Returns information and statistics about the client connections to the server
   in a mostly human readable format.
   The list can be filtered by client type using the TYPE subcommand,
   where type is one of normal, master, replica, or pubsub.
   Clients blocked by the MONITOR command belong to the normal class.
   The ID subcommand restricts the list to only those clients whose IDs match
   the provided client-id arguments.

   ```
   CLIENT LIST [TYPE <NORMAL | MASTER | REPLICA | PUBSUB>]
     [ID client-id [client-id ...]]
   ```

   TYPE NORMAL | MASTER | REPLICA | PUBSUB: list only clients of the given type.
   ID client-id [client-id ...]: list only the clients with the given IDs.

   Bulk string reply: information and statistics about client connections."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn client-no-evict!
  "Sets the client eviction mode for the current connection.
   When turned on and client eviction is configured, the current connection is excluded from the client eviction process even if the configured client eviction threshold is exceeded.
   When turned off, the current client is re-included in the pool of potential clients to be evicted.

   ```
   CLIENT NO-EVICT <ON | OFF>
   ```

   ON | OFF: turn eviction protection on (ON) or off (OFF) for the current connection.

   Simple string reply: OK if the command was successful.
   Simple error reply in these cases: wrong number of or invalid arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn client-no-touch!
  "Controls whether commands issued by the current client connection
   will update the LRU/LFU statistics of the keys they access.
   When set to ON, the client will not modify the last-access time
   or LFU counter of any key it touches, unless it explicitly sends
   the TOUCH command.
   When set to OFF, the client updates LRU/LFU statistics in the
   normal way, just like any other client.

   ```
   CLIENT NO-TOUCH <ON | OFF>
   ```

   ON | OFF: turn off (ON) or on (OFF) updates to keys last-access
   time and LFU counter for commands issued by this connection.

   Simple string reply: OK if the command was successful.
   Simple error reply in these cases: wrong number of or invalid arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn client-pause!
  "CLIENT PAUSE suspends all connected Redis clients for a specified duration in milliseconds.
   During the pause, clients are blocked from executing commands according to the selected mode.
   The default mode is ALL, which blocks every command a client attempts to run.
   The WRITE mode blocks only write commands, allowing read commands to proceed.
   If the timeout value is invalid, the command returns an error.

   ```
   CLIENT PAUSE timeout [WRITE | ALL]
   ```

   timeout: how long to pause clients, in milliseconds.
   WRITE | ALL: ALL (default) blocks all client commands; WRITE blocks only write commands.

   Simple string reply: OK or an error if the timeout is invalid."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn client-reply!
  "Controls whether the server sends replies back to the client for its commands.
   This is useful in fire-and-forget workloads, mass data loading, or streaming cache contexts where replies would be ignored and sending them wastes server time and bandwidth.
   The command accepts one of three modes that change how the connection handles replies.

   ```
   CLIENT REPLY <ON | OFF | SKIP>
   ```

   ON: the default mode, the server returns a reply to every command.
   OFF: the server will not reply to client commands.
   SKIP: skips the reply of the command immediately after it.

   Simple string reply: OK when called with ON.
   When called with either OFF or SKIP sub-commands, no reply is made."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn client-setinfo!
  "Assigns an info attribute to the current connection.
   The attribute is then visible in the output of CLIENT LIST and CLIENT INFO.
   Client libraries are expected to pipeline this command after authentication on all connections and ignore any failures.

   ```
   CLIENT SETINFO <LIB-NAME libname | LIB-VER libver>
   ```

   LIB-NAME libname: set the library name for the current connection.
   LIB-VER libver: set the library version for the current connection.

   Simple string reply: OK if the attribute name was successfully set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn client-setname!
  "Assigns a name to the current client connection.

   ```
   CLIENT SETNAME connection-name
   ```

   connection-name: the name to assign to the current connection.

   Simple string reply: OK if the connection name was successfully set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn client-tracking!
  "Enables or disables the server-assisted client-side caching tracking feature for the current connection.
   When tracking is on, Redis remembers the keys that the connection has requested and sends invalidation messages when those keys are later modified.
   Invalidation messages are sent on the same connection when using the RESP3 protocol, or can be redirected to a different connection via Pub/Sub.
   Broadcasting mode allows receiving invalidation messages for all keys matching specified prefixes, regardless of which keys the connection actually reads.
   Tracking remains active for the lifetime of the connection unless explicitly turned off with CLIENT TRACKING OFF.

   ```
   CLIENT TRACKING <ON | OFF> [REDIRECT client-id] [PREFIX prefix
     [PREFIX prefix ...]] [BCAST] [OPTIN] [OPTOUT] [NOLOOP]
   ```

   ON | OFF: turn key-invalidation tracking on or off for the connection.
   REDIRECT client-id: send invalidation messages to the connection with the specified ID, which must exist and can be obtained via CLIENT ID; if the redirected connection terminates while in RESP3 mode, the tracking connection receives tracking-redir-broken push messages.
   PREFIX prefix [PREFIX prefix ...]: track only keys that start with one of the specified prefixes, used with BCAST; can be specified multiple times for multiple prefixes; if broadcasting is enabled without this option, invalidation messages are sent for every key; individual prefixes cannot be removed, so to change the set, disable and re-enable tracking; adds O(N^2) time complexity where N is the total number of tracked prefixes.
   BCAST: enable broadcasting mode so that Redis sends invalidation messages for all keys or for keys matching a specified PREFIX, instead of only the keys the connection reads; without broadcasting, Redis tracks keys fetched by read-only commands and sends invalidation messages only for those.
   OPTIN: track keys only for read commands that immediately follow a CLIENT CACHING YES.
   OPTOUT: track all read keys except those for commands that immediately follow a CLIENT CACHING NO.
   NOLOOP: do not send invalidation messages for keys modified by this connection; in the default tracking mode, modifying a tracked key removes it from the invalidation table even when NOLOOP suppresses the message, so to track the key for future invalidations, read the key again.

   Simple string reply: OK if the connection was successfully put in tracking mode or if the tracking mode was successfully disabled.
   Error reply: otherwise."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn client-trackinginfo!
  "Returns information about the current client connection's use of the server assisted client side caching feature.

   ```
   CLIENT TRACKINGINFO
   ```

   Map reply: a list of tracking information sections and their respective values."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn client-unblock!
  "Unblocks a client that is currently blocked by a blocking operation such as BRPOP, XREAD, or WAIT.
   By default the unblocked client receives a reply as if its blocking timeout had expired.
   If the ERROR option is specified instead, the client receives an error reply carrying the code -UNBLOCKED.

   ```
   CLIENT UNBLOCK client-id [TIMEOUT | ERROR]
   ```

   client-id: the ID of the client to unblock.
   TIMEOUT | ERROR: how to unblock the client, as if its timeout expired (TIMEOUT, the default) or by returning an error (ERROR).

   Integer reply: 1 if the client was unblocked successfully.
   Integer reply: 0 if the client was not unblocked."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn client-unpause!
  "Resumes command processing for all clients that were previously paused by CLIENT PAUSE.
   Once unpaused, the server continues executing queued commands for those clients.

   ```
   CLIENT UNPAUSE
   ```

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn echo
  "ECHO returns the given message back to the client.
   It is a simple round-trip command with no side effects on the server state.

   ```
   ECHO message
   ```

   message: the message to echo back.

   Bulk string reply: the given string."
  [_state msg]
  (cond-> (second msg) keyword? name))

(defn hello!
  "Switches the connection to a different RESP protocol version, optionally authenticates the connection, and sets the connection name.
   When called with no arguments, or with a protocol version and optional authentication, the command also returns server and connection properties.
   Connections start in RESP2 mode by default, so existing RESP2 clients are unaffected.
   The protocol version must be 2 or 3; requesting any other value produces an error.

   ```
   HELLO [protover [AUTH username password] [SETNAME clientname]]
   ```

   protover: the RESP protocol version to switch to (2 or 3), required if any other option is given.
   AUTH username password: authenticate the connection in addition to switching protocol version, making a separate AUTH call unnecessary; username can be set to default to authenticate against a server using the requirepass mechanism.
   SETNAME clientname: set the name of the current connection, equivalent to calling CLIENT SETNAME.

   Map reply: a list of server properties.
   Simple error reply: if the protover requested does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ping
  "Returns the bulk string PONG if no argument is provided,
   otherwise it returns a copy of the provided message as a bulk string.
   If the client is subscribed to a channel or a pattern,
   it instead returns a multi-bulk string with PONG in the first position
   and an empty bulk string in the second position,
   unless an argument is provided,
   in which case it returns a copy of the argument in the first position.

   ```
   PING [message]
   ```

   message: a message to return instead of PONG.

   Simple string reply: PONG when no argument is provided.
   Bulk string reply: the provided argument."
  [_state _msg] :PONG)

(defn quit!
  "Asks the server to close the connection.
   The connection is closed as soon as all pending replies have been written to the client.
   Clients should prefer closing the connection on their own side,
   as terminating from the client eliminates TIME_WAIT lingering sockets on the server.

   ```
   QUIT
   ```

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn conn-reset!
  "Performs a full reset of the connection's server-side context.
   This mimics the effect of disconnecting and reconnecting the client.

   ```
   RESET
   ```

   Simple string reply: RESET."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn select!
  "Selects the logical Redis database identified by the given zero-based numeric index for the current connection.
   New connections always start on database zero.
   Databases act as a form of namespacing: all databases are persisted in the same RDB or AOF file, but different databases can hold keys with the same name.
   Commands such as FLUSHDB, SWAPDB, and RANDOMKEY operate on the currently selected database.
   In Redis Cluster, SELECT is not available because only database zero is supported.
   The selected database is a per-connection property, so clients should track it and re-issue SELECT after reconnecting.
   The CLIENT LIST output shows which database each client has currently selected.

   ```
   SELECT index
   ```

   index: the zero-based index of the database to switch to.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))
