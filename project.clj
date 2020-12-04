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

  :npm-deps [[react "16.13.1"]
             ["bloomer" "0.6.5"]
             ["bulma" "0.8.2"]
             ["bulma-checkradio" "1.1.1"]
             ["create-react-class" "15.6.3"]
             ["highlight.js" "9.18.1"]
             ["react" "16.13.1"]
             ["react-dom" "16.13.1"]
             ["react-highlight.js" "1.0.7"]
             ["react-router-dom" "5.1.2"]]

  :plugins [[lein-shadow "0.3.1"]
            [lein-shell "0.5.0"]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :shadow-cljs {:nrepl {:port 8777}
                
                :builds {:dashboard {:target :browser
                                     :output-dir "resources/public/js/compiled"
                                     :asset-path "/js/compiled"
                                     :modules {:app {:init-fn dashboard.core/init
                                                     :preloads [devtools.preload
                                                                day8.re-frame-10x.preload]}}
                                     :dev {:compiler-options {:closure-defines {re-frame.trace.trace-enabled? true
                                                                                day8.re-frame.tracing.trace-enabled? true}}}
                                     :release {:build-options
                                               {:ns-aliases
                                                {day8.re-frame.tracing day8.re-frame.tracing-stubs}}}
                        
                                     :devtools {:http-root "resources/public"
                                                :http-port 8280}}}}

  :aliases {"cljs-repl" [["with-profile" "dev" "do"
                            ["shadow" "cljs-repl" "dashboard"]]]

            "watch"        ["with-profile" "dev" "do"
                            ["shadow" "watch" "dashboard"]]
            
            "compile-cljs" ["with-profile" "dev" "do"
                            ["shadow" "compile" "dashboard"]]
           
            "release-cljs"      ["with-profile" "prod" "do"
                                 ["shadow" "release" "dashboard"]]}

  :profiles {:dev
             {:dependencies [[binaryage/devtools "1.0.0"]
                             [day8.re-frame/re-frame-10x "0.6.5"]
                             [day8.re-frame/tracing "0.5.5"]]}})
