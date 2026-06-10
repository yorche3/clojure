(ns proglangs.numbers-accumulator-test
  (:require [clojure.test :refer [deftest are testing]]
            [proglangs.numbers :as sut])) ; system under test

(deftest sum-first-n-acc-test
  (testing "Sum of first n numbers using accumulator"
    (are [n expected] (= (sut/sum-first-n-acc n) expected)
      0 0
      3 6)))

(deftest factorial-acc-test
  (testing "Factorial of a number using accumulator"
    (are [n expected] (= (sut/factorial-acc n) expected)
      0 1
      4 24)))

(deftest fibonacci-acc-test
  (testing "Fibonacci of a number using accumulator"
    (are [n expected] (= (sut/fibonacci-acc n) expected)
      0 0
      1 1
      6 8)))

(deftest largest-common-divisor-acc-test
  (testing "Largest common divisor of two numbers using accumulator"
    (are [a b expected] (= (sut/largest-common-divisor-acc a b) expected)
      12 8 4
      7 5 1)))

(deftest least-common-multiple-acc-test
  (testing "Least common multiple of two numbers using accumulator"
    (are [a b expected] (= (sut/least-common-multiple-acc a b) expected)
      8 6 24
      6 4 12)))