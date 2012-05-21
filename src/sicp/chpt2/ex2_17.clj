(ns sicp.chpt2.ex2-17)


(defn last-pair
  [l]
  (if (empty? (rest l))
    l
    (recur (rest l))))


;;; other implementations


(def last-pair
  (comp list last))
