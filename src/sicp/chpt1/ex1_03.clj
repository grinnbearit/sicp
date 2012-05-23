(ns sicp.chpt1.ex1-03)


(defn sqr
  [x]
  (* x x))


(defn sum-of-squares
  [x y]
  (+ (sqr x) (+ sqr y)))


(defn sum-of-greater-squares
  [x y z]
  (cond
   (and (< x y) (< x z))                ; x < y,z
   (sum-of-squares y z)

   (< x y)                              ; z < x < y
   (sum-of-squares x y)

   (< x z)                              ; y < x < z
   (sum-of-squares x z)

   (< y z)                              ; y < z < x
   (sum-of-squares z x)

   :else                                ; z < y < x
   (sum-of-squares y x)))
