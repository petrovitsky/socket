import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {


        try (ServerSocket server = new ServerSocket(8000)) {
            while (true) {
                try (
                        Socket socket = server.accept();
                        BufferedWriter writer =
                                new BufferedWriter(
                                        new OutputStreamWriter(
                                                socket.getOutputStream()));
                        BufferedReader reader =
                                new BufferedReader(
                                        new InputStreamReader(
                                                socket.getInputStream()))) {
                    String request = reader.readLine();
//              String request = reader.lines().collect(Collectors.joining(System.lineSeparator()));
                    String response = "HTTP/1.1 200 OK\r\n" +
                            "Content-Type: text/html\r\n\r\n" +
                            "<html><body><h1>Hello, from server!</h1></body></html>";

                    System.out.println("request = " + request);

                    writer.write(response);
                    writer.newLine();
                    writer.flush();

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
