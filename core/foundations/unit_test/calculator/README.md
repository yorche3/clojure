# Calculator — Clojure

Implementación de la especificación [03_Unit_Test_Calculator](https://yorche3.github.io/programming_languages/core/foundations/03_Unit_Test_Calculator/) en **Clojure**, utilizando **Clojure CLI (deps.edn)** como sistema de construcción y **clojure.test** como framework de pruebas unitarias.

Implementa las 5 operaciones aritméticas básicas usando únicamente suma y resta (sin operadores `*`, `/` ni `mod` directos), con propósitos educativos.

---

## 📂 Archivos y estructura / Files & Structure

| Archivo / Directorio | Propósito |
|----------------------|-----------|
| `src/proglangs/calculator.clj` | Implementación de las 5 operaciones aritméticas básicas. |
| `test/proglangs/calculator_test.clj` | Suite de pruebas unitarias con `clojure.test`. |
| `deps.edn` | Configuración de dependencias y alias (Clojure CLI). |
| `build.clj` | Script de automatización con `tools.build` (tests, JAR, CI). |
| `.gitignore` | Archivos y carpetas ignoradas. |

**Estructura de directorios esperada:**

```text
calculator/
├── src/
│   └── proglangs/
│       └── calculator.clj    # Implementación
├── test/
│   └── proglangs/
│       └── calculator_test.clj # Pruebas unitarias
├── resources/
│   └── .keep
├── doc/
│   └── intro.md
├── deps.edn                   # Manifiesto de dependencias
├── build.clj                  # Automatización (tools.build)
├── CHANGELOG.md
├── LICENSE
├── .gitignore
└── README.md                  # Este archivo
```

---

## 🛠️ Enfoque y construcción / Approach & Build

**ES:** El proyecto se creó con `clojure -T:build new` (template de `tools.build`) y se configuró manualmente para incluir:

1. **Biblioteca** — `src/proglangs/calculator.clj` con las 5 operaciones.
2. **Pruebas** — `test/proglangs/calculator_test.clj` con 5 tests usando `clojure.test`.
3. **Test runner** — `cognitect-test-runner` para ejecutar las pruebas vía `clojure -T:build test`.

**EN:** The project was created with `clojure -T:build new` (tools.build template) and manually configured to include:

1. **Library** — `src/proglangs/calculator.clj` with the 5 operations.
2. **Tests** — `test/proglangs/calculator_test.clj` with 5 tests using `clojure.test`.
3. **Test runner** — `cognitect-test-runner` to run the tests via `clojure -T:build test`.

### Pasos de inicialización:

```bash
# Crear el proyecto desde la plantilla tools.build
clojure -T:build new :template default :name proglangs/calculator

# Ejecutar las pruebas
clojure -T:build test
```

---

## 📄 Archivos de configuración clave / Key Configuration Files

### `deps.edn` – Manifiesto de dependencias

**ES:** Define las rutas de código fuente, la dependencia de Clojure y los alias para pruebas y construcción.
**EN:** Defines source paths, Clojure dependency, and aliases for testing and building.

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

### `build.clj` – Automatización

**ES:** Define tareas para `test`, `ci` (tests + JAR), `install` y `deploy` usando `tools.build`.
**EN:** Defines tasks for `test`, `ci` (tests + JAR), `install` and `deploy` using `tools.build`.

### `.gitignore` – Archivos ignorados

**ES:** Patrones para no versionar archivos generados (JARs, class files, directorios de salida).
**EN:** Patterns to avoid versioning generated files (JARs, class files, output directories).

```gitignore
pom.xml
pom.xml.asc
*.jar
*.class
/lib/
/classes/
/target/
/checkouts/
.lein-deps-sum
.lein-repl-history
.lein-plugins/
.lein-failures
.nrepl-port
.cpcache/
```

---

## 🚀 Compilación y ejecución / Build & Run

### Requisito: Tener Clojure CLI instalado

```bash
# Linux / macOS (con Homebrew)
brew install clojure/tools/clojure

# Linux (con apt)
sudo apt install clojure

# Windows (con winget)
winget install Clojure.Clojure

# O seguir la guía oficial: https://clojure.org/guides/install_clojure
```

### Ejecutar pruebas / Run tests

```bash
clojure -T:build test
```

**Salida esperada / Expected output:**

```text
Running tests for proglangs.calculator-test

Testing proglangs.calculator-test

Ran 5 tests containing 5 assertions.
0 failures, 0 errors.
```

---

## 🧠 Algoritmos / operaciones

| Función / Algoritmo | Enfoque | Descripción |
|---------------------|---------|-------------|
| `addition` | Directo | Suma de dos enteros usando el operador `+` nativo de Clojure. |
| `subtraction` | Directo | Resta de dos enteros usando el operador `-` nativo. |
| `multiplication` | Iterativo | Suma repetitiva con `loop`/`recur` sin usar el operador `*`. |
| `division` | Iterativo | Restas sucesivas con `loop`/`recur` sin usar el operador `/`. |
| `modulus` | Compuesto | Resto usando `addition`, `subtraction` y `multiplication` (sin `mod`). |

---

## 📝 Notas de implementación / Implementation Notes

- **ES:** Las operaciones de multiplicación y división usan `loop`/`recur` (la forma de Clojure para iteración sin mutación) para cumplir con el requisito educativo de no usar los operadores directos del lenguaje.
- **EN:** Multiplication and division operations use `loop`/`recur` (Clojure's way of iterating without mutation) to comply with the educational requirement of not using the language's direct operators.
- **ES:** `modulus` se implementa componiendo las otras operaciones: `a - (b * (a / b))`, sin usar la función `mod` ni `rem` de Clojure.
- **EN:** `modulus` is implemented by composing the other operations: `a - (b * (a / b))`, without using Clojure's `mod` or `rem` functions.
- **ES:** El namespace `proglangs.calculator` sigue la convención de Clojure de asociar namespaces con la estructura de directorios.
- **EN:** The `proglangs.calculator` namespace follows Clojure's convention of associating namespaces with the directory structure.

---

### 🌐 Otras implementaciones / Other implementations

Este proyecto también está implementado en otros lenguajes. Explora el [repositorio principal](https://github.com/yorche3/programming_languages) para ver todas las versiones.

---
*🌐 [github.com/yorche3/programming_languages](https://github.com/yorche3/programming_languages) · [GitHub Pages](https://yorche3.github.io/programming_languages/)*
