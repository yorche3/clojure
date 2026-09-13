(ns naive-sort.naive-sort-intarray-test
  (:require [clojure.test :refer [deftest is testing]]
            [naive-sort.naive-sort-intarray :as sut]))

;; Shared fixtures: one constant per case.

(def ^:private standard-input (int-array [5 2 9 1 5 6]))
(def ^:private standard-output [1 2 5 5 6 9])
(def ^:private sorted-input (int-array [1 2 3 4 5]))
(def ^:private reverse-input (int-array [5 4 3 2 1]))
(def ^:private reverse-output [1 2 3 4 5])
(def ^:private equal-input (int-array [7 7 7 7]))
(def ^:private negative-input (int-array [3 -1 4 -5 0]))
(def ^:private negative-output [-5 -1 0 3 4])
(def ^:private single-input (int-array [42]))
(def ^:private empty-input (int-array []))

;; Helper function to convert int-array to vector for comparison
(defn- array->vec [arr]
  (when arr (vec arr)))

;; Runs the shared cases against `sort-fn`, tagging failures with the
;; algorithm name. `nil` is a valid Clojure value, so the failure indicator
;; is checked too instead of being omitted.
(defn- assert-all-cases [sort-fn algorithm-name]
  (is (= (array->vec (sort-fn standard-input)) standard-output)
      (str algorithm-name " should sort an unsorted array"))
  (is (= (array->vec (sort-fn sorted-input)) (vec sorted-input))
      (str algorithm-name " should preserve an already sorted array"))
  (is (= (array->vec (sort-fn reverse-input)) reverse-output)
      (str algorithm-name " should sort a reverse-order array"))
  (is (= (array->vec (sort-fn equal-input)) (vec equal-input))
      (str algorithm-name " should preserve equal elements"))
  (is (= (array->vec (sort-fn negative-input)) negative-output)
      (str algorithm-name " should sort negative values"))
  (is (= (array->vec (sort-fn single-input)) (vec single-input))
      (str algorithm-name " should preserve a single-element array"))
  (is (= (array->vec (sort-fn empty-input)) (vec empty-input))
      (str algorithm-name " should preserve an empty array"))
  (is (nil? (sort-fn nil))
      (str algorithm-name " should return the nil failure indicator for a nil array")))

(deftest selection-sort-intarray-test
  (testing "selection-sort-intarray"
    (assert-all-cases sut/selection-sort-intarray "selection-sort-intarray")))

(deftest bubble-sort-intarray-test
  (testing "bubble-sort-intarray"
    (assert-all-cases sut/bubble-sort-intarray "bubble-sort-intarray")))

(deftest insertion-sort-intarray-test
  (testing "insertion-sort-intarray"
    (assert-all-cases sut/insertion-sort-intarray "insertion-sort-intarray")))
