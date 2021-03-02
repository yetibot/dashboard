# dashboard

The new (as of 2020) CLJS frontend dashboard for Yetibot

# Prerequisites

## Shadow-cljs

We chose `shadow-cljs` as our main build tool for the project. `shadow-cljs` is easy to use and configure and comes with a very good integration with the other tools that we already use on the other sub-projects under `Yetibot`, for e.g `Leiningen`.

See the [`shadow-cljs` _user-guide_](https://shadow-cljs.github.io/docs/UsersGuide.html "Shadow-cljs user guide") on how to install it.

# Development workflow using the shadow-cljs CLI

There are two (2) main steps to get into the development workflow :

* Starting the `shadow-cljs` server which will be (re)used by any process to interact with the app.
* Starting a `watch` process which will, as its name suggests, watch and push any changes in the project code to the runtime (_hot reload_).

> Starting a `watch` process actually starts a server process _automatically_ (step 2).
> 
> When the _server_ is launched via the _watch_ process, the _server_ will have to be killed if the _watch_ has to be restarted.
> When launched separately, the `watch` process can be killed and restarted without restarting the _server_ and the `watch` will just reuse the existing server process.

## Starting the `shadow-cljs` server

To start the server, just type in :

`$ shadow-cljs start`

It will then spin up a new jvm process for the server and the following output should be displayed on the console.

```shell script
shadow-cljs - config: /home/kaffein/Projects/dashboard/shadow-cljs.edn
shadow-cljs - updating dependencies
shadow-cljs - dependencies updated
shadow-cljs - server starting ........................................................................
ready!
```

> As part of the spinning-up process, the server process will also expose an _nrepl_ server via TCP.
> It can be used to manage the `shadow-cljs` process or interact with the application in its runtime.

## Running a `watch` process

To start a `watch` process which will monitor changes and automatically recompile the `dashboard` code base, run the following incantation from your terminal:

`$ shadow-cljs watch dashboard`

the output on the console should give something like this :

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

To stop the `watch` process, just `CTRL-C` at the command line : 

```shell script
 ...
 [:dashboard] Configuring build.
 [:dashboard] Compiling ...
 [:dashboard] Build completed. (157 files, 156 compiled, 0 warnings, 35.04s)

 Ctrl^C
```

## Checking the _dev_ setup

At this point, to check whether everything works as expected :

* open the application at `http://localhost:3000/` in the browser
* `jack-in`/`connect` your editor to the nrepl process

Once at the `repl` prompt, type in :

```shell script
To quit, type: :cljs/quit
...
cljs.user> (js/alert "test")
```
a popup should appear from inside the application.

# Compile the project

To compile the project once and exit :

`$ shadow-cljs compile :dashboard`

If everything goes right, you should have the following output on the console.

```shell script
 ...
 [:dashboard] Compiling ...
 [:dashboard] Build completed. (567 files, 1 compiled, 3 warnings, 1,96s)
```