package sdis.spotify.client.unit;

import sdis.spotify.common.Spotify;
import sdis.spotify.media.Media;

public class ClientContinousReadL {
    public static void main(String [] arg) {

        try{
            Spotify s = (Spotify) java.rmi.Naming.lookup("spotify");
            String r = s.hello();
            System.out.println(r);

            int vacio=0;
            while(vacio==0){
                try{
                    Media o1 = s.readL("Playlist1");
                    System.out.println(o1.getName());

                }catch (Exception e){
                    vacio =1;
                }

            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
