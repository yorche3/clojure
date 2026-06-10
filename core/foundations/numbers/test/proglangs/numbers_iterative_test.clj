(ns proglangs.numbers-iterative-test
  (:require [clojure.test :refer [deftest are testing]]
            [proglangs.numbers :as sut])) ; system under test

(deftest sum-first-n-iter-test
  (testing "Sum of first n numbers iteratively"
    (are [n expected] (= (sut/sum-first-n-iter n) expected)
      0 0
      3 6)))

(deftest factorial-iter-test
  (testing "Factorial iteratively"
    (are [n expected] (= (sut/factorial-iter n) expected)
      0 1
      4 24)))

(deftest fibonacci-iter-test
  (testing "Fibonacci iteratively"
    (are [n expected] (= (sut/fibonacci-iter n) expected)
      0 0
      1 1
      6 8)))

(deftest largest-common-divisor-iter-test
  (testing "Largest common divisor iteratively"
    (are [a b expected] (= (sut/largest-common-divisor-iter a b) expected)
      12 8 4
      7 5 1)))

(deftest least-common-multiple-iter-test
  (testing "Least common multiple iteratively"
    (are [a b expected] (= (sut/least-common-multiple-iter a b) expected)
      8 6 24
      6 4 12)))