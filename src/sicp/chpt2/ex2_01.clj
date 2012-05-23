(ns sicp.chpt2.ex2-01)


;;; Using Clojure's records instead of a cons cell
(defrecord Rational [n d])


(defn- gcd
  [x y]
  (if (zero? y)
    x
    (recur y (rem x y))))


(defn make-rat
  [n d]
  (let [div (Math/abs (gcd n d))
        n-div (if (or (and (pos? n) (neg? d))
                      (and (neg? n) (neg? d)))
                    (- div)
                    div)]
    (Rational. (/ n n-div) (/ d n-div))))
