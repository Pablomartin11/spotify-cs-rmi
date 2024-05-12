package sdis.spotify.client.unit;

import sdis.spotify.common.Spotify;
import sdis.spotify.media.Media;
import java.util.Scanner;
public class Client {
    public static void main(String [] arg) {

        Scanner scanner = new Scanner(System.in);


        try{
            Spotify s = (Spotify) java.rmi.Naming.lookup("spotify");

            String r = s.hello();

            System.out.println(r);

            int salida= 0;

            while(salida==0){

                System.out.println("Ingrese una opción:");
                System.out.println("1. login");
                System.out.println("2. add-to-list");
                System.out.println("3. read-from-list");
                System.out.println("4. Salir");
                System.out.print("Opción: ");

                String opcion = scanner.nextLine();
                //int opcion = Integer.parseInt(comando);

                System.out.println();

                switch (opcion) {
                    case "1":
                        System.out.println("Ha seleccionado la Opción 1.");
                        break;
                    case "2":
                        System.out.println("Ha seleccionado la opcion add-to-list");
                        System.out.println("Introduzca el nombre de la canción");
                        String nom = scanner.nextLine();
                        Media cancion = new Media(nom);
                        s.add2L(cancion);

                        break;
                    case "3":
                        System.out.println("Ha seleccionado la Opción 3.");
                        break;
                    case "4":
                        System.out.println("Saliendo...");
                        salida=1;
                        break;
                    default:


                        System.out.println("Opción inválida.");
                }


            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
