(ns sicp.chpt2.ex2-44
  (:use [sicp.chpt2.ex2-51 :only [below beside]]
        [sicp.chpt2.ex2-49 :only [wave-painter
                                  identity-frame
                                  render]]))


;;; The first few functions defined in this section refer
;;; to functions defined later (like `below` and `beside`)


(defn up-split
  [painter n]
  (if (zero? n)
    painter
    (let [smaller (up-split painter (dec n))]
      (below painter (beside smaller smaller)))))


;;; Don't worry too much about the examples right now,
;;; just evaluating them to understand what they do is enough


;;; the basic `wave-painter`

;;     (render (wave-painter identity-frame))

;;; `up-split`

;;     (render ((up-split wave-painter 3) identity-frame))
