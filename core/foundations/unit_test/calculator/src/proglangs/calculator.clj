(ns proglangs.calculator)

(defn addition [a, b]
  (+ a b))

(defn subtraction [a, b]
  (- a b))

(defn multiplication [a, b]
  (loop [i 0
         result 0]
    (if (>= i b)
      result
      (recur (addition i 1) (addition result a)))))

(defn division [a, b]
  (loop [remainder a
         quotient 0]
    (if (< remainder b)
      quotient
      (recur (subtraction remainder b) (addition quotient 1)))))
  
(defn modulus [a, b]
  (let [quotient (division a b)]
    (subtraction a (multiplication b quotient))))