(ns dashboard.core
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [re-frame.core :as rf]
            [taoensso.timbre :as log
             :refer-macros [log trace debug info warn error fatal report
                            logf tracef debugf infof warnf errorf fatalf reportf
                            spy get-env]]
            ["bloomer" :refer (Navbar Container NavbarStart NavbarBrand NavbarItem
                                      Icon MenuList MenuLabel Menu Field NavbarEnd)]
            ["react-router-dom" :refer (Route NavLink) :rename {BrowserRouter Router}]
            [dashboard.components.search :refer [search]]
            [dashboard.components.dashboard :refer [dashboard]]
            [dashboard.components.history :refer [history]]
            [dashboard.events.init :as init]))

(enable-console-print!)

(println "This text is printed from src/dashboard/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

;; Imported components
(def Dashboard (r/reactify-component dashboard))
(def History (r/reactify-component history))

(defn nav-bar
  "Top nav bar"
  []
  [:> Navbar {:class "is-white is-fixed-top"}
   [:> Container
    [:> NavbarStart
     [:> NavbarBrand
      [:> NavbarItem
       [:> NavLink {:to "/"}
        [:img {:style {:width  120
                       :height 28}
               :class "yetibot-logo"
               :alt   "Yetibot"
               :src   "https://yetibot.com/img/yetibot_lambda_blue_with_grey.svg"}]]]]]
    [:> NavbarEnd
     [:> NavbarItem
      [:> Field
       [search]]]]]])

(defn menu
  []
  [:div.column
   [:> Menu
    ;; yetibot
    [:> MenuLabel "Yetibot"]
    [:> MenuList
     [:li
      [:> NavLink {:exact true
                   :to    "/"} "Dashboard"]]
     [:li
      [:> NavLink {:exact true
                   :to "/history"} "History"]]
     [:li
      [:> NavLink {:to "/users"} "Users"]]
     [:li
      [:> NavLink {:to "/adapters"} "Adapters"]]
     [:li
      [:> NavLink {:to "/aliases"} "Aliases"]]
     [:li
      [:> NavLink {:to "/observers"} "Observers"]]
     [:li
      [:> NavLink {:to "/cron"} "Cron tasks"]]
     [:li
      [:> NavLink {:to "/repl"} "REPL"]]]

    ;; links
    [:> MenuLabel "Links"]
    [:> MenuList
     [:li
      [:a {:href "https://yetibot.com"}
       [:> Icon {:is-size "small"
                 :is-align "left"
                 :class "fa fa-external-link-alt"}]
       "Yetibot.com"]]
     [:li
      [:a {:href "https://github.com/yetibot/yetibot"}
       [:> Icon {:is-size "small"
                 :is-align "left"
                 :class "fa fa-external-link-alt"}]
       "Github"]]
     [:li
      [:a {:href "https://yetibot.com/archives"}
       [:> Icon {:is-size "small"
                 :is-align "left"
                 :class "fa fa-external-link-alt"}]
       "Blog"]]
     [:li
      [:a {:href "https://yetibot.com/user-guide"}
       [:> Icon {:is-size "small"
                 :is-align "left"
                 :class "fa fa-external-link-alt"}]
       "Docs"]]]]])

(defn routes
  []
  [:<>
   [:> Route {:path "/" :exact true :component Dashboard}]
   [:> Route {:path "/adapters"}]
   [:> Route {:path "/history" :exact true :component History}]
   [:> Route {:path "/users"}]
   [:> Route {:path "/user/:id"}]
   [:> Route {:path "/aliases"}]
   [:> Route {:path "/observers"}]
   [:> Route {:path "/cron"}]
   [:> Route {:path "/repl"}]])

(defn content-body
  "Content body"
  []
  [:> Container {:id "content-body"}
   [:div.columns
    [:div.column.is-2
     [menu]]
    [:div#content-container.column
     [routes]]]])

(defn dashboard-app
  "The dashboard component"
  []
  [:> Router
   [:div
    [nav-bar]
    [content-body]]])

(defn ^:dev/after-load start
  "Mounts the application root component in the DOM."
  []
  (rdom/render [dashboard-app] (js/document.getElementById "app")))

(defn ^:export init
  "Dashboard entrypoint which is called only once when `index.html` loads.
  It must be exported so it is available even in :advanced release builds."
  []
  (rf/dispatch-sync [::init/init])
  (start))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
