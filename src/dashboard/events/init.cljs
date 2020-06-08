(ns dashboard.events.init
  (:require [re-frame.core :as rf]
            [re-graph.core :as re-graph]
            [dashboard.graphql.queries :as queries]
            [taoensso.timbre :as log]
            [day8.re-frame.tracing :refer-macros [fn-traced]]))

;--------------------------------------------------------------
; Initialization
;--------------------------------------------------------------
(rf/reg-event-fx
 ::init
 (fn [{:keys [db]} _]
   {:dispatch [::init-re-graph]}))

;--------------------------------------------------------------
; Re-graph initialization
;--------------------------------------------------------------
(rf/reg-event-fx
 ::init-re-graph
 (fn-traced [_ _]
            {:dispatch [::re-graph/init
                        {:http
                         {:url "https://public.yetibot.com/graphql"}
                         :ws
                         {:url nil}}]}))

;--------------------------------------------------------------
; Dashboard
;--------------------------------------------------------------
(declare on-stats)

(rf/reg-event-fx
 :dashboard.stats/fetch
 (fn-traced [_ [_ timezone-offset-hours]]
   {:dispatch [::re-graph/query
               queries/stats
               {:timezone-offset-hours timezone-offset-hours}
               on-stats]}))

(defn on-stats
  [{:keys [data errors]}]
  (if errors
    (rf/dispatch [::on-error :dashboard/error (str "An error occured while fetching statistics data" errors)])
    (rf/dispatch [:dashboard.stats/store data])))

(rf/reg-event-db
 :dashboard.stats/store
 (fn-traced [db [_ data]]
            (assoc db :dashboard/stats data)))

;--------------------------------------------------------------
; Generic error-handling
;--------------------------------------------------------------
(rf/reg-event-db
 ::on-error
 (fn [db [{:keys [event-id & parameters]}]]
   {:db (assoc db :error/event-id event-id
               :error/parameters parameters)}))
