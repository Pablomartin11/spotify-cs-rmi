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
                    System.out.println("Siguiente canción: "+o1.getName());
                    System.out.println("Pulse 1 para escuchar la cancion \nPulse 2 para saltar \nPulse 3 para salir");

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
