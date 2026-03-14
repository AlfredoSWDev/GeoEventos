# GeoEventos

Plataforma B2B de gestión de eventos geolocalizados. Integra una API REST, un cliente de escritorio, una aplicación móvil y un cliente web para la publicación y visualización de eventos en tiempo real.

🔗 **[API en producción](https://geoeventosapi.onrender.com)** · **[Web en producción](https://alfredoswdev.github.io/GeoEventosWeb/)**

---

## Arquitectura General

| Módulo | Stack | Estado |
|--------|-------|--------|
| [GeoEventosAPI](https://github.com/AlfredoSWDev/GeoEventosAPI) | Java 21 · Spring Boot 4.0.3 · PostgreSQL (Neon) | ✅ En producción (Render) |
| [GeoEventosWeb](https://github.com/AlfredoSWDev/GeoEventosWeb) | Kotlin/Wasm · Compose Multiplatform 1.10.0 | ✅ En producción (GitHub Pages) |
| [GeoEventosAndroid](https://github.com/AlfredoSWDev/GeoEventosAndroid) | Kotlin · Jetpack Compose · OSMDroid | 🚧 En desarrollo |
| [GeoEventosDesktop](https://github.com/AlfredoSWDev/GeoEventosDesktop) | Java 23 · Swing · JavaFX · Leaflet | 🚧 En desarrollo |
| [GeoEventosDB](https://github.com/AlfredoSWDev/GeoEventosDB) | PostgreSQL 15+ · Neon | ✅ En producción |

---

## Requisitos

* JDK 21+
* PostgreSQL 14+
* Android Studio Hedgehog+
* Android SDK 28+
* Navegador moderno: Chrome 74+, Firefox 79+

---

## Clonar el monorepo

```bash
git clone --recurse-submodules git@github.com:AlfredoSWDev/GeoEventos.git
```

---

## Correr cada módulo

### GeoEventosAPI

```bash
cd GeoEventosAPI
./gradlew bootRun
```

La API estará disponible en `http://localhost:8080`.

### GeoEventosDesktop

```bash
cd GeoEventosDesktop
mvn javafx:run
```

### GeoEventosWeb

```bash
cd GeoEventosWeb
./gradlew :composeApp:wasmJsBrowserDevelopmentRun
```

La app abre en `http://localhost:8080`.

### GeoEventosAndroid

```bash
cd GeoEventosAndroid
./gradlew installDebug
```

O abre el proyecto en Android Studio y ejecuta con ▶️ Run.

---

## Flujo de Desarrollo

```
Desarrollo local → Feature branch → Pull Request → Merge a main → CI/CD → Deploy
```

Cada módulo usa su propio sistema de build:

* **GeoEventosAPI** / **GeoEventosWeb** / **GeoEventosAndroid**: Gradle
* **GeoEventosDesktop**: Maven

---

## Contribuyendo

1. Crea una rama feature desde `main`
2. Implementa los cambios
3. Ejecuta los tests del módulo correspondiente
4. Abre un Pull Request con descripción clara
5. Tras el merge, los cambios se despliegan automáticamente

---

## Autor

**Alfredo Sanchez** — [@AlfredoSWDev](https://github.com/AlfredoSWDev)

📺 Stream de desarrollo en [Twitch](https://twitch.tv/AlfredoSWDev) · [YouTube](https://youtube.com/@AlfredoSWDev)
