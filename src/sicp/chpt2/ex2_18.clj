(ns sicp.chpt2.ex2-18
  (:refer-clojure :exclude [reverse]))


;;; __Recursive__


(defn reverse
  [l]
  (if (empty l)
    ()
    (conj (reverse l) (first l))))


;;; __Iterative__


(defn reverse
  ([l]
     (reverse l ()))
  ([l acc]
     (if (empty? l)
       acc
       (recur (rest l) (conj acc (first l))))))


;;; __Other Implementations__


(def reverse clojure.core/reverse)
