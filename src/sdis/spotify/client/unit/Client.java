package sdis.spotify.client.unit;

import sdis.spotify.client.ClientImpl;
import sdis.spotify.common.Spotify;
import sdis.spotify.common.SpotifyClient;
import sdis.spotify.common.SpotifyServer;
import sdis.spotify.media.Media;
import java.util.Scanner;
public class Client {
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

            // Registro
            while(!r.equals("AUTH")){
                System.out.print("Usuario:  ");
                String usr = scanner.next();

                System.out.print("Contraseña:  ");
                String pswd = scanner.next();
                r = s.auth(usr,pswd);
                System.out.println("Server > "+r+"\n");
            }
            
            // Añadir Canciones y Playlist
            Media o1 = new Media("Esclava_Remix");
            Media o2 = new Media("ElNano");
            //Media o3 = new Media("Cancion 3");
            s.add2L(o1);
            s.add2L(o2);
            s.add2L("Playlist1",o1);
            s.add2L("Playlist1",o2);
            //s.add2L("Playlist1",o3);

            // Leer cancion destructiva
            //Media c = s.readL();
            //System.out.println("Cancion leida y eliminada: "+c.getName());
            //c = s.readL("Playlist1");
            //System.out.println("Cancion leida y eliminada: "+c.getName());

            // Leer cancion no destructiva
            //c = s.peekL();
            //System.out.println("Cancion leida: "+c.getName());
            //c = s.peekL("Playlist1");
            //System.out.println("Cancion leida: "+c.getName());

            // Borrar playlist
            //r = s.deleteL("Playlist1");
            //System.out.println(r);
            //r = s.deleteL("Playlist1");
            //System.out.println(r);

            // get-directory-list
            r = s.getDirectoryList();
            System.out.println(r);

            // recuperar elemento del directorio
            //c = s.retrieveMedia("Esclava_Remix");
            //System.out.println("Cancion recuperada: "+c.getName());
            //r = s.getDirectoryList();
            //System.out.println(r);

            // Cambiar caratula
            //TODO

            // Añadir puntuacion
            r = s.addScore("Esclava_Remix",9.8);
            System.out.println(r);
            r = s.addScore("ElNano",10.01);
            System.out.println(r);

            // Añadir comentario
            r = s.addComment("Esclava_Remix","buena cancion");
            System.out.println(r);
            r = s.addComment("Esclava_Remix","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            System.out.println(r);

            // Iniciar cancion
            SpotifyClient cliente = new ClientImpl();
            System.out.println(server.setClientStreamReceptor(cliente));
            System.out.println(server.randomPlay());


        }catch(Exception e){
            e.printStackTrace();
        }finally{
            scanner.close();;
        }

    }
}
