package sdis.spotify.common;

import sdis.spotify.media.Media;

public interface SpotifyClient extends java.rmi.Remote{
    boolean launchMediaPlayer(Media cancion) throws java.rmi.RemoteException;
    boolean isMediaPlayerActive() throws java.rmi.RemoteException;
    void startStream(Media media, String ip, int puerto) throws java.rmi.RemoteException;
}
