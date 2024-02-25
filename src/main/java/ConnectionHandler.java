import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ConnectionHandler implements Runnable{
    private Socket socket;
    private BufferedReader bufferedReader;
    private OutputStream bufferedWriter;

    public ConnectionHandler(Socket socket) {
        this.socket = socket;
        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = socket.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {

        String in = null;
        System.out.println("started...");
        try {
            while ((in = bufferedReader.readLine()) != null) {
                System.out.println("got it");
                System.out.println(in);
                if ("ping".equalsIgnoreCase(in)) {
                    bufferedWriter.write("PONG".getBytes());
                    bufferedWriter.write("\r\n".getBytes());
                    bufferedWriter.flush();
                }
            }

            System.out.println("leaving...");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
