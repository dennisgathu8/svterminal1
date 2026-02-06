(ns sovereign-terminal.ui.components
  (:require [reagent.core :as r]))

(defn button [props label]
  "A styled button component."
  [:button.button props label])

(defn input-field [props]
  "A styled input field component."
  [:input.input-field props])

(defn card [props & content]
  "A card container component."
  [:div.card props content])

(defn modal [is-open title content on-close]
  "A modal dialog component."
  (when is-open
    [:div.modal-overlay
     {:on-click on-close}
     [:div.modal-content
      {:on-click #(.stopPropagation %)}
      [:h2.modal-title title]
      content]]))

(defn spinner []
  "A loading spinner component."
  [:div.spinner])

(defn badge [label]
  "A badge component."
  [:span.badge label])
