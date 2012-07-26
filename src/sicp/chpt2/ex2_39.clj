(ns sicp.chpt2.ex2-39
  (:refer-clojure :exclude [reverse])
  (:use [sicp.chpt2.ex2-38 :only [fold-left fold-right]]))


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
