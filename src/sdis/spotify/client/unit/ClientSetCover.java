package sdis.spotify.client.unit;

import sdis.spotify.common.Spotify;
import sdis.spotify.common.SpotifyServer;
import sdis.spotify.media.Media;
import java.util.Scanner;
public class ClientSetCover {

    public static void main(String [] arg) {
        Scanner scanner = new Scanner(System.in);
        try{
            //System.setProperty("java.rmi.server.hostname","172.20.10.5");
            // Conexion
            //Object remoto = java.rmi.Naming.lookup("rmi://172.20.10.13:1099/spotify");
            Object remoto = java.rmi.Naming.lookup("spotify");
            Spotify s = (Spotify) remoto;

            SpotifyServer server = (SpotifyServer) remoto;

            // Bienvenida
            String r = s.hello();
            System.out.println(r);


            r = s.getDirectoryList();
            System.out.println(r);

            Media o1 = new Media("Suavemente");
            o1.loadCover("./cover/Suavemente.jpg");
            s.setCover(o1);



            o1=s.retrieveMedia(o1.getName());
            System.out.println(o1.toString());






        }catch(Exception e){
            e.printStackTrace();
        }finally{
            scanner.close();;
        }

    }
}
