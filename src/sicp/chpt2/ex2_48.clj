(ns sicp.chpt2.ex2-48
  (:use [sicp.chpt2.ex2-46 :only [make-vect]]))



(defrecord Segment [start end])


(defn make-segment
  [start end]
  (Segment. start end))


(defn start-segment
  [seg]
  (.start seg))


(defn end-segment
  [seg]
  (.end seg))


;;     (let [seg (make-segment (make-vect 0 0)
;;                             (make-vect 1 1))]
;;       [(start-segment seg)
;;        (end-segment seg)])
;;     => [#sicp.chpt2.ex2_46.Vector{:x 0, :y 0}
;;         #sicp.chpt2.ex2_46.Vector{:x 1, :y 1}]
