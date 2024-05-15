package sdis.spotify.common;

import java.io.FileNotFoundException;

public interface SpotifyServer extends java.rmi.Remote{
    boolean setClientStreamReceptor(SpotifyClient cliente) throws java.rmi.RemoteException;
    String randomPlay() throws java.rmi.RemoteException;
    String startMedia(Media media) throws java.rmi.RemoteException, FileNotFoundException;
}