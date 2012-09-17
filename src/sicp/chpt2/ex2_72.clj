(ns sicp.chpt2.ex2-72)

;; `element-of-set?` is used to determine which branch to traverse

;; In the trees from ex 2.71, the most frequent symbol requires 1 branch lookup
;; and the least frequent requires \\(n-1\\) branch lookups where \\(n\\) is the number
;; of symbols in the tree

;; Since the symbol-sets are implemented as lists in this case, branch lookups are \\(O(n)\\)
;; and encoding a symbol is \\(O(n^{2})\\) for the least frequent symbol
