# dashboard

The new (as of 2020) CLJS frontend dashboard for Yetibot

# Prerequisites

## Shadow-cljs

We chose `shadow-cljs` as our main build tool for the project. `shadow-cljs` is easy to use and configure and comes with a very good integration with the other tools that we already use on the other sub-projects under `Yetibot`, for e.g `Leiningen`.

See the `shadow-cljs` _user-guide_ on how to install it.

## Leiningen

`Leiningen` is the Clojure build tool that `Yetibot` uses. For `dashboard`, `shadow-cljs` delegates the _dependency management_ to `leiningen`.

See the `Leiningen` Installation docs to install it.

# Running the project

## Running a watch process

To start a `watch` process which will monitor changes and automatically recompile the `dashboard` code base, run :

`$ shadow-cljs watch :dashboard`

a `watch` will implicitly start a _server process_ which will be re-used by all commands sent by the CLI, instead of starting a new JVM.

```
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