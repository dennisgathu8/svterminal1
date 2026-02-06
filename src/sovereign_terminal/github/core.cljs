(ns sovereign-terminal.github.core
  (:require [clojure.string :as str]))

;; GitHub API configuration
(defonce github-config (atom {:token nil
                             :api-base "https://api.github.com"}))

(defn set-token! [token]
  "Set the GitHub API token."
  (swap! github-config assoc :token token))

(defn make-request [endpoint method body]
  "Make a GitHub API request."
  (let [config @github-config
        headers {"Accept" "application/vnd.github.v3+json"
                 "Content-Type" "application/json"}
        headers (if (:token config)
                  (assoc headers "Authorization" (str "token " (:token config)))
                  headers)]
    {:method method
     :headers headers
     :body (when body (js/JSON.stringify (clj->js body)))}))

(defn fetch-user []
  "Fetch the authenticated GitHub user."
  (let [endpoint (str (:api-base @github-config) "/user")]
    (js/fetch endpoint (make-request nil "GET"))))

(defn search-repos [query]
  "Search GitHub repositories."
  (let [endpoint (str (:api-base @github-config) "/search/repositories?q=" query)]
    (js/fetch endpoint (make-request nil "GET"))))

;; GitHub commands
(defn github-command [state args]
  "Handle github command from terminal."
  (let [subcmd (first args)]
    (case subcmd
      "login" (do
               (swap! state update :output conj "GitHub login: Please provide your token")
               (swap! state update :output conj "Usage: github set-token <token>"))
      
      "set-token" (let [token (second args)]
                    (set-token! token)
                    (swap! state update :output conj "GitHub token set successfully"))
      
      "user" (swap! state update :output conj "GitHub user: Feature coming soon")
      
      "search" (let [query (str/join " " (rest args))]
                 (swap! state update :output conj (str "Searching for: " query)))
      
      (swap! state update :output conj "Usage: github <login|set-token|user|search>"))))
