(ns sicp.chpt2.ex2-33
  (:refer-clojure :exclude [map]))


;;; Clojure comes with a built in `fold-left` called `reduce`, there isn't an implementation of `accumulate` which is `fold-right`


;;; __accumulate__


(defn accumulate
  [op initial sequence]
  (if (empty? sequence)
    initial
    (op (first sequence)
        (accumulate op initial (rest sequence)))))


;;; __map__


(defn map
  [p sequence]
  (accumulate (fn [x y] (conj y (p x))) () sequence))


;;      (map inc [1 2 3 4 5])
;;      => (2 3 4 5 6)


;;; __append__


(defn append
  [seq1 seq2]
  (accumulate cons seq2 seq1))


;;      (append [1 2 3] [4 5 6])
;;      => (1 2 3 4 5 6)


;;; __length__


(defn length
  [sequence]
  (accumulate (fn [_ n] (inc n)) 0 sequence))


;;      (length [1 2 3 4 5])
;;      => 5
