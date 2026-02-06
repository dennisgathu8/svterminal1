(ns sovereign-terminal.ui.terminal
  (:require [reagent.core :as r]
            [sovereign-terminal.repl.core :as repl]))

(defn terminal-output [output]
  "Render terminal output lines."
  [:div.output
   (for [[idx line] (map-indexed vector output)]
     ^{:key idx} [:div.line line])])

(defn terminal-input [state]
  "Render the terminal input line."
  (let [path-str (clojure.string/join "/" (:current-path @state))
        prompt (str "[" path-str "] $ ")]
    [:div.input-line
     [:span.prompt prompt]
     [:input {:type "text"
              :value (:input @state)
              :on-change #(swap! state assoc :input (-> % .-target .-value))
              :on-key-down #(when (= (.-keyCode %) 13)
                             (repl/execute-command! state)
                             (swap! state assoc :input ""))}]]))

(defn render-terminal [state]
  "Main terminal component."
  [:div.terminal
   {:class (name (:theme @state))}
   [:div.terminal-header
    [:h1.title "The Sovereign Terminal"]
    [:div.status-bar
     [:span.mode (name (:mode @state))]
     [:span.history-count (str (count (:history @state)) " commands")]]]
   [terminal-output (:output @state)]
   [terminal-input state]])

(defn add-output! [state output]
  "Add output to the terminal."
  (swap! state update :output conj output))

(defn clear-output! [state]
  "Clear terminal output."
  (swap! state assoc :output []))
