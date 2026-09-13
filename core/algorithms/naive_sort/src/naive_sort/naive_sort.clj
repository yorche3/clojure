(ns naive-sort.naive-sort)

;; selection-sort: sorts an array using the selection sort algorithm
;; input: an array of elements to be sorted
;; out: a new array with the elements sorted in ascending order
(defn selection-sort [arr]
  (cond
    ;; if the array is nil, return nil
    (nil? arr) nil
    ;; if the array is empty or has only one element, return the array as it is already sorted
    (or (empty? arr) (= (count arr) 1)) arr
    ;; otherwise, proceed with selection sort
    :else 
    ;; min-index: finds the index of the minimum element in the array starting from a given index
    (letfn [(min-index [xs start]
              (loop [i start
                     min-i start]
                ;; i: keeps track of the current index in the loop
                ;; min-i: keeps track of the index of the minimum element found so far
                (if (>= i (count xs))
                  ;; if the current index has reached the end of the array, return the index of the minimum element found so far
                  min-i
                  ;; otherwise, compare the current element with the minimum element found so far and update the minimum index if necessary
                  (recur (inc i) (if (< (nth xs i) (nth xs min-i)) i min-i)))))]
      ;; start the selection sort process by repeatedly finding the minimum element and building the sorted array
      ;; remaining: keeps track of the elements that are yet to be sorted
      ;; sorted: keeps track of the elements that have been sorted so far
      (loop [remaining (vec arr)
             sorted []]
        (if (empty? remaining)
          ;; if there are no more elements to sort, return the sorted array
          sorted
          ;; otherwise, find the minimum element in the remaining array and add it to the sorted array
          ;; find the index of the minimum element in the remaining array
          ;; min-i: the index of the minimum element in the remaining array
          ;; min-val: the value of the minimum element in the remaining array
          ;; rest-without-min: the remaining array after removing the minimum element
          (let [min-i (min-index remaining 0)
                min-val (nth remaining min-i)
                rest-without-min (into (subvec remaining 0 min-i) 
                                       (subvec remaining (inc min-i)))]
            ;; recur with the remaining elements and the updated sorted array
            (recur rest-without-min (conj sorted min-val))))))))

;; bubble-sort: sorts an array using the bubble sort algorithm
;; input: an array of elements to be sorted
;; out: a new array with the elements sorted in ascending order
(defn bubble-sort [arr]
  (cond
    ;; if the array is nil, return nil
    (nil? arr) nil
    ;; if the array is empty or has only one element, return the array as it is already sorted
    (or (empty? arr) (= (count arr) 1)) arr
    ;; otherwise, proceed with bubble sort
    :else
    ;; loop: repeatedly passes through the array, swapping adjacent elements if they are in the wrong order
    ;; sorted: keeps track of the current state of the array as it gets sorted
    ;; n: keeps track of the number of elements left to sort
    (loop [sorted arr
           n (count arr)]
      (if (<= n 1)
        ;; if there is only one element left to sort, the array is already sorted
        sorted
        ;; otherwise, perform a pass of bubble sort on the first n elements
        (recur (loop [i 0
                      s sorted]
                 ;; i: keeps track of the current index in the pass
                 ;; s: keeps track of the current state of the array during the pass
                 (if (>= i (dec n))
                   ;; if the current index has reached the end of the unsorted portion, return the array as it is for this pass
                   s
                   ;; otherwise, compare the current element with the next element and swap if necessary
                   (let [s (if (> (nth s i) (nth s (inc i)))
                             (assoc s i (nth s (inc i)) (inc i) (nth s i))
                             s)]
                     ;; continue to the next index in the pass
                     (recur (inc i) s))))
               ;; after completing a pass, reduce the number of elements left to sort and repeat
               (dec n))))))

;; Insertion sort: sorts an array using the insertion sort algorithm
;; input: an array of elements to be sorted
;; out: a new array with the elements sorted in ascending order
(defn insertion-sort [arr]
  (cond
    ;; if the array is nil, return nil
    (nil? arr) nil
    ;; if the array is empty or has only one element, return the array as it is already sorted
    (or (empty? arr) (= (count arr) 1)) arr
    ;; otherwise, proceed with insertion sort
    :else
    (loop [sorted [(first arr)]
           remaining (rest arr)]
      ;; loop: repeatedly inserts the next element from the remaining array into the sorted array
      ;; sorted: keeps track of the current state of the sorted array
      ;; remaining: keeps track of the elements yet to be inserted into the sorted array
      (if (empty? remaining)
        ;; if there are no more elements to insert, return the sorted array
        sorted
        ;; otherwise, insert the next element into the sorted array
        (let [current (first remaining)
              insert-pos (count (take-while #(< % current) sorted))
              new-sorted (concat (subvec (vec sorted) 0 insert-pos)
                                 [current]
                                 (subvec (vec sorted) insert-pos))]
          ;; continue with the next element in the remaining array
          (recur new-sorted (rest remaining)))))))