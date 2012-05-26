(ns sicp.chpt1.ex1-03)


(defn sqr
  [x]
  (* x x))


(defn sum-of-squares
  [x y]
  (+ (sqr x) (+ sqr y)))


;;; Each conditional step adds more information until we can make a decision

;;; 1. x < y,z
;;; 2. z < x < y
;;; 3. y < x < z
;;; 4. y < z < x
;;; 5. z < y < x


(defn sum-of-greater-squares
  [x y z]
  (cond
   (and (< x y) (< x z))
   (sum-of-squares y z)

   (< x y)
   (sum-of-squares x y)

   (< x z)
   (sum-of-squares x z)

   (< y z)
   (sum-of-squares z x)

   :else
   (sum-of-squares y x)))
