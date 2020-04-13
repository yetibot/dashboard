(ns dev
  (:require [shadow.cljs.devtools.server :as server]
            [shadow.cljs.devtools.api :as shadow]))

(defn- start-server
  "Starts a shadow-cljs server process which will be used
  by all subsequent processes.
  This is equivalent to invoking :
  $ shadow-cljs start server"
  []
  (server/start!))

(defn- stop-server
  "Stops the running shadow-cljs server process.
  This is equivalent to invoking :
  $ shadow-cljs stop server"
  []
  (server/stop!))

(defn- re-init-server
  "Re-initializes the running shadow-cljs server"
  []
  (do
    (server/stop!)
    (server/start!)))

(defn- watch-build
  "Starts a watch on build `build-id` where `build-id` is
  a keyword identifying a build. Optionally starts a shadow-cljs
  server process if none is running.
  This is equivalent to invoking :
  $ shadow-cljs watch :build-id"
  [build-id]
  (shadow/watch build-id))

(defn- compile-build
  "Compiles a build `build-id` where `build-id` is a
  keyword identifying a build.
  This is equivalent to invoking :
  $ shadow-cljs compile :build-id"
  [build-id]
  (shadow/compile build-id))

(do

  ;; You can just `eval` the following functions to control any
  ;; aspect of your build.

  ;; starts a shadow-cljs server
  (start-server)
  ;; stops the running shadow-cljs server
  (stop-server)
  ;; re-initializes the running shadow-cljs server
  (re-init-server)
  
  ;; watches a build (e.g here :dashboard)
  (watch-build :dashboard)
  ;; compiles a build (e.g here :dashboard)
  (compile-build :dashboard)
  )
