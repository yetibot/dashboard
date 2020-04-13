# dashboard

The new (as of 2020) CLJS frontend dashboard for Yetibot

# Prerequisites

## Shadow-cljs

We chose `shadow-cljs` as our main build tool for the project. `shadow-cljs` is easy to use and configure and comes with a very good integration with the other tools that we already use on the other sub-projects under `Yetibot`, for e.g `Leiningen`.

See the `shadow-cljs` _user-guide_ on how to install it.

## Leiningen

`Leiningen` is the Clojure build tool that `Yetibot` uses. For `dashboard`, `shadow-cljs` delegates the _dependency management_ to `leiningen`.

See the `Leiningen` Installation docs to install it.

# Development workflow using the shadow-cljs CLI

## Running a watch process

To start a `watch` process which will monitor changes and automatically recompile the `dashboard` code base, run the following from your terminal:

`$ shadow-cljs watch :dashboard`

a `watch` will implicitly start a _server process_ which will be re-used by all commands sent by the CLI, instead of starting a new JVM.

```shell script
 + react-dom@16.13.0
 + react@16.13.0
 added 1 package, updated 2 packages and audited 36 packages in 4.611s
 found 0 vulnerabilities
 
 shadow-cljs - server version: 2.8.94 running at http://localhost:9630
 shadow-cljs - nREPL server started on port 63095
 shadow-cljs - watching build :dashboard
 [:dashboard] Configuring build.
 [:dashboard] Compiling ...
 [:dashboard] Build completed. (157 files, 156 compiled, 0 warnings, 35.04s)
```

To stop the `watch` process, just type in CTRL-C from the CLI : 

```shell script
 ...
 [:dashboard] Configuring build.
 [:dashboard] Compiling ...
 [:dashboard] Build completed. (157 files, 156 compiled, 0 warnings, 35.04s)

 Ctrl^C
```

## The REPL

To start the `REPL`, run :

`$ shadow-cljs browser-repl :dashboard`

It will launch a browser providing the `runtime` used to evaluate the code entered in the `REPL`
 
```
+ react-dom@16.13.0
+ react@16.13.0
updated 2 packages and audited 36 packages in 1.479s
found 0 vulnerabilities

[:browser-repl] Configuring build.
[:browser-repl] Compiling ...
[:browser-repl] Build completed. (157 files, 156 compiled, 0 warnings, 21.91s)
cljs.user=>
```

# Development workflow using the provided `dev.clj` namespace

We also provide a `dev.clj` namespace in `dev/dev.clj` which allows for a more Clojure-_friendly_ workflow during development.
This namespace contains a few utility functions for _programmatically_ interacting with `shadow-cljs` through its Clojure API.
The first step is to `eval` those functions in your REPL.

```clojure
...
(defn- compile-build
  "Compiles a build `build-id` where `build-id` is a
  keyword identifying a build.
  This is equivalent to invoking :
  $ shadow-cljs compile :build-id"
  [build-id]
  (shadow/compile build-id))
=> nil
=> #'dev/start-server
=> #'dev/stop-server
=> #'dev/re-init-server
=> #'dev/watch-build
=> #'dev/compile-build

```

You can then `watch` or `compile` a build, `start`, `stop` or `re-init` the `shadow-cljs` server by just calling those functions from within the REPL.

## Starting the shadow-cljs server

```clojure
;; starts a shadow-cljs server
(start-server)
avr. 13, 2020 1:08:25 PM org.xnio.Xnio <clinit>
INFO: XNIO version 3.7.3.Final
avr. 13, 2020 1:08:25 PM org.xnio.nio.NioXnio <clinit>
INFO: XNIO NIO Implementation Version 3.7.3.Final
avr. 13, 2020 1:08:25 PM org.jboss.threads.Version <clinit>
INFO: JBoss Threads version 2.3.2.Final
shadow-cljs - server version: 2.8.94 running at http://localhost:9630
shadow-cljs - nREPL server started on port 34619
=> :shadow.cljs.devtools.server/started

;; since a shadow-cljs server is already running
(start-server)
=> :shadow.cljs.devtools.server/already-running
```

## Shutting down the shadow-cljs server

```clojure
;; stops the running shadow-cljs server
(stop-server)
shutting down ...
=> nil
```

## Re-initializing the shadow-cljs server

```clojure
;; re-initializes the running shadow-cljs server
(re-init-server)
shadow-cljs - server version: 2.8.94 running at http://localhost:9630
shadow-cljs - nREPL server started on port 42915
=> :shadow.cljs.devtools.server/started
```

## Watching a build

```clojure
;; watches a build (e.g here :dashboard)
(watch-build :dashboard)
[:dashboard] Configuring build.
[:dashboard] Compiling ...
=> :watching
[:dashboard] Build completed. (157 files, 1 compiled, 0 warnings, 2,18s)
...
```

## Compiling a build

```clojure
;; compiles a build (e.g here :dashboard)
(compile-build :dashboard)
[:dashboard] Compiling ...
[:dashboard] Build completed. (59 files, 0 compiled, 0 warnings, 0,58s)
=> :done
```
