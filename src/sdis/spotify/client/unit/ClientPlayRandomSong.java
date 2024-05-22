package sdis.spotify.client.unit;

import sdis.spotify.client.ClientImpl;
import sdis.spotify.common.Spotify;
import sdis.spotify.common.SpotifyClient;
import sdis.spotify.common.SpotifyServer;
import sdis.spotify.media.Media;

public class ClientPlayRandomSong {

    public static void main(String [] arg) {

        try{
            Spotify s = (Spotify) java.rmi.Naming.lookup("spotify");
            SpotifyServer server = (SpotifyServer) s;
            String r = s.hello();
            System.out.println(r);

            SpotifyClient cliente = new ClientImpl();
            System.out.println(server.setClientStreamReceptor(cliente));
            System.out.println(server.randomPlay());

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
