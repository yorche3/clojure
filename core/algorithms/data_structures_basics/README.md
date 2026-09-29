# Data Structures Basics — Clojure

Implementación de la especificación [06_Data_Structures_Basics](https://yorche3.github.io/programming_languages/core/algorithms/06_Data_Structures_Basics/) en **Clojure**, utilizando **Clojure CLI (deps.edn)** como sistema de construcción y **clojure.test** como framework de pruebas unitarias.

Implementation of the [06_Data_Structures_Basics](https://yorche3.github.io/programming_languages/core/algorithms/06_Data_Structures_Basics/) specification in **Clojure**, using **Clojure CLI (deps.edn)** as the build system and **clojure.test** as the unit-testing framework.

**ES:** El módulo construye desde cero un `Node` compartido y tres ADTs (lista enlazada, pila y cola) usando `defrecord` y funciones puras sobre estructuras persistentes. Cada operación devuelve un valor nuevo en lugar de mutar la instancia.

**EN:** The module builds from scratch a shared `Node` and three ADTs (linked list, stack, and queue) using `defrecord` and pure functions over persistent structures. Every operation returns a new value instead of mutating the instance.

---

## 📂 Archivos y estructura / Files & Structure

| Archivo / Directorio | Propósito / Purpose |
|---|---|
| [`src/data_structures_basics/data_structures_basics.clj`](src/data_structures_basics/data_structures_basics.clj) | Implementación: `Node`, `LinkedList`, `Stack` y `Queue` con todas las operaciones del contrato. |
| [`test/data_structures_basics/data_structures_basics_test.clj`](test/data_structures_basics/data_structures_basics_test.clj) | Suite: 4 `deftest` con 46 aserciones sobre los tres ADTs y el nodo. |
| [`deps.edn`](deps.edn) | Configuración de dependencias y aliases (`:test`, `:build`). |
| [`build.clj`](build.clj) | Automatización con `tools.build` (`test`, `ci`, `install`, `deploy`). |
| [`CHANGELOG.md`](CHANGELOG.md) | Historial de versiones. |
| [`LICENSE`](LICENSE) | Licencia Eclipse Public License 1.0. |
| [`.gitignore`](.gitignore) | Archivos generados excluidos. |

**Estructura real del directorio / Real directory tree:**

```text
data_structures_basics/
├── src/
│   └── data_structures_basics/
│       └── data_structures_basics.clj   # Node, LinkedList, Stack, Queue
├── test/
│   └── data_structures_basics/
│       └── data_structures_basics_test.clj  # 4 deftest / 46 aserciones
├── resources/
│   └── .keep
├── doc/
│   └── intro.md
├── deps.edn
├── build.clj
├── CHANGELOG.md
├── LICENSE
├── .gitignore
└── README.md                            # Este archivo / This file
```

**Nota de desviación / Deviation note:** La especificación indica `src/data_structures_basics.ext` y `test/data_structures_basics_test.ext`. En Clojure la convención de `deps-new` añade un subdirectorio con el nombre del proyecto bajo `src/` y `test/`, de modo que el código vive en `src/data_structures_basics/data_structures_basics.clj`. No existe el archivo `run_tests.ext` porque `build.clj` + `cognitect.test-runner` actúan como runner nativo. Ambas desviaciones se declaran en la tabla de adaptaciones idiomáticas.

---

## 🛠️ Enfoque y construcción / Approach & Build

**ES:** El proyecto se creó con la plantilla de `deps-new` (`clojure -Tnew lib :name data-structures-basics/data-structures-basics`) y se configuró manualmente.

Los cuatro tipos se declaran con `defrecord`, que en Clojure crea un registro inmutable con campos de acceso por keyword. Cada operación recibe una instancia y devuelve una nueva. Los tres helpers internos (`chain-append`, `chain-last`, `chain-remove`) son privados con `defn-`.

**EN:** The project was created with the `deps-new` template (`clojure -Tnew lib :name data-structures-basics/data-structures-basics`) and configured manually.

The four types are declared with `defrecord`, which in Clojure creates an immutable record with keyword-access fields. Each operation receives an instance and returns a new one. The three internal helpers (`chain-append`, `chain-last`, `chain-remove`) are private with `defn-`.

---

## 📄 Configuración clave / Key Configuration

### `deps.edn` — Manifiesto de dependencias

**ES:** Define rutas de código fuente, la dependencia de Clojure y aliases para pruebas y construcción. El runner de tests es `cognitect-labs/test-runner` declarado en `:test`.

**EN:** Defines source paths, the Clojure dependency, and aliases for testing and building. The test runner is `cognitect-labs/test-runner` declared in `:test`.

```clojure
{:paths ["src" "resources"]
 :deps {org.clojure/clojure {:mvn/version "1.12.1"}}
 :aliases
 {:test
  {:extra-paths ["test"]
   :extra-deps {org.clojure/test.check {:mvn/version "1.1.1"}
                io.github.cognitect-labs/test-runner {:git/tag "v0.5.1" :git/sha "dfb30dd"}}}
  :build {:deps {io.github.clojure/tools.build
                 {:mvn/version "0.10.9"}
                 slipset/deps-deploy {:mvn/version "0.2.2"}}
          :ns-default build}}}
```

### `build.clj` — Automatización

**ES:** La tarea `test` invoca `cognitect.test-runner` sobre el alias `:test` y propaga el fallo si algún test no pasa. No requiere un archivo `run_tests.clj` aparte.

**EN:** The `test` task invokes `cognitect.test-runner` over the `:test` alias and fails if any test does not pass. No separate `run_tests.clj` file is required.

---

## 🚀 Compilación y ejecución / Build & Run

```bash
clojure -T:build test    # Ejecuta los tests / Run the tests
clojure -T:build ci      # Tests + JAR (pipeline de CI)
clojure -T:build install # Instala el JAR localmente / Install JAR locally
clojure -T:build deploy  # Publica en Clojars / Publish to Clojars
```

**Salida real / Actual output:**

```text
Running tests in #{"test"}

Testing data-structures-basics.data-structures-basics-test

Ran 4 tests containing 46 assertions.
0 failures, 0 errors.
```

---

## 🧠 Algoritmos y operaciones / Algorithms & Operations

| Operación / Operation | Entrada → salida / Input → output | Complejidad / Complexity | Notas / Notes |
|---|---|---|---|
| `node-init` | `value → Node` | `O(1)` | Crea un nodo con `next` ausente (`nil`) |
| `(:value node)` | `Node → any` | `O(1)` | Acceso de campo por keyword; equivale a `get_value()` |
| `(:next node)` | `Node → Node\|nil` | `O(1)` | Acceso de campo; equivale a `get_next()` |
| `assoc node :next other` | `Node → Node` | `O(1)` | Devuelve nodo nuevo con enlace actualizado; equivale a `set_next()` |
| `linked-list-init` | `→ LinkedList` | `O(1)` | `head`/`tail` = `nil`, `count` = `0` |
| `linked-list-is-empty` | `LinkedList → bool` | `O(1)` | `true` exactamente cuando `count` = `0` |
| `linked-list-size` | `LinkedList → int` | `O(1)` | Devuelve `:count` |
| `get-head` | `LinkedList → any\|nil` | `O(1)` | Valor de la cabeza; `nil` si vacía |
| `ll-insert-head` | `LinkedList × value → LinkedList` | `O(1)` | Inserta al frente |
| `ll-insert-tail` | `LinkedList × value → LinkedList` | `O(n)` | Reconstruye la cadena; ver adaptaciones |
| `ll-delete` | `LinkedList × value → LinkedList\|nil` | `O(n)` | Éxito: nueva lista sin primera aparición; fallo: `nil` |
| `stack-init` | `→ Stack` | `O(1)` | `top` = `nil`, `count` = `0` |
| `stack-is-empty` | `Stack → bool` | `O(1)` | |
| `stack-size` | `Stack → int` | `O(1)` | |
| `stack-push` | `Stack × value → Stack` | `O(1)` | |
| `stack-peek` | `Stack → any\|nil` | `O(1)` | No muta; `nil` si vacía |
| `stack-pop` | `Stack → {:stack Stack :value any}\|nil` | `O(1)` | Devuelve mapa con nueva pila y valor; `nil` si vacía |
| `queue-init` | `→ Queue` | `O(1)` | `front`/`rear` = `nil`, `count` = `0` |
| `queue-is-empty` | `Queue → bool` | `O(1)` | |
| `queue-size` | `Queue → int` | `O(1)` | |
| `queue-enqueue` | `Queue × value → Queue` | `O(n)` | Reconstruye la cadena; ver adaptaciones |
| `queue-peek` | `Queue → any\|nil` | `O(1)` | No muta; `nil` si vacía |
| `queue-dequeue` | `Queue → {:queue Queue :value any}\|nil` | `O(1)` | Devuelve mapa con nueva cola y valor; `nil` si vacía |

---

## 🧩 Decisiones de diseño / Design decisions

| Decisión / Decision | Alternativa considerada / Alternative | Razón / Reason |
|---|---|---|
| `defrecord` para los cuatro tipos | Mapas simples de Clojure | `defrecord` nombra los tipos (verificable con `instance?`) y documenta los campos; el lector conoce la forma exacta del dato. |
| `defn-` para los tres helpers de cadena | Funciones auxiliares en el ns con `^:private` | `defn-` es la forma idiomática breve de declarar una función privada en el mismo namespace; es equivalente y más legible. |
| `{:stack …, :value …}` / `{:queue …, :value …}` en `pop`/`dequeue` | Devolver solo el valor; devolver un vector `[estructura valor]` | En un lenguaje inmutable, extraer un elemento requiere devolver tanto el valor como la estructura actualizada; un mapa con claves descriptivas es más claro que una tupla posicional. |
| Helpers `chain-append`/`chain-last`/`chain-remove` compartidos entre `LinkedList`, `Stack` y `Queue` | Triplicar la lógica | Los tres ADTs usan el mismo tipo `Node`; los helpers operan sobre nodos en lugar de sobre los ADTs y son completamente independientes de los punteros de cada estructura. |

---

## 🔀 Adaptaciones idiomáticas / Idiomatic adaptations

| Especificación / Specification | Adaptación / Adaptation | Justificación / Justification |
|---|---|---|
| `insert_tail`, `enqueue`: complejidad `O(1)` — el pseudocódigo enlaza el nodo nuevo directamente desde el nodo `tail`/`rear` actual | `ll-insert-tail` y `queue-enqueue` reconstruyen toda la cadena con `chain-append`: `O(n)` | En Clojure los registros son inmutables. El nodo `tail`/`rear` existente no puede actualizarse en su lugar: hay que producir una copia de cada nodo de la cadena con el enlace correcto. La semántica FIFO/LIFO y el orden de inserción se conservan; solo la complejidad de esas dos operaciones degrada. |
| `pop()` / `dequeue()`: devuelven el valor extraído | `stack-pop` y `queue-dequeue` devuelven `{:stack …, :value …}` / `{:queue …, :value …}` | En un lenguaje inmutable, extraer un elemento requiere producir tanto el valor como la estructura nueva. Un mapa con claves nominadas transporta ambos sin ambigüedad y sin introducir tipos nuevos. El contrato observable (el valor extraído coincide con el que tocaba por LIFO/FIFO) se cumple íntegramente. |
| `get_value()`, `get_next()`, `set_next(next)` como operaciones explícitas | Acceso por keyword (`:value`, `:next`) y `assoc` | `defrecord` expone todos los campos como keywords de Clojure. No existe una forma de «ocultar» el acceso de lectura a los campos de un record; el acceso por keyword es la notación nativa y equivale semánticamente a los getters del pseudocódigo. `assoc` devuelve un nodo nuevo con el campo actualizado, cumpliendo `set_next` para un lenguaje inmutable. |
| Ubicación `src/data_structures_basics.clj` y `test/data_structures_basics_test.clj` | `src/data_structures_basics/data_structures_basics.clj` y `test/data_structures_basics/data_structures_basics_test.clj` | La convención de `deps-new` (y el estándar de Clojure con `deps.edn`) añade un subdirectorio bajo `src/` y `test/` cuyo nombre refleja el namespace. |
| `run_tests.ext` como punto de entrada explícito | No existe; `build.clj` + `cognitect.test-runner` son el runner nativo | `cognitect.test-runner` descubre automáticamente los namespaces de test bajo `test/`; no es necesario (ni idiomático) un archivo de entrada aparte. |

---

## 🚨 Indicadores de fallo / Failure indicators

| Operación / Operation | Situación de fallo / Failure situation | Indicador / Indicator | Ejemplo / Example |
|---|---|---|---|
| `get-head` | Lista vacía / empty list | `nil` | `(get-head (linked-list-init))` → `nil` |
| `ll-delete` | Valor no presente / value not found | `nil` | `(ll-delete lista 99)` → `nil` |
| `stack-peek` | Pila vacía / empty stack | `nil` | `(stack-peek (stack-init))` → `nil` |
| `stack-pop` | Pila vacía / empty stack | `nil` | `(stack-pop (stack-init))` → `nil` |
| `queue-peek` | Cola vacía / empty queue | `nil` | `(queue-peek (queue-init))` → `nil` |
| `queue-dequeue` | Cola vacía / empty queue | `nil` | `(queue-dequeue (queue-init))` → `nil` |

**ES:** `nil` es el indicador de fallo idiomático de Clojure (familia «sin mutación razonable»). Los valores de prueba son enteros positivos (`5`, `10`, `20`, `30`, `40`) para no colisionar con `nil`.

**EN:** `nil` is Clojure's idiomatic failure indicator (the «no reasonable mutation» family). Test values are positive integers (`5`, `10`, `20`, `30`, `40`) to avoid colliding with `nil`.

---

## ✅ Cobertura de pruebas / Test coverage

| Caso de la especificación / Specification case | Cubierto / Covered | Prueba / Test | Notas / Notes |
|---|---|:--:|---|
| Node — inicializar y observar valor/enlace | Sí | `node-test` | `:value` y `:next` verificados |
| Node — enlazar y recorrer | Sí | `node-test` | `assoc :next` + `(:value (:next ...))` |
| LinkedList — estado vacío (`is_empty`, `size`, `get_head`) | Sí | `linked-list-test` | |
| LinkedList — insertar por ambos extremos; recorrido `5 10 20 10` | Sí | `linked-list-test` | helper `chain` recorre desde cabeza |
| LinkedList — eliminar primera aparición de `10` | Sí | `linked-list-test` | |
| LinkedList — valor ausente (`delete(99)`) | Sí | `linked-list-test` | retorno `nil` verificado |
| LinkedList — vaciar la lista (3 deletes) | Sí | `linked-list-test` | `is_empty` = `true`, `size` = `0`, `get_head` = `nil` |
| Stack — estado vacío y extracción fallida (`peek`, `pop`) | Sí | `stack-test` | |
| Stack — LIFO y `peek` no mutante | Sí | `stack-test` | `push(10,20,30)`, `peek` = `30`, `size` = `3` |
| Stack — extracción y reutilización (`pop`, `push(40)`, tres `pop`) | Sí | `stack-test` | orden `30, 40, 20, 10`; `is_empty` = `true` al final |
| Stack — vacío tras extracción (`pop` → fallo) | Sí | `stack-test` | `is_empty` sigue `true` después de llamar `pop` fallido |
| Queue — estado vacío y extracción fallida (`peek`, `dequeue`) | Sí | `queue-test` | |
| Queue — FIFO y `peek` no mutante | Sí | `queue-test` | `enqueue(10,20,30)`, `peek` = `10`, `size` = `3` |
| Queue — extracción y reutilización (`dequeue`, `enqueue(40)`, tres `dequeue`) | Sí | `queue-test` | orden `20, 30, 40`; `is_empty` = `true` al final |
| Queue — vacío tras extracción (`dequeue` → fallo) | Sí | `queue-test` | `is_empty` sigue `true` después de llamar `dequeue` fallido |

---

## ⚠️ Limitaciones conocidas / Known limitations

| Limitación / Limitation | Impacto / Impact | Alternativa o plan / Workaround or plan |
|---|---|---|
| `ll-insert-tail` y `queue-enqueue` son `O(n)` en lugar de `O(1)` | Solo afecta rendimiento; el orden de inserción y la semántica son correctos | La complejidad `O(1)` requeriría estructuras mutables (Java interop con `java.util.LinkedList`) o referencias mutables (`atom`), que están fuera del idioma puro de Clojure en esta fase. |

---

## 📝 Notas de implementación / Implementation Notes

### Inmutabilidad y `defrecord` / Immutability and `defrecord`

**ES:** `defrecord` crea un tipo de valor inmutable. Cada operación que modificaría un campo produce en su lugar un registro nuevo con `assoc` o con el constructor directo (p. ej. `LinkedList. nuevo-head nueva-tail nuevo-count`). La consecuencia más visible del contrato es que `pop` y `dequeue` deben devolver tanto el valor extraído como la estructura actualizada; el README documenta el indicador elegido (mapa con claves descriptivas).

**EN:** `defrecord` creates an immutable value type. Each operation that would modify a field instead produces a new record with `assoc` or with the direct constructor (e.g. `LinkedList. new-head new-tail new-count`). The most visible contract consequence is that `pop` and `dequeue` must return both the extracted value and the updated structure; the README documents the chosen indicator (map with descriptive keys).

### Contrato sin interfaz aparte / Contract without a separate interface

**ES:** La especificación (Fase 1) y `AGENT_Template.md` coinciden: no se exige `interface`, `protocol` ni `behaviour` mientras haya una sola implementación por estructura. El contrato se declara en la API pública del namespace (funciones exportadas), que es la forma idiomática de Clojure según la tabla de notación de `AGENT_Template.md`.

**EN:** The specification (Phase 1) and `AGENT_Template.md` agree: no `interface`, `protocol`, or `behaviour` is required while there is a single implementation per structure. The contract is declared in the namespace's public API (exported functions), which is Clojure's idiomatic form per the notation table in `AGENT_Template.md`.

### Helpers privados y TCO parcial / Private helpers and partial TCO

**ES:** Los tres helpers internos (`chain-append`, `chain-last`, `chain-remove`) se declaran con `defn-`. `chain-last` usa `recur` en posición final y tiene TCO real. `chain-append` y `chain-remove` son recursivos en la posición no terminal (construyen la cola antes de retornar) y, por tanto, crecen en pila proporcionalmente a la longitud de la cadena. En esta fase el comportamiento es correcto para cadenas de tamaño educativo; se documenta como limitación conocida.

**EN:** The three internal helpers (`chain-append`, `chain-last`, `chain-remove`) are declared with `defn-`. `chain-last` uses `recur` in tail position and has real TCO. `chain-append` and `chain-remove` are recursive in non-tail position (they build the tail before returning) and therefore grow in stack proportion to chain length. In this phase the behaviour is correct for educational-size chains; it is documented as a known limitation.

### Suite de pruebas: continuidad de estado / Test suite: state continuity

**ES:** Los casos de cada ADT son **pasos sucesivos sobre la misma instancia lógica**, conforme a la especificación. Como las estructuras son inmutables, cada operación devuelve la instancia siguiente, que la prueba enlaza con `let` anidado o con `->`. El helper `drain` de la suite extrae todos los elementos en orden para verificar el LIFO/FIFO completo.

**EN:** The cases for each ADT are **successive steps on the same logical instance**, as the specification requires. Because the structures are immutable, each operation returns the next instance, which the test threads with nested `let` or `->`. The suite's `drain` helper extracts all elements in order to verify the full LIFO/FIFO sequence.

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
| Especificación / Specification | [`06_Data_Structures_Basics.md`](https://yorche3.github.io/programming_languages/core/algorithms/06_Data_Structures_Basics/) |
| Módulo homologado del lenguaje / Homologated module | [`clojure/core/foundations/numbers/`](../../foundations/numbers/) |
| Módulo homologado Fase 1 / Phase 1 homologated module | [`clojure/core/algorithms/naive_sort/`](../naive_sort/) |
| Guía de inicialización / Initialisation guide | [`core/00_Project_Initialization_Guide.md`](../../../../docs/core/00_Project_Initialization_Guide.md) |
| Adaptaciones idiomáticas / Idiomatic adaptations | [`docs/AGENT_Template.md`](../../../../docs/AGENT_Template.md) |
| Validación de la documentación / Documentation validation | [`docs/WORKFLOW.md`](../../../../docs/WORKFLOW.md) |
| Documentación oficial del lenguaje / Language official docs | [clojure.org/reference/data_structures](https://clojure.org/reference/data_structures) |

---

*[← Volver a Algorithms Pure](../README.md) | [↑ Core](../../README.md)*

*🌐 [github.com/yorche3/programming_languages](https://github.com/yorche3/programming_languages) · [GitHub Pages](https://yorche3.github.io/programming_languages/)*
