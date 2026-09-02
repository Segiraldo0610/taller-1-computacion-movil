# Guía de sustentación

## Explicación general

La aplicación consulta 120 usuarios cuando `UsersApp` entra en composición. La lista se guarda en el estado de ese composable. Navigation 3 envía únicamente el ID seleccionado y el detalle busca el usuario en la lista ya cargada. Por esta razón no se necesita una segunda petición.

## Preguntas posibles del profesor

### 1. ¿Qué es una función `@Composable`?

Es una función que describe una parte de la interfaz y que Compose puede volver a ejecutar cuando cambia el estado que utiliza.

### 2. ¿Por qué `MainActivity` está tan corta?

Porque su responsabilidad es iniciar Compose, aplicar el tema y llamar al composable raíz `UsersApp`.

### 3. ¿Qué hace `remember`?

Conserva un valor mientras el composable siga dentro de la composición.

### 4. ¿Qué hace `mutableStateOf`?

Crea un valor observable. Cuando cambia, Compose actualiza las partes de la interfaz que lo leen.

### 5. ¿Qué es la recomposición?

Es el proceso mediante el cual Compose vuelve a ejecutar los composables afectados por un cambio de estado.

### 6. ¿Qué hace `LaunchedEffect`?

Permite ejecutar una corrutina relacionada con la vida de un composable.

### 7. ¿Por qué se usa `LaunchedEffect(Unit)`?

Porque `Unit` es una clave estable. La carga se inicia cuando `UsersApp` entra en composición y no se repite por una recomposición normal.

### 8. ¿Dónde se guardan los usuarios?

En la variable `users` de `UsersApp`, creada con `remember` y `mutableStateOf`.

### 9. ¿Qué es una función `suspend`?

Es una función que puede suspender su trabajo sin bloquear el hilo mientras espera una operación, como una respuesta de Internet.

### 10. ¿Por qué `fetchUsers` es `suspend`?

Porque realiza una petición HTTP y debe esperar la respuesta sin congelar la interfaz.

### 11. ¿Qué hace `HttpClient`?

Es el cliente de Ktor encargado de enviar la petición y recibir la respuesta de DummyJSON.

### 12. ¿Qué hace `ContentNegotiation`?

Permite que Ktor convierta automáticamente el cuerpo JSON en objetos Kotlin.

### 13. ¿Qué hace Kotlin Serialization?

Relaciona las propiedades del JSON con los `data class` marcados con `@Serializable`.

### 14. ¿Qué es un `data class`?

Es una clase pensada para representar datos. Kotlin genera funciones útiles como `equals`, `hashCode` y `toString`.

### 15. ¿Para qué sirve `ignoreUnknownKeys`?

Permite ignorar campos del JSON que no están definidos en nuestros modelos.

### 16. ¿Por qué se usa `LazyColumn` y no `Column`?

Porque `LazyColumn` compone principalmente los elementos visibles y es apropiado para una lista de 120 usuarios.

### 17. ¿Qué es `ListItem`?

Es un componente de Material 3 preparado para mostrar contenido principal, secundario, imagen e ícono final en una fila.

### 18. ¿Qué hace `stickyHeader`?

Mantiene visible el encabezado con el total mientras se desplaza la lista.

### 19. ¿Qué es `NavKey`?

Es la interfaz que implementan las rutas usadas por Navigation 3.

### 20. ¿Qué hace `rememberNavBackStack`?

Crea y conserva la pila que registra las pantallas visitadas.

### 21. ¿Qué hace `NavDisplay`?

Muestra la entrada actual de la pila de navegación.

### 22. ¿Qué hace `entryProvider`?

Relaciona cada tipo de ruta con el composable que debe mostrarse.

### 23. ¿Por qué la ruta de detalle solo lleva `userId`?

Porque la lista ya pertenece a `UsersApp`. Transportar solo el ID deja claro que el detalle reutiliza esos datos.

### 24. ¿Cómo se encuentra el usuario seleccionado?

Con `users.find { it.id == route.userId }`.

### 25. ¿Qué ocurre si el ID no existe?

Se muestra un mensaje sencillo y un botón para volver; la aplicación no se cierra ni consulta la API otra vez.

### 26. ¿Por qué no se consulta nuevamente en el detalle?

Porque todos los campos necesarios ya llegaron en la petición inicial y están guardados en `users`.

### 27. ¿Qué hace Coil?

Descarga y muestra las imágenes de los usuarios dentro de los composables.

### 28. ¿Qué hace `SubcomposeAsyncImage`?

Permite mostrar contenido diferente mientras la imagen carga, cuando falla y cuando termina correctamente.

### 29. ¿Por qué se usa `ACTION_DIAL` y no `ACTION_CALL`?

`ACTION_DIAL` abre el marcador y deja que el usuario confirme la llamada. No requiere permiso para llamar directamente.

### 30. ¿Cómo funciona el tema claro y oscuro?

`isSystemInDarkTheme` consulta el modo del dispositivo y `Taller1Theme` selecciona el esquema de colores correspondiente.

### 31. ¿Cómo se maneja un error de red?

La excepción se captura en `UsersApp`, se guarda un mensaje en `errorMessage` y la pantalla muestra el estado de error.

### 32. ¿Por qué la URL tiene `/users` en plural?

Porque ese es el endpoint actual de DummyJSON para obtener la colección de usuarios.
