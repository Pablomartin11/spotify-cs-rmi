package sdis.spotify.client.unit;

import sdis.spotify.common.Spotify;
import sdis.spotify.common.Media;

public class ClientAdd2L {
    public static void main(String [] arg) {

        try{
            Spotify s = (Spotify) java.rmi.Naming.lookup("spotify");
            String r = s.hello();
            System.out.println(r);

            Media o1 = new Media("Esclava remix");
            o1.addLike();
            o1.addLike();
            o1.tagAdultContent(true);
            o1.addComment("Esta bien duro manin");
            o1.addScore(7);
            o1.addScore(7.6);
            o1.loadCover("./Cover/EsclavaRemix.jpg");




            Media o2 = new Media("El nano");
            o2.addLike();
            o2.addLike();
            o2.addLike();
            o2.addLike();
            o2.tagAdultContent(false);
            o2.addComment("Laaaa 33 se vieneee");
            o2.addComment("Tomas sabe cosas");
            o2.addScore(10);
            o2.addScore(9);
            o2.addScore(9);
            o2.loadCover("./Cover/Elnano.jpg");



            Media o3 = new Media("Kemba walker");
            o3.addLike();
            o3.tagAdultContent(false);
            o3.addComment("Soy de la H");
            o3.addScore(7.9);
            o3.addScore(8.3);
            o3.loadCover("./Cover/KembaWalker.jpg");

            s.add2L(o1);
            s.add2L(o2);
            s.add2L(o3);


            System.out.println(o1.toString());
            System.out.println(o2.toString());
            System.out.println(o3.toString());



        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
