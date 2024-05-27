package sdis.spotify.client.unit;

import sdis.spotify.common.Spotify;
import sdis.spotify.common.SpotifyServer;
import sdis.spotify.media.Media;

public class ClientPeekL {
    public static void main(String [] arg) {

        try{
            javax.rmi.ssl.SslRMIClientSocketFactory rmicsf = new javax.rmi.ssl.SslRMIClientSocketFactory();
            java.rmi.registry.Registry reg = java.rmi.registry.LocateRegistry.getRegistry("localhost", 1099, rmicsf);
            Object remoto = reg.lookup("spotify");
            Spotify s = (Spotify) remoto;
            SpotifyServer server = (SpotifyServer) remoto;

            String r = s.hello();
            System.out.println(r);

            Media o1=s.peekL();
            Media o2=s.peekL();
            Media o3=s.peekL("Playlist1");

            System.out.println(o1.toString());
            System.out.println(o2.toString());
            System.out.println(o3.toString());


        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
