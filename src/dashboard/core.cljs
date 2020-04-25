(ns dashboard.core
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [taoensso.timbre :as log
             :refer-macros [log  trace  debug  info  warn  error  fatal  report
                            logf tracef debugf infof warnf errorf fatalf reportf
                            spy get-env]]
            ["bloomer" :as bloomer]
            ["react-router-dom" :as router]
            [dashboard.components.search :refer [search]]))

(enable-console-print!)

(println "This text is printed from src/dashboard/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(defn nav-bar
  "Top nav bar"
  []
  [:> bloomer/Navbar {:class "is-white is-fixed-top"}
   [:> bloomer/Container
    [:> bloomer/NavbarStart
     [:> bloomer/NavbarBrand
      [:> bloomer/NavbarItem
       [:> router/NavLink {:to "/"}
        [:img {:style {:width  120
                       :height 28}
               :class "yetibot-logo"
               :alt   "Yetibot"
               :src   "https://yetibot.com/img/yetibot_lambda_blue_with_grey.svg"}]]]]]
    [:> bloomer/NavbarEnd
     [:> bloomer/NavbarItem
      [:> bloomer/Field
       [search]]]]]])

(defn content-body
  "Content body"
  []
  [:> bloomer/Container {:id "content/body"}
   [:div {:class "columns"}
    [:div {:class "column is-2"}
     [:> bloomer/Menu
      ;; yetibot
      [:> bloomer/MenuLabel "Yetibot"]
      [:> bloomer/MenuList
       [:li
        [:> router/NavLink {:exact true
                             :to    "/"} "Dashboard"]]
       [:li
        [:> router/NavLink {:to "/history"} "History"]]
       [:li
        [:> router/NavLink {:to "/users"} "Users"]]
       [:li
        [:> router/NavLink {:to "/adapters"} "Adapters"]]
       [:li
        [:> router/NavLink {:to "/aliases"} "Aliases"]]
       [:li
        [:> router/NavLink {:to "/observers"} "Observers"]]
       [:li
        [:> router/NavLink {:to "/cron"} "Cron tasks"]]
       [:li
        [:> router/NavLink {:to "/repl"} "REPL"]]]

      ;; links
      [:> bloomer/MenuLabel "Links"]
      [:> bloomer/MenuList
       [:li
        [:a {:href "https://yetibot.com"}
         [:> bloomer/Icon {:is-size "small"
                           :is-align "left"
                           :class "fa fa-external-link-alt"}]
         "Yetibot.com"]]
       [:li
        [:a {:href "https://github.com/yetibot/yetibot"}
         [:> bloomer/Icon {:is-size "small"
                           :is-align "left"
                           :class "fa fa-external-link-alt"}]
         "Github"]]
       [:li
        [:a {:href "https://yetibot.com/archives"}
         [:> bloomer/Icon {:is-size "small"
                           :is-align "left"
                           :class "fa fa-external-link-alt"}]
         "Blog"]]
       [:li
        [:a {:href "https://yetibot.com/user-guide"}
         [:> bloomer/Icon {:is-size "small"
                           :is-align "left"
                           :class "fa fa-external-link-alt"}]
         "Docs"]]]]]]])

(defn dashboard-app
  "The dashboard component"
  []
  [:> router/BrowserRouter
   [:div
    [nav-bar]
    [content-body]]])

(defn start
    "Mounts the application root component in the DOM."
    []
    (rdom/render [dashboard-app] (js/document.getElementById "app")))

(defn ^:export init
  "Dashboard entrypoint which is called only once when `index.html` loads.
  It must be exported so it is available even in :advanced release builds."
  []
  (start))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
