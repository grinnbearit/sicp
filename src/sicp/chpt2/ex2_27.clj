(ns sicp.chpt2.ex2-27)


(defn deep-reverse
  [x]
  (cond (empty? x)
        []

        (coll? (first x))
        (conj (deep-reverse (rest x))
              (deep-reverse (first x)))

        :else
        (conj (deep-reverse (rest x))
              (first x))))


(def x (list (list 1 2) (list 3 4)))


;;;     (reverse x)
;;;     => ((3 4) (1 2))


;;;     (deep-reverse x)
;;;     => [[4 3] [2 1]]
