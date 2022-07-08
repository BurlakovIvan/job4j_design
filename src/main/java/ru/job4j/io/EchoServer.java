package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
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
                            break;
                        } else {
                            String[] strAnswer = str.split(" ");
                            for (String s : strAnswer) {
                                if (s.contains("?msg=")) {
                                    answer = s.split("=")[1];
                                    break;
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
        }
    }
}
