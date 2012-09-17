(ns sicp.chpt2.ex2-71)

;; for \\(n=5\\), most frequent takes 1 bit, least frequent takes 4

;;     -0-16
;;     |
;;     1
;;     |
;;     -0-8
;;     |
;;     1
;;     |
;;     -0-4
;;     |
;;     1
;;     |
;;     -0-2
;;     |
;;     1
;;     |
;;     1


;; for \\(n=10\\), most frequent takes 1 bit, least frequent takes 9

;;     -0-512
;;     |
;;     1
;;     |
;;     -0-256
;;     |
;;     1
;;     |
;;     -0-128
;;     |
;;     1
;;     |
;;     -0-64
;;     |
;;     1
;;     |
;;     -0-32
;;     |
;;     1
;;     |
;;     -0-16
;;     |
;;     1
;;     |
;;     -0-8
;;     |
;;     1
;;     |
;;     -0-4
;;     |
;;     1
;;     |
;;     -0-2
;;     |
;;     1
;;     |
;;     1

;; for such trees in general and for \\(n\\) symbols, the most frequent symbol requires 1 bit, while the least
;; frequent symbol requires \\(n-1\\) bits
