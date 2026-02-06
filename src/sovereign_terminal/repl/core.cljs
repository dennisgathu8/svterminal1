(ns sovereign-terminal.repl.core
  (:require [clojure.string :as str]
            [sovereign-terminal.history.core :as history]
            [sovereign-terminal.github.core :as github]))

;; Command registry
(defonce commands (atom {}))

(defn register-command! [name handler]
  "Register a new command handler."
  (swap! commands assoc name handler))

(defn execute-command! [state]
  "Execute the current input as a command."
  (let [input (str/trim (:input @state))
        [cmd & args] (str/split input #"\s+")]
    (when (seq input)
      (history/add-command! input)
      (if-let [handler (get @commands cmd)]
        (handler state args)
        (swap! state update :output conj (str "Command not found: " cmd))))))

;; Built-in commands
(register-command! "help" 
  (fn [state args]
    (swap! state update :output conj "Available commands:")
    (doseq [cmd (sort (keys @commands))]
      (swap! state update :output conj (str "  - " cmd)))
    nil))

(register-command! "clear"
  (fn [state args]
    (swap! state assoc :output [])
    nil))

(register-command! "echo"
  (fn [state args]
    (swap! state update :output conj (str/join " " args))
    nil))

(register-command! "pwd"
  (fn [state args]
    (swap! state update :output conj (str/join "/" (:current-path @state)))
    nil))

(register-command! "ls"
  (fn [state args]
    (swap! state update :output conj "Files and directories:")
    (swap! state update :output conj "  .")
    (swap! state update :output conj "  ..")
    nil))

(register-command! "date"
  (fn [state args]
    (swap! state update :output conj (.toISOString (js/Date)))
    nil))
