(ns data-structures-advanced.data-structures-advanced)

;; Node's value is embedded in each child; records cannot inherit from records.
(defrecord TreeNode [value left right])
(defrecord BinaryTree [root count])
(defrecord NeighborNode [label next])
(defrecord GraphNode [label neighbors next-vertex])
(defrecord Graph [vertices vertex-total edges])

(defn tree-node-init
  "Integer -> TreeNode. Creates a node with absent left and right links."
  [value]
  (->TreeNode value nil nil))

(defn tree-node-get-value
  "TreeNode -> Integer. Reads the embedded Node value without validation."
  [node]
  (:value node))

(defn tree-node-get-left
  "TreeNode -> TreeNode or nil. Reads the left link; nil means absent."
  [node]
  (:left node))

(defn tree-node-get-right
  "TreeNode -> TreeNode or nil. Reads the right link; nil means absent."
  [node]
  (:right node))

(defn tree-node-set-left
  "TreeNode, TreeNode or nil -> TreeNode. Returns a new node with this left
  link, preserving its value and right link; nil clears the link."
  [node left]
  (->TreeNode (:value node) left (:right node)))

(defn tree-node-set-right
  "TreeNode, TreeNode or nil -> TreeNode. Returns a new node with this right
  link, preserving its value and left link; nil clears the link."
  [node right]
  (->TreeNode (:value node) (:left node) right))

(defn binary-tree-init
  "() -> BinaryTree. Creates an empty tree without a capacity parameter."
  []
  (->BinaryTree nil 0))

(defn binary-tree-insert
  "BinaryTree, Integer -> BinaryTree. Returns the updated persistent tree;
  duplicates succeed without changing its size. No contractual limit failure."
  [tree value]
  ;; Función local: solo esta operación recorre el subárbol, así que no necesita
  ;; ámbito de espacio de nombres. Devuelve [subárbol-actualizado insertado?]
  ;; para que quien llama sepa si el contador debe crecer; un duplicado deja el
  ;; subárbol intacto.
  (letfn [(tree-node-insert [node value]
            (if (nil? node)
              [(tree-node-init value) true]
              (cond
                (< value (:value node))
                (let [[left inserted?] (tree-node-insert (:left node) value)]
                  [(assoc node :left left) inserted?])
                (> value (:value node))
                (let [[right inserted?] (tree-node-insert (:right node) value)]
                  [(assoc node :right right) inserted?])
                :else [node false])))]
    (let [[new-root inserted?] (tree-node-insert (:root tree) value)]
      (if inserted?
        (->BinaryTree new-root (inc (:count tree)))
        tree))))

(defn binary-tree-contains
  "BinaryTree, Integer -> true or false. Returns true when the value is present
  and false when it is absent; the query never changes the structure."
  [tree value]
  ;; Función local: el descenso solo sirve a esta consulta. Responde con boolean
  ;; y nunca con nil, porque la pregunta tiene dos respuestas y nil queda
  ;; reservado para una estructura que no se puede construir.
  (letfn [(tree-node-contains [node value]
            (cond
              (nil? node) false
              (= value (:value node)) true
              (< value (:value node)) (tree-node-contains (:left node) value)
              :else (tree-node-contains (:right node) value)))]
    (tree-node-contains (:root tree) value)))

(defn binary-tree-is-empty
  "BinaryTree -> Boolean. Reports whether the tree has no nodes."
  [tree]
  (= 0 (:count tree)))

(defn binary-tree-size
  "BinaryTree -> Integer. Returns the nonnegative node count."
  [tree]
  (:count tree))

(defn graph-init
  "() -> Graph. Creates an empty graph without a capacity parameter."
  []
  (->Graph nil 0 0))

(defn- find-vertex
  "Graph, Integer -> GraphNode or nil. Returns the registered node carrying
  this label, or nil when the label is absent. Shared by every graph operation."
  [graph label]
  (loop [node (:vertices graph)]
    (cond
      (nil? node) nil
      (= label (:label node)) node
      :else (recur (:next-vertex node)))))

