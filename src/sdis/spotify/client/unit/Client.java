package sdis.spotify.client.unit;

import sdis.spotify.common.Spotify;
import sdis.spotify.media.Media;
import java.util.Scanner;
public class Client {
    public static void main(String [] arg) {

        Scanner scanner = new Scanner(System.in);


        try{
            //Bienvenida
            Spotify s = (Spotify) java.rmi.Naming.lookup("spotify");
            String r = s.hello();
            System.out.println(r);
            //Registro
            r = s.auth("alex","PPP");
            System.out.println(r);
            r = s.auth("hector","1234");
            System.out.println(r);
            //Añadir Canciones y Playlist
            Media o1 = new Media("Cancion 1");
            Media o2 = new Media("Cancion 2");
            Media o3 = new Media("Cancion 3");
            s.add2L(o1);
            s.add2L(o2);
            s.add2L("Playlist1",o1);
            s.add2L("Playlist1",o2);
            s.add2L("Playlist1",o3);
            //Leer cancion destructiva
            Media c = s.readL();
            System.out.println("Cancion leida y eliminada: "+c.getName());
            c = s.readL("Playlist1");
            System.out.println("Cancion leida y eliminada: "+c.getName());
            //Leer cancion no destructiva
            c = s.peekL();
            System.out.println("Cancion leida: "+c.getName());
            c = s.peekL("Playlist1");
            System.out.println("Cancion leida: "+c.getName());
            //Borrar playlist
            r = s.deleteL("Playlist1");
            System.out.println(r);
            r = s.deleteL("Playlist1");
            System.out.println(r);
            //get-directory-list
            r = s.getDirectoryList();
            System.out.println(r);
            //recuperar elemento del directorio
            c = s.retrieveMedia("Cancion 1");
            System.out.println("Cancion recuperada: "+c.getName());
            r = s.getDirectoryList();
            System.out.println(r);
            //Cambiar caratula
            //TODO

            //añadir puntuacion
            r = s.addScore("Cancion 1",9.8);
            System.out.println(r);
            r = s.addScore("Cancion 2",10.01);
            System.out.println(r);

            //Añadir comentario
            r = s.addComment("Cancion 1","buena cancion");
            System.out.println(r);
            r = s.addComment("Cancion 1","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            System.out.println(r);


        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
