(ns dashboard.components.dashboard
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [reagent.dom :as rdom]
            [taoensso.timbre :as log
             :refer-macros [log trace debug info warn error fatal report
                            logf tracef debugf infof warnf errorf fatalf reportf
                            spy get-env]]
            ["bloomer" :refer (Tile Hero HeroBody Title Subtitle Notification)]
            ["react-router-dom" :refer (NavLink)]
            [dashboard.components.search :refer [search]]
            [dashboard.subs.dashboard]))

(defn display
  [label value]
  [:> Tile {:is-child "true" :class "box"}
    [:> Title @value]
    [:> Subtitle label]])

(defn expandable-stat-display
  [label value path]
  [:> NavLink {:class "tile is-parent is-4" :to path}
   [display label value]])

(defn stat-display
  [label value]
  [:> Tile {:is-size 4 :is-parent "true"}
   [display label value]])

(defn dashboard
  []
  (let [_ (rf/dispatch [:dashboard.stats/fetch 120])
        adapter-count (rf/subscribe [:dashboard.stats/adapter-count])
        command-count (rf/subscribe [:dashboard.stats/command-count])
        command-count-today (rf/subscribe [:dashboard.stats/command-count-today])
        user-count (rf/subscribe [:dashboard.stats/user-count])
        history-count (rf/subscribe [:dashboard.stats/history-count])
        history-count-today (rf/subscribe [:dashboard.stats/history-count-today])
        alias-count (rf/subscribe [:dashboard.stats/alias-count])
        observer-count (rf/subscribe [:dashboard.stats/observer-count])
        cront-count (rf/subscribe [:dashboard.stats/cron-count])
        uptime (rf/subscribe [:dashboard.stats/uptime])]
    (fn []
      [:div
       [:> Hero {:is-bold "true" :is-color "info" :is-size "small"}
        [:> HeroBody
       [:> Title "Dashboard"]
       [:> Subtitle (str "Uptime " @uptime)]]]
       [:div.tiles
        [:> Tile {:is-ancestor "true" :has-text-align "centered"}
         [expandable-stat-display "Adapters" adapter-count "/adapters"]
         [expandable-stat-display "Commands" command-count "/history?co=1"]
         [stat-display "Commands today" command-count-today]
         [expandable-stat-display "User" user-count "/users"]
         [expandable-stat-display "History items" history-count "/history"]
         [stat-display "History items today" history-count-today]
         [expandable-stat-display "Aliases" alias-count "/aliases"]
         [expandable-stat-display "Observers" observer-count "/observers"]
         [expandable-stat-display "Cron tasks" cront-count "/cron"]]]])))
