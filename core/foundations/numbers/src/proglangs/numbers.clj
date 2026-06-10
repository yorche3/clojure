(ns proglangs.numbers)

(defn sum-first-n-rec [n]
  (if (<= n 0)
    0
    (+ n (sum-first-n-rec (- n 1)))))

(defn factorial-rec [n]
  (if (<= n 1)
    1
    (* n (factorial-rec (- n 1)))))

(defn fibonacci-rec [n]
  (if (<= n 1)
    n
    (+ (fibonacci-rec (- n 1))
       (fibonacci-rec (- n 2)))))

(defn largest-common-divisor-rec [a b]
  (if (= b 0)
    a
    (largest-common-divisor-rec b (mod a b))))

(defn least-common-multiple-rec [a b]
  (/ (* a b) (largest-common-divisor-rec a b)))

(defn sum-first-n-helper [acc n]
  (if (<= n 0)
    acc
    (recur (+ acc n) (- n 1))))

(defn sum-first-n-acc [n]
  (sum-first-n-helper 0 n))

(defn factorial-helper [acc n]
  (if (<= n 1)
    acc
    (recur (* acc n) (- n 1))))

(defn factorial-acc [n]
  (factorial-helper 1 n))

(defn fibonacci-helper [n acc1 acc2]
  (if (< n 2)
    (if (< n 1)
      acc1
      acc2)
    (recur (- n 1) acc2 (+ acc1 acc2))))

(defn fibonacci-acc [n]
  (fibonacci-helper n 0 1))

(defn largest-common-divisor-helper [a b]
  (if (= b 0)
    a
    (recur b (mod a b))))

(defn largest-common-divisor-acc [a b]
  (largest-common-divisor-helper a b))

(defn least-common-multiple-acc [a b]
  (/ (* a b) (largest-common-divisor-acc a b)))

(defn sum-first-n-iter [n]
  (loop [acc 0
         i n]
    (if (<= i 0)
      acc
      (recur (+ acc i) (- i 1)))))

(defn factorial-iter [n]
  (loop [acc 1
         i n]
    (if (<= i 1)
      acc
      (recur (* acc i) (- i 1)))))

(defn fibonacci-iter [n]
  (if (<= n 1)
    n
    (loop [i n
           acc1 0
           acc2 1]
      (if (< i 2)
        acc2
        (recur (- i 1) acc2 (+ acc1 acc2))))))

(defn largest-common-divisor-iter [a b]
  (loop [a-copy a
         b-copy b]
    (if (= b-copy 0)
      a-copy
      (recur b-copy (mod a-copy b-copy)))))

(defn least-common-multiple-iter [a b]
  (/ (* a b) (largest-common-divisor-iter a b)))