(ns sicp.chpt2.ex2-75)


;; returning a map since clojure maps are functions of their keys

(defn make-from-mag-ang
  [mag ang]
  (let [dispatch {'real-part (* mag (Math/cos ang))
                  'imag-part (* mag (Math/sin ang))
                  'magnitude mag
                  'angle ang}]
    dispatch))


(defn apply-generic
  [op arg]
  (arg op))


;;     (apply-generic 'real-part (make-from-mag-ang 10 (/ Math/PI 4)))
;;     => 7.0710678118654755


;;     (apply-generic 'imag-part (make-from-mag-ang 10 (/ Math/PI 4)))
;;     => 7.0710678118654755


;;     (apply-generic 'magnitude (make-from-mag-ang 10 (/ Math/PI 4)))
;;     => 10


;;     (apply-generic 'angle (make-from-mag-ang 10 (/ Math/PI 4)))
;;     => 0.7853981633974483
