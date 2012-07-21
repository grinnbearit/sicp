(ns sicp.chpt2.ex2-38)


;;; Clojure's `reduce` is equivalent to `fold-left`


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


;;      (fold-right / 1 (list 1 2 3))
;;      => 3/2

;;      (fold-left / 1 (list 1 2 3))
;;      => 1/6

;;      (fold-right list nil (list 1 2 3))
;;      => (1 (2 (3 nil)))

;;      (fold-left list nil (list 1 2 3))
;;      => (((nil 1) 2) 3)


;;; for `fold-left` and `fold-right` to produce the same values on any sequence, the operation performed should be
;;; [associative](http://en.wikipedia.org/wiki/Associative), i.e. \\(\lambda(x,\lambda(y, z)) = \lambda(\lambda(x, y), z)\\)

;;      (fold-left + 0 (list 1 2 3))
;;      => 6

;;      (fold-right + 0 (list 1 2 3))
;;      => 6
