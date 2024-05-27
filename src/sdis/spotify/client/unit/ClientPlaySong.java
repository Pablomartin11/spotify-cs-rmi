package sdis.spotify.client.unit;

import sdis.spotify.client.ClientImpl;
import sdis.spotify.common.Spotify;
import sdis.spotify.common.SpotifyClient;
import sdis.spotify.common.SpotifyServer;
import sdis.spotify.media.Media;

public class ClientPlaySong {

    public static void main(String [] arg) {

        try{
            javax.rmi.ssl.SslRMIClientSocketFactory rmicsf = new javax.rmi.ssl.SslRMIClientSocketFactory();
            java.rmi.registry.Registry reg = java.rmi.registry.LocateRegistry.getRegistry("localhost", 1099, rmicsf);
            Object remoto = reg.lookup("spotify");

            Spotify s = (Spotify) remoto;
            SpotifyServer server = (SpotifyServer) remoto;

            String r = s.hello();
            System.out.println(r);

            Media o1 = new Media("Kemba_Walker");

            SpotifyClient cliente = new ClientImpl();
            System.out.println(server.setClientStreamReceptor(cliente));
            System.out.println(server.startMedia(o1));

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
