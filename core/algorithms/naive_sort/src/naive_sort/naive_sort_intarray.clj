(ns naive-sort.naive-sort-intarray)

;; Helper function to find the index of the minimum element in the range [start, n)
(defn- find-min-idx [^ints arr start n]
  (loop [j (inc start) min-idx start]
    (if (>= j n)
      min-idx
      (if (< (aget arr j) (aget arr min-idx))
        (recur (inc j) j)
        (recur (inc j) min-idx)))))

;; selection-sort-intarray: sorts an int-array using the selection sort algorithm with in-site mutation
;; input: an int-array to be sorted (mutated in-place)
;; output: the same int-array sorted in ascending order (or nil for nil input)
(defn selection-sort-intarray [^ints arr]
  (if (or (nil? arr) (<= (count arr) 1))
    arr  ; if the array is nil or has only one element, return the array as it is already sorted
    (let [n (count arr)]  ; perform selection sort
      (loop [i 0]  ; iterate over each element in the array, i from 0 to n-1
        (if (< i n)  ; continue while i < n
          (do
            (let [min-idx (find-min-idx arr i n)]  ; find minimum element in unsorted portion
              (when (not= min-idx i)  ; if min_idx != i, swap(arr[i], arr[min-idx])
                (let [temp (aget arr i)]
                  (aset arr i (aget arr min-idx))
                  (aset arr min-idx temp))))
            (recur (inc i)))  ; i++
          arr)))))  ; return arr when i >= n

;; Helper function to perform one pass of bubble sort
(defn- bubble-pass! [^ints arr n pass]
  (loop [j 0]
    (if (< j (- n pass 1))
      (do
        (when (> (aget arr j) (aget arr (inc j)))
          (let [temp (aget arr j)]
            (aset arr j (aget arr (inc j)))
            (aset arr (inc j) temp)))
        (recur (inc j)))
      arr)))

;; bubble-sort-intarray: sorts an int-array using the bubble sort algorithm with in-site mutation
;; input: an int-array to be sorted (mutated in-place)
;; output: the same int-array sorted in ascending order (or nil for nil input)
(defn bubble-sort-intarray [^ints arr]
  (if (or (nil? arr) (<= (count arr) 1))
    arr  ; if the array is nil or has only one element, return the array as it is already sorted
    (let [n (count arr)]  ; perform bubble sort
      (loop [i 0]
        (if (< i (dec n))
          (do
            (bubble-pass! arr n i)  ; perform one pass of bubble sort
            (recur (inc i)))
          arr)))))

;; Helper function to insert an element at the correct position
(defn- insert-element! [^ints arr pos]
  (let [key (aget arr pos)]
    (loop [j (dec pos)]
      (if (and (>= j 0) (> (aget arr j) key))  ; continue while j >= 0 and arr[j] > key
        (do
          (aset arr (inc j) (aget arr j))  ; arr[j+1] = arr[j]
          (recur (dec j)))  ; j--
        (aset arr (inc j) key)))))  ; arr[j+1] = key

;; insertion-sort-intarray: sorts an int-array using the insertion sort algorithm with in-site mutation
;; input: an int-array to be sorted (mutated in-place)
;; output: the same int-array sorted in ascending order (or nil for nil input)
(defn insertion-sort-intarray [^ints arr]
  (if (or (nil? arr) (<= (count arr) 1))
    arr  ; if the array is nil or has only one element, return the array as it is already sorted
    (let [n (count arr)]  ; perform insertion sort
      (loop [i 1]  ; i from 1 to n-1
        (if (< i n)  ; continue while i < n
          (do
            (insert-element! arr i)  ; insert arr[i] at the correct position
            (recur (inc i)))  ; i++
          arr)))))