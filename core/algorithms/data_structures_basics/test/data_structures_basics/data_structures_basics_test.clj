(ns data-structures-basics.data-structures-basics-test
  (:require [clojure.test :refer [deftest is]]
            [data-structures-basics.data-structures-basics :as sut]))

(def node-initial-value 10)
(def node-linked-value 20)

(def linked-list-tail-values [10 20 10])
(def linked-list-head-value 5)
(def linked-list-absent-value 99)
(def linked-list-initial-size 0)
(def linked-list-populated-size 4)
(def linked-list-deleted-size 3)
(def linked-list-empty-size 0)

(def stack-values [10 20 30])
(def stack-reused-value 40)
(def stack-empty-size 0)
(def stack-populated-size 3)

(def queue-values [10 20 30])
(def queue-reused-value 40)
(def queue-empty-size 0)
(def queue-populated-size 3)

(defn assert-case [subject case-name expected actual]
  (is (= expected actual)
      (str subject " should return " (pr-str expected)
           " in " case-name ", but returned " (pr-str actual))))

(defn run-node-cases []
  (let [a (sut/node-init node-initial-value)
        b (sut/node-init node-linked-value)]
    (assert-case "node-get-value" "initialize and observe value/link"
                 node-initial-value (sut/node-get-value a))
    (assert-case "node-get-next" "initialize and observe value/link"
                 nil (sut/node-get-next a))
    (sut/node-set-next a b)
    (assert-case "node-set-next" "initialize another node, link and traverse"
                 node-linked-value
                 (sut/node-get-value (sut/node-get-next a)))
    (assert-case "node-get-next" "initialize another node, link and traverse"
                 nil (sut/node-get-next b))))

(defn run-linked-list-cases []
  (let [linked-list (sut/linked-list-init)]
    (assert-case "linked-list-is-empty" "empty state"
                 true (sut/linked-list-is-empty linked-list))
    (assert-case "linked-list-size" "empty state"
                 linked-list-initial-size (sut/linked-list-size linked-list))
    (assert-case "linked-list-get-head" "empty state"
                 nil (sut/linked-list-get-head linked-list))
    (doseq [value linked-list-tail-values]
      (sut/linked-list-insert-tail linked-list value))
    (sut/linked-list-insert-head linked-list linked-list-head-value)
    (assert-case "linked-list-size" "insert at both ends"
                 linked-list-populated-size (sut/linked-list-size linked-list))
    (assert-case "linked-list-get-head" "insert at both ends"
                 linked-list-head-value (sut/linked-list-get-head linked-list))
    (assert-case "linked-list-delete" "delete first occurrence"
                 true (sut/linked-list-delete linked-list node-initial-value))
    (assert-case "linked-list-size" "delete first occurrence"
                 linked-list-deleted-size (sut/linked-list-size linked-list))
    (assert-case "linked-list-get-head" "delete first occurrence"
                 linked-list-head-value (sut/linked-list-get-head linked-list))
    (assert-case "linked-list-delete" "absent value"
                 false (sut/linked-list-delete linked-list linked-list-absent-value))
    (assert-case "linked-list-size" "absent value"
                 linked-list-deleted-size (sut/linked-list-size linked-list))
    (doseq [value [linked-list-head-value node-linked-value node-initial-value]]
      (assert-case "linked-list-delete" "empty the list"
                   true (sut/linked-list-delete linked-list value)))
    (assert-case "linked-list-is-empty" "empty the list"
                 true (sut/linked-list-is-empty linked-list))
    (assert-case "linked-list-size" "empty the list"
                 linked-list-empty-size (sut/linked-list-size linked-list))
    (assert-case "linked-list-get-head" "empty the list"
                 nil (sut/linked-list-get-head linked-list))))

