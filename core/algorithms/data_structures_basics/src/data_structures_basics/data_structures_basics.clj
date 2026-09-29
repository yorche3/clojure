(ns data-structures-basics.data-structures-basics)

(defrecord Node [value next])
(defrecord LinkedList [head tail count])
(defrecord Stack [top count])
(defrecord Queue [front rear count])

(def failure-value nil)

;; `init` of the shared node: assigns `value` and leaves `next` absent. Link it
;; with `assoc` (the specification's `set_next` returns a new node when the
;; language is immutable).
(defn node-init [value]
  (Node. value nil))

;; Chain helpers. With persistent nodes a link cannot be written in place, so
;; walking or rebuilding the chain is what `assoc` gives back.
(defn- chain-append
  "Chain of `node` with `new-tail` linked after its last node."
  [node new-tail]
  (if (nil? node)
    new-tail
    (assoc node :next (chain-append (:next node) new-tail))))

(defn- chain-last
  "Last node of the chain, or `nil` if there is none."
  [node]
  (when node
    (if-let [next-node (:next node)]
      (recur next-node)
      node)))

(defn- chain-remove
  "Returns `[node removed?]`: the chain without the first occurrence of
  `value`, and whether it appeared."
  [node value]
  (cond
    (nil? node) [nil false]
    (= (:value node) value) [(:next node) true]
    :else (let [[next-node removed?] (chain-remove (:next node) value)]
            [(assoc node :next next-node) removed?])))

(defn linked-list-init []
  (LinkedList. nil nil 0))

(defn linked-list-is-empty [linked-list]
  (zero? (:count linked-list)))

(defn linked-list-size [linked-list]
  (:count linked-list))

(defn get-head [linked-list]
  (if (linked-list-is-empty linked-list)
    failure-value
    (:value (:head linked-list))))

(defn ll-insert-head [linked-list value]
  (let [new-head (assoc (node-init value) :next (:head linked-list))]
    (LinkedList. new-head
                 (or (:tail linked-list) new-head)
                 (inc (:count linked-list)))))

;; Appending at the tail has to rebuild the chain up to the last node, because
;; the old tail cannot be linked in place: O(n) instead of the specification's
;; O(1). It is the module's immutable adaptation and goes in its README.
(defn ll-insert-tail [linked-list value]
  (let [new-tail (node-init value)]
    (if (linked-list-is-empty linked-list)
      (LinkedList. new-tail new-tail 1)
      (LinkedList. (chain-append (:head linked-list) new-tail)
                   new-tail
                   (inc (:count linked-list))))))

(defn ll-delete [linked-list value]
  (let [[head removed?] (chain-remove (:head linked-list) value)]
    (if removed?
      (LinkedList. head (chain-last head) (dec (:count linked-list)))
      failure-value)))

(defn stack-init []
  (Stack. nil 0))

(defn stack-is-empty [stack]
  (zero? (:count stack)))

(defn stack-size [stack]
  (:count stack))

(defn stack-push [stack value]
  (Stack. (assoc (node-init value) :next (:top stack))
          (inc (:count stack))))

(defn stack-peek [stack]
  (if (stack-is-empty stack)
    failure-value
    (:value (:top stack))))

(defn stack-pop [stack]
  (if (stack-is-empty stack)
    failure-value
    {:stack (Stack. (:next (:top stack)) (dec (:count stack)))
     :value (:value (:top stack))}))

(defn queue-init []
  (Queue. nil nil 0))

(defn queue-is-empty [queue]
  (zero? (:count queue)))

(defn queue-size [queue]
  (:count queue))

(defn queue-enqueue [queue value]
  (let [new-rear (node-init value)]
    (if (queue-is-empty queue)
      (Queue. new-rear new-rear 1)
      (Queue. (chain-append (:front queue) new-rear)
              new-rear
              (inc (:count queue))))))

(defn queue-peek [queue]
  (if (queue-is-empty queue)
    failure-value
    (:value (:front queue))))

(defn queue-dequeue [queue]
  (if (queue-is-empty queue)
    failure-value
    (let [front (:front queue)
          next-front (:next front)]
      {:queue (Queue. next-front
                      (when next-front (:rear queue))
                      (dec (:count queue)))
       :value (:value front)})))
