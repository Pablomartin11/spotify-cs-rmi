package sdis.spotify.client;

import java.rmi.RemoteException;

import sdis.spotify.common.Globals;
import sdis.spotify.common.Media;
import sdis.spotify.common.MediaPlayer;

public class ClientImpl extends java.rmi.server.UnicastRemoteObject implements sdis.spotify.common.SpotifyClient {
    protected ClientImpl() throws RemoteException {
        super();
    }

    private Thread playerThread;

    public boolean launchMediaPlayer(Media cancion) throws RemoteException {
        try{
            MediaPlayer mediaplayer = new MediaPlayer(
                Globals.player_command,
                Globals.player_abs_filepath+cancion.getName()+Globals.file_extension,
                Globals.player_delay_ms
            );

            playerThread = new Thread(mediaplayer);
            playerThread.start();
            return true;
        } catch(Exception e){ 
            e.printStackTrace();
            return false;
        }
    }

    public boolean isMediaPlayerActive() throws RemoteException {
        return playerThread.isAlive();
    }

    public void startStream(Media media, String ip, int puerto) throws RemoteException {
        ClientStream cs = new ClientStream();
        new Thread(cs, "clientstream").start();
    }
    
}
