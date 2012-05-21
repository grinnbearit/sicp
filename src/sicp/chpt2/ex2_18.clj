(ns sicp.chpt2.ex2-18
  (:refer-clojure :exclude [reverse]))


;;; recursive


(defn reverse
  [l]
  (if (empty l)
    ()
    (conj (reverse l) (first l))))


;;; iterative


(defn reverse
  ([l]
     (reverse l ()))
  ([l acc]
     (if (empty? l)
       acc
       (recur (rest l) (conj acc (first l))))))


;;; other implementations


(def reverse clojure.core/reverse)
