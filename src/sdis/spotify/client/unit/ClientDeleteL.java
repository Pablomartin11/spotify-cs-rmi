package sdis.spotify.client.unit;

import sdis.spotify.common.Spotify;
import sdis.spotify.media.Media;

public class ClientDeleteL {
    public static void main(String [] arg) {

        try{
            Spotify s = (Spotify) java.rmi.Naming.lookup("spotify");
            String r = s.hello();
            System.out.println(r);

            Media o1 = new Media("Kemba_Walker");
            Media o2 = new Media("Suavemente");


           s.add2L("Play1",o1);
           s.add2L("Play1",o2);


           r=s.deleteL("Play1");
           System.out.println(r);

            r=s.deleteL("Play1");
            System.out.println(r);

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
