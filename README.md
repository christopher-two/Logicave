# Logicave - Calabozos Y Compuertas

¡Bienvenido a Logicave! Este repositorio contiene un libro interactivo, autónomo y multiplataforma,
construido con **Kotlin Multiplatform** y **Compose Multiplataform**.

El módulo `:book:logicave` es el primer ejemplo de esta librería: un cuento ilustrado que narra una
aventura a través de cuevas mágicas, cada una representando una puerta lógica. Está diseñado para
ser una experiencia de lectura fluida y visualmente atractiva.

El objetivo de este proyecto es servir como una **librería de libros**. Puedes integrar fácilmente "
Logicave" o usar su arquitectura como una plantilla robusta para crear y añadir nuevas historias
interactivas a tu propia aplicación.

## Características Principales

* **100% Kotlin Multiplatform**: Comparte código entre Android, iOS y Web (WasmJs) desde una única
  base de código.
* **Experiencia de Usuario Inmersiva**: Navegación intuitiva mediante gestos de deslizamiento (
  swipe) o toques en los bordes de la pantalla.
* **Animaciones Fluidas**: Efecto de "pila de tarjetas" al pasar las páginas, con animaciones suaves
  y físicas que hacen la interacción más agradable.
* **Componentes Reutilizables**: La interfaz está construida con componentes modulares de **Compose
  Multiplataform**, listos para ser usados en otros libros.
* **Fácil de Integrar**: Añade un libro completo a tu aplicación llamando a un solo Composable.
* **Contenido Desacoplado**: La historia, imágenes y rutas están centralizadas en un único archivo (
  `Pages.kt`), lo que facilita la creación de nuevos libros sin tocar la lógica principal.

## ¿Cómo usar este módulo?

Integrar el libro `Logicave` en una aplicación existente de Compose Multiplatform es muy sencillo.

#### 1. Añadir la dependencia

En el archivo `build.gradle.kts` de tu módulo principal (por ejemplo, `composeApp`), añade el
proyecto `:book:logicave` como una dependencia.

```kotlin
// En composeApp/build.gradle.kts

kotlin {
    // ...
    sourceSets {
        commonMain.dependencies {
            // ...
            // Añade esta línea para incluir el módulo del libro
            implementation(projects.book.logicave)
        }
    }
}
```

#### 2. Mostrar el libro

En cualquier parte de tu UI de Compose, simplemente llama al Composable `LogicaveRoot()` para
renderizar toda la experiencia del libro.

```kotlin
import org.override.book.logicave.LogicaveRoot
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

@Composable
fun YourApp() {
    Surface {
        // ¡Eso es todo! El libro se mostrará aquí.
        LogicaveRoot()
    }
}
```

El módulo gestionará su propia navegación interna, estado y toda la lógica de la interfaz de
usuario.

## Vista Previa

Así es como se ve la experiencia de navegación en la aplicación:

*(Nota: Este es un marcador de posición. Puedes reemplazarlo con un GIF real de tu aplicación.)*

## Crea tu Propio Libro

Este repositorio está pensado como una base para una gran biblioteca de cuentos. Para crear un nuevo
libro:

1. **Crea un nuevo módulo** (ej. `:book:mi-cuento-nuevo`).
2. **Define tu contenido**: Dentro de tu nuevo módulo, crea un archivo similar a `utils/Pages.kt`
   para listar tus páginas, con sus respectivas imágenes y textos.
3. **Ajusta la navegación**: Configura las rutas de tu nuevo libro.
4. **¡Llama a tu nuevo `Composable` raíz** en la aplicación principal!

Los componentes como `BookSwipeNavigation` y `BookPage` son altamente reutilizables y pueden
adaptarse a cualquier contenido nuevo.

## Feedback y Contribuciones

Este proyecto está en constante evolución. Si tienes alguna idea, encuentras un error o quieres
contribuir, ¡no dudes en abrir un *issue* o enviar un *pull request*!
