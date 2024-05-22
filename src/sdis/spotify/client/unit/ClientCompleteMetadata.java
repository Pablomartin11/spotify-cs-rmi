package sdis.spotify.client.unit;

import sdis.spotify.common.Spotify;
import sdis.spotify.media.Media;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClientCompleteMetadata {

    public static void main(String [] arg) {

        try{
            Spotify s = (Spotify) java.rmi.Naming.lookup("spotify");
            String r = s.hello();
            System.out.println(r);

            String[] canciones = s.getDirectoryList().split(", ");

            r = s.getDirectoryList();
            System.out.println(r);

            // Crear una lista de 20 comentarios
            List<String> comments = new ArrayList<>();
            comments.add("¡Qué canción tan increíble!");
            comments.add("Esta canción me trae tantos recuerdos.");
            comments.add("La letra de esta canción es muy profunda.");
            comments.add("El ritmo de esta canción es contagioso.");
            comments.add("Me encanta la voz del cantante.");
            comments.add("El solo de guitarra es espectacular.");
            comments.add("Esta canción siempre me pone de buen humor.");
            comments.add("Una verdadera obra maestra.");
            comments.add("El video musical es impresionante.");
            comments.add("La producción de esta canción es impecable.");
            comments.add("Me encanta la melodía.");
            comments.add("Esta canción es perfecta para relajarse.");
            comments.add("La escucho en repetición todo el día.");
            comments.add("Los coros son realmente armoniosos.");
            comments.add("Es mi canción favorita de todos los tiempos.");
            comments.add("La energía de esta canción es contagiosa.");
            comments.add("Los arreglos musicales son muy creativos.");
            comments.add("Me encanta la letra de esta canción.");
            comments.add("Es perfecta para cualquier ocasión.");
            comments.add("Siempre me anima escuchar esta canción.");


            for (String can : canciones){
                Media o1 = s.retrieveMedia(can);

                // Crear una instancia de Random
                Random rand = new Random();
                // Generar un número aleatorio entre 0 y 9
                int rn = rand.nextInt(10);
                for(int i=0;i<rn;i++) {
                    o1.addLike();
                    int randomIndex = rand.nextInt(20);
                    String randomComment = comments.get(randomIndex);
                    o1.addComment(randomComment);
                    o1.addScore(rn+rand.nextInt(-2,2));
                }
                s.add2L(o1);
            }


            for (String can : canciones) {
                Media o1 = s.retrieveMedia(can);
                System.out.println(o1.toString());
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
