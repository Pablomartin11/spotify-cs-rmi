package sdis.spotify.client.unit;

import sdis.spotify.common.Spotify;
import sdis.spotify.media.Media;

public class ClientAdd2L {
    public static void main(String [] arg) {

        try{
            Object remoto = java.rmi.Naming.lookup("spotify");
            Spotify s = (Spotify) remoto;


            String r = s.hello();
            System.out.println(r);

            Media o1 = new Media("Esclava_Remix");
            o1.addLike();
            o1.addLike();
            o1.tagAdultContent(true);
            o1.addComment("Esta bien duro manin");
            o1.addScore(7);
            o1.addScore(7.6);
            o1.loadCover("./cover/Esclava_Remix.jpg");

            Media o2 = new Media("ElNano");
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
            o2.loadCover("./cover/ElNano.jpg");

            Media o3 = new Media("Kemba_Walker");
            o3.addLike();
            o3.tagAdultContent(false);
            o3.addComment("Soy de la H");
            o3.addScore(7.9);
            o3.addScore(8.3);
            o3.loadCover("./cover/Kemba_Walker.jpg");

            Media o4 = new Media("Prendio");
            o4.tagAdultContent(true);
            o4.loadCover("./cover/Prendio.jpg");

            Media o5 = new Media("Suavemente");
            o5.tagAdultContent(false);

            s.add2L("Playlist1",o1);
            s.add2L("Playlist1",o2);
            s.add2L(o3);
            s.add2L(o4);
            s.add2L(o5);



        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
