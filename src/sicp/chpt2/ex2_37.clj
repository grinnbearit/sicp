(ns sicp.chpt2.ex2-37
  (:use [sicp.chpt2.ex2-33 :only [accumulate]])
  (:use [sicp.chpt2.ex2-36 :only [accumulate-n]]))


;;; __dot-product__


(defn dot-product
  [v w]
  (accumulate + 0 (map * v w)))


;;      (dot-product [1 2] [3 4])
;;      => 11


;;; __matrix-*-vector__


(defn matrix-*-vector
  [m v]
  (map (fn [row]
         (dot-product v row))
       m))


;;      (matrix-*-vector [[1 2]
;;                        [3 4]]
;;
;;                       [5 6])
;;      => (17 39)


;;; __transpose__


(defn transpose
  [mat]
  (accumulate-n cons () mat))


;;      (transpose [[1 2]
;;                  [3 4]])
;;      => ((1 3) (2 4))


;;; __matrix-*-matrix__


(defn matrix-*-matrix
  [m n]
  (let [cols (transpose n)]
    (map (fn [row]
           (matrix-*-vector cols row))
         m)))


;;      (matrix-*-matrix [[1 2]
;;                        [3 4]]
;;
;;                       [[5 6]
;;                        [7 8]])
;;      => ((19 22) (43 50))
