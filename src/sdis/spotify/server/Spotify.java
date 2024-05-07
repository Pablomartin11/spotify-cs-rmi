package sdis.spotify.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sdis.spotify.common.MalMensajeProtocoloException;
import sdis.spotify.common.MensajeProtocolo;

public interface Spotify extends Remote{
    MensajeProtocolo INFO() throws RemoteException, MalMensajeProtocoloException;
    MensajeProtocolo XAUTH() throws RemoteException;
    MensajeProtocolo ADD2L() throws RemoteException;
    MensajeProtocolo ADDED() throws RemoteException;
    MensajeProtocolo READL() throws RemoteException;
    MensajeProtocolo MEDIA() throws RemoteException;
    MensajeProtocolo EMPTY() throws RemoteException;
    MensajeProtocolo DELETEL() throws RemoteException;
    MensajeProtocolo DELETED() throws RemoteException;
    MensajeProtocolo NOTAUTH() throws RemoteException;
    MensajeProtocolo ERROR() throws RemoteException;
}
