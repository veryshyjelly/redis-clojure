(ns redis.cmd.server)

(defn acl-cat!
  "Shows the available ACL categories when called without arguments.
   If a category name is provided, it lists all the Redis commands that belong to that category.
   An error is returned if an invalid category name is given.

   ```
   ACL CAT [category]
   ```

   category: list only the commands in the given ACL category, if omitted all categories are listed.

   Array reply: an array of Bulk string reply elements representing ACL categories or commands in a given category.
   Simple error reply: the command returns an error if an invalid category name is given."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn acl-deluser!
  "Deletes the specified ACL users and terminates all connections that are authenticated with those users.
   The special default user cannot be removed from the system.
   If a username in the list does not exist, no operation is performed for that user and no error is raised.

   ```
   ACL DELUSER username [username ...]
   ```

   username [username ...]: one or more usernames to delete.

   Integer reply: the number of users that were deleted.
   This number will not always match the number of arguments since certain users may not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn acl-dryrun!
  "Simulates the execution of a given command as a given user.
   This allows testing the permissions of a user without actually running the command or causing any side effects.
   The user does not need to be enabled for the simulation to work.

   ```
   ACL DRYRUN username command [arg [arg ...]]
   ```

   username: the user to run the simulated command as.
   command: the command to test.
   arg [arg ...]: the arguments to pass to the tested command.

   Simple string reply: OK on success.
   Bulk string reply: an error describing why the user can't execute the command."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn acl-genpass!
  "Generates a pseudorandom password suitable for ACL user authentication.
   The password is produced from /dev/urandom when available, or from a weaker
   but still strong internal mechanism otherwise.
   By default the output is 256 bits, rendered as 64 hexadecimal characters.
   The caller may request between 1 and 1024 bits; the value is always rounded
   up to the next multiple of 4, so requesting 1 bit yields a single hex character.
   The generation is inexpensive because Redis seeds a counter-mode HMAC-SHA256
   stream at startup, so the command can be called freely to obtain as many
   secure pseudorandom strings as needed.

   ```
   ACL GENPASS [bits]
   ```

   bits: the amount of entropy in bits for the generated password, defaults to 256.

   Bulk string reply: pseudorandom data.
   By default it contains 64 bytes, representing 256 bits of data.
   If bits was given, the output string length is the number of specified bits
   (rounded to the next multiple of 4) divided by 4."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn acl-getuser!
  "Returns all the rules defined for an existing ACL user.
   It lists the user's ACL flags, password hashes, commands, key patterns, channel patterns, and selectors.
   Command rules are returned in the same format as used in the ACL SETUSER command.
   The returned rules reflect the user's effective permissions, which may not be identical to the rules originally used to configure the user but are functionally equivalent.
   Selectors are listed in the order they were applied to the user and include information about commands, key patterns, and channel patterns.

   ```
   ACL GETUSER username
   ```

   username: the user whose rules to return.

   Map reply: a set of ACL rule definitions for the user.
   Null reply: if user does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn acl-list!
  "Shows the currently active ACL rules in the Redis server.
   Each line in the returned array defines a different user.
   The format matches what is used in the redis.conf file or an external ACL file,
   so the output can be pasted directly into a configuration file.

   ```
   ACL LIST
   ```

   Array reply: an array of Bulk string reply elements."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn acl-load!
  "Reloads the ACL rules from the configured ACL file, replacing all current ACL rules with those defined in the file.
   The command guarantees an all-or-nothing behavior: either all rules are loaded successfully or none are applied.
   Comment lines, which begin with the # character, are stripped when the ACL file is loaded and are not persisted across ACL LOAD and ACL SAVE commands.

   ```
   ACL LOAD
   ```

   Simple string reply: OK on success.
   The command may fail with an error for several reasons: if the file is not readable, if there is an error inside the file, and in such cases, the error will be reported to the user in the error.
   Finally, the command will fail if the server is not configured to use an external ACL file."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn acl-log!
  "Shows a list of recent ACL security events, displayed starting from the most recent.
   By default up to ten entries are returned.
   The optional argument can specify how many entries to show, or use RESET to clear the log entirely.

   ```
   ACL LOG [count | RESET]
   ```

   count | RESET: the number of recent entries to return, or RESET to clear the log.

   Array reply: an array of Bulk string reply elements representing ACL security events.
   Simple string reply: OK if the security log was cleared."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn acl-save!
  "Saves the currently defined ACLs from server memory to the ACL file.
   This command only works when Redis is configured to use an ACL file via the aclfile configuration option.
   If the file cannot be written, or if the server is not configured to use an external ACL file, the command fails with an error.

   ```
   ACL SAVE
   ```

   Simple string reply: OK.
   The command may fail with an error for several reasons: if the file cannot be written or if the server is not configured to use an external ACL file."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn acl-setuser!
  "Creates an ACL user with the specified rules, or modifies the rules of an existing user.
   If the username does not exist, it is created with no privileges, and the provided rules are then applied from left to right.
   If the user already exists, the new rules are applied in addition to the rules already set.
   Rules can be grouped into selectors by wrapping them in parentheses; to execute a command, either the root permissions (rules outside parentheses) or any selector must match.
   The special rule reset, when used as the first rule, flushes all existing rules and reverts the user to the default state: inactive, passwordless, with no command, key, or channel access.
   ACL rules are either plain words such as on, off, reset, allkeys, or special rules that start with a special character followed by a string, like +SET.

   ```
   ACL SETUSER username [rule [rule ...]]
   ```

   username: the user to create or modify.
   rule [rule ...]: one or more ACL rules to apply to the user.

   Simple string reply: OK. If the rules contain errors, the error is returned."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn acl-users!
  "Shows a list of all the usernames of the currently configured users in the Redis ACL system.

   ```
   ACL USERS
   ```

   Array reply: list of existing ACL users."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn acl-whoami!
  "Returns the username the current connection is authenticated with.
   New connections are authenticated with the default user.
   The user can be changed using AUTH.

   ```
   ACL WHOAMI
   ```

   Bulk string reply: the username of the current connection."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn backup-abort!
  "Cancels a backup that has not been sealed yet.

   ```
   BACKUP ABORT
   ```

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn backup-cleanup!
  "Removes sealed backup files.
   Returns the backup state machine to idle.

   ```
   BACKUP CLEANUP
   ```

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn backup-list!
  "Returns the absolute paths of the immutable backup files pinned so far.

   ```
   BACKUP LIST
   ```

   Array reply: the absolute paths of the immutable files pinned so far."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn backup-seal!
  "Freezes the current backup, producing the final immutable file set.
   The resulting set consists of the BASE snapshot, the incremental append-only file (INCR), and a standalone manifest.

   ```
   BACKUP SEAL
   ```

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn backup-start!
  "Starts a new backup by opening a backup window.
   A fresh BASE snapshot is produced in the directory specified by the backupdirname configuration setting.

   ```
   BACKUP START
   ```

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn backup-status!
  "Reports the current backup state.

   ```
   BACKUP STATUS
   ```

   Array reply: a flat array of field-value pairs describing the current backup state.
   Map reply: a map of fields to values describing the current backup state."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn bgrewriteaof!
  "Instructs Redis to start an Append Only File rewrite process.
   The rewrite creates a small optimized version of the current AOF.
   If the rewrite fails, no data is lost because the old AOF remains untouched.
   The rewrite is only triggered if there is not already a background process
   doing persistence.

   ```
   BGREWRITEAOF
   ```

   Simple string reply: a simple string reply indicating that the rewriting
   started or is about to start ASAP when the call is executed with success.
   The command may reply with an error in certain cases, as documented above.
   Bulk string reply: a simple string reply indicating that the rewriting
   started or is about to start ASAP when the call is executed with success.
   The command may reply with an error in certain cases, as documented above."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn bgsave!
  "Saves the entire database to disk in a background process.
   Redis forks a child process that performs the save while the parent continues serving client requests.
   An error is returned if a background save is already in progress or if an AOF rewrite is currently running.
   When the SCHEDULE option is used, the command returns OK even if an AOF rewrite is in progress and queues the save to run at the next opportunity.
   A client can verify whether the save completed by issuing the LASTSAVE command.

   ```
   BGSAVE [SCHEDULE]
   ```

   SCHEDULE: schedule the save to run after any in-progress BGSAVE finishes, instead of returning an error.

   Simple string reply: Background saving started.
   Simple string reply: Background saving scheduled."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn command!
  "Returns details about every command the server can process.
   It is an introspective command that clients call to discover the server runtime capabilities during handshake.
   The reply is an array with one element per command; each element is itself an array describing that command.
   The number of elements in each per-command array is fixed.
   The order of commands in the returned array is random.
   The command also exposes several subcommands for more targeted queries.
   For cluster-aware clients the reply includes key specification rules that let the client identify which arguments are keys and route requests to the correct shard.

   ```
   COMMAND
   ```

   Array reply: a nested list of command details. The order of the commands in the array is random."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn command-count!
  "Returns the total number of commands available in this Redis server.
   This is a simple informational command that takes no arguments.

   ```
   COMMAND COUNT
   ```

   Integer reply: the number of commands returned by COMMAND."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn command-docs!
  "Returns documentary information about commands.
   By default the reply includes all of the server commands.
   You can limit the results by specifying one or more command names.
   The reply contains a map for each returned command holding its documentary information.

   ```
   COMMAND DOCS [command-name [command-name ...]]
   ```

   command-name [command-name ...]: one or more command names to return documentation for, if omitted documentation for all commands is returned.

   Map reply: a map where each key is a command name, and each value is the documentary information."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn command-getkeys!
  "COMMAND GETKEYS is a helper command that identifies which keys are referenced by a given Redis command.
   For some commands, Redis can only determine the key names by parsing the full command line, so this command delegates to the internal command parser to extract them.
   It returns the list of keys that would be affected by the specified command and its arguments.

   ```
   COMMAND GETKEYS command [arg [arg ...]]
   ```

   command: the name of the command to analyze.
   arg [arg ...]: the arguments that would be passed to the command.

   Array reply: a list of keys from the given command."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn command-getkeysandflags!
  "COMMAND GETKEYSANDFLAGS is a helper command that identifies the keys in a full Redis command and returns flags describing how each key is used.
   For some commands, Redis can only determine the key positions by parsing the complete command line, so this command delegates that work to the internal command parser.
   The flags indicate the role of each key in the command.

   ```
   COMMAND GETKEYSANDFLAGS command [arg [arg ...]]
   ```

   command: the name of the command to analyze.
   arg [arg ...]: the arguments that would be passed to the command.

   Array reply: a list of keys from the given command and their usage flags."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn command-info!
  "Returns details about one or more Redis commands in the same format as COMMAND.
   If a requested command name does not exist, nil is returned for that entry.
   If no command names are specified, details for all commands are returned.

   ```
   COMMAND INFO [command-name [command-name ...]]
   ```

   command-name [command-name ...]: one or more command names to return details for, if omitted details for all commands are returned.

   Array reply: a nested list of command details."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn command-list!
  "Returns an array of the Redis server command names.
   The list can be filtered by module name, ACL category, or a glob-style name pattern.

   ```
   COMMAND LIST [FILTERBY <MODULE module-name | ACLCAT category |
     PATTERN pattern>]
   ```

   FILTERBY MODULE module-name | ACLCAT category | PATTERN pattern: filter the listed commands by module, ACL category, or a glob-style name pattern.

   Array reply: a list of command names."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn config-get!
  "Reads the configuration parameters of a running Redis server.
   The command accepts one or more glob-style patterns and returns every configuration parameter whose name matches any of the given patterns.
   The returned values carry the same meaning as the equivalent parameters in the redis.conf file.
   The symmetric command for altering configuration at run time is CONFIG SET.

   ```
   CONFIG GET parameter [parameter ...]
   ```

   parameter [parameter ...]: one or more configuration parameter names or glob-style patterns to retrieve.

   Map reply: a list of configuration parameters matching the provided arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn config-resetstat!
  "Resets the statistics reported by Redis using the INFO and LATENCY HISTOGRAM commands.

   ```
   CONFIG RESETSTAT
   ```

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn config-rewrite!
  "Rewrites the redis.conf file the server was started with, applying the minimal changes needed to make it reflect the configuration currently in use.
   This is useful after using CONFIG SET to change settings at runtime, so that the changes persist across restarts.
   The rewrite is performed conservatively, preserving the structure of the original file.
   If the original configuration file no longer exists, the command can recreate it from scratch.
   If the server was started without a configuration file at all, the command returns an error.

   ```
   CONFIG REWRITE
   ```

   Simple string reply: OK when the configuration was rewritten properly.
   Otherwise an error is returned."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn config-set!
  "CONFIG SET reconfigures Redis at runtime without requiring a server restart.
   It can change simple configuration parameters or switch between
   persistence modes such as RDB snapshotting and append-only file.
   All parameters set this way are loaded immediately and take effect
   starting with the next command executed.
   It is possible to enable or disable AOF and RDB snapshotting
   independently; the two persistence options are not mutually exclusive.
   Setting the appendonly parameter starts a background process that saves
   the initial append-only file from the in-memory dataset and then appends
   all subsequent commands to it.

   ```
   CONFIG SET parameter value [parameter value ...]
   ```

   parameter value [parameter value ...]: one or more configuration
   parameter-value pairs to set.

   Simple string reply: OK when the configuration was set properly.
   Otherwise an error is returned."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn dbsize!
  "Returns the number of keys in the currently-selected database.
   In a clustered Redis environment, the behavior of this command varies;
   see multi-key operations for details.

   ```
   DBSIZE
   ```

   Integer reply: the number of keys in the currently-selected database."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn failover!
  "Starts a coordinated failover between the currently connected master and one of its replicas.
   The failover is not synchronous; a background task handles the coordination.
   It is designed to limit data loss and unavailability of the cluster during the failover.
   The field master_failover_state in INFO replication can be used to track the current state of the failover.
   If the previous master had additional replicas attached to it, they will continue replicating from it as chained replicas.
   You will need to manually execute a REPLICAOF on these replicas to start replicating directly from the new master.

   ```
   FAILOVER [TO host port [FORCE]] [ABORT] [TIMEOUT milliseconds]
   ```

   TIMEOUT milliseconds: maximum time the master will wait in the waiting-for-sync state before aborting the failover attempt and rolling back, if not specified the timeout is considered infinite.
   TO host port: designates a specific replica by its host and port to failover to, the master will wait for this replica to catch up to its replication offset before failing over.
   FORCE: when both TIMEOUT and TO are set, once the timeout has elapsed the master fails over to the target replica instead of rolling back, enabling a best-effort failover with limited write outage.

   Simple string reply: OK if the command was accepted and a coordinated failover is in progress. An error if the operation cannot be executed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn flushall!
  "Deletes all keys across all existing databases, not just the currently selected one.
   This command never fails.
   By default the flush is performed synchronously, but the lazyfree-lazy-user-flush configuration directive can change the default to asynchronous.

   ```
   FLUSHALL [ASYNC | SYNC]
   ```

   ASYNC | SYNC: flush asynchronously or synchronously, the default is set by the lazyfree-lazy-user-flush configuration directive.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn flushdb!
  "Deletes all keys in the currently selected database.
   This command never fails.
   By default the flush is performed synchronously, but the lazyfree-lazy-user-flush configuration directive can change the default to asynchronous.

   ```
   FLUSHDB [ASYNC | SYNC]
   ```

   ASYNC | SYNC: flush asynchronously or synchronously, the default is set by the lazyfree-lazy-user-flush configuration directive.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hotkeys!
  "HOTKEYS is a container command for hotkeys tracking.
   It provides a method for identifying hotkeys inside a Redis server during a specified tracking time period.
   Hotkeys are defined by two metrics.

   ```
   HOTKEYS
   ```

   Array reply: hotkeys detected during the tracking period."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hotkeys-get!
  "Returns tracking results and metadata from the current or most recent hotkeys tracking session.
   It provides comprehensive information about the session, including metrics collected for both non-clustered and clustered Redis environments.
   On clustered environments, additional results are included when SLOTS was used with HOTKEYS START.

   ```
   HOTKEYS GET
   ```

   Array reply when tracking data is available, containing a single map with field names and values.
   Null reply when no tracking has been started or data has been reset."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hotkeys-reset!
  "Releases the resources used for hotkey tracking.
   This command can only be executed when hotkey tracking is stopped.
   If tracking is currently active, the command returns an error.
   Use HOTKEYS STOP first to stop tracking, then use HOTKEYS RESET to free the allocated resources.

   ```
   HOTKEYS RESET
   ```

   Simple string reply: OK when resources are successfully released.
   Simple error reply: when tracking is currently active."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hotkeys-start!
  "Starts a hotkey tracking session with the specified metrics.
   An error is returned if a tracking session is already in progress.
   The session continues until it is stopped with HOTKEYS STOP or until the configured duration elapses.

   ```
   HOTKEYS START METRICS count [CPU] [NET] [COUNT k] [DURATION seconds] [SAMPLE ratio] [SLOTS count slot [slot ...]]
   ```

   METRICS count [CPU] [NET]: specifies which metrics to track and how many hotkeys to collect,
     at least one of CPU or NET must be given.
   COUNT: sets the value of K for top-K hotkeys tracking.
   DURATION: the number of seconds the tracking runs before stopping automatically,
     if omitted the session continues until HOTKEYS STOP is issued.
   SAMPLE: the sampling ratio for probabilistic tracking, each key is sampled with
     probability 1/ratio, higher values reduce performance impact but may miss hotkeys
     while lower values are more accurate at higher cost.
   SLOTS: specifies which hash slots to track in a cluster environment, takes a count
     followed by that many slot numbers, only keys hashing to the given slots are tracked,
     an error is returned if not in cluster mode.

   Simple string reply: OK when tracking is successfully started.
   Error reply: when invalid parameters are provided."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hotkeys-stop!
  "Stops hotkeys tracking but preserves the collected data.
   After stopping, the tracking data remains available through HOTKEYS GET until HOTKEYS RESET is called or a new tracking session is started.

   ```
   HOTKEYS STOP
   ```

   Simple string reply: OK when tracking is successfully stopped.
   Simple error reply: when tracking is not currently active."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn info!
  "The INFO command returns information and statistics about the server in a format
   that is simple to parse by computers and easy to read by humans.
   The optional section parameter(s) can be used to select a specific section
   of information.
   When no parameter is provided, the default section is assumed.

   ```
   INFO [section [section ...]]
   ```

   section [section ...]: one or more sections to include in the output, such as
   server, clients, or memory, also accepts all, everything, and default.

   Verbatim string reply: a map of info fields, one field per line in the form
   of <field>:<value> where the value can be a comma separated map like
   <key>=<val>. Also contains section header lines starting with # and blank
   lines. Lines can contain a section name (starting with a # character) or a
   property. All the properties are in the form of field:value terminated by
   carriage return and line feed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn lastsave!
  "Returns the UNIX timestamp of the last successful database save.
   Redis considers the database saved successfully at startup.
   A client can verify whether a BGSAVE command succeeded by reading the LASTSAVE value, issuing BGSAVE, and then polling LASTSAVE at regular intervals to see if the value changed.

   ```
   LASTSAVE
   ```

   Integer reply: UNIX TIME of the last DB save executed with success."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn latency-doctor!
  "Reports a human-readable analysis of latency-related issues and suggests possible remedies.
   It provides statistical data such as the average period between latency spikes and the median deviation, along with a human-readable breakdown of each event.
   For certain events, such as fork, additional details are included, for example the rate at which the system forks processes.

   ```
   LATENCY DOCTOR
   ```

   Verbatim string reply: a human readable latency analysis report."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn latency-graph!
  "Produces an ASCII-art style graph for the specified latency event.
   This provides a quick visual overview of the latency trend, useful for grasping the situation before resorting to parsing raw data from LATENCY HISTORY or external tooling.

   ```
   LATENCY GRAPH event
   ```

   event: the latency event to graph.

   Bulk string reply: Latency graph"
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn latency-histogram!
  "Returns a cumulative distribution of command latencies in histogram format.
   By default, all available latency histograms are returned.
   You can filter the reply by providing one or more specific command names.
   This command requires the extended latency monitoring feature to be enabled, which is the default.
   If it is not enabled, call CONFIG SET latency-tracking yes to turn it on.
   To delete the latency histogram data, use the CONFIG RESETSTAT command.

   ```
   LATENCY HISTOGRAM [command [command ...]]
   ```

   command [command ...]: one or more command names to return latency histograms for, if omitted histograms for all commands are returned.

   Map reply: a map where each key is a command name, and each value is a map with the total calls, and an inner map of the histogram time buckets."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn latency-history!
  "Returns the raw latency spike time series data for the specified event.
   This is intended for applications that need to fetch raw data for monitoring or graphing purposes.
   The command returns up to 160 timestamp-latency pairs for the given event.

   ```
   LATENCY HISTORY event
   ```

   event: the latency event to return the history for.

   Array reply: an array where each element is a two elements array representing the timestamp and the latency of the event."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn latency-latest!
  "Reports the latest latency events logged by the Redis instance.
   Each event includes the event name, timestamp, latest latency measurement, and all-time latency measurement.
   All-time latency is the maximum latency observed since the instance was started or since LATENCY RESET was last issued.

   ```
   LATENCY LATEST
   ```

   Array reply: an array where each element is a four elements array representing the event's name, timestamp, latest and all-time latency measurements."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn latency-reset!
  "Resets the latency spikes time series of all, or only some, events.
   When called without arguments, it resets all events, discarding the currently logged latency spike events and resetting the maximum event time register.
   Specific events can be reset by providing their names as arguments.

   ```
   LATENCY RESET [event [event ...]]
   ```

   event [event ...]: one or more latency events to reset, if omitted all events are reset.

   Integer reply: the number of event time series that were reset."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn lolwut!
  "Displays the Redis version as a piece of generative computer art.
   By default it shows the art corresponding to the current Redis version.
   Each version of the art accepts a different set of numerical arguments that change the output.
   The command is intended as a playful reminder that programming is more than just writing useful code.

   ```
   LOLWUT [VERSION version]
   ```

   VERSION version: request a specific version of the generated art.

   Verbatim string reply: a string containing generative computer art and the Redis version."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn memory-doctor!
  "Reports about different memory-related issues that the Redis server experiences.
   Advises about possible remedies.

   ```
   MEMORY DOCTOR
   ```

   Verbatim string reply: a memory problems report."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn memory-malloc-stats!
  "Provides an internal statistics report from the memory allocator.
   This command is only implemented when jemalloc is used as the allocator;
   for all other allocators it evaluates to a benign NOOP.

   ```
   MEMORY MALLOC-STATS
   ```

   Verbatim string reply: a long string of statistics."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn memory-purge!
  "Attempts to purge dirty pages from the memory allocator so that they can be reclaimed.
   This command is only effective when Redis is built with jemalloc as its allocator; for all other allocators it is a benign no-op.

   ```
   MEMORY PURGE
   ```

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn memory-stats!
  "Returns information about the memory usage of the server.
   The information is provided as metrics and their respective values.

   ```
   MEMORY STATS
   ```

   Map reply: memory usage metrics and their values."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn memory-usage!
  "Reports the number of bytes that a key and its value require to be stored in RAM.
   The reported usage is the total of memory allocations for data and administrative overheads.
   For a hash stored in a compact hash encoding, the reported usage also includes the key's share of the shared compact hash template's memory, calculated as the template's size divided evenly across all keys that reference it.
   For nested data types, the optional SAMPLES option controls how many nested values are sampled to estimate the total size; the samples are averaged to produce the estimate.
   The default sample count is 5, and using SAMPLES 0 samples all nested values.

   ```
   MEMORY USAGE key [SAMPLES count]
   ```

   key: the key to report memory usage for.
   SAMPLES count: the number of sampled nested values for aggregate types, 0 samples all of them, the default is 5.

   Integer reply: the memory usage in bytes.
   Null reply: if the key does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn module-list!
  "Returns information about the modules loaded to the server.

   ```
   MODULE LIST
   ```

   Array reply: list of loaded modules. Each element in the list represents a module, and is a Map reply of property names and their values.
   name: the name of the module.
   ver: the version of the module."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn module-load!
  "Loads a module from a dynamic library at runtime.
   The path must be an absolute path to the library file, including the full filename.
   Any additional arguments are passed unmodified to the module's initialization function.

   ```
   MODULE LOAD path [arg [arg ...]]
   ```

   path: the absolute path to the module library file.
   arg [arg ...]: arguments passed to the module's initialization function.

   Simple string reply: OK if the module was loaded."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn module-loadex!
  "Loads and initializes a Redis module from a dynamic library file at runtime.
   The path must be the absolute path to the library file, including the full filename.
   This is an extended version of MODULE LOAD that additionally accepts configuration directives and extra arguments.
   The optional CONFIG keyword allows one or more name-value pairs to be set on the module at load time.
   Arguments following the ARGS keyword are passed unmodified to the module initialization function.

   ```
   MODULE LOADEX path [CONFIG name value [CONFIG name value ...]]
     [ARGS args [args ...]]
   ```

   path: the absolute path to the module library file.
   CONFIG name value [CONFIG name value ...]: one or more module configuration name-value pairs to set at load time.
   ARGS args [args ...]: additional arguments passed to the module initialization function.

   Simple string reply: OK if the module was loaded."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn module-unload!
  "Unloads a module by its name.
   The name must be the one reported by the MODULE LIST command,
   which may differ from the dynamic library filename.

   ```
   MODULE UNLOAD name
   ```

   name: the name of the module to unload, as reported by MODULE LIST.

   Simple string reply: OK if the module was unloaded."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn monitor!
  "MONITOR is a debugging command that streams back every command processed by the Redis server.
   It is useful for spotting bugs in an application, whether Redis is used as a database or as a distributed caching system.
   The command can be used via redis-cli or via telnet.
   Use SIGINT (Ctrl-C) to stop a MONITOR stream running via redis-cli.
   Manually issue the QUIT or RESET commands to stop a MONITOR stream running via telnet.

   ```
   MONITOR
   ```

   Non-standard return value. Dumps the received commands in an infinite flow."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn psync!
  "Initiates a replication stream from the master.
   This command is called by Redis replicas to start or resume a replication stream from the master node.

   ```
   PSYNC replicationid offset
   ```

   replicationid: the replication ID of the master.
   offset: the replication offset to resume from.

   Non-standard return value, a bulk transfer of the data followed by PING and write requests from the master."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn replconf!
  "The REPLCONF command is an internal command.
   It is used by a Redis master to configure a connected replica.

   ```
   REPLCONF
   ```

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn replicaof!
  "Changes the replication settings of a replica on the fly.
   If the server is currently acting as a replica, issuing REPLICAOF NO ONE stops replication and promotes the server to a master, while preserving the already replicated data.
   Issuing REPLICAOF hostname port makes the server a replica of the server at the given hostname and port.
   If the server is already a replica of another master, the old replication is stopped, the old dataset is discarded, and synchronization begins against the new master.
   Using REPLICAOF NO ONE does not discard the data that was already replicated, so the server can be promoted to master and serve read/write traffic while the old master is repaired.

   ```
   REPLICAOF <host port | NO ONE>
   ```

   host port | NO ONE: the host and port of the master to replicate from, or NO ONE to stop replicating and turn the server back into a master.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn restore-asking!
  "RESTORE-ASKING is an internal command used by a Redis cluster master during slot migration.
   It restores a key from a serialized value, as produced by DUMP.
   The ttl argument is the time to live in milliseconds; a value of 0 means no expiration.
   When ABSTTL is given, ttl is interpreted as an absolute Unix timestamp in milliseconds
   rather than a relative duration.
   REPLACE allows the key to be overwritten if it already exists.
   IDLETIME sets the key idle time in seconds for LRU-based eviction.
   FREQ sets the key access-frequency counter for LFU-based eviction.

   ```
   RESTORE-ASKING key ttl serialized-value [REPLACE] [ABSTTL]
     [IDLETIME seconds] [FREQ frequency]
   ```

   key: the name of the key to create.
   ttl: the time to live for the key in milliseconds, 0 means no expiration;
        with ABSTTL this is an absolute Unix timestamp in milliseconds.
   serialized-value: the serialized value, as produced by DUMP.
   REPLACE: replace the key if it already exists.
   ABSTTL: interpret ttl as an absolute Unix timestamp in milliseconds
           rather than a relative time.
   IDLETIME seconds: set the key idle time for LRU eviction to this many seconds.
   FREQ frequency: set the key access-frequency counter for LFU eviction to this value.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn role!
  "Returns the role of a Redis instance in the context of replication.
   The role is one of master, slave, or sentinel.
   Along with the role, the command returns additional information that depends on the role:
   replication state details for a master or slave, or the list of monitored master names for a sentinel.

   ```
   ROLE
   ```

   Array reply: where the first element is one of master, slave, or sentinel,
   and the additional elements are role-specific as illustrated above."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn save!
  "Performs a synchronous save of the entire dataset, producing a point-in-time snapshot of all data in the Redis instance as an RDB file.
   This command blocks all other clients for the duration of the save, so it should almost never be used in production.
   It can serve as a last resort when the background save child process cannot be created, for example due to a fork(2) system call error.

   ```
   SAVE
   ```

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn shutdown!
  "SHUTDOWN gracefully stops the Redis server.
   If persistence is enabled, the server ensures no data is lost before exiting.
   If no persistence is configured (no AOF and no save points),
   the RDB file is not dumped on shutdown,
   since instances used purely for caching should not block on exit.
   The same shutdown sequence is triggered
   when the server receives SIGTERM or SIGINT signals.

   ```
   SHUTDOWN [NOSAVE | SAVE] [NOW] [FORCE] [ABORT]
   ```

   NOSAVE | SAVE: SAVE forces a DB save even if no save points are configured;
   NOSAVE prevents a DB save even if one or more save points are configured.
   NOW: skip waiting for lagging replicas,
   bypassing the first step in the shutdown sequence.
   FORCE: ignore any errors that would normally prevent the server from exiting.
   ABORT: cancel an ongoing shutdown,
   cannot be combined with the other flags.

   Simple string reply: OK if ABORT was specified and shutdown was aborted.
   On successful shutdown, nothing is returned
   because the server quits and the connection is closed.
   On failure, an error is returned."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn slaveof!
  "Changes the replication settings of a server on the fly.
   If the server is currently acting as a replica, issuing SLAVEOF NO ONE stops replication and promotes the server to a master without discarding its dataset.
   Issuing SLAVEOF hostname port makes the server a replica of the server at the given address.
   If the server is already a replica of another master, the old replication is stopped, the old dataset is discarded, and synchronization with the new master begins.
   This command is deprecated; REPLICAOF is the preferred alternative for new code.

   ```
   SLAVEOF <host port | NO ONE>
   ```

   host port | NO ONE: the host and port of the master to replicate from, or NO ONE to stop replicating.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn slowlog-get!
  "Returns entries from the slow log in chronological order.
   The slow log records commands whose execution time exceeded the threshold set by the slowlog-log-slower-than configuration directive.
   Execution time covers only the actual command execution, not I/O operations such as talking with the client or sending the reply.
   The maximum number of entries retained is governed by the slowlog-max-len configuration directive.
   By default the command returns the latest ten entries.
   The optional count argument limits the number of returned entries; the special value -1 returns all entries.
   Each entry contains seven values, including the logged argument array and the total argument count.
   The logged argument array is truncated when the command has more arguments than the slowlog-max-argc directive allows (32 by default); in that case the last logged element is replaced with a string of the form ... (N more arguments).
   The total argument count always reflects the original argument count captured before truncation, so it can be read directly without parsing that string.
   The entry ID is unique and is never reset during server execution; only a server restart resets it.

   ```
   SLOWLOG GET [count]
   ```

   count: the number of recent slow-log entries to return, -1 returns all entries, default is 10.

   Array reply: a list of slow log entries per the above format."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn slowlog-len!
  "Returns the current number of entries in the slow log.
   A new entry is added to the slow log whenever a command exceeds the execution time threshold defined by the slowlog-log-slower-than configuration directive.
   The maximum number of entries is governed by the slowlog-max-len configuration directive.
   Once the slow log reaches its maximal size, the oldest entry is removed whenever a new entry is created.
   The slow log can be cleared with the SLOWLOG RESET command.

   ```
   SLOWLOG LEN
   ```

   Integer reply: the number of entries in the slow log."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn slowlog-reset!
  "Resets the slow log, clearing all entries from it.
   Once the entries are deleted, the information is lost forever.

   ```
   SLOWLOG RESET
   ```

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn swapdb!
  "Swaps two Redis databases in place.
   Immediately after the swap, all clients connected to one database will see the data of the other, and vice versa.

   ```
   SWAPDB index1 index2
   ```

   index1: the index of the first database.
   index2: the index of the second database.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn sync!
  "Initiates a replication stream from the master.
   This command is called by Redis replicas to begin the replication process.
   It has been superseded by PSYNC.

   ```
   SYNC
   ```

   Non-standard return value, a bulk transfer of the data followed by PING and write requests from the master."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn time!
  "Returns the current server time.
   The reply is a two-element array containing the Unix timestamp in seconds and the number of microseconds already elapsed in the current second.
   The interface is similar to the gettimeofday system call.

   ```
   TIME
   ```

   Array reply: a two-element array consisting of the Unix timestamp in seconds and the microseconds count."
  [_state _msg]
  (throw (Exception. "Not Implemented")))
