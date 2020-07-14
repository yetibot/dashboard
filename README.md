# dashboard

The new (as of 2020) CLJS frontend dashboard for Yetibot

# Prerequisites

## Shadow-cljs

We chose `shadow-cljs` as our main build tool for the project. `shadow-cljs` is easy to use and configure and comes with a very good integration with the other tools that we already use on the other sub-projects under `Yetibot`, for e.g `Leiningen`.

See the [`shadow-cljs` _user-guide_](https://shadow-cljs.github.io/docs/UsersGuide.html "Shadow-cljs user guide") on how to install it.

## Leiningen

`Leiningen` is the Clojure build tool that `Yetibot` uses. For `dashboard`, `shadow-cljs` delegates the _dependency management_ and _build entrypoint_ to `leiningen`. Using this tool is the easiest way to get started with the project.

See the [`Leiningen` _installation docs_](https://leiningen.org/#install "Leiningen installation instructions") on how to install it.


# Development workflow using Leiningen (the easy way)

## Running a watch process

To start a `watch` process, which will monitor changes and automatically recompile the `dashboard` code base, run the following command from the termimal:

`$ lein watch`

a `watch` will implicitly start a _server process_ which will be re-used by all commands sent by the `shadow-cljs CLI`, instead of starting a new JVM.

```shell script
added 43 packages from 380 contributors and audited 44 packages in 0.988s
found 0 vulnerabilities

[:dashboard] Compiling ...
[:dashboard] Build completed. (562 files, 0 compiled, 0 warnings, 5,46s)
shadow-cljs - HTTP server available at http://localhost:8000
shadow-cljs - server version: 2.8.94 running at http://localhost:9630
shadow-cljs - nREPL server started on port 34945
shadow-cljs - watching build :dashboard
[:dashboard] Configuring build.
[:dashboard] Compiling ...
[:dashboard] Build completed. (582 files, 1 compiled, 0 warnings, 5,49s)

```

## Compiling the project

To `compile` the project, run the following command :

`$ lein compile-cljs`


```shell script
audited 44 packages in 0.6s
found 0 vulnerabilities

[:dashboard] Compiling ...
[:dashboard] Build completed. (562 files, 0 compiled, 0 warnings, 5,45s)

```

## Releasing for production

When ready for a deployment in `production`, run the following command :

`$ lein release-cljs`

This phase will produce a `minified` version of the code for each module thanks to the `Google Closure Compiler`.

```shell script
[:dashboard] Compiling ...
[:dashboard] Build completed. (562 files, 0 compiled, 0 warnings, 5,45s)
[:dashboard] Compiling ...
[:dashboard] Build completed. (223 files, 0 compiled, 0 warnings, 17,65s)

```

## Starting a CLJS REPL

To start a `cljs repl`, run the following command :

`$ lein cljs-repl`

The output of the command should indicate the address at which to access the application.

```shell script
audited 44 packages in 0.548s
found 0 vulnerabilities
[:dashboard] Compiling ...
[:dashboard] Build completed. (562 files, 0 compiled, 0 warnings, 5,65s)
[2020-07-13 18:08:13.196 - WARNING] TCP Port 9630 in use.
[2020-07-13 18:08:14.207 - WARNING] TCP Port 8000 in use.
shadow-cljs - HTTP server available at http://localhost:8001
shadow-cljs - server version: 2.8.94 running at http://localhost:9631
shadow-cljs - nREPL server started on port 35103

```
you can then open your browser at the address exposed by the `shadow-cljs HTTP server` (here, `http://localhost:8001`).

To make sure that the `cljs repl` is correctly connected to your JS runtime (here the _browser_), you can try to open a popup by `eval`-ing the following form to the repl :

```shell script
$ cljs.user=> (js/alert "Hey Yeti!!")   
nil
```

a popup should be displayed in the browser tab/window containing the application.

Otherwise an explicit error is displayed in the `repl` if the JS runtime has not loaded our Clojurescript compiled code.

```shell script
cljs.user=> (js/alert "Hey Yeti!!")
No application has connected to the REPL server. Make sure your JS environment has loaded your compiled ClojureScript code.
```

At this point, simply opening the browser at the correct address should solve the issue.

---
**NOTE**

Depending on your workflow, it is also possible to use `shadow-cljs` directly via its `CLI` or programmatically through its `api` as detailed below.

---


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

The first step is to start a regular `Clojure repl` from your terminal (or using your IDE) using :

`$ lein repl`

and then `eval` the content of the `dev.clj` in the newly-started REPL :

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