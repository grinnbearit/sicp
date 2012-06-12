(ns sicp.chpt1.ex1-41
  (:refer-clojure :exclude [double]))


(defn double
  [f]
  (fn [x]
    (f (f x))))


;;;     (((double (double double)) inc) 5)
;;;     => 21
