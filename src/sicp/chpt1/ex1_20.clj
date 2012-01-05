(ns sicp.chpt1.ex1-20)


;;; Normal Order
(gcd 206
     40)                                ; 0, remainder count; each call to '=' evaluates b

(gcd 40
     (rem 206 40))                      ; +1 

(gcd (rem 206 40)
     (rem 40 (rem 206 40)))             ; +2

(gcd (rem 40 (rem 206 40))
     (rem (rem 206 40) (rem 40 (rem 206 40)))) ; +4

(gcd (rem (rem 206 40) (rem 40 (rem 206 40)))
     (rem (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40))))) ; +7

;;; Finally returning 'a' which is another +4
;;; Number of calls to rem 0 + 1 + 2 + 4 + 7 + 4 -> 18


;;; Applicative Order
(gcd 206
     40)                                ; 0, remainder count; each call to 'rem' evaluates immediately

(gcd 40
     6)                                 ; +1


(gcd 6
     4)                                 ; +1

(gcd 4
     2)                                 ; +1

(gcd 2
     0)                                 ; +1


;;; 'a' is already evaluated
;;; Number of calls to rem 0 + 1 + 1 + 1 + 1 -> 4
