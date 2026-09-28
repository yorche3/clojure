(ns data-structures-basics.data-structures-basics)

(defrecord Node [value next])
(defrecord LinkedList [head tail count])
(defrecord Stack [top count])
(defrecord Queue [front rear count])

(defn node-init [value]
  nil)

(defn node-get-value [node]
  nil)

(defn node-get-next [node]
  nil)

(defn node-set-next [node next]
  nil)

(defn linked-list-init []
  nil)

(defn linked-list-get-head [linked-list]
  nil)

(defn linked-list-insert-head [linked-list value]
  nil)

(defn linked-list-insert-tail [linked-list value]
  nil)

(defn linked-list-delete [linked-list value]
  nil)

(defn linked-list-is-empty [linked-list]
  nil)

(defn linked-list-size [linked-list]
  nil)

(defn stack-init []
  nil)

(defn stack-push [stack value]
  nil)

(defn stack-pop [stack]
  nil)

(defn stack-peek [stack]
  nil)

(defn stack-is-empty [stack]
  nil)

(defn stack-size [stack]
  nil)

(defn queue-init []
  nil)

(defn queue-enqueue [queue value]
  nil)

(defn queue-dequeue [queue]
  nil)

(defn queue-peek [queue]
  nil)

(defn queue-is-empty [queue]
  nil)

(defn queue-size [queue]
  nil)
