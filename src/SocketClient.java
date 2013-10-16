import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SocketClient {


    static void readLargerTextFileAlternate(String aFileName) throws IOException {
        Path path = Paths.get(aFileName);
        try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"))){
            String line = null;
            while ((line = reader.readLine()) != null) {
            }
        }
    }


    public static void main(String args[]) throws IOException {

        byte consoleInputBytes[] = new byte[256];
        Socket s = new Socket("localhost", 9999);
        InputStream is = s.getInputStream();
        OutputStream os = s.getOutputStream();
        byte buf[];
        int length;
        String wordToSend;
        String wordReceived;
        StringTokenizer sendTokenizer;

        System.out.println("Socket Client Application\nEnter any string or 'quit' to exit...");

        while (true) {
            length = System.in.read(consoleInputBytes);
            if (length != 1) {

                sendTokenizer = new StringTokenizer(new String(consoleInputBytes, 0), "\r\n");
                wordToSend = ((String) sendTokenizer.nextElement()).intern();

                if(wordToSend.equals("quit"))break;

                System.out.println(">  " + wordToSend);

                os.write(consoleInputBytes, 0, length);
                os.flush();

                buf = new byte[512];
                length = is.read(buf);
                if (length == -1)
                    break;

                sendTokenizer = new StringTokenizer(new String(buf, 0), ""+(char)0);
                wordReceived = ((String) sendTokenizer.nextElement()).intern();
                System.out.println(">> " + wordReceived);

            }
        }
        is.close();
        os.close();
        s.close();
    }
}