(defn run-stack-cases []
  (let [stack (sut/stack-init)]
    (assert-case "stack-is-empty" "empty state and failed removal"
                 true (sut/stack-is-empty stack))
    (assert-case "stack-size" "empty state and failed removal"
                 stack-empty-size (sut/stack-size stack))
    (assert-case "stack-peek" "empty state and failed removal"
                 nil (sut/stack-peek stack))
    (assert-case "stack-pop" "empty state and failed removal"
                 nil (sut/stack-pop stack))
    (doseq [value stack-values]
      (sut/stack-push stack value))
    (assert-case "stack-peek" "LIFO and non-mutating peek"
                 30 (sut/stack-peek stack))
    (assert-case "stack-size" "LIFO and non-mutating peek"
                 stack-populated-size (sut/stack-size stack))
    (assert-case "stack-pop" "removal and reuse"
                 30 (sut/stack-pop stack))
    (sut/stack-push stack stack-reused-value)
    (doseq [expected [stack-reused-value node-linked-value node-initial-value]]
      (assert-case "stack-pop" "removal and reuse"
                   expected (sut/stack-pop stack)))
    (assert-case "stack-is-empty" "removal and reuse"
                 true (sut/stack-is-empty stack))
    (assert-case "stack-size" "removal and reuse"
                 stack-empty-size (sut/stack-size stack))
    (assert-case "stack-pop" "empty after removal"
                 nil (sut/stack-pop stack))
    (assert-case "stack-is-empty" "empty after removal"
                 true (sut/stack-is-empty stack))))

(defn run-queue-cases []
  (let [queue (sut/queue-init)]
    (assert-case "queue-is-empty" "empty state and failed removal"
                 true (sut/queue-is-empty queue))
    (assert-case "queue-size" "empty state and failed removal"
                 queue-empty-size (sut/queue-size queue))
    (assert-case "queue-peek" "empty state and failed removal"
                 nil (sut/queue-peek queue))
    (assert-case "queue-dequeue" "empty state and failed removal"
                 nil (sut/queue-dequeue queue))
    (doseq [value queue-values]
      (sut/queue-enqueue queue value))
    (assert-case "queue-peek" "FIFO and non-mutating peek"
                 node-initial-value (sut/queue-peek queue))
    (assert-case "queue-size" "FIFO and non-mutating peek"
                 queue-populated-size (sut/queue-size queue))
    (assert-case "queue-dequeue" "removal and reuse"
                 node-initial-value (sut/queue-dequeue queue))
    (sut/queue-enqueue queue queue-reused-value)
    (doseq [expected [node-linked-value 30 queue-reused-value]]
      (assert-case "queue-dequeue" "removal and reuse"
                   expected (sut/queue-dequeue queue)))
    (assert-case "queue-is-empty" "removal and reuse"
                 true (sut/queue-is-empty queue))
    (assert-case "queue-size" "removal and reuse"
                 queue-empty-size (sut/queue-size queue))
    (assert-case "queue-dequeue" "empty after removal"
                 nil (sut/queue-dequeue queue))
    (assert-case "queue-is-empty" "empty after removal"
                 true (sut/queue-is-empty queue))))

(deftest node-init-test
  (run-node-cases))

(deftest node-get-value-test
  (run-node-cases))

(deftest node-get-next-test
  (run-node-cases))

(deftest node-set-next-test
  (run-node-cases))

(deftest linked-list-init-test
  (run-linked-list-cases))

(deftest linked-list-get-head-test
  (run-linked-list-cases))

(deftest linked-list-insert-head-test
  (run-linked-list-cases))

(deftest linked-list-insert-tail-test
  (run-linked-list-cases))

(deftest linked-list-delete-test
  (run-linked-list-cases))

(deftest linked-list-is-empty-test
  (run-linked-list-cases))

(deftest linked-list-size-test
  (run-linked-list-cases))

(deftest stack-init-test
  (run-stack-cases))

(deftest stack-push-test
  (run-stack-cases))

(deftest stack-pop-test
  (run-stack-cases))

(deftest stack-peek-test
  (run-stack-cases))

(deftest stack-is-empty-test
  (run-stack-cases))

(deftest stack-size-test
  (run-stack-cases))

(deftest queue-init-test
  (run-queue-cases))

(deftest queue-enqueue-test
  (run-queue-cases))

(deftest queue-dequeue-test
  (run-queue-cases))

(deftest queue-peek-test
  (run-queue-cases))

(deftest queue-is-empty-test
  (run-queue-cases))

(deftest queue-size-test
  (run-queue-cases))
