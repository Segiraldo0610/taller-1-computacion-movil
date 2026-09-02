# Guion del video de entrega

Duración objetivo: 3 minutos y 40 segundos.

## 0:00–0:20 — Presentación

“Somos Sebastián Peralta y Samuel Giraldo. Presentamos un directorio de 120 usuarios desarrollado con Kotlin y Jetpack Compose.”

## 0:20–1:05 — Funcionamiento

Mostrar la lista, el total fijo, el desplazamiento, un usuario y su detalle.

“La aplicación hace una sola consulta a DummyJSON. Cada fila muestra nombre, apellido, empresa e imagen. Al seleccionar una persona vemos todos los campos solicitados.”

## 1:05–1:25 — Marcador y tema

Mostrar el teléfono y cambiar el tema del dispositivo.

“El teléfono usa `ACTION_DIAL`, por lo que abre el marcador sin iniciar la llamada. Los colores provienen de Material 3 y se adaptan al tema claro u oscuro.”

## 1:25–2:05 — Organización

Mostrar `MainActivity.kt`, `UsersApp.kt`, `UsersApi.kt` y los paquetes.

“`MainActivity` solamente aplica el tema y llama a `UsersApp`. `UsersApp` contiene el estado y la navegación. `UsersApi` contiene la configuración de Ktor. Las pantallas reciben datos y callbacks sencillos.”

## 2:05–2:45 — Estado y API

Mostrar `LaunchedEffect`, `remember`, `mutableStateOf` y `fetchUsers`.

“`LaunchedEffect(Unit)` ejecuta la carga al entrar a `UsersApp`. `remember` conserva los valores y `mutableStateOf` permite que Compose recomponga la interfaz. Ktor y Kotlin Serialization convierten el JSON en data classes.”

## 2:45–3:20 — Lista y navegación

Mostrar `UserListScreen.kt` y la ruta de detalle.

“La lista usa `LazyColumn`, `ListItem` y `stickyHeader`. Navigation 3 envía solamente el ID. El detalle busca al usuario en la lista cargada, así que no hace otra petición.”

## 3:20–3:40 — Cierre

“Las versiones principales son Navigation 3 1.1.6, Ktor 3.5.2 y Coil 3.5.0. La arquitectura utiliza únicamente los conceptos vistos en clase.”
