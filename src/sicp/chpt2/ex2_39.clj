(ns sicp.chpt2.ex2-39
  (:refer-clojure :exclude [reverse]))


(defn fold-left
  [op initial sequence]
  (loop [acc initial s sequence]
    (if (empty? s)
      acc
      (recur (op acc (first s)) (rest s)))))


(defn fold-right
  [op initial sequence]
  (if (empty? sequence)
    initial
    (op (first sequence)
        (fold-right op initial (rest sequence)))))


;;; __with `fold-left`__


(defn reverse
  [sequence]
  (fold-left (fn [x y] (cons y x)) () sequence))


;;; __with `fold-right`__


(defn reverse
  [sequence]
  (fold-right (fn [x y] (concat y (list x))) () sequence))


;;      (reverse [1 2 3 4])
;;      => (4 3 2 1)
