(ns sovereign-terminal.history.core
  (:require [clojure.string :as str]))

;; History storage (using localStorage in browser)
(defonce history-key "sovereign-terminal-history")
(defonce history (atom []))

(defn load-history! []
  "Load command history from localStorage."
  (when-let [stored (.getItem js/localStorage history-key)]
    (try
      (reset! history (js->clj (js/JSON.parse stored)))
      (catch :default e
        (println "Failed to parse history:" e)
        (reset! history [])))))

(defn save-history! []
  "Save command history to localStorage."
  (.setItem js/localStorage history-key (js/JSON.stringify (clj->js @history))))

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
  @history)

(defn search-history [query]
  "Search command history."
  (filter #(str/includes? (:command %) query) @history))
