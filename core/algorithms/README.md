# 🚀 Algorithms Pure — Clojure

Implementaciones de la [Fase 1 — Algoritmos Puros](https://yorche3.github.io/programming_languages/ROADMAP/#fase-1--algoritmos-puros--algorithms-pure-) en **Clojure**: algoritmos sobre colecciones con indicadores de fallo compatibles y sin excepciones.

---

## 📁 Estructura / Structure

```text
algorithms/
├── naive_sort/                      # 05_Naive_Sort
│   ├── src/naive_sort/
│   │   └── naive_sort.clj
│   ├── test/naive_sort/
│   │   └── naive_sort_test.clj
│   ├── deps.edn
│   ├── build.clj
│   └── README.md
└── data_structures_basics/          # 06_Data_Structures_Basics
    ├── src/data_structures_basics/
    │   └── data_structures_basics.clj
    ├── test/data_structures_basics/
    │   └── data_structures_basics_test.clj
    ├── deps.edn
    ├── build.clj
    └── README.md
```

---

## 📖 Módulos / Modules

| Módulo | Especificación | Enfoque | Tests | Estado |
|--------|---------------|---------|-------|--------|
| [`naive_sort/`](naive_sort/) | [05_Naive_Sort](https://yorche3.github.io/programming_languages/core/algorithms/05_Naive_Sort/) | `clojure -T:build test` (deps.edn + clojure.test) | 3 tests / 24 aserciones | ✅ |
| [`data_structures_basics/`](data_structures_basics/) | [06_Data_Structures_Basics](https://yorche3.github.io/programming_languages/core/algorithms/06_Data_Structures_Basics/) | `clojure -T:build test` (deps.edn + clojure.test) | 4 tests / 46 aserciones | ✅ |
| 📋 `data_structures_advanced` | [07_Data_Structures_Advanced](https://yorche3.github.io/programming_languages/core/algorithms/07_Data_Structures_Advanced/) | — | — | 📋 |
| 📋 `efficient_sort` | [08_Efficient_Sort](https://yorche3.github.io/programming_languages/core/algorithms/08_Efficient_Sort/) | — | — | 📋 |
| 📋 `distributed_sort` | [09_Distributed_Sort](https://yorche3.github.io/programming_languages/core/algorithms/09_Distributed_Sort/) | — | — | 📋 |
| 📋 `searching` | [10_Searching](https://yorche3.github.io/programming_languages/core/algorithms/10_Searching/) | — | — | 📋 |

> **ES:** Los módulos marcados con 📋 están planificados; los que tienen enlace están implementados y documentados.
> **EN:** Modules marked with 📋 are planned; those with a link are implemented and documented.

---

## 🛠️ Patrón común / Common Pattern

Todos los módulos de esta fase usan **Clojure CLI con `deps.edn`** y **`clojure.test`** como framework de pruebas. La tarea `test` de `build.clj` invoca `cognitect.test-runner` y propaga el fallo si algún test no pasa.

All modules in this phase use **Clojure CLI with `deps.edn`** and **`clojure.test`** as the testing framework. The `test` task in `build.clj` invokes `cognitect.test-runner` and propagates failure if any test does not pass.

---

## 🚀 Compilación rápida / Quick Build

```bash
# naive_sort
cd naive_sort && clojure -T:build test

# data_structures_basics
cd data_structures_basics && clojure -T:build test
```

---

## ▶️ Siguiente / Next

👉 Sigue con [`data_structures_basics`](data_structures_basics/README.md).  
👉 Continue with [`data_structures_basics`](data_structures_basics/README.md).

### 🌐 Otras implementaciones / Other implementations

Este proyecto también está implementado en otros lenguajes. Explora el [repositorio principal](https://github.com/yorche3/programming_languages) para ver todas las versiones.

---

*[← Volver a Core](../README.md)*
