(ns sicp.chpt2.ex2-28)


(defn fringe
  [l]
  (cond (empty? l)
        ()

        (coll? (first l))
        (concat (fringe (first l))
                (fringe (rest l)))

        :else
        (conj (fringe (rest l)) (first l))))


(def x (list (list 1 2) (list 3 4)))


;;     (fringe x)
;;     => (1 2 3 4)

;;     (fringe (list x x))
;;     => (1 2 3 4 1 2 3 4)
