(ns data-structures-advanced.data-structures-advanced-test
  (:require [clojure.test :refer [deftest is testing]]
            [data-structures-advanced.data-structures-advanced :as sut])) ; system under test

;; Steps bind contract results by name; keyword arguments refer to those results.
;; Only node/structure returns are observed as success, never their representation.
;; Predicates (contains, has-edge) always answer true or false; nil is
;; reserved for an operation that should return a structure and cannot build it.
(def ^:private operations
  {:tree-node-init sut/tree-node-init
   :tree-node-get-value sut/tree-node-get-value
   :tree-node-get-left sut/tree-node-get-left
   :tree-node-get-right sut/tree-node-get-right
   :tree-node-set-left sut/tree-node-set-left
   :tree-node-set-right sut/tree-node-set-right
   :binary-tree-init sut/binary-tree-init
   :binary-tree-insert sut/binary-tree-insert
   :binary-tree-contains sut/binary-tree-contains
   :binary-tree-is-empty sut/binary-tree-is-empty
   :binary-tree-size sut/binary-tree-size
   :graph-init sut/graph-init
   :graph-add-vertex sut/graph-add-vertex
   :graph-add-edge sut/graph-add-edge
   :graph-has-edge sut/graph-has-edge
   :graph-get-neighbors sut/graph-get-neighbors
   :graph-is-empty sut/graph-is-empty
   :graph-vertex-count sut/graph-vertex-count
   :graph-edge-count sut/graph-edge-count})

(def ^:private structure-results
  #{:tree-node-init :tree-node-get-left :tree-node-get-right
    :tree-node-set-left :tree-node-set-right
    :binary-tree-init :binary-tree-insert
    :graph-init :graph-add-vertex :graph-add-edge})

(def ^:private create-node-input
  [[:parent :tree-node-init 10]
   [:value :tree-node-get-value :parent]])
(def ^:private create-node-output [:success 10])

(def ^:private absent-links-input
  [[:parent :tree-node-init 10]
   [:left :tree-node-get-left :parent]
   [:right :tree-node-get-right :parent]])
(def ^:private absent-links-output [:success nil nil])

(def ^:private link-children-input
  [[:parent :tree-node-init 10]
   [:left :tree-node-init 20]
   [:right :tree-node-init 30]
   [:with-left :tree-node-set-left :parent :left]
   [:linked :tree-node-set-right :with-left :right]
   [:linked-left :tree-node-get-left :linked]
   [:left-value :tree-node-get-value :linked-left]
   [:linked-right :tree-node-get-right :linked]
   [:right-value :tree-node-get-value :linked-right]])
(def ^:private link-children-output
  [:success :success :success :success :success :success 20 :success 30])

(def ^:private traverse-links-input
  (into link-children-input
        [[:left-left :tree-node-get-left :linked-left]
         [:left-right :tree-node-get-right :linked-left]
         [:right-left :tree-node-get-left :linked-right]
         [:right-right :tree-node-get-right :linked-right]]))
(def ^:private traverse-links-output
  (into link-children-output [nil nil nil nil]))

(def ^:private unlink-children-input
  (into link-children-input
        [[:without-left :tree-node-set-left :linked nil]
         [:value-after-left :tree-node-get-value :without-left]
         [:cleared-left :tree-node-get-left :without-left]
         [:preserved-right :tree-node-get-right :without-left]
         [:preserved-value :tree-node-get-value :preserved-right]
         [:without-children :tree-node-set-right :without-left nil]
         [:final-left :tree-node-get-left :without-children]
         [:final-right :tree-node-get-right :without-children]
         [:final-value :tree-node-get-value :without-children]]))
(def ^:private unlink-children-output
  (into link-children-output
        [:success 10 nil :success 30 :success nil nil 10]))

