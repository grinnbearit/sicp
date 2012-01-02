(ns sicp.chpt1.ex1-15)


(sine 12.15)
(p (sine 4.05))
(p (p (sine 1.35)))
(p (p (p (sine 0.45))))
(p (p (p (p (sine 0.15)))))
(p (p (p (p (p (sine 0.05))))))

;;; p is applied 5 times


;;; Assuming p is O(1)
;;; the time and space complexity are directly proportional to the number of times 'p' is applied

;;; (sine a) will make at most n calls to p where n satisfies the equation


;;; a/3^n <= 0.1


;;; Solving for n

;;; log(3)a <= n

;;; Therefore the O(sine) is approximately log(3)a, with 'a' as input
