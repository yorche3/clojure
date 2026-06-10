(ns proglangs.numbers-recursive-test
  (:require [clojure.test :refer [deftest are testing]]
            [proglangs.numbers :as sut])) ; system under test

(deftest sum-first-n-rec-test
  (testing "Sum of first n numbers recursively"
    (are [n expected] (= (sut/sum-first-n-rec n) expected)
      0 0
      3 6)))

(deftest factorial-rec-test
  (testing "Factorial of a number recursively"
    (are [n expected] (= (sut/factorial-rec n) expected)
      0 1
      4 24)))

(deftest fibonacci-rec-test
  (testing "Fibonacci of a number recursively"
    (are [n expected] (= (sut/fibonacci-rec n) expected)
      0 0
      1 1
      6 8)))

(deftest largest-common-divisor-rec-test
  (testing "Largest common divisor of two numbers recursively"
    (are [a b expected] (= (sut/largest-common-divisor-rec a b) expected)
      12 8 4
      7 5 1)))

(deftest least-common-multiple-rec-test
  (testing "Least common multiple of two numbers recursively"
    (are [a b expected] (= (sut/least-common-multiple-rec a b) expected)
      8 6 24
      6 4 12)))