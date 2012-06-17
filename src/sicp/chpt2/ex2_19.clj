(ns sicp.chpt2.ex2-19)


(def no-more? empty?)


(def except-first-denomination rest)


(def first-denomination first)


(defn cc
  [amount coin-values]
  (cond (= amount 0)
        1

        (or (< amount 0) (no-more? coin-values))
        0

        :else
        (+ (cc amount
               (except-first-denomination coin-values))
           (cc (- amount
                  (first-denomination coin-values))
               coin-values))))



(def us-coins [50 25 10 5 1])


(def uk-coins [100 50 20 10 5 2 1 0.5])


;;; No the initial ordering doesn't matter as long as the same
;;; ordering is maintained throughout subsequent calls

;;; The problem is defined in terms of 2 sub problems

;;; The first problem calculates the number of ways to count change
;;; without one of the denominations but the same amount

;;; The second problem calculates the number of ways to count change
;;; with the same denomination list but with the amount reduced by the
;;; first value in the demoniation list

;;; Both functions make the assumption that the initial order doesn't change and
;;; track the state of the system that way.
