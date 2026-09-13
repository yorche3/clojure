# 🚀 Algorithms Pure — Clojure

Implementaciones de la [Fase 1 — Algoritmos Puros](https://yorche3.github.io/programming_languages/ROADMAP/#fase-1--algoritmos-puros--algorithms-pure-) en **Clojure**: algoritmos sobre colecciones con indicadores de fallo compatibles y sin excepciones.

---

## 📁 Estructura / Structure

```text
algorithms/
└── naive_sort/               # 05_Naive_Sort
    ├── src/
    │   └── naive_sort/
    │       └── naive_sort.clj
    ├── test/
    │   └── naive_sort/
    │       └── naive_sort_test.clj
    ├── deps.edn
    ├── build.clj
    └── README.md
```

---

## 📖 Módulos / Modules

| Módulo | Especificación | Enfoque | Tests | Estado |
|--------|---------------|---------|-------|--------|
| [`naive_sort/`](naive_sort/) | [05_Naive_Sort](https://yorche3.github.io/programming_languages/core/algorithms/05_Naive_Sort/) | `clojure -T:build test` (deps.edn + clojure.test) | 3 | ✅ |
| `data_structures` | [06_Data_Structures](https://yorche3.github.io/programming_languages/core/algorithms/06_Data_Structures/) | — | — | 📋 |
| `structures_apps` | [07_Structures_Apps](https://yorche3.github.io/programming_languages/core/algorithms/07_Structures_Apps/) | — | — | 📋 |
| `efficient_sort` | [08_Efficient_Sort](https://yorche3.github.io/programming_languages/core/algorithms/08_Efficient_Sort/) | — | — | 📋 |
| `distributed_sort` | [09_Distributed_Sort](https://yorche3.github.io/programming_languages/core/algorithms/09_Distributed_Sort/) | — | — | 📋 |
| `searching` | [10_Searching](https://yorche3.github.io/programming_languages/core/algorithms/10_Searching/) | — | — | 📋 |

> **ES:** `naive_sort` es la primera implementación homologada de esta fase en Clojure. Los módulos restantes siguen el orden de la numeración canónica `05_` a `10_`.
> **EN:** `naive_sort` is the first standardized implementation of this phase in Clojure. The remaining modules follow the canonical numbering from `05_` to `10_`.

---

## ▶️ Siguiente / Next

👉 Empieza por [`naive_sort`](naive_sort/README.md).  
👉 Start with [`naive_sort`](naive_sort/README.md).

### 🌐 Otras implementaciones / Other implementations

Este proyecto también está implementado en otros lenguajes. Explora el [repositorio principal](https://github.com/yorche3/programming_languages) para ver todas las versiones.

---

*[← Volver a Core](../README.md)*
