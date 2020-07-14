(defproject dashboard "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.9.1"

  :dependencies [[org.clojure/clojure "1.10.1"]

                 ;; clojurescript and tooling
                 [org.clojure/clojurescript "1.10.597" :scope "provided"]
                 [thheller/shadow-cljs "2.8.94"]
                 
                 ;; react
                 [reagent "0.10.0"]
                 [re-frame "0.12.0"]

                 ;; logging
                 [com.taoensso/timbre "4.10.0"]

                 ;; graphql
                 [re-graph "0.1.13" :exclusions [re-graph.hato]]
                 [district0x/graphql-query "1.0.5"]

                 ;; utils
                 [com.cognitect/transit-cljs "0.8.264"]
                 [camel-snake-kebab "0.4.1"]

                 ;; These has to be explicitly specified as lein does not
                 ;; properly manage dependency version conflicts :
                 ;; https://github.com/thheller/shadow-cljs/issues/488#issuecomment-486732296
                 [com.google.javascript/closure-compiler-unshaded "v20191027"]
                 [org.clojure/google-closure-library "0.0-20191016-6ae1f72f"]]

  :source-paths ["src"]

  :profiles {:dev
             {:dependencies [[binaryage/devtools "1.0.0"]
                             [day8.re-frame/re-frame-10x "0.6.5"]
                             [day8.re-frame/tracing "0.5.5"]]}})
