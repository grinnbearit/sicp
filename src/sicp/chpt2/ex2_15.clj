(ns sicp.chpt2.ex2-15)


;;; The formula for calculating resitances in parallel is \\(\frac{1}{R1} + \frac{1}{R2}\\)

;;; Simplifying the formula by one step we get \\(\frac{1}{\frac{R2 + R1}{R1R2}}\\)

;;; This makes the assumption that \\(\frac{R1}{R1}\\) and \\(\frac{R2}{R2}\\) are 1

;;; But with interval arithmetic, they aren't exactly 1

;;; Therefore the simplification is not possible

;;; `par2` is the more accurate formula because it doesn't make this assumption
