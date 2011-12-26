(ns sicp.chpt1.ex1-11)


(defn f-recursive
  [n]
  (if (< n 3)
    n
    (+ (f-recursive (- n 1))
       (* 2 (f-recursive (- n 2)))
       (* 3 (f-recursive (- n 3))))))


(defn f-iterative
  [n]
  (loop [a 0 b 1 c 2 i n]
    (if (zero? i)
      a
      (recur b c (+ c (* 2 b) (* 3 a)) (dec i)))))