import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                clientOptions();
                String option = scanner.nextLine();
                meny(option, scanner);
            }
        }
    }

    private static void meny(String option, Scanner scanner) {
        switch (option) {
            case "1" -> {
                Client client = new Client();
                client.runClient(scanner);
            }
            case "2" -> {
                Client client = new Client();
                client.runClientWithIp(scanner);
            }
            case "3" -> {
                System.out.println("Avslutter...");
                System.exit(0);
            }
            default -> System.out.println("Ugyldig valg.");
        }
    }

    private void runClientWithIp(Scanner scanner) {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        System.out.println("Skriv in ip x.x.x.x");
        String hostIp = scanner.nextLine();

        try {
            socket = new Socket(hostIp, 6969);

            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            System.out.println("Koblet til server på localhost:6969");

            while (true) {
                if (scanner.hasNextLine()) {
                    String sender = scanner.nextLine();

                    bufferedWriter.write(sender);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    String serverMessage = bufferedReader.readLine();
                    if (serverMessage != null) {
                        System.out.println("Server: " + serverMessage);
                    }

                    if (sender.equalsIgnoreCase("exit")) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("En feil oppstod under kommunikasjon: " + e.getMessage());
        } finally {
            try {
                if (socket != null) socket.close();
                if (inputStreamReader != null) inputStreamReader.close();
                if (outputStreamWriter != null) outputStreamWriter.close();
                if (bufferedReader != null) bufferedReader.close();
                if (bufferedWriter != null) bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void runClient(Scanner scanner) {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            socket = new Socket("localhost", 6969);

            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            System.out.println("Koblet til server på localhost:6969");

            while (true) {
                if (scanner.hasNextLine()) {
                    String sender = scanner.nextLine();

                    bufferedWriter.write(sender);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    String serverMessage = bufferedReader.readLine();
                    if (serverMessage != null) {
                        System.out.println("Server: " + serverMessage);
                    }

                    if (sender.equalsIgnoreCase("exit")) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("En feil oppstod under kommunikasjon: " + e.getMessage());
        } finally {
            try {
                if (socket != null) socket.close();
                if (inputStreamReader != null) inputStreamReader.close();
                if (outputStreamWriter != null) outputStreamWriter.close();
                if (bufferedReader != null) bufferedReader.close();
                if (bufferedWriter != null) bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void clientOptions() {
        System.out.println("\n--- Welcome to the client ---");
        System.out.println("1: Connect via localhost");
        System.out.println("2: Connect via ip (Krever Server-klasse)");
        System.out.println("3: Exit");
        System.out.print("Velg et alternativ: ");
    }

    public String returnName() {
        return "Huzef";
    }
}