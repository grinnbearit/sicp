(ns sicp.chpt2.ex2-51
  (:use [sicp.chpt2.ex2-46 :only [make-vect]]
        [sicp.chpt2.ex2-48 :only [make-segment]]
        [sicp.chpt2.ex2-49 :only [render
                                  chain
                                  identity-frame
                                  wave-painter]]
        [sicp.chpt2.ex2-50 :only [transform-painter
                                  rotate90
                                  rotate270]]))



(defn beside
  [painter1 painter2]
  (let [paint-left (transform-painter painter1
                                      (make-vect 0 0)
                                      (make-vect 0.5 0)
                                      (make-vect 0 1))
        paint-right (transform-painter painter2
                                       (make-vect 0.5 0)
                                       (make-vect 1 0)
                                       (make-vect 0.5 1))]
    (fn [frame]
      (chain (paint-left frame)
             (paint-right frame)))))



(defn below
  [painter1 painter2]
  (let [paint-top (transform-painter painter1
                                     (make-vect 0 0)
                                     (make-vect 1 0)
                                     (make-vect 0 0.5))
        paint-bottom (transform-painter painter2
                                        (make-vect 0 0.5)
                                        (make-vect 1 0.5)
                                        (make-vect 0 1))]
    (fn [frame]
      (chain (paint-top frame)
             (paint-bottom frame)))))


(defn below
  [painter1 painter2]
  (rotate270
   (beside (rotate90 painter1)
           (rotate90 painter2))))


;;; `beside`

;;     (render ((beside wave-painter wave-painter) identity-frame))

;;; `below`

;;     (render ((below wave-painter wave-painter) identity-frame))
