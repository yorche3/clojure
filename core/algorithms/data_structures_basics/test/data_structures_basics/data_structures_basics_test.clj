(ns data-structures-basics.data-structures-basics-test
  (:require [clojure.test :refer [deftest is testing]]
            [data-structures-basics.data-structures-basics :as sut])) ; system under test

;; Shared fixtures: the specification's positive integers, so no value can be
;; confused with the failure indicator.

(def ^:private first-value 10)
(def ^:private second-value 20)
(def ^:private head-value 5)
(def ^:private third-value 30)
(def ^:private absent-value 99)
(def ^:private reused-value 40)

;; Two adaptations the tests rely on; the module README declares them:
;;
;;   1. The structures are immutable, so every operation *returns* the new
;;      value: the scenarios thread it instead of mutating it in place. That is
;;      why a removal gives back the structure next to the extracted value.
;;   2. There are no getters or setters. A record field is read with the keyword
;;      (`(:value node)`, `(:next node)`) and updated with `assoc`, which is the
;;      language's own reading and writing of a field.
;;
;; `nil` is the failure indicator: `sut/ll-delete` returns it when the value is
;; absent, and `sut/stack-pop`/`sut/queue-dequeue` return it when there is
;; nothing to remove.

(defn- chain
  "The linked list's values, walked from its head through the node links. It is
  the traversal the specification's cases observe: the contract exposes the
  head's value, so the walk reads the chain the structure links."
  [linked-list]
  (->> (:head linked-list)
       (iterate :next)
       (take-while some?)
       (map :value)))

(defn- drain
  "Removes every element of `structure` in removal order and returns
  `[values remaining]`. `is-empty?` and `remove-fn` are the structure's own
  operations, and `remaining-key` is the key `remove-fn` returns it under."
  [structure is-empty? remove-fn remaining-key]
  (loop [structure structure
         values []]
    (if (is-empty? structure)
      [values structure]
      (let [removed (remove-fn structure)]
        (recur (get removed remaining-key)
               (conj values (:value removed)))))))

(deftest node-test
  (testing "Initialize and observe value/link"
    (let [node (sut/node-init first-value)]
      (is (= first-value (:value node)))
      (is (nil? (:next node)))))
  (testing "Initialize another node, link and traverse"
    (let [first-node (sut/node-init first-value)
          second-node (sut/node-init second-value)
          linked (assoc first-node :next second-node)]
      (is (= second-value (:value (:next linked))))
      (is (nil? (:next second-node))))))

(deftest linked-list-test
  (let [empty-list (sut/linked-list-init)]
    (testing "Empty state"
      (is (sut/linked-list-is-empty empty-list))
      (is (= 0 (sut/linked-list-size empty-list)))
      (is (nil? (sut/get-head empty-list))))
    (testing "Insert at both ends"
      (let [linked-list (-> empty-list
                            (sut/ll-insert-tail first-value)
                            (sut/ll-insert-tail second-value)
                            (sut/ll-insert-head head-value)
                            (sut/ll-insert-tail first-value))]
        (is (= 4 (sut/linked-list-size linked-list)))
        (is (= head-value (sut/get-head linked-list)))
        (is (= [head-value first-value second-value first-value] (chain linked-list)))
        (testing "Delete first occurrence"
          (let [without-first (sut/ll-delete linked-list first-value)]
            (is (some? without-first))
            (is (= 3 (sut/linked-list-size without-first)))
            (is (= head-value (sut/get-head without-first)))
            (is (= [head-value second-value first-value] (chain without-first)))
            (is (= 4 (sut/linked-list-size linked-list)))
            (testing "Absent value"
              (is (nil? (sut/ll-delete without-first absent-value)))
              (is (= 3 (sut/linked-list-size without-first)))
              (is (= [head-value second-value first-value] (chain without-first))))
            (testing "Empty the list"
              (let [after-head (sut/ll-delete without-first head-value)
                    after-second (sut/ll-delete after-head second-value)
                    after-last (sut/ll-delete after-second first-value)]
                (is (= [second-value first-value] (chain after-head)))
                (is (= [first-value] (chain after-second)))
                (is (sut/linked-list-is-empty after-last))
                (is (= 0 (sut/linked-list-size after-last)))
                (is (nil? (sut/get-head after-last)))
                (is (= [] (chain after-last)))))))))))

(deftest stack-test
  (testing "Empty state and failed removal"
    (let [stack (sut/stack-init)]
      (is (sut/stack-is-empty stack))
      (is (= 0 (sut/stack-size stack)))
      (is (nil? (sut/stack-peek stack)))
      (is (nil? (sut/stack-pop stack)))))
  (testing "LIFO and non-mutating peek"
    (let [stack (-> (sut/stack-init)
                    (sut/stack-push first-value)
                    (sut/stack-push second-value)
                    (sut/stack-push third-value))]
      (is (= third-value (sut/stack-peek stack)))
      (is (= 3 (sut/stack-size stack)))))
  (testing "Removal and reuse"
    (let [stack (-> (sut/stack-init)
                    (sut/stack-push first-value)
                    (sut/stack-push second-value)
                    (sut/stack-push third-value))
          removed (sut/stack-pop stack)
          reused (sut/stack-push (:stack removed) reused-value)
          [values remaining] (drain reused sut/stack-is-empty sut/stack-pop :stack)]
      (is (= third-value (:value removed)))
      (is (= 3 (sut/stack-size stack)))
      (is (= [reused-value second-value first-value] values))
      (is (sut/stack-is-empty remaining))
      (is (= 0 (sut/stack-size remaining))))))

(deftest queue-test
  (testing "Empty state and failed removal"
    (let [queue (sut/queue-init)]
      (is (sut/queue-is-empty queue))
      (is (= 0 (sut/queue-size queue)))
      (is (nil? (sut/queue-peek queue)))
      (is (nil? (sut/queue-dequeue queue)))))
  (testing "FIFO and non-mutating peek"
    (let [queue (-> (sut/queue-init)
                    (sut/queue-enqueue first-value)
                    (sut/queue-enqueue second-value)
                    (sut/queue-enqueue third-value))]
      (is (= first-value (sut/queue-peek queue)))
      (is (= 3 (sut/queue-size queue)))))
  (testing "Removal and reuse"
    (let [queue (-> (sut/queue-init)
                    (sut/queue-enqueue first-value)
                    (sut/queue-enqueue second-value)
                    (sut/queue-enqueue third-value))
          removed (sut/queue-dequeue queue)
          reused (sut/queue-enqueue (:queue removed) reused-value)
          [values remaining] (drain reused sut/queue-is-empty sut/queue-dequeue :queue)]
      (is (= first-value (:value removed)))
      (is (= 3 (sut/queue-size queue)))
      (is (= [second-value third-value reused-value] values))
      (is (sut/queue-is-empty remaining))
      (is (= 0 (sut/queue-size remaining))))))
