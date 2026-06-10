(ns proglangs.calculator-test
  (:require [clojure.test :refer [deftest is testing]]
            [proglangs.calculator :as sut])) ; system under test

(deftest addition-test
  (testing "3 + 2 must be 5"
    (is (= 5 (sut/addition 3 2)))))

(deftest subtraction-test
  (testing "5 - 2 must be 3"
    (is (= 3 (sut/subtraction 5 2)))))

(deftest multiplication-test
  (testing "3 * 4 must be 12"
    (is (= 12 (sut/multiplication 3 4)))))

(deftest division-test
  (testing "10 / 3 must be 3"
    (is (= 3 (sut/division 10 3)))))

(deftest modulus-test
  (testing "10 % 3 must be 1"
    (is (= 1 (sut/modulus 10 3)))))