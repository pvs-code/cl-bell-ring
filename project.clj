(defproject clo-postgres "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [seesaw "1.4.4"]
                 [org.postgresql/postgresql "9.3-1100-jdbc4"]
                 [org.clojure/java.jdbc "0.3.5"]
                 [org.clojure/algo.monads "0.1.5"]
                 [org.clojure/tools.logging "0.3.1"]
                 [seesaw "1.4.1"]
                 [javazoom/jlayer "1.0.1"]
                 [clj-time "0.8.0"]]
  :plugins [                [cider/cider-nrepl "0.7.0"]]
:resource-paths ["resources"]
  :main pvs.core
)
