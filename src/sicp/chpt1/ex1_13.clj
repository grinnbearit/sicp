(ns sicp.chpt1.ex1-13)

;;; The fibonacci recursion can be expressed as

;;; * \\(fib(1) = 1\\)
;;; * \\(fib(2) = 2\\)
;;; * \\(fib(n) = fib(n-1) + fib(n-2)\\)

;;; To prove:

;;; \\(fib(n) = \frac{\phi^n - \psi^n}{\sqrt{5}}\\)

;;; where

;;; * \\(\phi = \frac{1 + \sqrt{5}}{2}\\)
;;; * \\(\psi = \frac{1 - \sqrt{5}}{2}\\)

;;; Base conditions:

;;; * \\(fib(1) = 1, true\\)
;;; * \\(fib(2) = 2, true\\)

;;; Assume \\(fib(k-1), fib(k)\\) is true

;;; To prove

;;; \\(fib(k+1) = fib(k) + fib(k-1)\\)

;;; Substituting

;;; \\(fib(k+1) = \frac{\phi^k - \psi^k}{\sqrt{5}} + \frac{\phi^(k-1) - \psi^(k-1)}{\sqrt{5}}\\)

;;; Rearranging

;;; \\(fib(k+1) = \frac{\phi^k(1+\frac{1}{\phi}) - \psi^k(1+\frac{1}{\psi})}{\sqrt{5}}\\)

;;; This resolves to

;;; \\(fib(k+1) = \frac{\phi^(k+1) - \psi^(k+1)}{\sqrt{5}}\\)

;;; if and only if

;;; * \\(1 + \frac{1}{\phi} = \phi\\)
;;; * \\(1 + \frac{1}{\psi} = \psi\\)

;;; Which can be represented as the solution to the equation

;;; \\(x^2 - x - 1 = 0\\)

;;; This equation has the roots

;;; * \\(\frac{1 + \sqrt{5}}{2}\\)
;;; * \\(\frac{1 - \sqrt{5}}{2}\\)

;;; The different possible values which \\(\phi\\) and \\(\psi\\) can take

;;; \\(\phi = \frac{1 - \sqrt{5}}{2}, \psi = \frac{1 - \sqrt{5}}{2}\\)

;;; Invalid, \\(fib(n) = 0\\), \\(\forall\\) \\(n>0\\)

;;; \\(\phi = \frac{1 + \sqrt{5}}{2}, \psi = \frac{1 + \sqrt{5}}{2}\\)

;;; Invalid, \\(fib(n) = 0\\), \\(\forall\\) \\(n>0\\)

;;; \\(\phi = \frac{1 - \sqrt{5}}{2}, \psi = \frac{1 + \sqrt{5}}{2}\\)

;;; Invalid, \\(fib(n) < 0\\), \\(\forall\\) \\(n>0\\)


;;; \\(\phi = \frac{1 + \sqrt{5}}{2}, \psi = \frac{1 - \sqrt{5}}{2}\\)

;;; Valid, \\(\forall\\) \\(n>0\\)

;;; For larger values of \\(n\\), \\(\psi\\) becomes negligible

;;; Therefore \\(fib(n)\\) is approximately \\(\frac{\phi^n}{\sqrt{5}}\\)