(def ^:private immutable-node-input
  [[:parent :tree-node-init 10]
   [:child :tree-node-init 20]
   [:linked :tree-node-set-left :parent :child]
   [:linked-child :tree-node-get-left :linked]
   [:child-value :tree-node-get-value :linked-child]
   [:original-left :tree-node-get-left :parent]
   [:original-right :tree-node-get-right :parent]
   [:original-value :tree-node-get-value :parent]])
(def ^:private immutable-node-output
  [:success :success :success :success 20 nil nil 10])

(def ^:private node-cases
  [["Create a tree node" create-node-input create-node-output]
   ["Absent initial links" absent-links-input absent-links-output]
   ["Link two children" link-children-input link-children-output]
   ["Traverse each link" traverse-links-input traverse-links-output]
   ["Unlink children" unlink-children-input unlink-children-output]
   ["Immutable adaptation" immutable-node-input immutable-node-output]])

(def ^:private empty-tree-input
  [[:tree :binary-tree-init]
   [:empty :binary-tree-is-empty :tree]
   [:size :binary-tree-size :tree]
   [:missing :binary-tree-contains :tree 50]])
(def ^:private empty-tree-output [:success true 0 false])

(def ^:private root-input
  [[:tree :binary-tree-init]
   [:root :binary-tree-insert :tree 50]
   [:size :binary-tree-size :root]
   [:empty :binary-tree-is-empty :root]
   [:present :binary-tree-contains :root 50]])
(def ^:private root-output [:success :success 1 false true])

(def ^:private lower-higher-input
  [[:tree :binary-tree-init]
   [:root :binary-tree-insert :tree 50]
   [:lower :binary-tree-insert :root 30]
   [:higher :binary-tree-insert :lower 70]
   [:size :binary-tree-size :higher]
   [:root-present :binary-tree-contains :higher 50]
   [:lower-present :binary-tree-contains :higher 30]
   [:higher-present :binary-tree-contains :higher 70]])
(def ^:private lower-higher-output
  [:success :success :success :success 3 true true true])

(def ^:private multilevel-input
  [[:tree :binary-tree-init]
   [:root :binary-tree-insert :tree 50]
   [:lower :binary-tree-insert :root 30]
   [:higher :binary-tree-insert :lower 70]
   [:lowest :binary-tree-insert :higher 20]
   [:middle :binary-tree-insert :lowest 40]
   [:root-present :binary-tree-contains :middle 50]
   [:lower-present :binary-tree-contains :middle 30]
   [:higher-present :binary-tree-contains :middle 70]
   [:lowest-present :binary-tree-contains :middle 20]
   [:middle-present :binary-tree-contains :middle 40]
   [:size :binary-tree-size :middle]])
(def ^:private multilevel-output
  [:success :success :success :success :success :success
   true true true true true 5])

(def ^:private missing-tree-value-input
  [[:tree :binary-tree-init]
   [:root :binary-tree-insert :tree 50]
   [:missing :binary-tree-contains :root 99]
   [:size :binary-tree-size :root]
   [:present :binary-tree-contains :root 50]])
(def ^:private missing-tree-value-output [:success :success false 1 true])

(def ^:private duplicate-tree-value-input
  [[:tree :binary-tree-init]
   [:root :binary-tree-insert :tree 50]
   [:lower :binary-tree-insert :root 30]
   [:higher :binary-tree-insert :lower 70]
   [:duplicate :binary-tree-insert :higher 30]
   [:size :binary-tree-size :duplicate]
   [:root-present :binary-tree-contains :duplicate 50]
   [:lower-present :binary-tree-contains :duplicate 30]
   [:higher-present :binary-tree-contains :duplicate 70]])
(def ^:private duplicate-tree-value-output
  [:success :success :success :success :success 3 true true true])

(def ^:private ordered-insertions-input
  [[:tree :binary-tree-init]
   [:first :binary-tree-insert :tree 20]
   [:second :binary-tree-insert :first 30]
   [:third :binary-tree-insert :second 40]
   [:fourth :binary-tree-insert :third 50]
   [:fifth :binary-tree-insert :fourth 70]
   [:size :binary-tree-size :fifth]
   [:first-present :binary-tree-contains :fifth 20]
   [:second-present :binary-tree-contains :fifth 30]
   [:third-present :binary-tree-contains :fifth 40]
   [:fourth-present :binary-tree-contains :fifth 50]
   [:fifth-present :binary-tree-contains :fifth 70]
   [:missing :binary-tree-contains :fifth 99]])
