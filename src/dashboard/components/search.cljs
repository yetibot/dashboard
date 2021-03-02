(ns dashboard.components.search
  (:require [taoensso.timbre :as log
             :refer-macros [log trace debug info warn error fatal report
                            logf tracef debugf infof warnf errorf fatalf reportf
                            spy get-env]]
            ["bloomer" :as bloomer]))

(defn search
  "Search component skeleton"
  []
  [:> bloomer/Control {:is-expanded true
                       :has-icons   "left"}
   [:input {:type        :text
            :value       ""
            :placeholder "Search history"
            :iscolor     "light"
            :on-change   (fn [e]
                           (info "changed this to stuff"))}]
   [:> bloomer/Icon {:is-size  "small"
                     :is-align "left"
                     :class    "fa fa-search"}]])