(defn graph-add-vertex
  "Graph, Integer -> Graph. Returns the updated persistent graph; duplicates
  succeed, preserving adjacency and counts. No contractual limit failure."
  [graph label]
  ;; El registro es idempotente: si la etiqueta ya existe no se crea otro nodo,
  ;; no cambia la adyacencia y el contador no crece.
  (if (find-vertex graph label)
    graph
    ;; Nodo nuevo: etiqueta propia, sin adyacencia y enlazado a la cabeza del
    ;; registro, de modo que el registro sigue siendo una lista enlazada.
    (->Graph (->GraphNode label nil (:vertices graph))
             (inc (:vertex-total graph))
             (:edges graph))))

(defn graph-add-edge
  "Graph, Integer, Integer -> Graph or nil. Returns the updated graph when both
  vertices exist, including duplicates and self-loops; otherwise nil, with no
  mutation or implicit vertices. Only a new edge increases the edge count."
  [graph u v]
  ;; Funciones locales: la adyacencia y el registro solo se recorren desde esta
  ;; operación, así que no necesitan ámbito de espacio de nombres.
  (letfn [(insert-neighbor
            ;; Lista ordenada de forma ascendente y sin duplicados: avanza hasta
            ;; la primera etiqueta mayor o igual que v y enlaza delante de ella.
            [neighbors v]
            (cond
              (nil? neighbors) [(->NeighborNode v nil) true]
              (= v (:label neighbors)) [neighbors false]
              (< v (:label neighbors)) [(->NeighborNode v neighbors) true]
              :else
              (let [[rest-neighbors inserted?]
                    (insert-neighbor (:next neighbors) v)]
                [(assoc neighbors :next rest-neighbors) inserted?])))
          (replace-neighbors
            ;; Los registros son inmutables: se reconstruye el registro hasta el
            ;; vértice u, que recibe la adyacencia nueva.
            [node label neighbors]
            (cond
              (nil? node) nil
              (= label (:label node)) (assoc node :neighbors neighbors)
              :else (assoc node :next-vertex
                           (replace-neighbors (:next-vertex node)
                                              label neighbors))))]
    (let [source (find-vertex graph u)]
      (if (or (nil? source) (nil? (find-vertex graph v)))
        nil
        (let [[neighbors inserted?] (insert-neighbor (:neighbors source) v)]
          (if inserted?
            (->Graph (replace-neighbors (:vertices graph) u neighbors)
                     (:vertex-total graph)
                     (inc (:edges graph)))
            graph))))))

(defn graph-has-edge
  "Graph, Integer, Integer -> true or false. Returns true for an existing
  directed edge and false for an absent edge or a missing endpoint, without
  changing the graph."
  [graph u v]
  ;; La adyacencia está ordenada: basta avanzar hasta la primera etiqueta mayor
  ;; o igual que v para decidir, sin recorrerla entera.
  (letfn [(has-neighbor? [neighbors v]
            (cond
              (nil? neighbors) false
              (= v (:label neighbors)) true
              (< v (:label neighbors)) false
              :else (recur (:next neighbors) v)))]
    (if-let [source (find-vertex graph u)]
      (has-neighbor? (:neighbors source) v)
      false)))

(defn graph-get-neighbors
  "Graph, Integer -> immutable sequence of Integers or nil. Returns ascending,
  unique neighbors, empty for an isolated vertex, nil for a missing vertex.
  Returned values remain unchanged by subsequent graph updates."
  [graph u]
  (when-let [source (find-vertex graph u)]
    ;; Vector nuevo en orden ascendente: modificar esta copia no toca el grafo y
    ;; lo guardado no cambia con actualizaciones posteriores.
    (loop [neighbors (:neighbors source)
           labels []]
      (if (nil? neighbors)
        labels
        (recur (:next neighbors) (conj labels (:label neighbors)))))))

(defn graph-is-empty
  "Graph -> Boolean. Reports whether the graph has no vertices."
  [graph]
  (= 0 (:vertex-total graph)))

(defn graph-vertex-count
  "Graph -> Integer. Returns the nonnegative vertex count."
  [graph]
  (:vertex-total graph))

(defn graph-edge-count
  "Graph -> Integer. Returns the nonnegative directed-edge count."
  [graph]
  (:edges graph))
