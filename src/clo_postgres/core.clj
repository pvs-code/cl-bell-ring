(ns clo-postgres.core
  (:require [clojure.java.jdbc :as j]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
(def db {
         :subprotocol "postgresql"
         :subname "test-db"
         :user "pvs"
         :password ""})
(defn t []
  (j/query db
    ["select * from ttt where a = ?" "1"]
    ))

(+ 2 3)