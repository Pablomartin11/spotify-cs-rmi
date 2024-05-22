package sdis.spotify.client.unit;

import sdis.spotify.common.Spotify;
import sdis.spotify.media.Media;

public class ClientPeekL {
    public static void main(String [] arg) {

        try{
            Spotify s = (Spotify) java.rmi.Naming.lookup("spotify");
            String r = s.hello();
            System.out.println(r);

            Media o1=s.peekL();
            Media o2=s.peekL();
            Media o3=s.peekL("Playlist1");

            System.out.println(o1.toString());
            System.out.println(o2.toString());
            System.out.println(o3.toString());


        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