(def ^:private ordered-insertions-output
  [:success :success :success :success :success :success
   5 true true true true true false])

(def ^:private tree-cases
  [["Create empty" empty-tree-input empty-tree-output]
   ["Insert the root" root-input root-output]
   ["Insert lower and higher" lower-higher-input lower-higher-output]
   ["Multilevel branches" multilevel-input multilevel-output]
   ["Missing value in nonempty tree" missing-tree-value-input missing-tree-value-output]
   ["Idempotent duplicate" duplicate-tree-value-input duplicate-tree-value-output]
   ["Ordered insertions" ordered-insertions-input ordered-insertions-output]])

(def ^:private empty-graph-input
  [[:graph :graph-init]
   [:empty :graph-is-empty :graph]
   [:vertices :graph-vertex-count :graph]
   [:edges :graph-edge-count :graph]
   [:missing-edge :graph-has-edge :graph 10 30]
   [:missing-neighbors :graph-get-neighbors :graph 10]])
(def ^:private empty-graph-output [:success true 0 0 false nil])

(def ^:private isolated-vertex-input
  [[:graph :graph-init]
   [:isolated :graph-add-vertex :graph 10]
   [:empty :graph-is-empty :isolated]
   [:vertices :graph-vertex-count :isolated]
   [:edges :graph-edge-count :isolated]
   [:neighbors :graph-get-neighbors :isolated 10]])
(def ^:private isolated-vertex-output [:success :success false 1 0 []])

(def ^:private directed-edge-input
  [[:graph :graph-init]
   [:first :graph-add-vertex :graph 10]
   [:second :graph-add-vertex :first 70]
   [:linked :graph-add-edge :second 10 70]
   [:forward :graph-has-edge :linked 10 70]
   [:reverse :graph-has-edge :linked 70 10]
   [:vertices :graph-vertex-count :linked]
   [:edges :graph-edge-count :linked]])
(def ^:private directed-edge-output
  [:success :success :success :success true false 2 1])

(def ^:private duplicate-vertex-input
  [[:graph :graph-init]
   [:first :graph-add-vertex :graph 10]
   [:second :graph-add-vertex :first 30]
   [:linked :graph-add-edge :second 10 30]
   [:duplicate :graph-add-vertex :linked 10]
   [:vertices :graph-vertex-count :duplicate]
   [:edges :graph-edge-count :duplicate]
   [:neighbors :graph-get-neighbors :duplicate 10]])
(def ^:private duplicate-vertex-output
  [:success :success :success :success :success 2 1 [30]])

(def ^:private duplicate-edge-input
  [[:graph :graph-init]
   [:first :graph-add-vertex :graph 10]
   [:second :graph-add-vertex :first 30]
   [:linked :graph-add-edge :second 10 30]
   [:duplicate :graph-add-edge :linked 10 30]
   [:vertices :graph-vertex-count :duplicate]
   [:edges :graph-edge-count :duplicate]
   [:neighbors :graph-get-neighbors :duplicate 10]])
(def ^:private duplicate-edge-output
  [:success :success :success :success :success 2 1 [30]])

