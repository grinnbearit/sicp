(ns sicp.chpt1.ex1-10)


;;; Ackermann's function

(defn A
  [x y]
  (cond (= y 0)
        0

        (= x 0)
        (* 2 y)

        (= y 1)
        2

        :else
        (recur (- x 1)
               (A x (- y 1)))))


(A 1 10)
;; => 1024

(A 2 4)
;; => 65536


(A 3 3)
;; => 65536


(defn f [n] (A 0 n))
;;; f(n) = 2*n


(defn g [n] (A 1 n))
;;; f(n) = 2^n


(defn h [n] (A 2 n))
;;; f(n) = 2^2^2^2...n times
