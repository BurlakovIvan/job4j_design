package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = in.readLine();
                    if (str != null && !str.isEmpty()) {
                        String answer = str;
                        if (str.contains("?msg=Hello")) {
                            answer = "Hello, dear friend.";
                        } else if (str.contains("?msg=Exit")) {
                            server.close();
                            answer = "Exit";
                        } else {
                            String[] strAnswer = str.split(" ");
                            for (String s : strAnswer) {
                                if (s.contains("?msg=")) {
                                    var stringSplit = s.split("=");
                                    if (stringSplit.length > 1) {
                                        answer = stringSplit[1];
                                    } else {
                                        answer = "NULL";
                                    }
                                }
                            }
                        }
                        out.write(answer.getBytes());
                    }
                    for (; str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                    }
                    out.flush();
                }
            }
        } catch (IOException ex) {
            LOG.error("Exception in log, type - IOException. ", ex);
        }
    }
}
