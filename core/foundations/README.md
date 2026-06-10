# 🚀 Foundations — Clojure

Implementaciones de la [Fase 0 — Fundamentos](https://yorche3.github.io/programming_languages/ROADMAP/#fase-0--fundamentos--foundations--completada) en **Clojure** (v1.12): `hello_world`, `hello_user`, `calculator` y `numbers`.

---

## 📖 Módulos / Modules

| Módulo | Especificación | Enfoque | Tests | Estado |
|--------|---------------|---------|:-----:|:------:|
| [`helloworld/`](helloworld/) | [01_Hello_World](https://yorche3.github.io/programming_languages/core/foundations/01_Hello_World/) | `clj -M` (archivo único) | — | ✅ |
| [`hellouser/`](hellouser/) | [02_Hello_User](https://yorche3.github.io/programming_languages/core/foundations/02_Hello_User/) | `clj -M` (archivo único) | — | ✅ |
| [`unit_test/calculator/`](unit_test/calculator/) | [03_Unit_Test_Calculator](https://yorche3.github.io/programming_languages/core/foundations/03_Unit_Test_Calculator/) | `deps.edn` + `tools.build` + **clojure.test** | 5 | ✅ |
| [`numbers/`](numbers/) | [04_Numbers](https://yorche3.github.io/programming_languages/core/foundations/04_Numbers/) | `deps.edn` + `tools.build` + **clojure.test** | 15 | ✅ |

---

## 📁 Estructura / Structure

```text
foundations/
├── helloworld/                   # 01_Hello_World
│   ├── hello_world.clj           # Imprime "Hello, World! from Clojure!"
│   └── README.md
│
├── hellouser/                    # 02_Hello_User
│   ├── hello_user.clj            # Lee nombre y saluda
│   └── README.md
│
├── unit_test/
│   └── calculator/               # 03_Unit_Test_Calculator
│       ├── src/
│       │   └── proglangs/
│       │       └── calculator.clj    # 5 operaciones aritméticas
│       ├── test/
│       │   └── proglangs/
│       │       └── calculator_test.clj # 5 tests
│       ├── deps.edn              # Dependencias (Clojure CLI)
│       ├── build.clj             # Automatización (tools.build)
│       └── README.md
│
└── numbers/                      # 04_Numbers
    ├── src/
    │   └── proglangs/
    │       └── numbers.clj           # 15 funciones (3 enfoques × 5 algoritmos)
    ├── test/
    │   └── proglangs/
    │       ├── numbers_recursive_test.clj     # 5 tests recursivos
    │       ├── numbers_accumulator_test.clj   # 5 tests con acumulador
    │       └── numbers_iterative_test.clj     # 5 tests iterativos
    ├── deps.edn
    ├── build.clj
    └── README.md
```

---

## 🛠️ Patrón común / Common Pattern

| Característica | Descripción |
|---------------|-------------|
| **Runtime** | JVM (Java Virtual Machine) |
| **CLI** | Clojure CLI (`clj` / `clojure`) |
| **Build system** | `deps.edn` + `tools.build` (`build.clj`) para proyectos con tests |
| **Test framework** | [`clojure.test`](https://clojure.github.io/clojure/clojure.test-api.html) (incluido en Clojure) |
| **Test runner** | `cognitect-test-runner` vía alias `:test` en `deps.edn` |
| **Convención de directorios** | `src/` (código), `test/` (pruebas), namespaces reflejan la estructura |
| **Script simple** | `clj -M <archivo>.clj` para programas de un solo archivo |

---

## 🚀 Compilación rápida / Quick Build

```bash
# Hello, World!
cd helloworld
clj -M hello_world.clj

# Hello, User!
cd hellouser
clj -M hello_user.clj

# Calculator Tests
cd unit_test/calculator
clojure -T:build test

# Numbers Tests
cd numbers
clojure -T:build test
```

---

### 🌐 Otras implementaciones / Other implementations

Este proyecto también está implementado en otros lenguajes. Explora el [repositorio principal](https://github.com/yorche3/programming_languages) para ver todas las versiones.

---

## ▶️ Siguiente / Next

👉 Después de fundamentos, continúa con [Fase 1 — Algoritmos Puros](https://yorche3.github.io/programming_languages/ROADMAP/#fase-1--algoritmos-puros--algorithms-pure-).  
👉 After foundations, continue with [Phase 1 — Algorithms Pure](https://yorche3.github.io/programming_languages/ROADMAP/#fase-1--algoritmos-puros--algorithms-pure-).

---

*[← Volver a Clojure](../README.md)*

*🌐 [github.com/yorche3/programming_languages](https://github.com/yorche3/programming_languages) · [GitHub Pages](https://yorche3.github.io/programming_languages/)*
