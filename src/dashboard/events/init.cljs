(ns dashboard.events.init
  (:require [re-frame.core :as rf]
            [re-graph.core :as re-graph]
            [dashboard.graphql.queries :as queries]
            [taoensso.timbre :as log]))

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
 (fn [_ _]
   {:dispatch [::re-graph/init
               {:http
                {:url "https://public.yetibot.com/graphql"}}]}))

;--------------------------------------------------------------
; Dashboard
;--------------------------------------------------------------
(rf/reg-event-db
 :dashboard/stats
 (fn [_ [_ timezone-offset-hours]]
   {:dispatch [::re-graph/query
               queries/stats
               {:timezone_offset_hours timezone-offset-hours}
               [:dashboard/on-stats]]}))

(rf/reg-event-db
 :dashboard/on-stats
 (fn [db [_ {:keys [data errors] :as payload}]]
   (if errors
     {:dispatch [::on-error :dashboard/stats payload]}
     {:db (assoc db :dashboard/stats data)})))

;--------------------------------------------------------------
; Generic error-handling
;--------------------------------------------------------------
(rf/reg-event-db
 ::on-error
 (fn [db [{:keys [event-id & parameters]}]]
   {:db (assoc db :error/event-id event-id
               :error/parameters parameters)}))
