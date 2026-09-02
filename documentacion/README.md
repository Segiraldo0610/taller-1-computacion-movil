# Taller 1: Layouts y listas en Android Compose

## Integrantes

- Sebastián Peralta Neme (`SebastianNeme`)
- Samuel Enrique Giraldo Sabogal (`Segiraldo0610`)

## Descripción

Aplicación Android escrita en Kotlin y Jetpack Compose. Consulta 120 usuarios de DummyJSON y presenta una lista con nombre, apellido, empresa e imagen. Al seleccionar un usuario muestra su información completa y permite abrir el marcador con su número telefónico.

Endpoint utilizado:

`https://dummyjson.com/users?limit=120`

## Funcionalidades

- Una petición inicial para obtener 120 usuarios.
- Lista con `LazyColumn`, `ListItem` y `stickyHeader`.
- Estados de carga, error y resultado.
- Pantalla de detalle con los campos solicitados.
- Navegación por ID sin repetir la consulta.
- Imágenes con Coil.
- Marcador mediante `ACTION_DIAL`.
- Tema claro y oscuro con Material 3.

## Arquitectura

```text
MainActivity
└── UsersApp
    ├── estado con remember y mutableStateOf
    ├── carga con LaunchedEffect(Unit)
    ├── Navigation 3
    ├── UserListScreen
    └── UserDetailScreen
```

`MainActivity` solo configura el tema y llama a `UsersApp`. `UsersApp` posee la lista, la carga, el error y la navegación. `UsersApi` es el único archivo que realiza la petición HTTP.

## Organización del código

```text
data/model       Modelos serializables de DummyJSON
data/remote      Cliente Ktor y consulta de usuarios
ui/components    Componente de imagen reutilizado
ui/navigation    Estado principal y Navigation 3
ui/screens       Lista y detalle
ui/theme         Tema claro y oscuro
```

## Versiones principales

- Navigation 3: 1.1.6.
- Ktor: 3.5.2.
- Coil: 3.5.0.
- Kotlin Serialization: 1.11.0.

## Ejecución

1. Abrir el proyecto en Android Studio.
2. Esperar la sincronización de Gradle.
3. Seleccionar un emulador o dispositivo.
4. Ejecutar la aplicación.

Verificación desde Windows:

```powershell
.\gradlew.bat clean testDebugUnitTest lintDebug assembleDebug
```

El APK queda en `app/build/outputs/apk/debug/app-debug.apk`.

## Documentos relacionados

- [Guía de sustentación](GUIA_SUSTENTACION.md)
- [Lista de entrega](CHECKLIST_ENTREGA.md)
- [Resultados de verificación](VERIFICACION.md)
- [Guion del video](GUION_VIDEO.md)
- [Plan de trabajo](PLAN_TRABAJO.md)
