(ns sovereign-terminal.repl.eval
  (:require [clojure.string :as str]))

;; Simple REPL evaluation (browser-safe subset)
(defn eval-expression [expr]
  "Evaluate a Clojure expression."
  (try
    (cond
      (str/starts-with? expr "(+")
      (let [nums (map js/parseInt (re-seq #"\d+" expr))]
        (apply + nums))
      
      (str/starts-with? expr "(-")
      (let [nums (map js/parseInt (re-seq #"\d+" expr))]
        (apply - nums))
      
      (str/starts-with? expr "(*")
      (let [nums (map js/parseInt (re-seq #"\d+" expr))]
        (apply * nums))
      
      (str/starts-with? expr "(quot")
      (let [[a b] (map js/parseInt (re-seq #"\d+" expr))]
        (quot a b)))
    
    (catch :default e
      (str "Error: " (.-message e)))))

(defn eval-command [state args]
  "Evaluate a Clojure expression from the REPL."
  (let [expr (str/join " " args)
        result (eval-expression expr)]
    (swap! state update :output conj (str "=> " result))
    nil))
