package sdis.spotify.stream;

import sdis.spotify.media.Globals;
import sdis.spotify.media.Media;
import java.io.*;
import java.net.Socket;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLSocket;

/**
 * Stream class ClientStream
 * @author hector
 */
public class ClientStream implements Runnable{
    private String serverStreaming;
    private int serverStreamingPort;
    private Thread activeMediaPlayer;
    private String FILE_TO_RECEIVE;
    private int FILE_PACKET_SIZE;
    
    public ClientStream(Media media, String server, int port, Thread player){
        this.FILE_TO_RECEIVE = Globals.path_destination+media.getName()+
                Globals.file_extension;
        this.FILE_PACKET_SIZE = Globals.tx_packet_size_bytes;
        this.serverStreaming = server;
        this.serverStreamingPort = port;
        this.activeMediaPlayer = player;
    }
    public void run(){
        int bytesRead;
        int currentBytes = 0;
        SSLSocket sock = null;
        InputStream is = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            System.out.println(">> Stream connecting to "+serverStreaming+":"+serverStreamingPort);
            SSLSocketFactory factoria = (SSLSocketFactory) SSLSocketFactory.getDefault();
            sock = (SSLSocket) factoria.createSocket(serverStreaming,serverStreamingPort);

            is = sock.getInputStream();
            File file = new File(FILE_TO_RECEIVE);
            file.getParentFile().mkdirs();
            file.createNewFile(); // if file already exists will do nothing
// receive file
            byte [] mybytearray = new byte [FILE_PACKET_SIZE];
            fos = new FileOutputStream(FILE_TO_RECEIVE);
            bos = new BufferedOutputStream(fos);
            while((bytesRead = is.read(mybytearray)) > 0 && (activeMediaPlayer.isAlive()))
            {
                System.err.print(String.format("\033[%dA",1)); // Move up
                System.err.print("\033[2K"); // Erase line content
                System.err.print("Rcv " + bytesRead + "(pos:" + currentBytes + ") ");
                bos.write(mybytearray, 0, bytesRead);
                currentBytes += bytesRead;
                System.err.println(">> Written " + bytesRead + "(" + currentBytes + "bytes)");
            }
            bos.flush();
            System.out.println(">> File " + FILE_TO_RECEIVE
                    + " downloaded (" + currentBytes + " bytes read)");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
                bos.close();
                fos.close();
                sock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ClientStream closed");
    }
}