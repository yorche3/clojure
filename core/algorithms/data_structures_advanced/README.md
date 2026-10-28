# Data Structures Advanced — Clojure

Implementación de la especificación [07_Data_Structures_Advanced](https://yorche3.github.io/programming_languages/core/algorithms/07_Data_Structures_Advanced/) en **Clojure**, utilizando **Clojure CLI (deps.edn)** como sistema de construcción y **clojure.test** como framework de pruebas unitarias.

Implementation of the [07_Data_Structures_Advanced](https://yorche3.github.io/programming_languages/core/algorithms/07_Data_Structures_Advanced/) specification in **Clojure**, using **Clojure CLI (deps.edn)** as the build system and **clojure.test** as the unit-testing framework.

**ES:** El módulo construye desde cero un árbol binario de búsqueda (`BinaryTree` con `TreeNode`) y un grafo dirigido (`Graph` con `GraphNode` y `NeighborNode`) usando registros inmutables (`defrecord`) y funciones puras sobre estructuras persistentes. La base `Node` se realiza mediante valor embebido (`:value` y `:label` en los propios registros). El grafo implementa un registro de vértices manual como lista enlazada canónica y adyacencia ordenada manual sin delegar en mapas ni conjuntos de biblioteca.

**EN:** The module builds from scratch a binary search tree (`BinaryTree` with `TreeNode`) and a directed graph (`Graph` with `GraphNode` and `NeighborNode`) using immutable records (`defrecord`) and pure functions over persistent structures. The `Node` base is implemented via embedded values (`:value` and `:label` directly within child records). The graph implements a manual vertex registry as a canonical linked list and manual sorted adjacency without delegating to library maps or sets.

---

## 📂 Archivos y estructura / Files & Structure

| Archivo / Directorio | Propósito / Purpose |
|---|---|
| [`src/data_structures_advanced/data_structures_advanced.clj`](src/data_structures_advanced/data_structures_advanced.clj) | Implementación: registros `TreeNode`, `BinaryTree`, `NeighborNode`, `GraphNode`, `Graph` y operaciones del contrato. |
| [`test/data_structures_advanced/data_structures_advanced_test.clj`](test/data_structures_advanced/data_structures_advanced_test.clj) | Suite: 19 `deftest` con 159 aserciones cubriendo los casos de nodo, árbol y grafo. |
| [`deps.edn`](deps.edn) | Configuración de dependencias y aliases (`:test`, `:build`). |
| [`build.clj`](build.clj) | Automatización con `tools.build` (`test`, `ci`, `install`, `deploy`). |
| [`CHANGELOG.md`](CHANGELOG.md) | Historial de versiones. |
| [`LICENSE`](LICENSE) | Licencia Eclipse Public License 1.0. |
| [`.gitignore`](.gitignore) | Archivos generados excluidos. |

**Estructura real del directorio / Real directory tree:**

```text
data_structures_advanced/
├── src/
│   └── data_structures_advanced/
│       └── data_structures_advanced.clj       # TreeNode, BinaryTree, GraphNode, Graph
├── test/
│   └── data_structures_advanced/
│       └── data_structures_advanced_test.clj  # 19 deftest / 159 aserciones
├── resources/
│   └── .keep
├── doc/
│   └── intro.md
├── deps.edn
├── build.clj
├── CHANGELOG.md
├── LICENSE
├── .gitignore
└── README.md                                  # Este archivo / This file
```

**Nota de desviación / Deviation note:** La especificación propone `src/data_structures_advanced.ext` y `test/data_structures_advanced_test.ext`. En Clojure, las convenciones de tooling (`deps-new` y Clojure CLI) requieren subdirectorios con el nombre del namespace (`src/data_structures_advanced/` y `test/data_structures_advanced/`). Tampoco existe un script `run_tests.ext` independiente porque `build.clj` y `cognitect.test-runner` asumen ese rol de runner nativo. Ambas desviaciones se documentan en la sección de adaptaciones idiomáticas.

---

## 🛠️ Enfoque y construcción / Approach & Build

**ES:** El proyecto se creó con la plantilla estándar de `deps-new` (`clojure -Tnew lib :name data-structures-advanced/data-structures-advanced`) y se configuró manualmente.

Los tipos se implementan con `defrecord`, creando registros inmutables con campos accesibles por keyword. Como los records en Clojure no admiten herencia entre records, la base conceptual `Node` se realiza como valor embebido (`:value` en `TreeNode`, `:label` en `GraphNode` y `NeighborNode`). Las operaciones son funciones puras que devuelven nuevas instancias de las estructuras.

**EN:** The project was created with the standard `deps-new` template (`clojure -Tnew lib :name data-structures-advanced/data-structures-advanced`) and configured manually.

Types are implemented with `defrecord`, creating immutable records with keyword-accessible fields. Since Clojure records cannot inherit from records, the conceptual `Node` base is realized as an embedded value (`:value` in `TreeNode`, `:label` in `GraphNode` and `NeighborNode`). Operations are pure functions returning new instances of persistent structures.

---

## 📄 Configuración clave / Key Configuration

### `deps.edn` — Manifiesto de dependencias

**ES:** Declara las rutas fuente, la versión de Clojure (1.12.5) y aliases para ejecución de tests con `cognitect-labs/test-runner` (`:test`) y tareas de construcción con `tools.build` (`:build`).

**EN:** Declares source paths, Clojure dependency (1.12.5), and aliases for test running with `cognitect-labs/test-runner` (`:test`) and build tasks with `tools.build` (`:build`).

```clojure
{:paths ["src" "resources"]
 :deps {org.clojure/clojure {:mvn/version "1.12.5"}}
 :aliases
 {:test
  {:extra-paths ["test"]
   :extra-deps {org.clojure/test.check {:mvn/version "1.1.3"}
                io.github.cognitect-labs/test-runner {:git/tag "v0.5.1" :git/sha "dfb30dd"}}}
  :build {:deps {io.github.clojure/tools.build
                 {:mvn/version "0.10.14"}
                 slipset/deps-deploy {:mvn/version "0.2.5"}}
          :ns-default build}}}
```

### `build.clj` — Automatización

**ES:** Configura las tareas `test` (ejecuta la suite con `cognitect.test-runner` y propaga códigos de salida no nulos), `ci` (compila y empaqueta el JAR tras pasar los tests), `install` y `deploy`.

**EN:** Configures `test` task (runs suite via `cognitect.test-runner` and propagates non-zero exit codes), `ci` (builds JAR after tests pass), `install`, and `deploy`.

---

## 🚀 Compilación y ejecución / Build & Run

```bash
clojure -T:build test    # Ejecutar la suite de pruebas / Run the test suite
clojure -T:build ci      # Tests + empaquetado JAR / Tests + build JAR
```

**Salida real / Actual output:**

```text
SLF4J(I): Connected with provider of type [org.slf4j.nop.NOPServiceProvider]

Running tests in #{"test"}

Testing data-structures-advanced.data-structures-advanced-test

Ran 19 tests containing 159 assertions.
0 failures, 0 errors.
```

---

## 🧠 Algoritmos y operaciones / Algorithms & Operations

| Operación / Operation | Entrada → salida / Input → output | Complejidad / Complexity | Notas / Notes |
|---|---|---|---|
| `tree-node-init` | `Integer → TreeNode` | `O(1)` | Crea un nodo con valor embebido y enlaces `nil` |
| `tree-node-get-value` | `TreeNode → Integer` | `O(1)` | Lee `:value` del registro |
| `tree-node-get-left` | `TreeNode → TreeNode\|nil` | `O(1)` | Lee `:left`; `nil` indica enlace ausente |
| `tree-node-get-right` | `TreeNode → TreeNode\|nil` | `O(1)` | Lee `:right`; `nil` indica enlace ausente |
| `tree-node-set-left` | `TreeNode × (TreeNode\|nil) → TreeNode` | `O(1)` | Devuelve un nuevo nodo con `:left` actualizado |
| `tree-node-set-right` | `TreeNode × (TreeNode\|nil) → TreeNode` | `O(1)` | Devuelve un nuevo nodo con `:right` actualizado |
| `binary-tree-init` | `() → BinaryTree` | `O(1)` | Árbol vacío (`root` = `nil`, `count` = `0`) |
| `binary-tree-insert` | `BinaryTree × Integer → BinaryTree` | `O(h)` | Inserción en ABB inmutable; duplicado devuelve árbol sin alterar `count` |
| `binary-tree-contains` | `BinaryTree × Integer → Boolean` | `O(h)` | Búsqueda siguiendo invariante del ABB; devuelve `true`/`false` |
| `binary-tree-is-empty` | `BinaryTree → Boolean` | `O(1)` | Comprueba si `count` es `0` |
| `binary-tree-size` | `BinaryTree → Integer` | `O(1)` | Retorna el número de nodos (`count`) |
| `graph-init` | `() → Graph` | `O(1)` | Grafo vacío (`vertices` = `nil`, `vertex-total` = `0`, `edges` = `0`) |
| `graph-add-vertex` | `Graph × Integer → Graph` | `O(V)` | Registra vértice en lista canónica si no existe; duplicado preserva adyacencia |
| `graph-add-edge` | `Graph × Integer × Integer → Graph\|nil` | `O(V + d(u))` | Añade arista ordenada; bucles permitidos; `nil` si falta un extremo |
| `graph-has-edge` | `Graph × Integer × Integer → Boolean` | `O(V + d(u))` | Comprueba arista dirigida; `false` si no existe o falta extremo |
| `graph-get-neighbors` | `Graph × Integer → [Integer]\|nil` | `O(V + d(u))` | Secuencia de vecinos en orden ascendente; `[]` si aislado; `nil` si `u` falta |
| `graph-is-empty` | `Graph → Boolean` | `O(1)` | Comprueba si `vertex-total` es `0` |
| `graph-vertex-count` | `Graph → Integer` | `O(1)` | Retorna `vertex-total` |
| `graph-edge-count` | `Graph → Integer` | `O(1)` | Retorna `edges` |

---

## 🧩 Decisiones de diseño / Design decisions

| Decisión / Decision | Alternativa considerada / Alternative | Razón / Reason |
|---|---|---|
| `defrecord` para los 5 tipos (`TreeNode`, `BinaryTree`, `NeighborNode`, `GraphNode`, `Graph`) | Mapas de Clojure sin tipo | `defrecord` provee tipos nominados con campos explícitos, acceso rápido por keyword y compatibilidad con verificación de tipos sin abstracciones prematuras. |
| Base `Node` mediante valor embebido | Jerarquía de protocolos o interfaces | Clojure no permite herencia de estado entre `defrecord`. La especificación permite explícitamente valor embebido (composición interna directa de campos `:value` y `:label`). |
| Inmutabilidad estructural con reconstrucción funcional | Nodos mutables o referencias (`atom`) | Clojure es funcional puro por defecto. Devolver nuevas instancias persistentes garantiza aislamiento total de hilos y preserva la inmutabilidad idiomática. |
| Predicados devuelven booleano puro (`true`/`false`), operaciones estructurales devuelven `nil` en fallo | Devolver `nil` en consultas booleanas fallidas | Distingue claramente una respuesta negativa (`false` en `binary-tree-contains`, `graph-has-edge`) de un fallo de construcción o de extremo faltante (`nil` en `graph-add-edge`, `graph-get-neighbors`). |
| Registro de adyacencia de `GraphNode` mediante lista enlazada canónica propia (`NeighborNode`) | Uso de `java.util.LinkedList` | Cumple el contrato educativo de implementación manual estricta del módulo: inserción ordenada y detección de duplicados recorriendo la cadena enlazada sin colecciones mutables externas. |

---

## 🔀 Adaptaciones idiomáticas / Idiomatic adaptations

| Especificación / Specification | Adaptación / Adaptation | Justificación / Justification |
|---|---|---|
| `TreeNode extends Node` con herencia de valor | Valor embebido en `TreeNode` (`:value`) y `GraphNode` (`:label`) | En Clojure los `defrecord` no admiten herencia de tipos o campos. La especificación permite herencia real o valor embebido; se adoptó valor embebido. |
| Pseudocódigo con mutaciones in-place (`this.left = node`, `edges = edges + 1`) | Estructuras persistentes: funciones puras que retornan instancias actualizadas | Clojure favorece la inmutabilidad; `tree-node-set-left`/`right`, `binary-tree-insert`, `graph-add-vertex`, `graph-add-edge` devuelven nuevas instancias sin mutar los originales. |
| Ubicación `src/data_structures_advanced.clj` y `test/data_structures_advanced_test.clj` | `src/data_structures_advanced/data_structures_advanced.clj` y `test/data_structures_advanced/data_structures_advanced_test.clj` | Clojure CLI y la convención de `deps-new` requieren que la estructura de carpetas refleje el namespace completo para su resolución en el classpath. |
| Archivo de ejecución `run_tests.ext` | Runner nativo mediante `build.clj` (`clojure -T:build test`) | `cognitect.test-runner` descubre y ejecuta automáticamente los tests de `test/` mediante el script de automatización sin necesidad de un script de entrada separado. |

---

## 🚨 Indicadores de fallo / Failure indicators

| Operación / Operation | Situación de fallo / Failure situation | Indicador / Indicator | Ejemplo / Example |
|---|---|---|---|
| `tree-node-get-left` | Enlace izquierdo ausente / Missing left child | `nil` | `(tree-node-get-left (tree-node-init 10))` → `nil` |
| `tree-node-get-right` | Enlace derecho ausente / Missing right child | `nil` | `(tree-node-get-right (tree-node-init 10))` → `nil` |
| `binary-tree-contains` | Valor ausente / Value not in tree | `false` | `(binary-tree-contains (binary-tree-init) 50)` → `false` |
| `graph-add-edge` | Extremo `u` o `v` inexistente / Missing endpoint | `nil` | `(graph-add-edge (graph-init) 10 99)` → `nil` |
| `graph-has-edge` | Arista ausente o extremos inexistentes / Edge or endpoint missing | `false` | `(graph-has-edge (graph-init) 10 30)` → `false` |
| `graph-get-neighbors` | Vértice inexistente / Vertex not registered | `nil` | `(graph-get-neighbors (graph-init) 99)` → `nil` |

**ES:** En Clojure, `nil` representa la ausencia o el fallo en operaciones constructoras o de búsqueda de nodos/vecinos, mientras que las consultas de pertenencia y existencia devuelven `false`. Se distingue nítidamente el vértice inexistente (`nil`) del vértice aislado sin vecinos (`[]`).

**EN:** In Clojure, `nil` denotes absence or failure in structural operations and neighbor lookups, whereas membership and existence queries return `false`. A missing vertex (`nil`) is distinctly separated from an isolated vertex with no neighbors (`[]`).

---

## ✅ Cobertura de pruebas / Test coverage

| Caso de la especificación / Specification case | Cubierto / Covered | Prueba / Test | Notas / Notes |
|---|---|:--:|---|
| TreeNode — Crear un nodo de árbol | Sí | `tree-node-init-test` (`create-node-input`) | `:value` = `10` |
| TreeNode — Enlaces iniciales ausentes | Sí | `tree-node-init-test` (`absent-links-input`) | `:left` y `:right` son `nil` |
| TreeNode — Enlazar dos hijos | Sí | `tree-node-set-left-test`, `tree-node-set-right-test` (`link-children-input`) | `20` en izquierdo y `30` en derecho |
| TreeNode — Recorrer cada enlace | Sí | `tree-node-get-left-test`, `tree-node-get-right-test` (`traverse-links-input`) | Hijos `20` y `30`, enlaces terminales `nil` |
| TreeNode — Desenlazar hijos | Sí | `tree-node-set-left-test`, `tree-node-set-right-test` (`unlink-children-input`) | Limpieza sucesiva con `nil` preservando valores |
| TreeNode — Adaptación inmutable | Sí | `tree-node-set-left-test` (`immutable-node-input`) | Nodo nuevo retornado; nodo padre original inalterado |
| BinaryTree — Crear vacío | Sí | `binary-tree-init-test` (`empty-tree-input`) | `is_empty` = `true`, `size` = `0`, `contains(50)` = `false` |
| BinaryTree — Insertar la raíz | Sí | `binary-tree-insert-test` (`root-input`) | Éxito, `size` = `1`, `is_empty` = `false`, `contains(50)` = `true` |
| BinaryTree — Insertar menor y mayor | Sí | `binary-tree-insert-test` (`lower-higher-input`) | Inserción de `50`, `30`, `70`; `size` = `3`, los tres presentes |
| BinaryTree — Ramas de varios niveles | Sí | `binary-tree-insert-test` (`multilevel-input`) | Inserción de `50`, `30`, `70`, `20`, `40`; `size` = `5`, todos presentes |
| BinaryTree — Valor ausente en árbol no vacío | Sí | `binary-tree-contains-test` (`missing-tree-value-input`) | `contains(99)` = `false`, `size` = `1`, `50` presente |
| BinaryTree — Duplicado idempotente | Sí | `binary-tree-insert-test` (`duplicate-tree-value-input`) | Insertar `30` duplicado conserva `size` = `3` |
| BinaryTree — Inserciones ordenadas | Sí | `binary-tree-insert-test` (`ordered-insertions-input`) | `20`, `30`, `40`, `50`, `70`; `size` = `5`, `99` ausente |
| Graph — Crear vacío | Sí | `graph-init-test` (`empty-graph-input`) | `is_empty` = `true`, vértices/aristas = `0`, consultas fallan |
| Graph — Vértice aislado | Sí | `graph-add-vertex-test` (`isolated-vertex-input`) | 1 vértice, 0 aristas, vecinos de 10 = `[]` (no `nil`) |
| Graph — Etiquetas no consecutivas y arista dirigida | Sí | `graph-add-edge-test` (`directed-edge-input`) | `has_edge(10, 70)` = `true`, reverso = `false` |
| Graph — Vértice duplicado conserva adyacencia | Sí | `graph-add-vertex-test` (`duplicate-vertex-input`) | Re-registrar `10` mantiene 2 vértices, 1 arista, vecinos `[30]` |
| Graph — Arista duplicada | Sí | `graph-add-edge-test` (`duplicate-edge-input`) | Añadir `(10, 30)` dos veces conserva 1 arista |
| Graph — Extremo inexistente sin mutación | Sí | `graph-add-edge-test` (`missing-endpoint-input`) | Fallo (`nil`) con 98 y 99; grafo inalterado (2 vértices, 1 arista) |
| Graph — Vecinos ordenados y contadores | Sí | `graph-get-neighbors-test` (`sorted-neighbors-input`) | Vecinos `10` → `[30 50 70]`; 4 vértices, 4 aristas |
| Graph — Consulta de arista ausente | Sí | `graph-has-edge-test` (`missing-edge-input`) | `has_edge(10, 30)` = `false`, contadores intactos |
| Graph — Vecinos de vértice inexistente | Sí | `graph-get-neighbors-test` (`missing-neighbors-input`) | `get_neighbors(99)` = `nil` (distinto de vecinos vacíos `[]`) |
| Graph — Bucle y duplicado | Sí | `graph-add-edge-test` (`self-loop-input`) | Bucle `(10, 10)` duplicado resulta en 1 arista y vecinos `[10]` |
| Graph — Aislamiento del resultado de vecinos | Sí | `graph-get-neighbors-test` (`neighbor-isolation-input`) | Vecinos previos `[30]` inmutables tras añadir arista `(10, 50)` |

---

## ⚠️ Limitaciones conocidas / Known limitations

| Limitación / Limitation | Impacto / Impact | Alternativa o plan / Workaround or plan |
|---|---|---|
| Inmutabilidad y reconstrucción de rutas en árbol y grafo | Cada inserción o actualización reconstruye los ancestros/nodos hasta la raíz o la cabeza del registro (`O(h)` y `O(V)`) | Es el comportamiento esperado y estándar en un lenguaje funcional inmutable; garantiza seguridad ante concurrencia sin cerrojos. |

---

## 📝 Notas de implementación / Implementation Notes

### Inmutabilidad y registros persistentes / Immutability and persistent records

**ES:** Todos los tipos se declaran mediante `defrecord`. Al ser inmutables, cualquier operación que actualiza enlaces (`tree-node-set-left`, `binary-tree-insert`, `graph-add-vertex`, `graph-add-edge`) genera una nueva instancia compartiendo estructura no modificada.

**EN:** All types are declared via `defrecord`. Being immutable, every operation updating links (`tree-node-set-left`, `binary-tree-insert`, `graph-add-vertex`, `graph-add-edge`) produces a new instance while sharing unmodified structure.

### Registro manual de vértices y adyacencia / Manual vertex and adjacency registry

**ES:** El grafo no emplea mapas ni conjuntos (`HashMap`, `PersistentHashSet`) de la biblioteca estándar de Clojure para su registro o adyacencia. `find-vertex` implementa un recorrido lineal iterativo con `loop`/`recur` (`O(V)`). La inserción de vecinos en `NeighborNode` mantiene la lista ordenada de forma ascendente y sin duplicados mediante recursión manual (`O(d(u))`).

**EN:** The graph does not use standard Clojure library maps or sets (`HashMap`, `PersistentHashSet`) for its registry or adjacency. `find-vertex` implements a manual linear traversal via `loop`/`recur` (`O(V)`). Inserting neighbors into `NeighborNode` maintains an ascending list without duplicates through manual recursion (`O(d(u))`).

### TCO y aislamiento de resultados / TCO and result isolation

**ES:** En `find-vertex` y `graph-has-edge` se utiliza `loop`/`recur` para garantizar tail call optimization en la JVM. `graph-get-neighbors` acumula las etiquetas en un vector persistente `[]` con `loop`/`recur`, garantizando aislamiento estructural inmutable frente a posteriores modificaciones del grafo.

**EN:** `find-vertex` and `graph-has-edge` use `loop`/`recur` ensuring tail call optimization on the JVM. `graph-get-neighbors` accumulates labels into a persistent vector `[]` via `loop`/`recur`, guaranteeing immutable structural isolation against subsequent graph modifications.

**ES:** Este proyecto también está implementado en otros lenguajes. Explora el [repositorio principal](https://github.com/yorche3/programming_languages) para consultar las demás versiones.

**EN:** This project is also implemented in other languages. Explore the [main repository](https://github.com/yorche3/programming_languages) to see the other versions.

---

## 🔍 Checklist de validación / Validation checklist

- [x] La suite nativa se ejecutó y su salida real está copiada en este README.
- [x] Cada caso de la especificación tiene su fila en _Cobertura de pruebas_ (o `Omitido` con razón).
- [x] Cada desviación del pseudocódigo o de la ubicación esperada está en _Adaptaciones idiomáticas_.
- [x] Cada operación con fallo posible está en _Indicadores de fallo_.
- [x] No hay rutas absolutas del autor, credenciales ni salidas inventadas.
- [x] Los enlaces relativos resuelven dentro del repositorio y el documento es bilingüe.
- [x] Ninguna sección repite lo que ya dice la especificación.

---

## 📚 Referencias / References

| Tipo / Kind | Referencia / Reference |
|---|---|
| Especificación / Specification | [`07_Data_Structures_Advanced.md`](https://yorche3.github.io/programming_languages/core/algorithms/07_Data_Structures_Advanced/) |
| Primera base del lenguaje / First language reference | [`clojure/core/algorithms/data_structures_basics/`](../data_structures_basics/) |
| Segunda base del lenguaje / Second language reference | [`clojure/core/foundations/numbers/`](../../foundations/numbers/) |
| Guía de inicialización / Initialisation guide | [`core/00_Project_Initialization_Guide.md`](../../../../docs/core/00_Project_Initialization_Guide.md) |
| Adaptaciones idiomáticas / Idiomatic adaptations | [`docs/AGENT_Template.md`](../../../../docs/AGENT_Template.md) |
| Validación de la documentación / Documentation validation | [`docs/WORKFLOW.md`](../../../../docs/WORKFLOW.md) |
| Documentación oficial del lenguaje / Language official docs | [clojure.org/reference/data_structures](https://clojure.org/reference/data_structures) |

---

*[← Volver a Algorithms Pure](../README.md) | [↑ Core](../../README.md)*

*🌐 [github.com/yorche3/programming_languages](https://github.com/yorche3/programming_languages) · [GitHub Pages](https://yorche3.github.io/programming_languages/)*
