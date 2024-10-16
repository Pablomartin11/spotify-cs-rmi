# Spotify Client-Server RMI

[![es](https://img.shields.io/badge/lang-es-red.svg)](/README-ES.md)

This project was part of the subject _Distributed Systems_ in the Computer Science Degree at University of Valladolid. The main goal of this project is to learn a client-server technology such as RMI (Remote Method Interface) and the use of it by creating some example client programmes.

We have some packages which makes the structure of the project tha we are going to explain through:

## src package
### client
As we said before, in order to use and try the server side, we made some example programmes which they use the [ClientImpl.java](https://github.com/Pablomartin11/spotify-sdis2/blob/main/src/sdis/spotify/client/ClientImpl.java), this class, is the one which allows to play the music the server sent in our computer implementing the [SpotifyClient.java](https://github.com/Pablomartin11/spotify-sdis2/blob/main/src/sdis/spotify/common/SpotifyClient.java) class from the RMI.

### common
The common package has in its inside the interfaces we have to use to create de Remote Objects RMI its going to send us.

### media
Here we have all the information the server its going to need in some point of the execution, we mention, the [Media.java](https://github.com/Pablomartin11/spotify-sdis2/blob/main/src/sdis/spotify/media/Media.java) class which allows us to define the serializables the servers going to send to the client through the RMI.
Also we have the Media Player and a Globals class for all the constants for the server.

### server
In the server side, firstly we have the launcher obviusly which creates the server implementation objects and binds it in the RMI Registry which the client could pull it when it is created.

We have the [SpotifyServerImpl.java](https://github.com/Pablomartin11/spotify-sdis2/blob/main/src/sdis/spotify/server/SpotifyServerImpl.java) class which implemates the server interface object as we said.

### stream
The server-client Stream classes they are going to need to stablish the TCP connection and send the media through.








