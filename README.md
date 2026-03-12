# GeoEventos

Plataforma de eventos que integra una API, un cliente de escritorio, una aplicación móvil y un cliente web para la gestión y visualización de eventos.

## Arquitectura General

La plataforma GeoEventos se compone de cuatro módulos principales:

* **GeoEventosAPI**: El backend de la plataforma, desarrollado en Java 23 con Spring Boot 3.x, que proporciona una API RESTful para la gestión de eventos.
* **GeoEventosGUI**: El cliente de escritorio, desarrollado en Java 23 con Swing y JavaFX, que permite a los usuarios administrar y visualizar eventos.
* **GeoEventosWeb**: El cliente web, desarrollado en Kotlin 2.3 con Compose Multiplatform, que ofrece una interfaz de usuario web para la visualización de eventos.
* **GeoEventosAndroid**: La aplicación móvil, desarrollada en Kotlin 1.9 con Jetpack Compose, que permite a los usuarios acceder y visualizar eventos en su dispositivo móvil.

## Requisitos

* Java / JDK: 23+
* PostgreSQL: 14+
* Android Studio: Hedgehog+
* Android SDK: 28+
* Navegador moderno: Chrome 74+, Firefox 79+

## Cómo Clonar el Monorepo

Para clonar el monorepo completo, ejecuta el siguiente comando:

```bash
git clone --recurse-submodules git@github.com:AlfredoSWDev/GeoEventos.git
```

## Cómo Correr Cada Módulo

### GeoEventosAPI

Para levantar la API, ejecuta el siguiente comando:

```bash
cd GeoEventosAPI
./gradlew bootRun
```

La API estará disponible en `http://localhost:8080`.

### GeoEventosGUI

Para ejecutar el cliente de escritorio, abre el proyecto en IntelliJ IDEA y ejecuta la clase `Main.java`. Alternativamente, puedes ejecutar el siguiente comando:

```bash
cd GeoEventosGUI
mvn javafx:run
```

### GeoEventosWeb

Para ejecutar el cliente web, ejecuta el siguiente comando:

```bash
cd GeoEventosWeb
./gradlew :composeApp:wasmJsBrowserDevelopmentRun
```

El cliente web se abrirá automáticamente en `http://localhost:8080`.

### GeoEventosAndroid

Para ejecutar la aplicación móvil, abre el proyecto en Android Studio y ejecuta la aplicación en un emulador o dispositivo físico. Alternativamente, puedes ejecutar el siguiente comando:

```bash
cd GeoEventosAndroid
./gradlew installDebug
```

## Flujo de Desarrollo

1. Desarrollo local → 
2. Commits a rama feature → 
3. Pull Request → 
4. Merge a main → 
5. CI/CD (Tests) → 
6. Deploy a producción

Cada proyecto utiliza su propio sistema de build:
* **GeoEventosAPI**: Gradle
* **GeoEventosGUI**: Maven
* **GeoEventosWeb**: Gradle
* **GeoEventosAndroid**: Gradle

## Contribuyendo

1. Crea una rama feature desde `main`
2. Implementa los cambios
3. Ejecuta los tests del proyecto correspondiente
4. Abre un Pull Request con descripción clara
5. Después del merge, los cambios se despliegan automáticamente


