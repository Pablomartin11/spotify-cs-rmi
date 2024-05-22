package sdis.spotify.client.unit;

import sdis.spotify.client.ClientImpl;
import sdis.spotify.common.Spotify;
import sdis.spotify.common.SpotifyClient;
import sdis.spotify.common.SpotifyServer;
import sdis.spotify.media.Media;

public class ClientPlaySong {

    public static void main(String [] arg) {

        try{
            Spotify s = (Spotify) java.rmi.Naming.lookup("spotify");
            SpotifyServer server = (SpotifyServer) s;
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
