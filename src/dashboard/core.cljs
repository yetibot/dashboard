(ns dashboard.core
    (:require [reagent.core :as r]
              [reagent.dom :as rdom]))

(enable-console-print!)

(println "This text is printed from src/dashboard/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(defn dashboard-app
  "The dashboard component"
  []
  [:div.container
   [:h1.title "And we are good to go!!!"]])

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
