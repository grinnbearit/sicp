(ns sicp.chpt2.ex2-15)


;;; The actual formula for calculating resitances in parallel
;;; 1/[(1/R1) + (1/R2)]

;;; Simplifying the formula by one step we get
;;; 1/[(R2 + R1)/R1R2]

;;; But this simplification makes the assumption that R1/R1 and R2/R2 are 1
;;; but using interval arithmetic, they aren't exactly 1

;;; Therefore the simplification is wrong

;;; par2 is the more accurate formula simply because it is the actual formula without simplification
