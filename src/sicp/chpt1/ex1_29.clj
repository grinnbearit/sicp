(ns sicp.chpt1.ex1-29)


(defn cube
  [x]
  (* x x x))


(defn integral
  [f a b dx]
  (* dx (reduce + (map f (range a b dx)))))


(defn simpson-integral
  [f a b n]
  (let [h (float (/ (- b a) n))]
    (* (/ h 3)
       (apply +
              (f a)
              (f b)
              (map *
                   (map f (range (+ a h) b h))
                   (cycle [4 2]))))))


;; Simpson's integral is more accurate than the basic integral from earlier,
;; it approximates a better result in the same number of iterations
;;
;;     (integral cube 0 1 0.01)
;;     => 0.2450250000000004
;;
;;     (integral cube 0 1 0.001)
;;     => 0.24950025000000053
;;
;;     (simpson-integral cube 0 1 100)
;;     => 0.25666664394239663
;;
;;     (simpson-integral cube 0 1 1000)
;;     => 0.25000004744995713
