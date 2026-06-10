# Hello, User! — Clojure

Implementación de la especificación [02_Hello_User](https://yorche3.github.io/programming_languages/core/foundations/02_Hello_User/) en **Clojure**, ejecutado con **Clojure CLI (clj)** sobre la **JVM**.

Lee un nombre desde la entrada estándar y saluda al usuario.

---

## 📂 Archivos y estructura / Files & Structure

| Archivo | Propósito |
|---------|-----------|
| [`hello_user.clj`](hello_user.clj) | Código fuente: solicita un nombre al usuario y saluda. |

**Estructura de directorios esperada:**

```text
hellouser/
├── hello_user.clj      # Código fuente
└── README.md           # Este archivo
```

---

## 🛠️ Enfoque y construcción / Approach & Build

**ES:** Este programa introduce tres conceptos nuevos respecto a `helloworld`:

1. **Entrada de usuario** — `read-line` lee una línea completa desde `stdin`.
2. **Binding local** — `let` vincula el nombre ingresado a una variable local.
3. **Concatenación de cadenas** — `str` combina el saludo con el nombre ingresado.

**EN:** This program introduces three new concepts compared to `helloworld`:

1. **User input** — `read-line` reads a full line from `stdin`.
2. **Local binding** — `let` binds the entered name to a local variable.
3. **String concatenation** — `str` combines the greeting with the entered name.

---

## 📄 Archivos de configuración clave / Key Configuration Files

### `hello_user.clj`

**ES:** El flujo del programa es:

1. Imprimir `"Enter your name: "` con `println`.
2. Leer una línea completa con `read-line` y vincularla a `name`.
3. Construir el saludo con `str` e imprimirlo con `println`.

**EN:** Program flow:

1. Print `"Enter your name: "` with `println`.
2. Read a full line with `read-line` and bind it to `name`.
3. Build the greeting with `str` and print it with `println`.

```clojure
(println "Enter your name: ")
(let [name (read-line)]
  (println (str "Hello, " name "!")))
```

| Elemento | Propósito |
|----------|-----------|
| `println` | Imprime una cadena en la consola (con salto de línea) |
| `let` | Crea un binding local (variable inmutable) en un ámbito |
| `name` | Símbolo local que almacena el nombre ingresado |
| `read-line` | Lee una línea completa desde la entrada estándar |
| `str` | Concatena argumentos en una sola cadena |

> **ES:** A diferencia de lenguajes imperativos, en Clojure `let` no es una asignación sino un *binding*: vincula un valor a un símbolo dentro de un ámbito léxico. El valor no puede ser reasignado.
> **EN:** Unlike imperative languages, in Clojure `let` is not an assignment but a *binding*: it associates a value with a symbol within a lexical scope. The value cannot be reassigned.

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
clj -M hello_user.clj
```

**Salida esperada / Expected output:**

```text
Enter your name: Ada
Hello, Ada!
```

> **ES:** El programa espera a que el usuario escriba su nombre y presione Enter antes de mostrar el saludo.
> **EN:** The program waits for the user to type their name and press Enter before showing the greeting.

---

## 📝 Notas de implementación / Implementation Notes

- **ES:** `read-line` lee hasta el final de la línea (incluyendo espacios) y descarta el salto de línea. A diferencia de lenguajes como C++, no hay problemas con nombres que contengan espacios.
- **EN:** `read-line` reads until the end of the line (including spaces) and discards the newline. Unlike languages like C++, there are no issues with names containing spaces.
- **ES:** Todo el programa está envuelto en un `let`, lo que demuestra el estilo funcional de Clojure: en lugar de asignar variables y mutarlas, se crean bindings inmutables y se pasa el flujo mediante expresiones anidadas.
- **EN:** The entire program is wrapped in a `let`, demonstrating Clojure's functional style: instead of assigning and mutating variables, immutable bindings are created and the flow is passed through nested expressions.
- **ES:** No se necesita compilación explícita; Clojure compila a bytecode JVM en tiempo de ejecución.
- **EN:** No explicit compilation needed; Clojure compiles to JVM bytecode at runtime.

---

### 🌐 Otras implementaciones / Other implementations

Este proyecto también está implementado en otros lenguajes. Explora el [repositorio principal](https://github.com/yorche3/programming_languages) para ver todas las versiones.

---

*🌐 [github.com/yorche3/programming_languages](https://github.com/yorche3/programming_languages) · [GitHub Pages](https://yorche3.github.io/programming_languages/)*
