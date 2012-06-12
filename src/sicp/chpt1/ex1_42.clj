(ns sicp.chpt1.ex1-42)


(defn compose
  [f g]
  (fn [x]
    (f (g x))))


;;;     ((compose #(* % %) inc) 6)
;;;     => 49
