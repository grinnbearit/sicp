(ns sicp.chpt1.ex1-13)


;;; fib(n) = fib(n-1) + fib(n-2), fib(1) = 1, fib(2) = 2

;;; To prove:
;;; fib(n) = (phi^n - psi^n)/sqrt(5)
;;; where phi = (1 + sqrt(5))/2
;;;       psi = (1 - sqrt(5))/2

;;; Testing:
;;; fib(1) = 1, true
;;; fib(2) = 2, true

;;; Assume fib(k-1), fib(k) is true

;;; To prove fib(k+1) = fib(k) + fib(k-1)
;;; fib(k-1) = (phi^(k-1) - psi^(k-1))/sqrt(5) + (phi^k - psi^k)/sqrt(5)

;;; this can be rearranged as
;;; (phi^k(1-phi) - psi^k(1-psi))/2

;;; fib(k+1) = fib(k) + fib(k-1) when phi/psi take the value of x in
;;; x^k(1-x) = x^(k+1)

;;; Solving for x
;;; x^2 - x - 1 = 0

;;; Roots
;;; (1 + sqrt(5))/2, (1 - sqrt(5))/2

;;; Possible combinations of phi and psi

;;; phi = (1 - sqrt(5))/2, psi = (1 - sqrt(5))/2
;;; Invalid, fib(n) = 0 for all n > 0

;;; phi = (1 + sqrt(5))/2, psi = (1 + sqrt(5))/2
;;; Invalid, fib(n) = 0 for all n > 0

;;; phi = (1 - sqrt(5))/2, psi = (1 + sqrt(5))/2
;;; Invalid, fib(n) < 0 for all n > 0


;;; phi = (1 + sqrt(5))/2, psi = (1 - sqrt(5))/2
;;; Valid

;;; As n -> Inf+
;;; psi -> 0

;;; Therefore fib(n) approximates to phi^n/sqrt(5)
