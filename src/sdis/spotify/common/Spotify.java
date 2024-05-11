package sdis.spotify.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sdis.spotify.media.Media;

public interface Spotify extends Remote{
    String hello() throws RemoteException;
    String auth(String username, String password) throws RemoteException;
    void add2L(Media elemento) throws RemoteException;
    void add2L(String playlist, Media elemento) throws RemoteException;
    Media readL() throws RemoteException;
    Media readL(String playlist) throws RemoteException;
    Media peekL() throws RemoteException;
    Media peekL(String playlist) throws RemoteException;
    String deleteL(String playlist) throws RemoteException;
    String getDirectoryList() throws RemoteException;
    Media retrieveMedia(String elemento) throws RemoteException;
    String setCover(Media objeto) throws RemoteException;
    String addScore(String elemento, double score) throws RemoteException;
    String addComment(String elemento, String comentario) throws RemoteException;

}
