package sdis.spotify.server;

import java.rmi.RemoteException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import sdis.spotify.common.Spotify;
import sdis.spotify.common.Strings;
import sdis.spotify.media.Media;
import sdis.spotify.utils.MultiMap;


public class SpotifyImpl extends java.rmi.server.UnicastRemoteObject implements Spotify{
    
    ConcurrentHashMap<String,Media> directorio = new ConcurrentHashMap<>();
    MultiMap<String,Media> contenido = new MultiMap<>();
    private static final ConcurrentHashMap<String, String> credenciales = new ConcurrentHashMap<>();
    static{
        credenciales.put("hector", "1234");
        credenciales.put("sdis","asdf");
    }

    /*
     * Constructor
     */
    public SpotifyImpl() throws RemoteException{
        super();
    }

    /**
     * Método de bienvenido al servidor, simplemente se retorna una bienvenida.
     * @throws RemoteException
     */
    public String hello() throws RemoteException{
        return Strings.MENSAJE_INICIO;
    }

    /**
     * Método de identificación del usuario.
     * @param username del usuario.
     * @param password del usuario.
     * @throws RemoteException
     * 
     */
    public String auth(String username, String password) throws RemoteException {
                      
        if (validateCredentials(username,password)){
            return "AUTH";
        }
        else{
            return "NOTAUTH";    
        }            
    }

    /**
     * Añadir un objeto Media a la playlist "DEFAULT" y al directorio.
     * @param elemento media a añadir.
     * @throws RemoteException
     */
    public void add2L(Media elemento) throws RemoteException {
        String playlist = "DEFAULT";
        contenido.push(playlist, elemento);
        directorio.put(playlist, elemento);
    }

    /**
     * Añadir un elemento Media a una playlist y al directorio
     * @param playlist donde añadir el elemento.
     * @param elemento media a añadir a la playlist.
     * @throws RemoteException
     */
    public void add2L(String playlist, Media elemento) throws RemoteException {
        contenido.push(playlist, elemento);
        directorio.put(playlist, elemento);
    }

    /**
     * Leer media de manera destructiva (eliminando el objeto) de la playlist "DEFAULT".
     * @return El objeto media extraido o null.
     * @throws RemoteException
     */
    public Media readL() throws RemoteException {
        String playlist = "DEFAULT";

        Media elem = contenido.pop(playlist);
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
        return elem;
    }

    /**
     * Leer media de manera no destructiva, sin eliminar el objeto de la playlist "DEFAULT".
     * @return el elemento media o null.
     * @throws RemoteException
     */
    public Media peekL() throws RemoteException {
        String playlist = "DEFAULT";

        Media elem = contenido.peek(playlist);
        return elem;
    }

    /**
     * Leer media de manera no destructiva, sin eliminar el objeto de la playlist dada.
     * @param playlist a obtener el objeto media.
     * @return El objeto media o null.
     * @throws RemoteException
     */
    public Media peekL(String playlist) throws RemoteException {
        Media elem = contenido.peek(playlist);
        return elem;
    }

    /**
     * Borrar una playlist.
     * @param playlist a borrar.
     * @return "DELETED" si eliminada o "EMPTY" si no existe la playlist o en caso de que no se borre la playlist.
     * @throws RemoteException
     */
    public String deleteL(String playlist) throws RemoteException {
        boolean borrado = false;
        //Se comprueba que la clave existe
        if(contenido.containsKey(playlist)){
            borrado = contenido.remove(playlist);

            if(!borrado) return "EMPTY";    // Si no te consigue eliminar
            else return "DELETED";
        }else {
            return "EMPTY";
        }
    }


    public String getDirectoryList() throws RemoteException {
        Set<String> keys = this.directorio.keySet();
        String keysAsString = String.join(", ", keys);
        return keysAsString;
    }


    public Media retrieveMedia(String elemento) throws RemoteException {
        return this.directorio.get(elemento);
    }


    public String setCover(Media objeto) throws RemoteException {
        return null;
    }

    public String addScore(String elemento, double score) throws RemoteException{
        boolean malFlag = comprobarScore(score);

        if (!malFlag){
            this.directorio.get(elemento).addScore(score);
            return "SCORE ADDED";
        } else return "NOT A SCORE";
    }

    public String addComment(String elemento, String comentario) throws RemoteException{
        boolean malFlag = comprobarComentario(comentario);

        if (!malFlag){
            this.directorio.get(elemento).addComment(comentario);
            return "COMMENT ADDED";
        } else return "NOT ALLOWED COMMENT";
    }

    private boolean comprobarComentario(String comentario) {
        if (comentario.length() > 100) return false;
        else return true;
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
