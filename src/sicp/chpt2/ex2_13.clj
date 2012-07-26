(ns sicp.chpt2.ex2-13
  (:use [sicp.chpt2.ex2-12 :only [make-center-percent
                                  center
                                  percent]]))


;; Let \\(x \pm a\\) and \\(y \pm b\\) be 2 intervals

;; \\((x \pm a) \times (y \pm b)\\)

;; In [lower bound, upper bound] form

;; \\(\Rightarrow [x (1 - \frac{a}{100}), x(1 + \frac{a}{100})] \times [y (1 - \frac{b}{100}), y(1 + \frac{b}{100})]\\)

;; Assuming both have positive lower-bounds

;; \\(\Rightarrow [xy(1 - \frac{a}{100})(1 - \frac{b}{100}), xy(1 + \frac{a}{100})(1 + \frac{b}{100})]\\)

;; \\(\Rightarrow [xy(1 - \frac{a}{100} - \frac{b}{100} + \frac{ab}{10000}), xy(1 + \frac{a}{100} + \frac{b}{100} + \frac{ab}{10000})]\\)

;; For small percentage tolerances, \\(\frac{ab}{10000} \rightarrow 0\\)

;; \\(\Rightarrow [xy(1 - \frac{a + b}{100}), xy(1 + \frac{a+b}{100})]\\)

;; \\(\Rightarrow xy \pm (a+b)\\)


(defn mul-interval
  [i1 i2]
  (make-center-percent (* (center i1) (center i2))
                       (+ (percent i1) (percent i2))))
