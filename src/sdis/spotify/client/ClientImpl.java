package sdis.spotify.client;

import java.rmi.RemoteException;
import sdis.spotify.media.Globals;
import sdis.spotify.media.Media;
import sdis.spotify.media.MediaPlayer;
import sdis.spotify.common.SpotifyClient;

public class ClientImpl extends java.rmi.server.UnicastRemoteObject implements SpotifyClient {
    private Thread playerThread;

    /**
     * Constructor del cliente.
     * @throws RemoteException
     */
    protected ClientImpl() throws RemoteException {
        super();
    }

    /**
     * Lanzar el reproductor multimedia en el lado del cliente.
     * @param cancion que se quiere reproducir.
     * @return Reproductor lanzado correctamente.
     * @throws RemoteException
     */
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

    /**
     * Comprobar si el reproductor está activo.
     * @return Reproductor activo.
     * @throws RemoteException
     */
    public boolean isMediaPlayerActive() throws RemoteException {
        return playerThread.isAlive();
    }

    /**
     * Iniciar la transmisión de bytes que conforman el streaming de la canción.
     * @param media que se quiere reproducir.
     * @param ip por el medio del cual se realiza la transmisión de bytes.
     * @param puerto
     * @throws RemoteException
     */
    public void startStream(Media media, String ip, int puerto) throws RemoteException {
        ClientStream cs = new ClientStream();
        new Thread(cs, "clientstream").start();
    }
    
}
