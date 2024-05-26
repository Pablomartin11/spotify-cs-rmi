package sdis.spotify.stream;

import sdis.spotify.media.Globals;
import sdis.spotify.common.SpotifyClient;
import sdis.spotify.server.Utils;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;

/**
 * Stream class ServerStream
 * @author hector
 */
public class ServerStream implements Runnable{
    private String fileToSend;
    private int serverSocketPort;
    private SpotifyClient client;
    private final String streamLog = Globals.log_path+"streams"+Globals.log_extension;
    public ServerStream(String fileToSend, SpotifyClient client){
        this.fileToSend = fileToSend;
        this.client = client;
    }
    public int getServerSocketPort() {
        return serverSocketPort;
    }
    public void run(){
        try {
            SSLServerSocketFactory factoriaServer =
                    (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket socketServidor =
                    (SSLServerSocket) factoriaServer.createServerSocket(Globals.server_port);
            this.serverSocketPort = socketServidor.getLocalPort();
            System.out.println("--Stream Waiting...");
            SSLSocket sock = (SSLSocket) socketServidor.accept();

            System.out.println("--Accepted connection : " + sock+"\n");
            Utils.logMsg(streamLog, Utils.nowDate()+Globals.log_stream+sock);
            OutputStream os = null;
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                os = sock.getOutputStream();
// send file
                File myFile = new File(this.fileToSend);
                byte[] mybytearray = new byte[Globals.tx_packet_size_bytes];
                fis = new FileInputStream(myFile);
                bis = new BufferedInputStream(fis);
                int count, i = 0, total = 0;
                while ((count = bis.read(mybytearray)) > 0 && client.isMediaPlayerActive()) {
                    System.err.print(String.format("\033[%dA",1)); // Move up
                    System.err.print("\033[2K"); // Erase line content
                    System.err.println("--" + i + ": Sending " + count + "bytes (" + total + ")");
                    os.write(mybytearray, 0, count);
                    Thread.sleep(Globals.delivery_delay_ms);
                    i++;
                    total += count;
                }
                os.flush();
                System.out.println("--Done. (Sent total: " + total + ")");
                Utils.logMsg(streamLog, Utils.nowDate()+Globals.log_stream_end+total);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }finally {
                os.close();
                fis.close();
                bis.close();
                socketServidor.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

