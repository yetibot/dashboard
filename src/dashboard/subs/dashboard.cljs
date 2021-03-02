(ns dashboard.subs.dashboard
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 :dashboard.stats/uptime
 (fn [db _]
   (get-in db [:dashboard/stats :uptime])))

(rf/reg-sub
 :dashboard.stats/adapter-count
 (fn [db _]
   (get-in db [:dashboard/stats :adapter-count])))

(rf/reg-sub
 :dashboard.stats/command-count
 (fn [db _]
   (get-in db [:dashboard/stats :command-count])))

(rf/reg-sub
 :dashboard.stats/command-count-today
 (fn [db _]
   (get-in db [:dashboard/stats :command-count-today])))

(rf/reg-sub
 :dashboard.stats/user-count
 (fn [db _]
   (get-in db [:dashboard/stats :user-count])))

(rf/reg-sub
 :dashboard.stats/history-count
 (fn [db _]
   (get-in db [:dashboard/stats :history-count])))

(rf/reg-sub
 :dashboard.stats/history-count-today
 (fn [db _]
   (get-in db [:dashboard/stats :history-count-today])))

(rf/reg-sub
 :dashboard.stats/alias-count
 (fn [db _]
   (get-in db [:dashboard/stats :alias-count])))

(rf/reg-sub
 :dashboard.stats/observer-count
 (fn [db _]
   (get-in db [:dashboard/stats :observer-count])))

(rf/reg-sub
 :dashboard.stats/cron-count
 (fn [db _]
   (get-in db [:dashboard/stats :cron-count])))
