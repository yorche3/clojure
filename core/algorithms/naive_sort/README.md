# Naive Sort — Clojure

Implementación de la especificación [05_Naive_Sort](https://yorche3.github.io/programming_languages/core/algorithms/05_Naive_Sort/) en **Clojure**, utilizando **Clojure CLI (deps.edn)** como sistema de construcción y **clojure.test** como framework de pruebas unitarias.

Implementation of the [05_Naive_Sort](https://yorche3.github.io/programming_languages/core/algorithms/05_Naive_Sort/) specification in **Clojure**, using **Clojure CLI (deps.edn)** as the build system and **clojure.test** as the unit-testing framework.

> **ES:** La especificación admite resolver el ordenamiento in-place o devolviendo una copia ordenada, "según el paradigma del lenguaje". Clojure es funcional y su idioma evita la mutación y prefiere la composición, así que las tres funciones **devuelven siempre una colección nueva** y nunca modifican la de entrada. El algoritmo subyacente es el mismo (selección, burbuja e inserción); cambia la realización, no el concepto.
>
> **EN:** The specification allows sorting in place or returning a sorted copy, "depending on the language paradigm". Clojure is functional and its idiom avoids mutation and favours composition, so all three functions **always return a new collection** and never modify the input. The underlying algorithm is the same (selection, bubble, and insertion); the realization changes, not the concept.

---

## 📂 Archivos y estructura / Files & Structure

| Archivo / Directorio | Propósito |
|----------------------|-----------|
| [`src/naive_sort/naive_sort.clj`](src/naive_sort/naive_sort.clj) | Implementación idiomática: 3 funciones públicas usando vectores persistentes (`reduce`, `conj`, `subvec`, `concat`). |
| [`src/naive_sort/naive_sort_intarray.clj`](src/naive_sort/naive_sort_intarray.clj) | Implementación con Java arrays: 3 funciones públicas usando `int-array`, `aget`, `aset`, `dotimes`. |
| [`test/naive_sort/naive_sort_test.clj`](test/naive_sort/naive_sort_test.clj) | Pruebas para la versión idiomática: 3 tests con 8 casos compartidos (24 aserciones). |
| [`test/naive_sort/naive_sort_intarray_test.clj`](test/naive_sort/naive_sort_intarray_test.clj) | Pruebas para la versión con arrays: 3 tests con 8 casos compartidos (24 aserciones). |
| [`deps.edn`](deps.edn) | Configuración de dependencias y alias (`:test`, `:build`). |
| [`build.clj`](build.clj) | Automatización con `tools.build` (`test`, `ci`, `install`, `deploy`). |
| [`doc/intro.md`](doc/intro.md) | Documentación de la librería generada por la plantilla. |
| [`CHANGELOG.md`](CHANGELOG.md) | Historial de versiones. |
| [`LICENSE`](LICENSE) | Licencia Eclipse Public License 2.0. |
| [`.gitignore`](.gitignore) | Archivos y carpetas ignoradas. |

**Estructura de directorios esperada:**

```text
naive_sort/
├── src/
│   └── naive_sort/
│       └── naive_sort.clj           # 3 funciones públicas (idiomática)
│       └── naive_sort_intarray.clj  # 3 funciones públicas (int-array)
├── test/
│   └── naive_sort/
│       └── naive_sort_test.clj      # 3 tests / 24 aserciones (idiomática)
│       └── naive_sort_intarray_test.clj  # 3 tests / 24 aserciones (int-array)
├── resources/
│   └── .keep
├── doc/
│   └── intro.md
├── deps.edn                         # Manifiesto de dependencias
├── build.clj                        # Automatización (tools.build)
├── CHANGELOG.md
├── LICENSE
├── .gitignore
└── README.md                        # Este archivo
```

---

## 🛠️ Enfoque y construcción / Approach & Build

**ES:** El proyecto se creó con la plantilla de `tools.build` (`clojure -Tnew lib :name naive_sort`) y se configuró manualmente.

Las tres funciones comparten la misma estructura de guardas y difieren solo en el algoritmo:

```clojure
(cond
  (nil? arr) nil                                       ; indicador de fallo
  (or (empty? arr) (= (count arr) 1)) arr               ; ya ordenado
  :else ...)                                            ; algoritmo
```

**EN:** The project was created with the `tools.build` template (`clojure -Tnew lib :name naive_sort`) and configured manually.

All three functions share the same guard structure and differ only in the algorithm:

1. **`selection-sort`** — Un helper local `min-index` definido con `letfn` localiza el mínimo; un `loop` acumula en `remaining` los elementos pendientes y en `sorted` el resultado, que se construye con `conj` y `subvec`.
2. **`bubble-sort`** — Un `loop` externo reduce `n` (la porción aún sin ordenar) y un `loop` interno recorre esa porción intercambiando adyacentes con `assoc`.
3. **`insertion-sort`** — Un `loop` mantiene `sorted` y `remaining`; para cada elemento calcula su posición con `take-while` y reconstruye la colección con `concat` sobre `subvec`.

### Pasos de inicialización / Initialization steps

```bash
# Crear el proyecto desde la plantilla / Create the project from the template
clojure -Tnew lib :name naive_sort

# Ejecutar las pruebas / Run the tests
clojure -T:build test
```

---

## 📄 Archivos de configuración clave / Key Configuration Files

### `deps.edn` — Manifiesto de dependencias

**ES:** Define las rutas de código fuente, la dependencia de Clojure y los alias para pruebas y construcción. El runner de pruebas es `cognitect-labs/test-runner`, declarado en el alias `:test`.

**EN:** Defines source paths, the Clojure dependency, and aliases for testing and building. The test runner is `cognitect-labs/test-runner`, declared in the `:test` alias.

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

**ES:** La tarea `test` construye una base con el alias `:test` e invoca `clojure.main -m cognitect.test-runner`, propagando el fallo si los tests no pasan. No requiere un archivo `run_tests.clj` aparte.

**EN:** The `test` task builds a basis with the `:test` alias and invokes `clojure.main -m cognitect.test-runner`, failing if the tests do not pass. It requires no separate `run_tests.clj` file.

---

## 🚀 Compilación y ejecución / Build & Run

### Ejecutar pruebas unitarias / Run unit tests

```bash
clojure -T:build test    # Ejecuta los tests
clojure -T:build ci      # Tests + JAR (pipeline de CI)
clojure -T:build install # Instala el JAR localmente
clojure -T:build deploy  # Publica en Clojars
```

**Salida real / Actual output:**

```text
Running tests in #{"test"}

Testing naive-sort.naive-sort-test

Ran 3 tests containing 24 assertions.
0 failures, 0 errors.
```

---

## 🧠 Algoritmos / Algorithms

| Algoritmo / Algorithm | Estrategia / Strategy | Complejidad temporal / Time complexity | Mutación / Mutation |
|---|---|---|:---:|
| `selection-sort` | Busca el mínimo del resto no ordenado y lo ubica al inicio / Finds the minimum of the unsorted remainder and places it at the front | $O(n^2)$ siempre / always | ❌ ninguna / none (idiomática)<br>✅ in-place (int-array) |
| `bubble-sort` | Compara e intercambia adyacentes en pasadas sucesivas / Compares and swaps adjacent elements in successive passes | $O(n^2)$ peor/promedio, $O(n)$ mejor / worst-average, best | ❌ ninguna / none (idiomática)<br>✅ in-place (int-array) |
| `insertion-sort` | Inserta cada elemento en su posición dentro del sub-array ya ordenado / Inserts each element into its position within the already sorted sub-array | $O(n^2)$ peor/promedio, $O(n)$ mejor / worst-average, best | ❌ ninguna / none (idiomática)<br>✅ in-place (int-array) |

### Casos cubiertos por las pruebas / Cases covered by the tests

Cada algoritmo verifica los mismos 8 casos (24 aserciones en total) / Each algorithm checks the same 8 cases (24 assertions total):

| Caso / Case | Entrada / Input | Salida esperada / Expected output |
|---|---|---|
| Desordenado / Unsorted | `[5 2 9 1 5 6]` | `[1 2 5 5 6 9]` |
| Ya ordenado / Already sorted | `[1 2 3 4 5]` | `[1 2 3 4 5]` |
| Orden inverso / Reverse order | `[5 4 3 2 1]` | `[1 2 3 4 5]` |
| Idénticos / Identical | `[7 7 7 7]` | `[7 7 7 7]` |
| Negativos / Negatives | `[3 -1 4 -5 0]` | `[-5 -1 0 3 4]` |
| Un elemento / Single element | `[42]` | `[42]` |
| Vacío / Empty | `[]` | `[]` |
| Nulo / Nil | `nil` | `nil` (indicador de fallo / failure indicator) |

---

## 📝 Notas de implementación / Implementation Notes

### 🧬 Inmutabilidad y composición / Immutability and composition

**ES:** La versión idiomática (`naive_sort.clj`) no muta su argumento. Los intercambios y la inserción se expresan con operaciones que devuelven colecciones nuevas (`assoc`, `conj`, `subvec`, `concat`), y el estado de cada pasada se propaga con `loop`/`recur` en lugar de reasignar variables. Esto es lo que hace que la realización difiera del `in-place` que describe la especificación: el concepto algorítmico es el mismo, pero el idioma de Clojure trabaja con valores.

La versión con arrays (`naive_sort_intarray.clj`) usa `int-array`, `aget`, `aset` y `dotimes` para lograr mutación in-place real, a costa de abandonar la inmutabilidad. Esta versión se incluye para comparar rendimiento y mostrar la interop con Java.

**EN:** The idiomatic version (`naive_sort.clj`) does not mutate its argument. Swaps and insertion are expressed with operations that return new collections (`assoc`, `conj`, `subvec`, `concat`), and each pass's state is threaded with `loop`/`recur` instead of reassigning variables. This is what makes the realization differ from the `in-place` approach described in the specification: the algorithmic concept is the same, but Clojure's idiom works with values.

The array-based version (`naive_sort_intarray.clj`) uses `int-array`, `aget`, `aset`, and `dotimes` to achieve real in-place mutation at the cost of abandoning immutability. This version is included for performance comparison and to demonstrate Java interop.

### 🔁 Iteración con `loop`/`recur` / Iteration with `loop`/`recur`

**ES:** Clojure no tiene bucles imperativos; `loop`/`recur` es su construcción iterativa y `recur` en posición final reutiliza el marco de pila, por lo que la profundidad no crece con `n`. Se usa tanto para los bucles de cada algoritmo como para el helper `min-index` de `selection-sort`.

**EN:** Clojure has no imperative loops; `loop`/`recur` is its iterative construct, and `recur` in tail position reuses the stack frame, so depth does not grow with `n`. It is used for each algorithm's loops and for `selection-sort`'s `min-index` helper.

### 🔍 El helper local `min-index` / The local `min-index` helper

**ES:** `selection-sort` define `min-index` con `letfn`, que permite declarar funciones mutuamente recursivas con alcance local. Al ser un detalle interno del algoritmo, no forma parte de la API pública (a diferencia de los helpers `static` de C, aquí ni siquiera existe a nivel de namespace).

**EN:** `selection-sort` defines `min-index` with `letfn`, which declares mutually recursive functions with local scope. Being an internal detail of the algorithm, it is not part of the public API (unlike C's `static` helpers, here it does not even exist at namespace level).

### ⚠️ Indicador de fallo / Failure indicator

**ES:** `nil` es un valor válido en Clojure, así que la entrada nula es representable y se resuelve devolviendo `nil`, el indicador de fallo idiomático del lenguaje. La comprobación es la primera guarda de cada función y no se lanza ninguna excepción. Por eso las pruebas incluyen un octavo caso (`nil`) además de los 7 del enunciado.

**EN:** `nil` is a valid value in Clojure, so a nil input is representable and resolves by returning `nil`, the language's idiomatic failure indicator. The check is the first guard of each function and no exception is thrown. That is why the tests include an eighth case (`nil`) in addition to the 7 in the problem statement.

### 🟰 Igualdad estructural en las aserciones / Structural equality in the assertions

**ES:** `selection-sort` y `bubble-sort` devuelven vectores, mientras que `insertion-sort` puede devolver una secuencia perezosa (`concat`). Las aserciones comparan con `=`, que en Clojure compara colecciones secuenciales elemento a elemento con independencia del tipo concreto, así que las tres funciones se validan con las mismas expectativas.

**EN:** `selection-sort` and `bubble-sort` return vectors, while `insertion-sort` may return a lazy sequence (`concat`). The assertions compare with `=`, which in Clojure compares sequential collections element-wise regardless of the concrete type, so all three functions are validated against the same expectations.

### 🧪 Estructura de las pruebas / Test structure

**ES:** Los datos de los 8 casos se declaran una sola vez como constantes `def ^:private` y un único helper `assert-all-cases` ejecuta las aserciones contra la función que reciba, con el nombre del algoritmo en cada mensaje. Cada test es de 3 líneas, lo que evita repetir los casos tres veces.

Ambas implementaciones (idiomática y con arrays) comparten la misma estructura de pruebas, con la diferencia de que la versión con arrays usa un helper `array->vec` para convertir el `int-array` mutado a vector antes de la comparación.

**EN:** The data for the 8 cases is declared once as `def ^:private` constants and a single `assert-all-cases` helper runs the assertions against whichever function it receives, with the algorithm name in each message. Each test is 3 lines, avoiding repetition of the cases three times.

Both implementations (idiomatic and array-based) share the same test structure, with the difference that the array-based version uses an `array->vec` helper to convert the mutated `int-array` to a vector before comparison.

```clojure
(deftest selection-sort-test
  (testing "selection-sort"
    (assert-all-cases sut/selection-sort "selection-sort")))
```

---

### 🧬 Implementación con arrays mutables / Implementation with mutable arrays

**ES:** Se incluyen dos versiones de cada algoritmo para ilustrar el trade-off entre inmutabilidad y rendimiento:

- **Idiomática** (`naive_sort.clj`): usa vectores persistentes y composición de funciones (`reduce`, `conj`, `subvec`). Es el estilo natural de Clojure y no requiere dependencias adicionales. Las funciones **devuelven siempre una colección nueva** y nunca modifican la de entrada.
- **Con Java arrays** (`naive_sort_intarray.clj`): usa `int-array`, `aget`, `aset` y `dotimes`. Estas funciones son parte de `clojure.core` y no requieren instalación adicional. Esta versión logra mutación in-place real, a costa de abandonar la inmutabilidad. Se incluye para comparar rendimiento y mostrar la interop con Java.

La versión idiomática es la recomendada para aprendizaje y uso en producción; la versión con arrays muestra cómo acceder a la interop con Java cuando se necesita rendimiento crítico.

**EN:** Two versions of each algorithm are included to illustrate the trade-off between immutability and performance:

- **Idiomatic** (`naive_sort.clj`): uses persistent vectors and functional composition (`reduce`, `conj`, `subvec`). This is Clojure's natural style and requires no additional dependencies. Functions **always return a new collection** and never mutate the input.
- **With Java arrays** (`naive_sort_intarray.clj`): uses `int-array`, `aget`, `aset`, and `dotimes`. These functions are part of `clojure.core` and require no additional installation. This version achieves real in-place mutation at the cost of abandoning immutability. It is included for performance comparison and to demonstrate Java interop.

The idiomatic version is recommended for learning and production use; the array-based version shows how to access Java interop when critical performance is needed.

---

### 🌐 Otras implementaciones / Other implementations

Este proyecto también está implementado en otros lenguajes. Explora el [repositorio principal](https://github.com/yorche3/programming_languages) para ver todas las versiones.

This project is also implemented in other languages. Explore the [main repository](https://github.com/yorche3/programming_languages) to see all the versions.

---

*[← Volver al Roadmap](https://yorche3.github.io/programming_languages/ROADMAP/)*

*🌐 [github.com/yorche3/programming_languages](https://github.com/yorche3/programming_languages) · [GitHub Pages](https://yorche3.github.io/programming_languages/)*
