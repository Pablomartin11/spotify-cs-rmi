package sdis.spotify.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sdis.spotify.common.MalMensajeProtocoloException;
import sdis.spotify.common.MensajeProtocolo;
import sdis.spotify.common.Primitiva;
import sdis.spotify.common.Strings;


public class SpotifyImpl extends java.rmi.server.UnicastRemoteObject implements Spotify{
    
    /*
     * Constructor
     */
    public SpotifyImpl() throws RemoteException{
        super();
    }

    public MensajeProtocolo INFO() throws RemoteException, MalMensajeProtocoloException{
        MensajeProtocolo ms = new MensajeProtocolo(Primitiva.INFO, Strings.MENSAJE_INICIO);
        return ms;
    }

    public MensajeProtocolo XAUTH() throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'XAUTH'");
    }


    public MensajeProtocolo ADD2L() throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ADD2L'");
    }


    public MensajeProtocolo ADDED() throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ADDED'");
    }


    public MensajeProtocolo READL() throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'READL'");
    }


    public MensajeProtocolo MEDIA() throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'MEDIA'");
    }


    public MensajeProtocolo EMPTY() throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'EMPTY'");
    }


    public MensajeProtocolo DELETEL() throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DELETEL'");
    }


    public MensajeProtocolo DELETED() throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DELETED'");
    }


    public MensajeProtocolo NOTAUTH() throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'NOTAUTH'");
    }


    public MensajeProtocolo ERROR() throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ERROR'");
    }
}
