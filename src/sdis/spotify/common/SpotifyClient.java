package sdis.spotify.common;

import sdis.spotify.media.Media;

public interface SpotifyClient extends java.rmi.Remote{
    static boolean launchMediaPlayer(Media cancion) throws java.rmi.RemoteException {
        return false;
    }

    boolean isMediaPlayerActive() throws java.rmi.RemoteException;

    static void startStream(Media media, String ip, int puerto) throws java.rmi.RemoteException {
    }
}
