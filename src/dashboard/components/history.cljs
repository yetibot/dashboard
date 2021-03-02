(ns dashboard.components.history
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            ["bloomer" :refer (Tile Hero HeroBody Title Subtitle Notification)]
            ["react-router-dom" :refer (NavLink)]))

(defn history
  []
  (let [_ (rf/dispatch [:dashboard.history/fetch 480])]
    (fn []
      [:div
       [:> Hero {:is-bold "true" :is-color "info" :is-size "small"}
        [:> HeroBody
         [:> Title "History"]
         [:> Subtitle (str "Total items")]]]
       [:div.tiles
        [:> Tile {:is-ancestor "true" :has-text-align "centered"}
         ]]])))

