(ns sicp.chpt2.ex2-40
  (:use [sicp.chpt1.ex1-22 :only [prime?]]))


;;; Clojure's `mapcat` is equivalent to `flatmap`


(defn unique-pairs
  [n]
  (mapcat (fn [i]
            (map (fn [j] [i j])
                 (range 1 i)))
          (range 2 (inc n))))


;;      (unique-pairs 3)
;;      => ([2 1] [3 1] [3 2])


(defn make-pair-sum
  [[i j]]
  [i j (+ i j)])


(defn prime-sum?
  [[_ _ sum]]
  (prime? sum))


(defn prime-sum-pairs
  [n]
  (->> (unique-pairs n)
       (map make-pair-sum)
       (filter prime-sum?)))


;;      (prime-sum-pairs 5)
;;      => ([2 1 3] [3 2 5] [4 1 5] [4 3 7] [5 2 7])


;;; `unique-pairs` lends itself better to a [list-comprehension](http://en.wikipedia.org/wiki/List_comprehension)
;;; which can be expressed in Clojure by `for`


(defn unique-pairs
  [n]
  (for [i (range 2 (inc n))
        j (range 1 i)]
    [i j]))


;;; `for` can also take a predicate making it possible to define `prime-sum-pairs` in a single step


(defn prime-sum-pairs
  [n]
  (for [i (range 2 (inc n))
        j (range 1 i)
        :when (prime? (+ i j))]
    [i j (+ i j)]))
