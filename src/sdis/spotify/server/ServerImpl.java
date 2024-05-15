package sdis.spotify.server;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;

import sdis.spotify.common.Media;
import sdis.spotify.common.SpotifyClient;

public class ServerImpl extends java.rmi.server.UnicastRemoteObject implements sdis.spotify.common.SpotifyServer{

    private SpotifyClient cliente;

    protected ServerImpl() throws RemoteException {
        super();
    }

    public boolean setClientStreamReceptor(SpotifyClient cliente) throws RemoteException {
        this.cliente = cliente;
    }

    public String randomPlay() throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'randomPlay'");
    }

    public String startMedia(Media media) throws RemoteException, FileNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startMedia'");
    }
    
}
