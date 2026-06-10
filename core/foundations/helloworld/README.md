# Hello, World! — Clojure

Implementación de la especificación [01_Hello_World](https://yorche3.github.io/programming_languages/core/foundations/01_Hello_World/) en **Clojure**, ejecutado con **Clojure CLI (clj)** sobre la **JVM**.

---

## 📂 Archivos y estructura / Files & Structure

| Archivo | Propósito |
|---------|-----------|
| [`hello_world.clj`](hello_world.clj) | Código fuente: imprime `"Hello, World! from Clojure!"` en la consola. |

**Estructura de directorios esperada:**

```text
helloworld/
├── hello_world.clj     # Código fuente
└── README.md           # Este archivo
```

---

## 🛠️ Enfoque y construcción / Approach & Build

**ES:** Este proyecto usa **Clojure CLI** y sigue un enfoque minimalista: un único archivo fuente, sin dependencias externas, ejecutado directamente con `clj`.

Características:
- **Sin dependencias externas** — solo usa la función `println` del core de Clojure.
- **Ejecución directa** — un solo comando `clj` ejecuta el programa.
- **Sin archivo de proyecto** — no necesita `deps.edn` ni `project.clj`.

**EN:** This project uses **Clojure CLI** and follows a minimalist approach: a single source file, no external dependencies, executed directly with `clj`.

Features:
- **No external dependencies** — only uses the `println` function from Clojure core.
- **Direct execution** — a single `clj` command runs the program.
- **No project file** — no `deps.edn` or `project.clj` needed.

---

## 📄 Archivos de configuración clave / Key Configuration Files

### `hello_world.clj`

**ES:** Punto de entrada. Define el namespace `hello-world` e imprime el saludo en la consola usando `println`.

**EN:** Entry point. Defines the `hello-world` namespace and prints the greeting to the console using `println`.

```clojure
(println "Hello, World! from Clojure!")
```

| Elemento | Propósito |
|----------|-----------|
| `println` | Función de Clojure que imprime una cadena seguida de un salto de línea |
| `"Hello, World! from Clojure!"` | Cadena literal que se muestra en la salida estándar |

> **ES:** `println` es una función del core de Clojure que acepta cualquier número de argumentos, los convierte a string y los imprime separados por espacio, añadiendo un salto de línea al final.
> **EN:** `println` is a Clojure core function that takes any number of arguments, converts them to string, prints them separated by spaces, and adds a newline at the end.

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

### Ejecutar

```bash
clj -M hello_world.clj
```

> **ES:** La flag `-M` le indica a Clojure CLI que ejecute la función `-main` o el script proporcionado usando el classpath sin alias de desviación.
> **EN:** The `-M` flag tells Clojure CLI to run the provided script using the classpath without deviation aliases.

**Salida esperada / Expected output:**

```text
Hello, World! from Clojure!
```

> **ES:** Como alternativa, también se puede ejecutar con `clojure hello_world.clj` si se prefiere la versión más larga del comando.
> **EN:** Alternatively, you can also run it with `clojure hello_world.clj` if you prefer the longer command name.

---

## 📝 Notas de implementación / Implementation Notes

- **ES:** No se necesita compilación explícita; Clojure es un lenguaje que se ejecuta directamente sobre la JVM, compilando a bytecode en tiempo de ejecución.
- **EN:** No explicit compilation needed; Clojure is a language that runs directly on the JVM, compiling to bytecode at runtime.
- **ES:** El namespace `hello-world` se infiere automáticamente del nombre del archivo; no es necesario declararlo explícitamente.
- **EN:** The `hello-world` namespace is automatically inferred from the file name; it doesn't need to be explicitly declared.

---

### 🌐 Otras implementaciones / Other implementations

Este proyecto también está implementado en otros lenguajes. Explora el [repositorio principal](https://github.com/yorche3/programming_languages) para ver todas las versiones.

---

*🌐 [github.com/yorche3/programming_languages](https://github.com/yorche3/programming_languages) · [GitHub Pages](https://yorche3.github.io/programming_languages/)*
