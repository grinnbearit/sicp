(ns sicp.chpt1.ex1-19)


;;; for reference

;;; Tpq(a,b)
;;; a <- bq + aq + ap
;;; b <- bp + aq


;;; Applying Tpq twice

;;; Tpq(Tpq(a,b))
;;; Tpq(bq + aq + ap, bp + aq)

;;; a <- bpq +aqq + bqq + aqq + apq + bpq + apq + app
;;; b <- bpp + apq + bqq + aqq + apq


;;; In Tpq form
;;; a <- b(2pq + qq) + a(2pq + qq) + a(pp + qq)
;;; b <- b(pp + qq) + a(2pq + qq)

;;; Therefore p' and q' are
;;; p' <- pp + qq
;;; q' <- 2pq + qq


;;; Filling in the gaps

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
