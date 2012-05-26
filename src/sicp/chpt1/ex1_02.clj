(ns sicp.chpt1.ex1-02)


;;; Using prefix notation has 2 advantages

;;; First, your operators aren't treated differently from
;;; other functions and so you have consistency

;;; Second, precedence is obvious


(/ (+ 5
      4
      (- 2
         (- 3
            (+ 6
               (/ 4 5)))))
   (* 3
      (- 6 2)
      (- 2 7)))
