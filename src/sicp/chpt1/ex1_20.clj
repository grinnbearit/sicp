(ns sicp.chpt1.ex1-20)


;;; __Normal Order__

;;; Evaluating `gcd` with normal order
(gcd 206
     40)

;;; each call to `(= b 0)` evaluates `b`

;;; initial number of calls to rem is 0

(gcd 40
     (rem 206 40))

;;; +1 call to `rem`

(gcd (rem 206 40)
     (rem 40 (rem 206 40)))

;;; +2 calls to `rem`

(gcd (rem 40 (rem 206 40))
     (rem (rem 206 40) (rem 40 (rem 206 40))))

;;; +4 calls to `rem`

(gcd (rem (rem 206 40) (rem 40 (rem 206 40)))
     (rem (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40)))))

;;; +7 calls to `rem`

;;; Finally +4 calls to `rem` when returning `a`

;;; Total number of calls to `rem` with normal order

;;; \\(0 + 1 + 2 + 4 + 7 + 4 \rightarrow 18\\)


;;; __Applicative Order__

;;; Evaluating `gcd` with applicative order
(gcd 206
     40)

;;; Each call to `gcd` evaluates both arguments immediately

;;; initial number of calls to rem is 0

(gcd 40
     6)

;;; +1 call to `rem`

(gcd 6
     4)

;;; +1 call to `rem`

(gcd 4
     2)

;;; +1 call to `rem`

(gcd 2
     0)

;;; Finally `a` is already evaluated so no additional calls to `rem` required

;;; Total number of calls to `rem` with applicative order

;;; \\(0 + 1 + 1 + 1 + 1 \rightarrow 4\\)
