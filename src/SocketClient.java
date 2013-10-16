import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        byte bKbdInput[] = new byte[256];
        Socket s;
        InputStream is;
        OutputStream os;

        System.out.println(
                "Socket Client Application" +
                        "\nEnter any string or" +
                        " 'quit' to exit...");


        s = new Socket("localhost", 9999);
        is = s.getInputStream();
        os = s.getOutputStream();
        byte buf[] = new byte[512];
        int length;
        String str;

        while (true) {
            length = System.in.read(bKbdInput);
            if (length != 1) {
                str = new String(bKbdInput, 0);

                StringTokenizer st;
                st = new StringTokenizer(
                        str, "\r\n");
                str = ((String) st.nextElement()).intern();

                System.out.println(">  " + str);

                os.write(bKbdInput, 0, length);
                os.flush();

                length = is.read(buf);
                if (length == -1)
                    break;

                str = new String(buf, 0);
                st = new StringTokenizer(
                        str, "\r\n");
                str = ((String) st.nextElement()).intern();
                System.out.println(">> " + str);

                if (str.equals("quit"))
                    break;
            }
        }
        is.close();
        os.close();
        s.close();
    }
}