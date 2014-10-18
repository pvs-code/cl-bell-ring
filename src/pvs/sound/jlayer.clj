(ns pvs.sound.jlayer
  (:import [javazoom.jl.player.advanced.AdvancedPlayer]))


;; tests
(def zfile (str "file://" (.getCanonicalPath (java.io.File. ".")) "/resources/SHkolnyj_zvonok_-_SHkolnyj_zvonok_(get-tune.net).mp3"))
(defn zstream [] (-> (java.net.URL. zfile) .openStream))
(defn player [] (javazoom.jl.player.advanced.AdvancedPlayer. (zstream) 
                                 (.createAudioDevice (javazoom.jl.player.FactoryRegistry/systemRegistry))))