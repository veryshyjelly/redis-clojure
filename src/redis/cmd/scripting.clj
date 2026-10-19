(ns redis.cmd.scripting)

(defn eval!
  "Executes a server-side Lua script using the embedded Redis Lua 5.1 interpreter.
   The first argument is the script source code, and the second is the number of key name arguments that follow.
   Keys are made available to the script as the KEYS global runtime variable, and any additional arguments are passed via the ARGV variable.
   All keys that a script accesses must be explicitly provided as input key arguments; scripts should never access keys with programmatically-generated names or based on the contents of data structures stored in the database.
   In clustered Redis environments, the behavior of this command varies for multi-key operations.

   ```
   EVAL script numkeys [key [key ...]] [arg [arg ...]]
   ```

   script: the Lua script to evaluate.
   numkeys: the number of key names that follow, arguments after the keys are passed as regular arguments.
   key [key ...]: the key names the script accesses, provided to it via the Lua global KEYS runtime variable, there must be exactly numkeys of them.
   arg [arg ...]: additional arguments provided to the script via the Lua ARGV variable.

   The return value depends on the script that was executed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn evalsha!
  "EVALSHA evaluates a Lua script that has been previously cached on the server,
   identified by its SHA1 digest.
   The script must have been loaded into the cache via SCRIPT LOAD or a prior EVAL call.
   If the script is not found in the cache, the command returns an error.
   Otherwise, the command behaves identically to EVAL:
   the script is executed with the provided keys and arguments.
   In a clustered Redis environment, the behavior of multi-key operations may vary.

   ```
   EVALSHA sha1 numkeys [key [key ...]] [arg [arg ...]]
   ```

   sha1: the SHA1 digest of a script previously cached with SCRIPT LOAD or EVAL.
   numkeys: the number of key names that follow,
   arguments after the keys are passed as regular arguments.
   key [key ...]: the key names the script accesses,
   provided to it via the Lua KEYS global variable,
   there must be exactly numkeys of them.
   arg [arg ...]: additional arguments provided to the script via the Lua ARGV variable.

   The return value depends on the script that was executed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn evalsha-ro!
  "EVALSHA_RO executes a previously cached Lua script identified by its SHA1 digest,
   but in read-only mode.
   It is a read-only variant of EVALSHA and cannot run any command that modifies data.
   The script must have been loaded into the script cache beforehand via SCRIPT LOAD or EVAL_RO.

   ```
   EVALSHA_RO sha1 numkeys [key [key ...]] [arg [arg ...]]
   ```

   sha1: the SHA1 digest of a read-only script previously cached with SCRIPT LOAD or EVAL_RO.
   numkeys: the number of key names that follow, arguments after the keys are passed as regular arguments.
   key [key ...]: the key names the script accesses, provided to it via the Lua KEYS global variable, there must be exactly numkeys of them.
   arg [arg ...]: additional arguments provided to the script via the Lua ARGV variable.

   The return value depends on the script that was executed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn eval-ro!
  "Runs a read-only Lua script on the server.
   Unlike EVAL, this command cannot execute any command that modifies data.
   Keys are passed to the script via the Lua KEYS global variable, and additional arguments via the Lua ARGV variable.

   ```
   EVAL_RO script numkeys [key [key ...]] [arg [arg ...]]
   ```

   script: the Lua script to evaluate, must not modify data.
   numkeys: the number of key names that follow, arguments after the keys are passed as regular arguments.
   key [key ...]: the key names the script accesses, provided via the Lua KEYS global variable, there must be exactly numkeys of them.
   arg [arg ...]: additional arguments provided to the script via the Lua ARGV variable.

   The return value depends on the script that was executed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn fcall!
  "Invokes a function that was previously loaded into the server with the FUNCTION LOAD command.
   The first argument is the name of the loaded function.
   The second argument is the number of input key names, followed by that many key names.
   In Lua, the input key names are available to the function as a table that is the callback's first argument.
   All key names that a function accesses must be explicitly provided as input key arguments.
   Functions should only access keys whose names are given as input arguments, and should never access keys with programmatically-generated names or names based on the contents of data structures stored in the database.
   Any additional arguments beyond the keys are regular arguments passed in a Lua table as the callback's second argument.

   ```
   FCALL function numkeys [key [key ...]] [arg [arg ...]]
   ```

   function: the name of the function to call.
   numkeys: the number of key names that follow, arguments after the keys are passed as regular arguments.
   key [key ...]: the key names the function accesses, provided to it via the Lua KEYS global variable, there must be exactly numkeys of them.
   arg [arg ...]: additional arguments provided to the function via the Lua ARGV variable.

   The return value depends on the function that was executed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn fcall-ro!
  "FCALL_RO is a read-only variant of the FCALL command.
   It calls a previously defined function by name and passes it key names and additional arguments.
   Unlike FCALL, it cannot execute commands that modify data, making it safe for use in read-only contexts.

   ```
   FCALL_RO function numkeys [key [key ...]] [arg [arg ...]]
   ```

   function: the name of the function to call, it must be a read-only function.
   numkeys: the number of key names that follow, arguments after the keys are passed as regular arguments.
   key [key ...]: the key names the function accesses, provided to it via the Lua KEYS global variable, there must be exactly numkeys of them.
   arg [arg ...]: additional arguments provided to the function via the Lua ARGV variable.

   The return value depends on the function that was executed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn function-delete!
  "Deletes a library and all its functions.
   If the library does not exist, the server returns an error.

   ```
   FUNCTION DELETE library-name
   ```

   library-name: the name of the library to delete.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn function-dump!
  "Returns the serialized payload of all loaded libraries.
   The payload can later be restored using the FUNCTION RESTORE command.

   ```
   FUNCTION DUMP
   ```

   Bulk string reply: the serialized payload"
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn function-flush!
  "Deletes all function libraries.
   If no mode is specified, the effective behavior is determined by the lazyfree-lazy-user-flush configuration directive.

   ```
   FUNCTION FLUSH [ASYNC | SYNC]
   ```

   ASYNC | SYNC: flush the function libraries asynchronously or synchronously, the default is set by the lazyfree-lazy-user-flush configuration directive.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn function-kill!
  "Kills a function that is currently executing.
   It can only be used on functions that did not modify the dataset during their execution,
   since stopping a read-only function does not violate the scripting engine's guaranteed atomicity.

   ```
   FUNCTION KILL
   ```

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn function-list!
  "Return information about loaded functions and libraries.
   The command can be filtered to a specific library name pattern and can optionally include the source code of each library.

   ```
   FUNCTION LIST [LIBRARYNAME library-name-pattern] [WITHCODE]
   ```

   LIBRARYNAME library-name-pattern: list only libraries whose names match the given pattern.
   WITHCODE: include the source code of each library in the reply.

   Array reply: information about loaded functions and libraries.
   Map reply: information about loaded functions and libraries."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn function-load!
  "Loads a library into Redis.
   The library payload must begin with a shebang statement that declares the engine and the library name, for example #!lua name=mylib.
   Currently the only supported engine is lua.
   For the Lua engine, the implementation must declare one or more entry points using the redis.register_function() API.
   Once loaded, the functions in the library can be invoked with the FCALL or FCALL_RO commands.
   If the REPLACE modifier is omitted and a library with the same name already exists, the command returns an error.
   An error is also returned when the engine name is invalid, when a function name collides with one in another library (even when REPLACE is specified), when the engine fails to create the library functions (for example due to a compilation error), or when no functions were declared.

   ```
   FUNCTION LOAD [REPLACE] function-code
   ```

   function-code: the library source code, must begin with a shebang statement
     declaring the engine and library name, for example #!lua name=mylib.
   REPLACE: replace an existing library that has the same name.

   Bulk string reply: the library name that was loaded.
   Simple error string: returned when an invalid engine name was provided,
     the library name already exists without the REPLACE modifier, a function
     name collides with one in another library (even when REPLACE is specified),
     the engine failed in creating the library functions (for example because of
     a compilation error), or no functions were declared by the library."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn function-restore!
  "Restores function libraries from a serialized payload produced by FUNCTION DUMP.
   The optional policy argument controls how existing libraries are handled when the restored payload contains libraries with the same names.
   The default policy is APPEND, which appends the restored libraries to the existing ones and aborts on collision.
   FLUSH deletes all existing libraries before restoring the payload.
   REPLACE appends the restored libraries, replacing any existing ones with the same library name, but does not prevent function name collisions.

   ```
   FUNCTION RESTORE serialized-value [FLUSH | APPEND | REPLACE]
   ```

   serialized-value: the payload produced by FUNCTION DUMP.
   FLUSH | APPEND | REPLACE: policies for handling existing libraries; APPEND (default) appends restored libraries and aborts on collision, FLUSH deletes all existing libraries before restoring, REPLACE appends restored libraries replacing any with the same library name but does not prevent function name collisions.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn function-stats!
  "Returns information about the function that is currently running
   and information about the available execution engines.
   The reply is a map with two keys.
   This command can be used to inspect the invocation of a long-running
   function and decide whether to kill it with the FUNCTION KILL command.

   ```
   FUNCTION STATS
   ```

   Array reply: information about the function that's currently running
   and information about the available execution engines.
   Map reply: information about the function that's currently running
   and information about the available execution engines."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn script-debug!
  "Sets the debug mode for subsequent scripts executed with EVAL.
   In debug mode Redis acts as a remote debugging server, allowing a client to execute scripts step by step, set breakpoints, and inspect variables.
   There are two debugging modes: asynchronous and synchronous.
   In asynchronous mode, Redis creates a forked debugging session that does not block the server, and all data changes are rolled back after the session ends so you can restart from the same initial state.
   In synchronous mode, Redis blocks the server while the debugging session is active and retains all data changes after the session ends.
   Setting the mode to NO disables Lua script debug mode, and subsequent scripts run normally.
   Avoid using this on a production server; use a development server instead.

   ```
   SCRIPT DEBUG <YES | SYNC | NO>
   ```

   YES | SYNC | NO: set the debugging mode; YES enables non-blocking asynchronous debugging where changes are discarded, SYNC enables blocking synchronous debugging where changes are saved, NO disables debug mode so scripts run normally.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn script-exists!
  "Returns whether one or more scripts are present in the Redis script cache.
   Each SHA1 digest argument is checked independently, and the result for each is 1 if the script is cached or 0 if it is not.
   This is useful before running a pipeline of EVALSHA commands to verify that the required scripts have been loaded.

   ```
   SCRIPT EXISTS sha1 [sha1 ...]
   ```

   sha1 [sha1 ...]: one or more SHA1 digests to look up in the script cache.

   Array reply: an array of integers that correspond to the specified SHA1 digest arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn script-flush!
  "Flushes the Lua scripts cache, removing all cached scripts.
   By default the flush is performed synchronously.
   The default flush mode can be changed to asynchronous by setting the lazyfree-lazy-user-flush configuration directive to yes.

   ```
   SCRIPT FLUSH [ASYNC | SYNC]
   ```

   ASYNC | SYNC: flush the script cache asynchronously (ASYNC) or synchronously (SYNC), the default is set by the lazyfree-lazy-user-flush configuration directive.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn script-kill!
  "Kills the currently executing EVAL script, provided no write operation has been performed yet.
   This is mainly useful for terminating a script that is running for too long, such as one stuck in an infinite loop.
   The blocked client will receive an error reply.
   If the script has already performed write operations, it cannot be killed this way because doing so would violate the atomicity contract of Lua scripts.
   In that case, only SHUTDOWN NOSAVE can terminate the script, which kills the Redis process and prevents persisting half-written data.

   ```
   SCRIPT KILL
   ```

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn script-load!
  "Loads a Lua script into the scripts cache without executing it.
   Once loaded, the script can be invoked using EVALSHA with its SHA1 digest.
   The script remains in the cache indefinitely unless SCRIPT FLUSH is called.
   Loading a script that is already present in the cache has the same effect.

   ```
   SCRIPT LOAD script
   ```

   script: the Lua script to load into the script cache, its SHA1 digest is returned for use with EVALSHA.

   Bulk string reply: the SHA1 digest of the script added into the script cache."
  [_state _msg]
  (throw (Exception. "Not Implemented")))
