import wordchecker.ConsonantChecker;
import wordchecker.WordChecker;
import wordchecker.WordCheckerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SocketServer {
    private final static boolean DIFFERENT_MODES_ENABLED = false;

    public static void main(String args[]) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        byte buf[] = new byte[512];
        int length;
        String result;
        String processMode;
        String stringReceived;
        String word;
        WordChecker checker;
        StringTokenizer modeTokenizer;
        StringTokenizer receiveTokenizer;

        System.out.println("Socket Server Application");

        while (true) {
            length = is.read(buf);
            if (length == -1)
                break;

            stringReceived = new String(buf, 0);
            receiveTokenizer = new StringTokenizer(stringReceived, "\r\n");
            stringReceived = receiveTokenizer.nextToken();


            if (DIFFERENT_MODES_ENABLED) {
                modeTokenizer = new StringTokenizer(stringReceived, "/");
                processMode = (String) modeTokenizer.nextElement();
                word = (String) modeTokenizer.nextElement();
                checker = WordCheckerFactory.getWordChecker(processMode);
            }else{
                word = stringReceived;
                checker = new ConsonantChecker();
            }

            result = checker.check(word);

            System.out.println(">  " + result);
            os.write(result.getBytes(), 0, result.getBytes().length);
            os.flush();
        }

        is.close();
        os.close();
        socket.close();
        serverSocket.close();
    }
}