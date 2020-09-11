(ns dashboard.events.init
  (:require [re-frame.core :as rf]
            [re-graph.core :as re-graph]
            [camel-snake-kebab.core :as csk]
            [camel-snake-kebab.extras :as cske]
            [dashboard.graphql.queries :as queries]
            [taoensso.timbre :as log]
            [cognitect.transit :as t]
            [clojure.walk]
            [day8.re-frame.tracing :refer-macros [fn-traced]]))

#_(def ^:const graphql-endpoint "https://public.yetibot.com/graphql")
(def ^:const graphql-endpoint "http://localhost:3003/graphql")

(def json-reader (t/reader :json))

(def kebab-case-keywords (partial cske/transform-keys csk/->kebab-case-keyword))

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
   {:dispatch [::re-graph/init {:http
                                {:url graphql-endpoint
                                 :impl {:with-credentials? false}}
                                :ws
                                {:url nil}}]}))

;--------------------------------------------------------------
; Dashboard
;--------------------------------------------------------------
(rf/reg-event-fx
 :dashboard.stats/fetch
 (fn [_ [_ timezone-offset-hours]]
   {:dispatch [::re-graph/query
               queries/stats
               {:timezone_offset_hours timezone-offset-hours}
               [:dashboard.stats/store]]}))

(rf/reg-event-fx
 :dashboard.stats/store
 (fn [{:keys [db]} [_ payload]]
            (let [data (-> (t/read json-reader payload)
                           (clojure.walk/keywordize-keys)
                           kebab-case-keywords
                           (get-in [:data :stats]))]
              (if (nil? data)
                {:dispatch [::on-error :dashboard/error (str "An error occured while fetching statistics data")]}
                {:db (assoc db :dashboard/stats data)}))))

;--------------------------------------------------------------
; History
;--------------------------------------------------------------
(rf/reg-event-fx
 :dashboard.history/fetch
 (fn [_ [_ timezone-offset-hours]]
   {:dispatch [::re-graph/query
               queries/history
               {:commands_only true
                :yetibot_only true
                :search_query ""
                :timezone_offset_hours timezone-offset-hours}
               [:dashboard.history/store]]}))

(rf/reg-event-fx
 :dashboard.history/store
 (fn [{:keys [db]} [_ payload]]
   (println payload)
   (let [data (-> (t/read json-reader payload)
                             (clojure.walk/keywordize-keys)
                             kebab-case-keywords
                             (get-in [:data]))]
     (if (nil? data)
       {:dispatch [::on-error :dashboard/error (str "An error occured while fetching statistics data")]}
       {:db (assoc db :dashboard/stats data)}))))

;--------------------------------------------------------------
; Generic error-handling
;--------------------------------------------------------------
(rf/reg-event-db
 ::on-error
 (fn [db [{:keys [event-id & parameters]}]]
   {:db (assoc db :error/event-id event-id
               :error/parameters parameters)}))
