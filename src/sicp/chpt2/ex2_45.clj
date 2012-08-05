(ns sicp.chpt2.ex2-45
  (:use [sicp.chpt2.ex2-51 :only [beside below]]
        [sicp.chpt2.ex2-49 :only [wave-painter
                                  identity-frame
                                  render]]))


(defn split
  [major-op minor-op]
  (fn [painter n]
    (if (zero? n)
      painter
      (let [smaller ((split major-op minor-op) painter (dec n))]
        (major-op painter (minor-op smaller smaller))))))


(def right-split (split beside below))
(def up-split (split below beside))


;;; `right-split`

;;      (render ((right-split wave-painter 3) identity-frame))


;;; `up-split`

;;      (render ((up-split wave-painter 3) identity-frame))
