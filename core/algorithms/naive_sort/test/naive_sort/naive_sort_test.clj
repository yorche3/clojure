(ns naive-sort.naive-sort-test
  (:require [clojure.test :refer [deftest is testing]]
            [naive-sort.naive-sort :as sut])) ; system under test

;; Shared fixtures: one constant per case.

(def ^:private standard-input [5 2 9 1 5 6])
(def ^:private standard-output [1 2 5 5 6 9])
(def ^:private sorted-input [1 2 3 4 5])
(def ^:private reverse-input [5 4 3 2 1])
(def ^:private reverse-output [1 2 3 4 5])
(def ^:private equal-input [7 7 7 7])
(def ^:private negative-input [3 -1 4 -5 0])
(def ^:private negative-output [-5 -1 0 3 4])
(def ^:private single-input [42])
(def ^:private empty-input [])

;; Runs the shared cases against `sort-fn`, tagging failures with the
;; algorithm name. `nil` is a valid Clojure value, so the failure indicator
;; is checked too instead of being omitted.
(defn- assert-all-cases [sort-fn algorithm-name]
  (is (= (sort-fn standard-input) standard-output)
      (str algorithm-name " should sort an unsorted array"))
  (is (= (sort-fn sorted-input) sorted-input)
      (str algorithm-name " should preserve an already sorted array"))
  (is (= (sort-fn reverse-input) reverse-output)
      (str algorithm-name " should sort a reverse-order array"))
  (is (= (sort-fn equal-input) equal-input)
      (str algorithm-name " should preserve equal elements"))
  (is (= (sort-fn negative-input) negative-output)
      (str algorithm-name " should sort negative values"))
  (is (= (sort-fn single-input) single-input)
      (str algorithm-name " should preserve a single-element array"))
  (is (= (sort-fn empty-input) empty-input)
      (str algorithm-name " should preserve an empty array"))
  (is (nil? (sort-fn nil))
      (str algorithm-name " should return the nil failure indicator for a nil array")))

(deftest selection-sort-test
  (testing "selection-sort"
    (assert-all-cases sut/selection-sort "selection-sort")))

(deftest bubble-sort-test
  (testing "bubble-sort"
    (assert-all-cases sut/bubble-sort "bubble-sort")))

(deftest insertion-sort-test
  (testing "insertion-sort"
    (assert-all-cases sut/insertion-sort "insertion-sort")))
