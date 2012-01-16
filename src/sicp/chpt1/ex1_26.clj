(ns sicp.chpt1.ex1-26)


;; The log(n) runtime from the original expmod algorithm works by halving the number of steps
;; each time we find an even power

;; by calling expmod twice when multiplying, Louis loses this advantage, calculating half the number of steps
;; twice and then multiplying them instead of squaring the result once, thus making expmod O(n).
