(ns dev.preload)

(defn ^:before-load before-load []
  (println "Reloading..."))

(defn ^:after-load after-load []
  (println "Reload complete")))
