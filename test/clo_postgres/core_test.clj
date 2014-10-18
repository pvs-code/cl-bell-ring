(ns clo-postgres.core-test
  (:require [clojure.test :refer :all]
            [clo-postgres.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))
(defn disp [content]
  (config! f :content content))