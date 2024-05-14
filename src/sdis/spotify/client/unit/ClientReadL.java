package sdis.spotify.client.unit;

import sdis.spotify.common.Spotify;
import sdis.spotify.common.Media;

public class ClientReadL {
    public static void main(String [] arg) {

        try{
            Spotify s = (Spotify) java.rmi.Naming.lookup("spotify");
            String r = s.hello();
            System.out.println(r);

            Media o1=s.readL();
            Media o2=s.readL();
            Media o3=s.readL();

            System.out.println(o1.toString());
            System.out.println(o2.toString());
            System.out.println(o3.toString());


        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
