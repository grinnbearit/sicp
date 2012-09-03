(ns sicp.chpt2.ex2-59)


(defn element-of-set?
  [x s]
  (cond (empty? s)
        false

        (= x (first s))
        true

        :else
        (recur x (rest s))))


(defn adjoin-set
  [x s]
  (if (element-of-set? x s)
    s
    (conj s x)))


(defn union-set
  [s1 s2]
  (cond (empty? s1)
        s2

        (empty? s2)
        s1

        (element-of-set? (first s1) s2)
        (recur (rest s1) s2)

        :else
        (conj (union-set (rest s1) s2) (first s1))))


;;     (union-set [1 2 3] [4 5 6])
;;     => [4 5 6 3 2 1]

;;     (union-set [1 2 3] [3 4 5])
;;     => [3 4 5 2 1]


;;; alternative implementation

(defn union-set
  [s1 s2]
  (reduce #(adjoin-set %2 %1) s1 s2))


;;     (union-set [1 2 3] [4 5 6])
;;     => [1 2 3 4 5 6]

;;     (union-set [1 2 3] [3 4 5])
;;     => [1 2 3 4 5]
