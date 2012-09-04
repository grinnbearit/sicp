(ns sicp.chpt2.ex2-61)


(defn adjoin-set
  [x s]
  (cond (empty? s)
        (conj s x)

        (< x (first s))
        (conj s x)

        :else
        s))


;;     (adjoin-set 3 '(2 3 4))
;;     => (2 3 4)


;;     (adjoin-set 1 '(2 3 4))
;;     => (1 2 3 4)
