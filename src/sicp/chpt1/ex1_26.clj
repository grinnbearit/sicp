(ns sicp.chpt1.ex1-26)


;; The original `expmod` function has a runtime of \\(O(\log _2 n\\) because it halves the number of
;; remaining steps each time we find an even `exp`

;; by calling `expmod` twice, Louis loses this advantage, calculating half the number of steps
;; twice, thus making \\(O(expmod) \rightarrow n\\)
