(ns sicp.chpt1.ex1-17
  (:refer-clojure :exclude [double]))


;;; For readability, double and halve

(def double #(bit-shift-left % 1))
(def halve #(bit-shift-right % 1))


;;; Recursive version of fast-*

(defn fast-*
  [a b]
  (cond (zero? b)
        0

        (even? b)
        (recur (double a) (halve b))

        :else
        (+ a (fast-* a (dec b)))))
