package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.StringTokenizer;

/**
 * User: Dmytro Vynokurov
 * Date: 10/17/13
 * Time: 11:30 PM
 */
public class SocketManager {

    public static void sendWord(OutputStream os, String wordToSend) throws IOException {
        os.write(wordToSend.getBytes(), 0, wordToSend.length());
        os.flush();
    }


    public static String receiveWord(InputStream is) throws IOException {
        StringTokenizer sendTokenizer;
        byte[] buf = new byte[512];

        is.read(buf);
        if (buf.length == -1)
            throw new SocketException("No response from client");

        sendTokenizer = new StringTokenizer(new String(buf, 0), "" + (char) 0);
        return (String) sendTokenizer.nextElement();
    }
}
