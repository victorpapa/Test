import java.io.*;
import java.net.Socket;

public class MainClass{

    private static Socket socket;
    private final static int DEFAULT_PORT = 79;
    private final static int BUFF_SIZE = 1024;
    private final static String HOST = "hermes.cam.ac.uk";

    private static void initSocket(){
        try {
            System.out.println("Connecting...");
            socket = new Socket(HOST, DEFAULT_PORT);
            System.out.println("Successfully connected to " + HOST + " on port " + DEFAULT_PORT + "!");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private static class fingerServer extends Thread{
        @Override
        public void run(){
            try {
                char[] buffer = new char[BUFF_SIZE];
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                int bytesRead = in.read(buffer, 0, BUFF_SIZE);

                if (bytesRead >= 0) {
                    System.out.println(new String(buffer, 0, bytesRead));
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private static void runServer(){
        Thread fingerServer = new fingerServer();
        fingerServer.start();
    }

    private static void getFinger() throws IOException {

        initSocket();

        BufferedWriter w;
        BufferedReader r;

        w = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        r = new BufferedReader(new InputStreamReader(System.in));

        runServer();

        w.write(r.readLine() + "\r\n");
        w.flush();
    }

    public static void main(String[] args) {
        try{
            getFinger();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}