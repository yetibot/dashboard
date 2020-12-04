(defproject dashboard "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.9.1"

  :dependencies [[org.clojure/clojure "1.10.1"]

                 ;; clojurescript and tooling
                 [org.clojure/clojurescript "1.10.597" :scope "provided"
                  :exclusions [com.google.javascript/closure-compiler-unshaded
                               org.clojure/google-closure-library
                               org.clojure/google-closure-library-third-party]]
                 [thheller/shadow-cljs "2.8.94"]
                 
                 ;; react
                 [reagent "0.10.0"]
                 [re-frame "1.1.2"]
                 [day8.re-frame/tracing "0.6.0"]

                 ;; logging
                 [com.taoensso/timbre "4.10.0"]

                 ;; graphql
                 [re-graph "0.1.13" :exclusions [re-graph.hato]]
                 [district0x/graphql-query "1.0.5"]

                 ;; utils
                 [com.cognitect/transit-cljs "0.8.264"]
                 [camel-snake-kebab "0.4.1"]]


  :plugins [[lein-shell "0.5.0"]]

  :source-paths ["src"]

  :aliases {"compile-cljs" ["do"
                            ["shell" "npm" "install"]
                            ["run" "-m" "shadow.cljs.devtools.cli" "compile" ":dashboard"]]
            "watch" ["do"
                     ["run" "-m" "shadow.cljs.devtools.cli" "watch" ":dashboard"]]
            "cljs-repl" ["do"
                         ["run" "-m" "shadow.cljs.devtools.cli" "cljs-repl" ":dashboard"]]
            "release-cljs" ["do"
                            ["run" "-m" "shadow.cljs.devtools.cli" "release" ":dashboard"]]}

  :profiles {:dev
             {:dependencies [[binaryage/devtools "1.0.0"]
                             [day8.re-frame/re-frame-10x "0.6.5"]
                             [day8.re-frame/tracing "0.5.5"]]}})