(def ^:private missing-endpoint-input
  [[:graph :graph-init]
   [:first :graph-add-vertex :graph 10]
   [:second :graph-add-vertex :first 30]
   [:linked :graph-add-edge :second 10 30]
   [:missing-target :graph-add-edge :linked 10 99]
   [:missing-source :graph-add-edge :linked 99 10]
   [:both-missing :graph-add-edge :linked 98 99]
   [:missing-loop :graph-add-edge :linked 99 99]
   [:vertices :graph-vertex-count :linked]
   [:edges :graph-edge-count :linked]
   [:neighbors :graph-get-neighbors :linked 10]
   [:isolated-neighbors :graph-get-neighbors :linked 30]
   [:absent-target :graph-has-edge :linked 10 99]
   [:absent-source :graph-has-edge :linked 99 10]
   [:absent-both :graph-has-edge :linked 98 99]
   [:absent-loop :graph-has-edge :linked 99 99]
   [:missing-98 :graph-get-neighbors :linked 98]
   [:missing-99 :graph-get-neighbors :linked 99]])
(def ^:private missing-endpoint-output
  [:success :success :success :success nil nil nil nil
   2 1 [30] [] false false false false nil nil])

(def ^:private sorted-neighbors-input
  [[:graph :graph-init]
   [:first :graph-add-vertex :graph 10]
   [:second :graph-add-vertex :first 30]
   [:third :graph-add-vertex :second 50]
   [:fourth :graph-add-vertex :third 70]
   [:edge-70 :graph-add-edge :fourth 10 70]
   [:edge-30 :graph-add-edge :edge-70 10 30]
   [:edge-50 :graph-add-edge :edge-30 10 50]
   [:linked :graph-add-edge :edge-50 30 50]
   [:neighbors-10 :graph-get-neighbors :linked 10]
   [:neighbors-30 :graph-get-neighbors :linked 30]
   [:neighbors-50 :graph-get-neighbors :linked 50]
   [:neighbors-70 :graph-get-neighbors :linked 70]
   [:vertices :graph-vertex-count :linked]
   [:edges :graph-edge-count :linked]])
(def ^:private sorted-neighbors-output
  [:success :success :success :success :success
   :success :success :success :success [30 50 70] [50] [] [] 4 4])

(def ^:private missing-edge-input
  [[:graph :graph-init]
   [:first :graph-add-vertex :graph 10]
   [:second :graph-add-vertex :first 30]
   [:missing :graph-has-edge :second 10 30]
   [:vertices :graph-vertex-count :second]
   [:edges :graph-edge-count :second]
   [:empty :graph-is-empty :second]])
(def ^:private missing-edge-output
  [:success :success :success false 2 0 false])

(def ^:private missing-neighbors-input
  [[:graph :graph-init]
   [:isolated :graph-add-vertex :graph 10]
   [:missing :graph-get-neighbors :isolated 99]
   [:neighbors :graph-get-neighbors :isolated 10]
   [:vertices :graph-vertex-count :isolated]
   [:edges :graph-edge-count :isolated]])
(def ^:private missing-neighbors-output
  [:success :success nil [] 1 0])

(def ^:private self-loop-input
  [[:graph :graph-init]
   [:vertex :graph-add-vertex :graph 10]
   [:loop :graph-add-edge :vertex 10 10]
   [:duplicate :graph-add-edge :loop 10 10]
   [:present :graph-has-edge :duplicate 10 10]
   [:neighbors :graph-get-neighbors :duplicate 10]
   [:vertices :graph-vertex-count :duplicate]
   [:edges :graph-edge-count :duplicate]])
(def ^:private self-loop-output
  [:success :success :success :success true [10] 1 1])

(def ^:private neighbor-isolation-input
  [[:graph :graph-init]
   [:first :graph-add-vertex :graph 10]
   [:second :graph-add-vertex :first 30]
   [:third :graph-add-vertex :second 50]
   [:linked :graph-add-edge :third 10 30]
   [:saved :graph-get-neighbors :linked 10]
   [:updated :graph-add-edge :linked 10 50]
   [:saved-after-update :saved-neighbors :saved]
   [:fresh :graph-get-neighbors :updated 10]
   [:vertices :graph-vertex-count :updated]
   [:edges :graph-edge-count :updated]])
(def ^:private neighbor-isolation-output
  [:success :success :success :success :success [30] :success [30] [30 50] 3 2])

