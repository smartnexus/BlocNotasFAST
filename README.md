# BlocNotasFAST
Trabajo Fundamentos de Aplicaciones y Servicios telemáticos con modificaciones adicionales a las propuestas.

Modificaciones añadidas
-------------------
- Formulario de registro para nuevos usuarios, de tal forma que cuando se envíe ese formulario, si no existe ningún usuario creado con ese mismo nombre se inicie la sesión automáticamente.
- Mensaje de error en la pantalla de acceso cuando se intenta entrar con un usuario y contraseña no válidos o cuando el usuario con el que se intenta registrar existe en la base de datos. (Todas estas comunicaciones entre distintas peticiones se han hecho utilizando atributos en el contexto de sesión para evitar incluir parámetros en la url).
- Panel de control de usuarios para administradores. Muestra una lista de todos los usuarios registrados, de su nivel de privilegios y con opciones para cambiar el tipo de usuario o borrarlos directamente. (Ambas peticiones se hacen por AJAX y a un servlet específico en cada caso).
