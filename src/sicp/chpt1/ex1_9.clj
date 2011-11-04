(ns sicp.chpt1.ex1-9
  (:refer-clojure :exclude [+]))


(defn +
  [a b]
  (if (= a 0)
    b
    (inc (+ (dec a) b))))


;;; This version is recursive

;; (+ 4 5)
;; (inc (+ 3 5))
;; (inc (inc (+ 2 5)))
;; (inc (inc (inc (+ 1 5))))
;; (inc (inc (inc (inc (+ 0 5)))))
;; (inc (inc (inc (inc 5))))
;; (inc (inc (inc 6)))
;; (inc (inc 7))
;; (inc 8)
;; 9


(defn +
  [a b]
  (if (= a 0)
    b
    (recur (dec a) (inc b))))


;;; This version is iterative

;; (+ 4 5)
;; (+ 3 6)
;; (+ 2 7)
;; (+ 1 8)
;; (+ 0 9)
;; 9