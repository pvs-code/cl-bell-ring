(ns pvs.core
  (:require [pvs.ui.seesaw :as ui])
  (:gen-class :main true))
(defn -main [& args]
  (ui/run)
  (ui/clock-thread))
