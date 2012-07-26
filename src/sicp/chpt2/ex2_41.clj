(ns sicp.chpt2.ex2-41)


;;; __using `mapcat`__


(defn ordered-triples
  [n]
  (mapcat (fn [i]
            (mapcat (fn [j]
                      (map (fn [k]
                             [i j k])
                           (range 1 (inc n))))
                    (range 1 (inc n))))
          (range 1 (inc n))))


(defn filtered-triples
  [n s]
  (letfn [(sum-s? [[i j k]]
            (= s (+ i j k)))]

    (filter sum-s? (ordered-triples n))))


;;      (filtered-triples 5 5)
;;      => ([1 1 3] [1 2 2] [1 3 1] [2 1 2] [2 2 1] [3 1 1])


;;; __using `for`__


(defn filtered-triples
  [n s]
  (for [i (range 1 (inc n))
        j (range 1 (inc n))
        k (range 1 (inc n))
        :when (= s (+ i j k))]
    [i j k]))
