(ns sicp.chpt2.ex2-52
  (:use [sicp.chpt2.ex2-45 :only [up-split right-split]]
        [sicp.chpt2.ex2-46 :only [make-vect]]
        [sicp.chpt2.ex2-48 :only [make-segment]]
        [sicp.chpt2.ex2-49 :only [render
                                  chain
                                  identity-frame
                                  segments->painter
                                  wave-painter]]
        [sicp.chpt2.ex2-50 :only [flip-vert flip-horiz rotate180]]
        [sicp.chpt2.ex2-51 :only [beside below]]))


(defn dance-painter
  [frame]
  (let [face-painter (segments->painter [(make-segment (make-vect 0.35 0.3)
                                                       (make-vect 0.45 0.3))
                                         (make-segment (make-vect 0.3 0.15)
                                                       (make-vect 0.4 0.15))])]
    (chain (wave-painter frame)
           (face-painter frame))))


(defn corner-split
  [painter n]
  (if (zero? n)
    painter
    (let [up (up-split painter (dec n))
          right (right-split painter (dec n))
          corner (corner-split painter (dec n))]
      (beside (below painter up)
              (below right corner)))))


(defn square-of-four
  [tl tr bl br]
  (fn [painter]
    (let [top (beside (tl painter)
                      (tr painter))
          bottom (beside (bl painter)
                         (br painter))]
      (below bottom top))))


(defn square-limit
  [painter n]
  (let [combine4 (square-of-four flip-vert rotate180
                                 identity flip-horiz)]
    (combine4 (corner-split painter n))))


;;; `dance-painter`

;;     (render (dance-painter identity-frame))

;;; `corner-split`

;;     (render ((corner-split dance-painter 3) identity-frame))

;;; `square-limit`

;;     (render ((square-limit dance-painter 3) identity-frame))
