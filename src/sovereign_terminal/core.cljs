(ns sovereign-terminal.core
  (:require [reagent.core :as r]
            [reagent.dom :as d]
            [sovereign-terminal.ui.terminal :as terminal]
            [sovereign-terminal.history.core :as history]
            [sovereign-terminal.repl.core :as repl]))

;; Application state
(defonce app-state (r/atom {:input ""
                           :history []
                           :current-path []
                           :output []
                           :theme :dark
                           :mode :normal}))

(defn init! []
  "Initialize the application."
  (println "The Sovereign Terminal initialized")
  (history/load-history!))

(defn render []
  "Render the application to the DOM."
  (d/render [terminal/render-terminal app-state] 
            (.getElementById js/document "app")))

;; Export for shadow-cljs
(defonce _init (do (init!) (render)))

;; Hot reload support
(defn ^:after-load reload []
  (render))
