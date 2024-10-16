# Spotify Cliente-Servidor RMI

[![es](https://img.shields.io/badge/lang-es-red.svg)](/README.md)

Este proyecto forma parte de la asignatura _Distributed Systems_ en el Grado de Ingeniería Informática en la Universidad de Valladolid. El objetivo principal de este proyecto es aprender una tecnología cliente-servidor como RMI (Remote Method Interface) y su uso mediante la creación de algunos programas cliente de ejemplo.

Tenemos varios paquetes que estructuran el proyecto y que vamos a explicar a continuación:

## Paquete src
### client
Como mencionamos antes, para usar y probar el lado del servidor, creamos algunos programas de ejemplo que utilizan la clase [ClientImpl.java](https://github.com/Pablomartin11/spotify-sdis2/blob/main/src/sdis/spotify/client/ClientImpl.java). Esta clase es la que permite reproducir la música que el servidor envía a nuestro ordenador, implementando la clase [SpotifyClient.java](https://github.com/Pablomartin11/spotify-sdis2/blob/main/src/sdis/spotify/common/SpotifyClient.java) desde RMI.

### common
El paquete common contiene las interfaces que necesitamos para crear los Objetos Remotos que el RMI nos va a enviar.

### media
Aquí tenemos toda la información que el servidor va a necesitar en algún punto de la ejecución, como la clase [Media.java](https://github.com/Pablomartin11/spotify-sdis2/blob/main/src/sdis/spotify/media/Media.java), que nos permite definir los objetos serializables que el servidor enviará al cliente a través de RMI.
También tenemos el Reproductor (Media Player) y una clase Globals para todas las constantes del servidor.

### server
En el lado del servidor, primero tenemos el _launcher_, que obviamente crea los objetos de implementación del servidor y los enlaza en el Registro de RMI, donde el cliente podrá acceder a ellos cuando se creen.

Tenemos la clase [SpotifyServerImpl.java](https://github.com/Pablomartin11/spotify-sdis2/blob/main/src/sdis/spotify/server/SpotifyServerImpl.java), que implementa el objeto de interfaz del servidor, como mencionamos.

### stream
Las clases Stream de cliente-servidor son necesarias para establecer la conexión TCP y enviar el contenido a través de esta.
