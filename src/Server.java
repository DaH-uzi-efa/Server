import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        try(Scanner scanner = new Scanner(System.in)){
            while (true) {
                serverOptions();
                String option = scanner.nextLine();
                meny(option, scanner);
            }
        }
    }

    public static void meny(String option, Scanner scanner) {
        switch (option) {
            case "1" -> {
                Server server = new Server();
                try {
                    server.runServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case "2" -> {
                Server server = new Server();
                try {
                    server.runServerWithIP();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case "3" -> {
                System.out.println("Exiting...");
                System.exit(0);
            }
            default -> System.out.println("Invalid option.");
        }
    }

    public void runServerWithIP() throws IOException {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        ServerSocket serverSocket = null;
        InetAddress ia = InetAddress.getLocalHost();
        String ip = ia.getHostAddress();
        System.out.println("Server is running on IP: " + ip + " and port: 6969");

        serverSocket = new ServerSocket(6969);

        while (true) {
            try {
                socket = serverSocket.accept();

                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                while (true) {
                    String receiver = bufferedReader.readLine();
                    System.out.println("Client: " + receiver);
                    bufferedWriter.write("MSG received");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    if (receiver.equalsIgnoreCase("exit"))
                        break;
                }

                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



    private static void serverOptions() {
        System.out.println("Welcome to the server");
        System.out.println("1: Host via localhost");
        System.out.println("2: Host via ip");
        System.out.println("3: Exit");
    }




    public void runServer() throws IOException {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        ServerSocket serverSocket = null;

        serverSocket = new ServerSocket(6969);

        while (true) {
            try {
                socket = serverSocket.accept();

                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                while (true) {
                    String receiver = bufferedReader.readLine();
                    System.out.println("Client: " + receiver);
                    bufferedWriter.write("MSG received");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    if (receiver.equalsIgnoreCase("exit"))
                        break;
                }
                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


