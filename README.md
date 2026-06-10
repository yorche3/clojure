# Clojure

Proyectos en **Clojure** (v1.12), ejecutados sobre la **JVM** con **Clojure CLI (clj)**. Usa `deps.edn` para la gestión de dependencias y `clojure.test` como framework de pruebas unitarias, con `tools.build` para automatización.

---

## 📂 Módulos / Modules

| Módulo | Descripción |
|--------|-------------|
| [`core/foundations/`](core/foundations/) | **Fase 0 — Fundamentos**: `hello_world`, `hello_user`, `calculator`, `numbers` |

---

### ▶️ Comenzar / Getting Started

```bash
# Hello, World!
cd core/foundations/helloworld
clj -M hello_world.clj

# Hello, User!
cd core/foundations/hellouser
clj -M hello_user.clj

# Calculator Tests
cd core/foundations/unit_test/calculator
clojure -T:build test

# Numbers Tests
cd core/foundations/numbers
clojure -T:build test
```

---

## 📦 Requisitos / Requirements

| Herramienta | Instalación |
|-------------|-------------|
| [Clojure CLI](https://clojure.org/guides/install_clojure) | `brew install clojure/tools/clojure` (macOS) / `sudo apt install clojure` (Linux) / `winget install Clojure.Clojure` (Windows) |
| [Java JDK](https://adoptium.net/) (17+) | `brew install temurin` (macOS) / `sudo apt install openjdk-17-jdk` (Linux) |

```bash
# Verificar instalación
clj --version && java -version
```

---

## 🏗️ Tipos de proyecto / Project Types

### 1. Programa simple (archivo único)

**ES:** Un único archivo fuente `.clj`, sin dependencias externas, ejecutado directamente con `clj -M`. Ideal para `hello_world` y `hello_user`.

**EN:** A single `.clj` source file, no external dependencies, executed directly with `clj -M`. Ideal for `hello_world` and `hello_user`.

```bash
clj -M <archivo>.clj
```

### 2. Proyecto biblioteca + pruebas (deps.edn + tools.build)

**ES:** Para proyectos que requieren pruebas unitarias, se utiliza `deps.edn` para gestionar dependencias y `tools.build` (`build.clj`) para automatizar la ejecución de pruebas con `clojure.test`.

**EN:** For projects that require unit tests, `deps.edn` manages dependencies and `tools.build` (`build.clj`) automates test execution with `clojure.test`.

```bash
clojure -T:build test
```

---

## 🌐 Otras implementaciones / Other implementations

Este proyecto también está implementado en otros lenguajes. Explora el [repositorio principal](https://github.com/yorche3/programming_languages) para ver todas las versiones.

---
*🌐 [github.com/yorche3/programming_languages](https://github.com/yorche3/programming_languages) · [GitHub Pages](https://yorche3.github.io/programming_languages/)*