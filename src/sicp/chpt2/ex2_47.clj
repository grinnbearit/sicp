(ns sicp.chpt2.ex2-47
  (:use [sicp.chpt2.ex2-46 :only [make-vect]]))


;;; Clojure doesn't have a concept of pairs so `cons` can't be used


;;; __using `list`__


(defn make-frame
  [origin edge1 edge2]
  (list origin edge1 edge2))


(defn origin-frame
  [frame]
  (nth frame 0))


(defn edge1-frame
  [frame]
  (nth frame 1))


(defn edge2-frame
  [frame]
  (nth frame 2))


;;     (let [frame (make-frame (make-vect 0 0)
;;                             (make-vect 1 1)
;;                             (make-vect 2 2))]
;;       [(origin-frame frame)
;;        (edge1-frame frame)
;;        (edge2-frame frame)])
;;     => [#sicp.chpt2.ex2_46.Vector{:x 0, :y 0}
;;         #sicp.chpt2.ex2_46.Vector{:x 1, :y 1}
;;         #sicp.chpt2.ex2_46.Vector{:x 2, :y 2}]


;;; __using records__


(defrecord Frame [origin edge1 edge2])


(defn make-frame
  [origin edge1 edge2]
  (Frame. origin edge1 edge2))


(defn origin-frame
  [frame]
  (.origin frame))


(defn edge1-frame
  [frame]
  (.edge1 frame))


(defn edge2-frame
  [frame]
  (.edge2 frame))
