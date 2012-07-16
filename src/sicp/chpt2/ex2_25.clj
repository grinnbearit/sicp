(ns sicp.chpt2.ex2-25)


;;; `(1 3 (5 7) 9)`


(let [s [1 3 [5 7] 9]]
  (first (rest (first (rest (rest s))))))


;;; `((7))`

(let [s [[7]]]
  (first (first s)))


;;; `(1 (2 (3 (4 (5 (6 7))))))`

(let [s [1 [2 [3 [4 [5 [6 7]]]]]]]
  (first (rest (first (rest (first (rest (first (rest (first (rest (first (rest s)))))))))))))
