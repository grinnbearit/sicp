(ns sicp.chpt2.ex2-13)

;; (center, percent)
;; (x, a) * (y, b)
;; [x * (1 - a/100), x * (1 + a/100)] * [y * (1 - b/100), y * (1 + b/100)]

;; Assuming both have positive lower-bounds

;; [xy * (1 - a/100) * (1 - b/100), xy * (1 + a/100) * (1 + b/100)]
;; [xy * (1 - a/100- b/100 + ab/10000), xy * (1 + a/100 + b/100 + ab/10000)]

;; For small percentage tolerances, ab/10000 -> 0

;; [xy * (1 - (a + b)/100), xy * (1 + (a + b)/100)]
;; (xy, a+b)


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
