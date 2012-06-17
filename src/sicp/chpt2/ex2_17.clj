(ns sicp.chpt2.ex2-17)


(defn last-pair
  [l]
  (if (empty? (rest l))
    l
    (recur (rest l))))


;;; __Other Implementations__


(def last-pair
  (comp list last))
