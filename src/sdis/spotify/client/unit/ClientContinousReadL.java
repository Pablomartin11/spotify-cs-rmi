package sdis.spotify.client.unit;

import sdis.spotify.common.Spotify;
import sdis.spotify.common.SpotifyServer;
import sdis.spotify.media.Media;

public class ClientContinousReadL {
    public static void main(String [] arg) {

        try{
            javax.rmi.ssl.SslRMIClientSocketFactory rmicsf = new javax.rmi.ssl.SslRMIClientSocketFactory();
            java.rmi.registry.Registry reg = java.rmi.registry.LocateRegistry.getRegistry("localhost", 1099, rmicsf);
            Object remoto = reg.lookup("spotify");
            Spotify s = (Spotify) remoto;
            SpotifyServer server = (SpotifyServer) remoto;

            String r = s.hello();
            System.out.println(r);

            int vacio=0;
            while(vacio==0){
                try{
                    Media o1 = s.readL("Playlist1");
                    System.out.println(o1.getName());

                }catch (Exception e){
                    vacio =1;
                }

            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
