# GeoEventos

Plataforma de eventos geolocalizados que permite a empresas locales **publicar, promocionar y gestionar sus eventos** en un mapa interactivo, de forma similar a Google Maps.

---

## ¿Qué es GeoEventos?

GeoEventos conecta empresas con usuarios a través de un mapa interactivo. Las empresas publican sus eventos con ubicación, precio, descripción e imágenes. Los usuarios los descubren de forma gratuita desde cualquier dispositivo.

### Modelo de Negocio — B2B

| Actor | Rol | Acceso |
|-------|-----|--------|
| **Empresas / Clientes** | Pagan por publicar y promocionar eventos | Panel de gestión (GUI) |
| **Usuarios finales** | Descubren y exploran eventos en el mapa | App Android (gratuito) |

---

## Repositorios

| Repositorio | Tecnología | Descripción |
|-------------|------------|-------------|
| [GeoEventosAPI](https://github.com/AlfredoSWDev/GeoEventosAPI) | Spring Boot + PostgreSQL | API REST — lógica de negocio y acceso a datos |
| [GeoEventosGUI](https://github.com/AlfredoSWDev/GeoEventosGUI) | Java Swing + JavaFX | Cliente de escritorio para gestión de eventos (B2B) |
| [GeoEventosAndroid](https://github.com/AlfredoSWDev/GeoEventosAndroid) | Kotlin + Jetpack Compose | App móvil para usuarios finales |

---

## Arquitectura General

```
┌─────────────────────────────────────────────┐
│              Clientes                        │
│                                             │
│  GeoEventosGUI        GeoEventosAndroid     │
│  (Swing + Leaflet)    (Compose + OSMDroid)  │
│       │                      │              │
└───────┼──────────────────────┼──────────────┘
        │    HTTP REST (JSON)  │
        ▼                      ▼
┌─────────────────────────────────────────────┐
│              GeoEventosAPI                  │
│         (Spring Boot - puerto 8080)         │
│                                             │
│  Controller → Service → Repository         │
└──────────────────┬──────────────────────────┘
                   │
        ┌──────────┴──────────┐
        ▼                     ▼
   PostgreSQL              ImgBB API
   (eventos,               (almacenamiento
   coordenadas)             de imágenes)
```

---

## Funcionalidades Actuales (MVP)

- ✅ CRUD completo de eventos (crear, leer, actualizar, borrar)
- ✅ Búsqueda de eventos por nombre y lugar
- ✅ Subida de imágenes a ImgBB desde el backend
- ✅ Coordenadas geográficas (latitud / longitud) por evento
- ✅ Mapa interactivo con marcadores en el cliente de escritorio
- ✅ Click en marcador para ver detalle del evento
- ✅ Click en mapa para asignar ubicación al crear un evento
- ✅ App Android con mapa OSM y panel de detalle
- ✅ Suite de tests en GeoEventosGUI (JUnit 5 + Mockito + WireMock)
- ✅ Suite de tests en GeoEventosAndroid (JVM + Compose UI)

---

## Testing

### GeoEventosGUI

Tests automatizados que no requieren GeoEventosAPI corriendo — usan WireMock para simular el servidor.

| Capa | Archivo | Tests | Requiere |
|------|---------|-------|----------|
| Modelo / DTO | `ImagenesTest` | 8 | Solo JVM |
| Datos | `ConectorTest` | 7 | Solo JVM |
| Datos | `LeerEventoTest` | 3 | Solo JVM |
| HTTP (integración) | `ApiClientIntegrationTest` | 9 | Solo JVM |

```bash
# Desde el directorio GeoEventosGUI
mvn test
```

En entornos sin pantalla (servidores, CI/CD):

```bash
# Fedora / RHEL
sudo dnf install xorg-x11-server-Xvfb

# Ubuntu / Debian
sudo apt install xvfb
```

```bash
Xvfb :99 &
export DISPLAY=:99
mvn test
```

Resultado esperado: **27 tests, 0 failures**.

---

### GeoEventosAndroid

| Capa | Archivo | Tests | Requiere |
|------|---------|-------|----------|
| ViewModel | `EventosViewModelTest` | 18 | Solo JVM |
| DTO | `EventoResponseTest` | 16 | Solo JVM |
| Validación de foto | `FotoUrlValidationTest` | 13 | Solo JVM |
| Service + MockWebServer | `EventoApiServiceTest` | 25 | Solo JVM |
| Compose UI | `PantallaMapaEventosUITest` | 12 | Emulador |

```bash
# Desde el directorio GeoEventosAndroid
./gradlew testDebugUnitTest       # Tests JVM
./gradlew connectedAndroidTest    # Tests de UI (requiere emulador)
```

---

## Roadmap

- [ ] Autenticación y autorización (JWT)
- [ ] Filtros por categoría, fecha y proximidad geográfica
- [ ] Endpoint de eventos por radio de distancia
- [ ] Panel de estadísticas para clientes B2B
- [ ] Notificaciones push en Android
- [ ] Documentación automática con Swagger / OpenAPI
- [ ] Despliegue en AWS (EC2 + RDS)
- [ ] Publicación en Google Play Store

---

## Cómo Clonar el Proyecto Completo

```bash
# Clonar el repo principal con todos los submódulos
git clone --recurse-submodules git@github.com:AlfredoSWDev/GeoEventos.git
```

Si ya clonaste sin submódulos:
```bash
git submodule update --init --recursive
```

---

## Cómo Levantar el Proyecto

**1. Levantar la API (requerido por todos los clientes):**
```bash
cd GeoEventosAPI
./gradlew bootRun
```

**2. Levantar el cliente de escritorio:**
```bash
cd GeoEventosGUI
# Ejecutar Main.java desde IntelliJ IDEA
```

**3. Levantar la app Android:**
```bash
cd GeoEventosAndroid
# Abrir en Android Studio y correr en emulador o dispositivo
```

---

## Requisitos

| Herramienta | Versión mínima |
|-------------|---------------|
| Java | 23+ |
| PostgreSQL | 14+ |
| Android Studio | Hedgehog+ |
| Android SDK | 28+ |

---

## Desarrollado por

**Alfredo** — [github.com/AlfredoSWDev](https://github.com/AlfredoSWDev)
