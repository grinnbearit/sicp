(ns sicp.chpt1.ex1-43)


(defn repeated
  [f n]
  (loop [g f i n]
    (cond (= 1 i)
          g

          (even? i)
          (recur (comp g g) (/ i 2))

          :else
          (recur (comp f g) (dec i)))))


;;;     ((repeated #(* % %) 2) 5)
;;;     => 625
