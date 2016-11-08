(ns pvs.time
  (:require [clj-time.core :as t]
            [clj-time.coerce :as c]))
(defn now [] (t/to-time-zone (c/from-long (System/currentTimeMillis)) (t/default-time-zone)))
(defn time-str [separator]
  (format "%02d%s%02d" (t/hour (now)) separator (t/minute (now))))
