(ns sicp.chpt2.ex2-76)


;; Explicit dispatch requires every user of each implementation to update their code
;; to handle the new type or operation, there is no simple way to determine whether a type
;; or operation is supported

;; In the data-directed style, adding a new operation or type requires a new entry
;; in the global lookup table. This can be done with an explicit installation
;; function for the type or the operation.

;; With message passing, new operations need to be added to the dispatch
;; function in every type constructor where they're to be supported. A new type,
;; on the other hand, is trivial to add with no change to any existing code or
;; global state.

;; In a system where new types are added often, message passing makes the most sense
;; since the impact on the existing system is minimal. If new operations are added more
;; frequently, the data directed approach is preferable.
