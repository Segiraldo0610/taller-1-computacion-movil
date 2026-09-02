# Resultados de verificación

Fecha: 2 de septiembre de 2026.

## Datos comprobados

- `https://dummyjson.com/users?limit=120` responde con 120 usuarios.
- La respuesta actual informa un total de 208 usuarios disponibles.
- El primer usuario contiene nombre, apellido y empresa.

## Pruebas automatizadas

El test `UserParsingTest` comprueba que Kotlin Serialization:

- crea correctamente los modelos;
- conserva el nombre de la empresa;
- acepta campos adicionales gracias a `ignoreUnknownKeys`.

## Compilación final

Comando ejecutado:

```powershell
.\gradlew.bat clean testDebugUnitTest lintDebug assembleDebug
```

Resultado: `BUILD SUCCESSFUL`.

- Pruebas unitarias: sin fallos.
- Lint: 0 problemas reportados.
- APK: `app/build/outputs/apk/debug/app-debug.apk`.
- Tamaño del APK: 13.942.499 bytes.
- SHA-256: `22465EA3325DFB801EE7D0C9CDCB1DC923CADC4BFFCC1037D121A1924410125A`.

## Prueba funcional pendiente

Este entorno no tiene un emulador ni un dispositivo conectado. Antes de entregar debe comprobarse manualmente:

- carga de 120 usuarios;
- lista, encabezado fijo y desplazamiento;
- navegación al detalle y regreso;
- ausencia de una segunda carga al regresar;
- apertura del marcador;
- tema claro y oscuro;
- ausencia de cierres inesperados.
