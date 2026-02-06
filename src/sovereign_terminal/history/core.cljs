(ns sovereign-terminal.history.core
  (:require [clojure.string :as str]))

;; History storage (using localStorage in browser)
(defonce history-key "sovereign-terminal-history")
(defonce history (atom []))

(defn load-history! []
  "Load command history from localStorage."
  (when-let [stored (.getItem js/localStorage history-key)]
    (reset! history (cljs.reader/read-string stored))))

(defn save-history! []
  "Save command history to localStorage."
  (.setItem js/localStorage history-key (pr-str @history)))

(defn add-command! [command]
  "Add a command to history."
  (when (seq command)
    (swap! history conj {:command command
                         :timestamp (.toISOString (js/Date))})
    (save-history!)))

(defn clear-history! []
  "Clear command history."
  (reset! history [])
  (save-history!))

(defn get-history []
  "Get all history entries."
  @history))

(defn search-history [query]
  "Search command history."
  (filter #(str/includes? (:command %) query) @history))
