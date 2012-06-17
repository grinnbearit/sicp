(ns sicp.chpt2.ex2-13)

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


(defrecord Interval [lb ub])


(defn make-interval
  [lb ub]
  (Interval. lb ub))


(defn lower-bound
  [i]
  (.lb i))


(defn upper-bound
  [i]
  (.ub i))


(defn make-center-percent
  [c p]
  (let [width (* (/ p 100) c)]
    (make-interval (- c width)
                   (+ c width))))


(defn center
  [i]
  (/ (+ (lower-bound i)
        (upper-bound i))
     2))


(defn percent
  [i]
  (let [c (center i)]
    (* (/ (- (upper-bound i) c)
          c)
       100)))


(defn mul-interval
  [i1 i2]
  (make-center-percent (* (center i1) (center i2))
                       (+ (percent i1) (percent i2))))