(def ^:private graph-cases
  [["Create empty" empty-graph-input empty-graph-output]
   ["Isolated vertex" isolated-vertex-input isolated-vertex-output]
   ["Nonconsecutive labels and directed edge" directed-edge-input directed-edge-output]
   ["Duplicate vertex preserves adjacency" duplicate-vertex-input duplicate-vertex-output]
   ["Duplicate edge" duplicate-edge-input duplicate-edge-output]
   ["Missing endpoint without mutation" missing-endpoint-input missing-endpoint-output]
   ["Sorted neighbours and counts" sorted-neighbors-input sorted-neighbors-output]
   ["Missing-edge query" missing-edge-input missing-edge-output]
   ["Neighbours of missing vertex" missing-neighbors-input missing-neighbors-output]
   ["Self-loop and duplicate" self-loop-input self-loop-output]
   ["Neighbour-result isolation" neighbor-isolation-input neighbor-isolation-output]])

(defn- execute-case [operation-functions input]
  (loop [steps input
         bindings {}
         output []]
    (if-let [[binding operation & arguments] (first steps)]
      (let [arguments (mapv #(if (keyword? %) (get bindings %) %) arguments)
            ;; Keep the actual neighbour value until after the graph update.
            result (if (= :saved-neighbors operation)
                     (first arguments)
                     (apply (get operation-functions operation) arguments))
            observed (if (and (structure-results operation) (some? result))
                       :success
                       result)]
        (recur (next steps)
               (assoc bindings binding result)
               (conj output observed)))
      output)))

(defn- run-cases [subject operation cases]
  (let [operation-functions (assoc operations subject operation)]
    (doseq [[case-name input expected] cases]
      (testing case-name
        (is (= expected (execute-case operation-functions input))
            (str (name subject) " should produce " (pr-str expected)
                 " for " case-name))))))

(deftest tree-node-init-test
  (run-cases :tree-node-init sut/tree-node-init node-cases))

(deftest tree-node-get-value-test
  (run-cases :tree-node-get-value sut/tree-node-get-value node-cases))

(deftest tree-node-get-left-test
  (run-cases :tree-node-get-left sut/tree-node-get-left node-cases))

(deftest tree-node-get-right-test
  (run-cases :tree-node-get-right sut/tree-node-get-right node-cases))

(deftest tree-node-set-left-test
  (run-cases :tree-node-set-left sut/tree-node-set-left node-cases))

(deftest tree-node-set-right-test
  (run-cases :tree-node-set-right sut/tree-node-set-right node-cases))

(deftest binary-tree-init-test
  (run-cases :binary-tree-init sut/binary-tree-init tree-cases))

(deftest binary-tree-insert-test
  (run-cases :binary-tree-insert sut/binary-tree-insert tree-cases))

(deftest binary-tree-contains-test
  (run-cases :binary-tree-contains sut/binary-tree-contains tree-cases))

(deftest binary-tree-is-empty-test
  (run-cases :binary-tree-is-empty sut/binary-tree-is-empty tree-cases))

(deftest binary-tree-size-test
  (run-cases :binary-tree-size sut/binary-tree-size tree-cases))

(deftest graph-init-test
  (run-cases :graph-init sut/graph-init graph-cases))

(deftest graph-add-vertex-test
  (run-cases :graph-add-vertex sut/graph-add-vertex graph-cases))

(deftest graph-add-edge-test
  (run-cases :graph-add-edge sut/graph-add-edge graph-cases))

(deftest graph-has-edge-test
  (run-cases :graph-has-edge sut/graph-has-edge graph-cases))

(deftest graph-get-neighbors-test
  (run-cases :graph-get-neighbors sut/graph-get-neighbors graph-cases))

(deftest graph-is-empty-test
  (run-cases :graph-is-empty sut/graph-is-empty graph-cases))

(deftest graph-vertex-count-test
  (run-cases :graph-vertex-count sut/graph-vertex-count graph-cases))

(deftest graph-edge-count-test
  (run-cases :graph-edge-count sut/graph-edge-count graph-cases))
