(ns sovereign-terminal.demos.core
  (:require [sovereign-terminal.repl.core :as repl]
            [clojure.string :as str]))

;; Demo command registry
(defonce demos (atom {}))

(defn register-demo! [name description handler]
  "Register a new demo."
  (swap! demos assoc name {:description description
                           :handler handler}))

(defn run-demo! [state demo-name]
  "Run a demo by name."
  (if-let [demo (get @demos demo-name)]
    (do
      (swap! state update :output conj (str "Running demo: " demo-name))
      ((:handler demo) state))
    (swap! state update :output conj (str "Demo not found: " demo-name))))

(defn list-demos []
  "List all available demos."
  (map name (keys @demos)))

;; Register some demos
(register-demo! 
  "hello" 
  "A simple hello world demo"
  (fn [state]
    (swap! state update :output conj "Hello, World!")
    (swap! state update :output conj "Welcome to The Sovereign Terminal!")
    nil))

(register-demo! 
  "math" 
  "Demonstrate basic arithmetic"
  (fn [state]
    (swap! state update :output conj "Math Operations Demo:")
    (swap! state update :output conj "  1 + 2 = 3")
    (swap! state update :output conj "  10 - 4 = 6")
    (swap! state update :output conj "  5 * 6 = 30")
    (swap! state update :output conj "  100 / 4 = 25")
    nil))

(register-demo! 
  "colors" 
  "Showcase terminal color codes"
  (fn [state]
    (swap! state update :output conj "Terminal Colors:")
    (swap! state update :output conj "  \u001b[31mRed text\u001b[0m")
    (swap! state update :output conj "  \u001b[32mGreen text\u001b[0m")
    (swap! state update :output conj "  \u001b[33mYellow text\u001b[0m")
    (swap! state update :output conj "  \u001b[34mBlue text\u001b[0m")
    (swap! state update :output conj "  \u001b[35mMagenta text\u001b[0m")
    (swap! state update :output conj "  \u001b[36mCyan text\u001b[0m")
    nil))

(register-demo! 
  "clock" 
  "Display a running clock"
  (fn [state]
    (swap! state update :output conj "Current time: " (.toLocaleTimeString (js/Date)))
    nil))
