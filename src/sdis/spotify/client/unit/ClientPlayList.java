package sdis.spotify.client.unit;

import sdis.spotify.client.ClientImpl;
import sdis.spotify.common.Spotify;
import sdis.spotify.common.SpotifyClient;
import sdis.spotify.common.SpotifyServer;
import sdis.spotify.media.Media;
import java.util.Scanner;

public class ClientPlayList {

    public static void main(String [] arg) {
        Scanner scanner = new Scanner(System.in);
        try{
            Spotify s = (Spotify) java.rmi.Naming.lookup("spotify");
            SpotifyServer server = (SpotifyServer) s;
            String r = s.hello();
            System.out.println(r);

            int vacio=0;
            while(vacio==0){
                try{
                    Media o1 = s.readL("Playlist1");
                    System.out.println("Siguiente canción: "+o1.getName());
                    String opcion = scanner.next();

                    switch (opcion) {
                        case "1":
                            SpotifyClient cliente = new ClientImpl();
                            System.out.println(server.setClientStreamReceptor(cliente));
                            System.out.println(server.startMedia(o1));
                            break;
                        case "2":
                            break;
                        case "3":

                        default:
                            break;
                    }

                    if (opcion.equals("3")) {
                        System.out.println("Cerrando programa...");
                        break;

                    }

                }catch (Exception e){
                    vacio =1;
                    System.out.println("No hay más canciones");
                }

            }


        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
