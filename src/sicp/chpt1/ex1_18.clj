(ns sicp.chpt1.ex1-18
  (:refer-clojure :exclude [double]))


;;; For readability, double and halve

(def double #(bit-shift-left % 1))
(def halve #(bit-shift-right % 1))


;;; Iterative version of `fast-*`

;;; Keeping \\(ba+n\\) constant in every iteration


(defn fast-*
  ([a b]
     (fast-* a b 0))
  ([a b n]
     (cond (zero? b)
           n

           (even? b)
           (recur (double a) (halve b) n)

           :else
           (recur a (dec b) (+ a n)))))
