(defproject dashboard "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.9.1"

  :dependencies [[org.clojure/clojure "1.10.0"]

                 ;; clojurescript and tooling
                 [org.clojure/clojurescript "1.10.520" :scope "provided"]
                 [binaryage/devtools "1.0.0"]
                 [thheller/shadow-cljs "2.8.94"]

                 ;; react
                 [reagent "0.10.0"]

                 ;; logging
                 [com.taoensso/timbre "4.10.0"]

                 ;; These has to be explicitly specified as lein does not
                 ;; properly manage dependency version conflicts :
                 ;; https://github.com/thheller/shadow-cljs/issues/488#issuecomment-486732296
                 [com.google.javascript/closure-compiler-unshaded "v20190325"]
                 [org.clojure/google-closure-library "0.0-20190213-2033d5d9"]]

  :source-paths ["src"]

  :profiles {:dev {}})
