(ns sicp.chpt1.ex1-44)


(def dx 1/100000)


(defn smooth
  [f]
  (fn [x]
    (/ (+ (f (- x dx))
          (f x)
          (f (+ x dx)))
       3)))


(defn repeated
  [f n]
  (loop [g f i n]
    (cond (= 1 i)
          g

          (even? i)
          (recur (comp g g) (/ i 2))

          :else
          (recur (comp f g) (dec i)))))


(defn n-fold-smooth
  [f n]
  ((repeated smooth n) f))
