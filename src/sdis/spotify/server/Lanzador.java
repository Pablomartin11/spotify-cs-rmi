package sdis.spotify.server;

import sdis.spotify.common.Spotify;
import sdis.spotify.common.SpotifyServer;

public class Lanzador {
    public static void main(String [] args){
        try{

            //System.setProperty("java.rmi.server.hostname", "172.20.10.13");
            // Declaracion de objetos remotos
            SpotifyServerImpl spotify = new SpotifyServerImpl();

            // Accedemos a una referencia al registro (rmiregistry) local

            //Cresmos el RMIRegistry
            java.rmi.registry.Registry registro = java.rmi.registry.LocateRegistry.createRegistry(1099);

            // Bindear objetos remotos a registry
            registro.rebind("spotify", spotify);

            System.out.println("Servidor Spotify operativo");
        } catch (Exception e){
            System.err.println("Excepción del servidor: "+e.toString());
            e.printStackTrace();
        }
    }
}
