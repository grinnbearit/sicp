(ns sicp.chpt1.ex1-29)


(defn cube
  [x]
  (* x x x))


(defn integral
  [f a b n]
  (let [h (/ (- b a) n)]
    (* (/ h 3)
       (apply +
              (f a)
              (f b)
              (map *
                   (map f (range (+ a h) b h))
                   (cycle [4 2]))))))


;; Clojure has rational arithmetic so both 100 and 1000 give the same result, 1/4
;; (integral cube 0 1 100) => 1/4
;; (integral cube 0 1 1000) => 1/4

