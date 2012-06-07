(ns sicp.chpt1.ex1-19)


;;; The transformation \\(T _{pq}(p,q)\\) is defined as

;;; * \\(a \leftarrow bq + aq + ap\\)
;;; * \\(b \leftarrow bp + aq\\)


;;; Applying \\(T _{pq}\\) twice,

;;; \\(T _{pq}(T _{pq}(a,b))\\)

;;; which expands to

;;; \\(T _{pq}(bq + aq + ap, bp + aq)\\)

;;; finally giving us the transforms

;;; * \\(a \leftarrow bpq +aq^2 + bq^2 + aq^2 + apq + bpq + apq + ap^2\\)
;;; * \\(b \leftarrow bp^2 + apq + bq^2 + aq^2 + apq\\)

;;; Grouping to reveal \\(T _{pq}\\) form, we get the new transforms

;;; * \\(a \leftarrow b(2pq + q^2) + a(2pq + q^2) + a(p^2 + q^2)\\)
;;; * \\(b \leftarrow b(p^2 + q^2) + a(2pq + q^2)\\)

;;; So the new \\(T _{p'q'}\\), which is the same as applying \\(T _{pq}\\) twice is

;;; * \\(p' \leftarrow p^2 + q^2\\)
;;; * \\(q' \leftarrow 2pq + q^2\\)


;;; Filling in the gaps, here's `fib` with \\(O(log _2 n)\\)

(defn fib
  ([n]
     (fib 1 0 0 1 n))
  ([a b p q n]
     (cond (zero? n)
           b

           (even? n)
           (recur a
                  b
                  (+ (* p p) (* q q))
                  (+ (* 2 p q) (* q q))
                  (/ n 2))

           :else
           (recur (+ (* b q) (* a q) (* a p))
                  (+ (* b p) (* a q))
                  p
                  q
                  (dec n)))))
