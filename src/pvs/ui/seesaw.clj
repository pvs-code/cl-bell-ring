(ns pvs.ui.seesaw
  (:require [seesaw.core :as s]
            [seesaw.font :as f]
            [seesaw.table :as tb]
            [pvs.sound.jlayer :as j]
            [pvs.time :as t])
  (:import [java.util.concurrent Executors ]))
(s/native!)
(def clock-field (s/label :font (f/font :name :monospaced
                                              :style #{:bold :italic}
                                              :size 28)
                                :border 5
                                :background :black
                                :foreground :green
                                :text "00:00"))
(defn b-time [] (map #(get (tb/value-at center % ) :time) (range (tb/row-count center))))
(defn next-bell-time [cur-time]
  (some #(and (> (.compareTo % cur-time) 0) %) (b-time)))

(def bell-field (s/label :font (f/font :name :monospaced
                                              :style #{:bold :italic}
                                              :size 28)
                                :border 5
                                :background :black
                                :foreground :pink
                                :text  (or (next-bell-time (t/time-str ":")) (first (b-time)))))
(def north-items (list clock-field
                       (s/make-widget [5 :by 5])
                       bell-field
                       (s/make-widget [150 :by 5])
                       ))

(def table-mod [:columns [:text1 {:key :no :text "n"} {:key :time :text "время"}]
                              :rows [
                                     ["урок 1" 1 "10:00"]
                                     ["перемена 1" 2 "10:50"]
                                     ["урок 2" 3 "11:05"]
                                     ["перемена 2" 4 "11:45"]
                                     ["урок 3" 1 "11:50"]
                                     ["перемена 3" 5 "12:30"]
                                     ["урок 4" 6 "12:35"]
                                     ["обед" 7 "13:15"]
                                     ["урок 5" 8 "13:55"]
                                     ["перемена 5" 9 "14:35"]
                                     ["урок 6" 10 "14:45"]
                                     ["конец" 11  "15:25"]
                                     ]])
(def center (s/table :show-grid? true :show-horizontal-lines? true
                     :model table-mod))
(def dz (s/button :id :dz :text "дзынь !"))
(def exec (Executors/newSingleThreadExecutor))
(s/listen dz :action (fn [e] 
                         (.execute exec (fn [] 
                                          (do
                                            (s/config! dz :enabled? false)
                                            (.play (j/player))
                                            (s/config! dz :enabled? true))))))
(def f (s/frame :title "звонок" 
              :content (s/border-panel
                         :north (s/horizontal-panel :items north-items)
                         :center center
                         :south (s/horizontal-panel :items (list (s/button :text "GO") dz))
                         :vgap 5 :hgap 5 :border 5)))

(defn clock [f b]
  (do 
    (Thread/sleep 1000)
    (s/config! f :text (t/time-str ":"))
    (when-not (.equals (s/config b :text)  (or (next-bell-time (t/time-str ":")) (first (b-time))))
      (.execute exec (fn [] (.play (j/player) ))))
    (s/config! b :text (or (next-bell-time (t/time-str ":")) (first (b-time))))
    (Thread/sleep 1000)
    (s/config! f :text (t/time-str " "))

    (when-not (.equals (s/config b :text)  (or (next-bell-time (t/time-str ":")) (first (b-time))))
      (.execute exec (fn [] (.play (j/player) ))))
    (s/config! b :text (or (next-bell-time (t/time-str ":")) (first (b-time))))
))



(defn clock-thread []
  (.start (Thread. #(doall (repeatedly (fn [] (clock clock-field bell-field)))))
      ))

(def test-1 (or (next-bell-time (t/time-str ":")) (first (b-time))))
(defn run [] 
  (do
    (-> f s/pack! s/show!)
    ))
