package sdis.spotify.server;

import java.io.FileNotFoundException;
import java.net.ServerSocket;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import sdis.spotify.common.Spotify;
import sdis.spotify.common.SpotifyClient;
import sdis.spotify.common.SpotifyServer;
import sdis.spotify.media.Globals;
import sdis.spotify.media.Media;
import sdis.spotify.stream.ServerStream;


public class SpotifyImpl extends java.rmi.server.UnicastRemoteObject implements Spotify, SpotifyServer{
    
    ConcurrentHashMap<String,Media> directorio = new ConcurrentHashMap<>();
    MultiMap<String,Media> contenido = new MultiMap<>();
    private SpotifyClient cliente;

    // Hashmap concurrente de credenciales
    private static final ConcurrentHashMap<String, String> credenciales = new ConcurrentHashMap<>();
    static{
        credenciales.put("hector", "1234");
        credenciales.put("sdis","asdf");
    }

    /*
     * Constructor del servidor.
     */
    public SpotifyImpl() throws RemoteException{
        super();
    }

    /**
     * Método de bienvenida al servidor, simplemente se retorna una bienvenida.
     * @return Bienvenida al servidor.
     * @throws RemoteException
     */
    public String hello() throws RemoteException{
        try {
            System.out.println(this.getClientHost()+"-> ping-check-server");
        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
        return Globals.hello_banner;
    }

    /**
     * Método de identificación del usuario.
     * @param username del usuario.
     * @param password del usuario.
     * @return "AUTH" / "NOTAUTH" en función de si se ha identificado con éxito o no.
     * @throws RemoteException
     */
    public String auth(String username, String password) throws RemoteException {
        String res ;
        if (validateCredentials(username,password)){
            res= "AUTH";
        }
        else{
            res =  "NOTAUTH";
        }
        try {
            System.out.println(this.getClientHost()+"-> login :"+res);
        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Añadir un objeto Media a la playlist "DEFAULT" y al directorio.
     * @param elemento media a añadir.
     * @throws RemoteException
     */
    public void add2L(Media elemento) throws RemoteException {
        String playlist = "DEFAULT";
        contenido.push(playlist, elemento);
        directorio.put(elemento.getName(), elemento);
        try {
            System.out.println(this.getClientHost()+"-> Canción: "+elemento.getName()+" añadida a Playlist: "+playlist);
        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
    }

    /**
     * Añadir un elemento Media a una playlist y al directorio
     * @param playlist donde añadir el elemento.
     * @param elemento media a añadir a la playlist.
     * @throws RemoteException
     */
    public void add2L(String playlist, Media elemento) throws RemoteException {
        directorio.put(elemento.getName(), elemento);
        contenido.push(playlist, directorio.get(elemento.getName()));
        
        try {
            System.out.println(this.getClientHost()+"-> Canción: "+elemento.getName()+" añadida a Playlist: "+playlist);

        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
    }

    /**
     * Leer media de manera destructiva (eliminando el objeto) de la playlist "DEFAULT".
     * @return El objeto media extraido o null.
     * @throws RemoteException
     */
    public Media readL() throws RemoteException {
        String playlist = "DEFAULT";
        Media elem = contenido.pop(playlist);

        try {
            System.out.println(this.getClientHost()+"-> Canción: "+elem.getName()+" leida y eliminada de Playlist: "+playlist);

        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }

        return elem;
    }

    /**
     * Leer media de manera destructiva (eliminando el objeto) de una playlist.
     * @param playlist a obtener el objeto media.
     * @return El objeto media o null.
     * @throws RemoteException
     */
    public Media readL(String playlist) throws RemoteException {
        Media elem = contenido.pop(playlist);

        try {
            System.out.println(this.getClientHost()+"-> Canción: "+elem.getName()+" leida y eliminada de Playlist: "+playlist);

        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
        return elem;
    }

    /**
     * Leer media de manera no destructiva, sin eliminar el objeto de la playlist "DEFAULT".
     * @return el elemento media o null.
     * @throws RemoteException
     * @throws ServerNotActiveException 
     */
    public Media peekL() throws RemoteException {
        String playlist = "DEFAULT";

        Media elem = contenido.peek(playlist);

        try {
            System.out.println(this.getClientHost()+"-> Canción: "+elem.getName()+" leida de Playlist: "+playlist);

        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }

        return elem;
    }

    /**
     * Leer media de manera no destructiva, sin eliminar el objeto de la playlist dada.
     * @param playlist a obtener el objeto media.
     * @return El objeto media o null.
     * @throws RemoteException
     * @throws ServerNotActiveException
     */
    public Media peekL(String playlist) throws RemoteException {
        Media elem = contenido.peek(playlist);
        try {
            System.out.println(this.getClientHost()+"-> Canción: "+elem.getName()+" leida de Playlist: "+playlist);

        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
        return elem;
    }

    /**
     * Borrar una playlist.
     * @param playlist a borrar.
     * @return "DELETED" si eliminada o "EMPTY" si no existe la playlist o en caso de que no se borre la playlist.
     * @throws RemoteException
     * @throws ServerNotActiveException
     */
    public String deleteL(String playlist) throws RemoteException {
        String res;
        boolean borrado = false;
        //Se comprueba que la clave existe
        if(contenido.containsKey(playlist)){
            borrado = contenido.remove(playlist);

            if(!borrado) res= "EMPTY";    // Si no te consigue eliminar
            else res= "DELETED";
        }else {
            res= "EMPTY";
        }
        try {
            System.out.println(this.getClientHost()+"-> delete-list "+playlist+" : "+res);

        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Obtener el contenido del directorio global de elementos en forma de String.
     * @return Todo el contenido concatenado en un String.
     * @throws RemoteException
     * @throws ServerNotActiveException
     */
    public String getDirectoryList() throws RemoteException {
        Set<String> keys = this.directorio.keySet();
        String keysAsString = String.join(", ", keys);
        try {
            System.out.println(this.getClientHost()+"-> get-directory-list");

        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
        return keysAsString;
    }

    /**
     * Permite recuperar un elemento del directorio.
     * @param elemento, ID del elemento en el directorio.
     * @return objeto media deseado.
     * @throws RemoteException
     * @throws ServerNotActiveException
     */
    public Media retrieveMedia(String elemento) throws RemoteException {
        try {
            System.out.println(this.getClientHost()+"-> retrieve-directory-element-media: "+elemento);

        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
        return this.directorio.get(elemento);
    }

    /**
     * Permite cambiar la carátula/imagen/cover predefinida del elemento,
     * es necesario que la canción esté añadida en el directorio global.
     * @param objeto media con la imagen deseada ya cargada.
     * @return "COVER ADDED" / "COVER NOT ADDED" en función si la operación ha sido realizada con éxito.
     */
    public String setCover(Media objeto) throws RemoteException {
        if (this.directorio.containsKey(objeto.getName())){
            this.directorio.remove(objeto.getName());
            this.directorio.put(objeto.getName(), objeto);
            return "COVER ADDED";
        } else return "COVER NOT ADDED";
    }

    /**
     * Añadir una puntuación 0-10 al objeto media guardado en el directorio.
     * Es necesario que la canción se encuentre en el directorio.
     * @param elemento canción.
     * @param score [0,10] que queremos añadir.
     * @throws remoteException
     * @return "SCORE ADDED" si se añadió correctamente, "NOT A SCORE" si no está [0,10], "MEDIA NOT IN DIRECTORY" si la canción no está en el directorio
     */
    public String addScore(String elemento, double score) throws RemoteException{
        String res;

        if (this.directorio.containsKey(elemento)){
            if (comprobarScore(score)){
                this.directorio.get(elemento).addScore(score);
                res= "SCORE ADDED";
            } else res= "NOT A SCORE";
        } else res = "MEDIA NOT IN DIRECTORY";
        try {
            System.out.println(this.getClientHost()+"-> add-score-to-directory-element-media "+elemento+": "+res);

        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Añadir un comentario al objeto media guardado en el directorio.
     * Es necesario que la canción se encuentre en el directorio.
     * @param elemento canción.
     * @param comentario que queremos añadir.
     * @throws remoteException
     * @return "COMMENT ADDED" si se añadió correctamente, "NOT ALLOWED COMMENT" si la longitud del comentario supera los 100 caracteres, "MEDIA NOT IN DIRECTORY" si la canción no está en el directorio
     
     */
    public String addComment(String elemento, String comentario) throws RemoteException{
        String res;

        if (this.directorio.containsKey(elemento)){
            if (comprobarComentario(comentario)){
                this.directorio.get(elemento).addComment(comentario);
                res = "COMMENT ADDED";
            } else res =  "NOT ALLOWED COMMENT";
        } else res = "MEDIA NOT IN DIRECTORY";
        try {
            System.out.println(this.getClientHost()+"-> add-comment-to-directory-element-media "+elemento+": "+res);

        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
        return res;
    }

    
    // Métodos interfaz SpotifyServer
    /**
     * Asociar la interfaz cliente al servidor.
     * @param cliente interfaz.
     * @return Asignación realizada correctamente.
     * @throws RemoteException
     */
    public boolean setClientStreamReceptor(SpotifyClient cliente) throws RemoteException {
        this.cliente = cliente;
        return true;
    }

    /**
     * Indicar al servidor el deseo de escuchar una canción.
     * @return
     * @throws RemoteException
     */
    public String randomPlay() throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'randomPlay'");
    }

    public String startMedia(Media media) throws RemoteException, FileNotFoundException {
        // 1. CHECKS
        if (media == null) {
            throw new IllegalArgumentException("El objeto Media no puede ser null");
        }
        if (!directorio.containsKey(media.getName())) {
            throw new IllegalArgumentException("El objeto Media no existe en el directorio");
        }
        // 2. PREPARE A SERVERSOCKET FOR THE STREAMING
        String pathFile =Globals.path_origin+media.getName()+Globals.file_extension;
        ServerStream ss = new ServerStream(pathFile, cliente);
        new Thread(ss, "streamserver").start();

        try{ Thread.sleep(2000); }
        catch (InterruptedException e) { e.printStackTrace(); }

        // 3. LAUNCH CLIENT MEDIAPLAYER
        System.out.println("- Checking MediaPlayer status...");
        try {
            if (!cliente.launchMediaPlayer(media)) {
                return "Launcher cannot be triggered";
            }
        } catch (Exception e){
            e.printStackTrace();
            return "Error launching Media Player at client";
        }
        // 4. READY FOR STREAMING, PLEASE CLIENT GO GO GO
        System.out.println("- Sending server streaming ready signal..."+Globals.server_host+":"+ss.getServerSocketPort());
        try {
            cliente.startStream(media, Globals.server_host, ss.getServerSocketPort());
        } catch (Exception e){
            e.printStackTrace();
            return "Error during streaming at client";
        }
        return "MEDIA "+media.getName()+" started";
    }


    // Métodos privados
    private boolean comprobarComentario(String comentario) {
        return comentario.length() <= 100;
    }

    private boolean comprobarScore(double score) {
        if (score < 0) return false;
        else if(score > 10) return false;
        else return true;
    }

    private static boolean validateCredentials(String username, String password) {
        // Verifica si las credenciales proporcionadas coinciden con las almacenadas
        String storedPassword = credenciales.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }
}
