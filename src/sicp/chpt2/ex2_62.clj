(ns sicp.chpt2.ex2-62)


(defn union-set
  [s1 s2]
  (cond (empty? s1)
        s2

        (empty? s2)
        s1

        (< (first s1) (first s2))
        (conj (union-set (rest s1) s2) (first s1))

        (> (first s1) (first s2))
        (conj (union-set s1 (rest s2)) (first s2))

        :else
        (conj (union-set (rest s1) (rest s2))
              (first s1))))


;;     (union-set '(1 3 5) '(2 3 6))
;;     => (1 2 3 5 6)
