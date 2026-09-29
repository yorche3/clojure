(ns data-structures-basics.data-structures-basics)

(defrecord Node [value next])
(defrecord LinkedList [head tail count])
(defrecord Stack [top count])
(defrecord Queue [front rear count])

(def failure-value nil)

(defn linked-list-init []
  (LinkedList. nil nil 0))

(defn linked-list-is-empty [linked-list]
  (zero? (:count linked-list)))

(defn linked-list-size [linked-list]
  (:count linked-list))

(defn get-head [linked-list]
  (if (not (linked-list-is-empty linked-list))
    (:head linked-list)
    failure-value))

(defn insert-head [linked-list value]
  (let [new-head (Node. value (:head linked-list))]
    (LinkedList. new-head (:tail linked-list) (inc (:count linked-list)))))

(defn insert-tail [linked-list value]
  (let [new-tail (Node. value nil)]
    (if (linked-list-is-empty linked-list)
      (LinkedList. new-tail new-tail 1)
      (LinkedList. (:head linked-list) new-tail (inc (:count linked-list))))))

(defn delete [linked-list value]
  (loop [current (:head linked-list)
         prev nil]
    (if (nil? current)
      failure-value
      (if (= (:value current) value)
        (if (nil? prev)
          {:list (LinkedList. (:next current) (:tail linked-list) (dec (:count linked-list)))
           :success true}
          {:list (LinkedList. (:head linked-list) (:tail linked-list) (dec (:count linked-list)))
           :success true})
        (recur (:next current) current)))))

(defn stack-init []
  (Stack. nil 0))

(defn stack-is-empty [stack]
  (zero? (:count stack)))

(defn stack-size [stack]
  (:count stack))

(defn push [stack value]
  (let [new-top (Node. value (:top stack))]
    (Stack. new-top (inc (:count stack)))))

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

(defn enqueue [queue value]
  (let [new-rear (Node. value nil)]
    (if (queue-is-empty queue)
      (Queue. new-rear new-rear 1)
      (Queue. (:front queue) new-rear (inc (:count queue)))))))

(defn queue-peek [queue]
  (if (queue-is-empty queue)
    failure-value
    (:value (:front queue))))

(defn dequeue [queue]
  (if (queue-is-empty queue)
    failure-value
    {:queue (Queue. (:next (:front queue)) (:rear queue) (dec (:count queue)))
     :value (:value (:front queue))}))
