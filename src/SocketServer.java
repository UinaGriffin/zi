import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class SocketServer {
    public static void main(String args[]) throws IOException {
        ServerSocket serverSocket = null;
        Socket socket;
        InputStream is;
        OutputStream os;

        System.out.println("Socket Server Application");

        serverSocket = new ServerSocket(9999);
        socket = serverSocket.accept();
        is = socket.getInputStream();
        os = socket.getOutputStream();
        byte buf[] = new byte[512];
        int length;
       String result;


        while (true) {
            length = is.read(buf);
            if (length == -1)
                break;

            String str = new String(buf, 0);

            StringTokenizer st;
            st = new StringTokenizer(str, "\r\n");
            str = ((String) st.nextElement()).intern();

            result = str;

            System.out.println(">  " + result);
            os.write(buf, 0, length);
            os.flush();
        }

        is.close();
        os.close();
        socket.close();
        serverSocket.close();


    }
}