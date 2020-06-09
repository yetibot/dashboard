(ns dashboard.components.dashboard
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [taoensso.timbre :as log
             :refer-macros [log trace debug info warn error fatal report
                            logf tracef debugf infof warnf errorf fatalf reportf
                            spy get-env]]
            ["bloomer" :refer (Tile Hero HeroBody Title Subtitle Notification)]
            ["react-router-dom" :refer (NavLink)]
            [dashboard.components.search :refer [search]]))

(defn dashboard
  []
  [:div
   [:> Hero {:isbold "true" :iscolor "true" :issize "small"}
    [:> HeroBody
     [:> Title "Dashboard"]
     [:> Subtitle "Uptime"]]]
   [:div.tiles
    [:> Tile {:isancestor "true" :hastextalign "centered"}
     [:> NavLink {:class "tile is-parent is-4" :to "/adapters"}
      [:> Tile {:ischild "true" :class "box"}
       [:> Title "stats.adapter_count"]
       [:> Subtitle "Adapters"]]]]]])