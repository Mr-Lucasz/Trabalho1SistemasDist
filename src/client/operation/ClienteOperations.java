package client.operation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteOperations {
    public static void insertCliente(String serverIp, int serverPort, Scanner scanner) throws IOException {
        try (Socket conn = new Socket(serverIp, serverPort);
             PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
             BufferedReader server = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            out.println("INSERT_CLIENTE");
            System.out.println("Inserir Cliente:");
            System.out.print("CPF: ");
            out.println(scanner.nextLine());
            System.out.print("Nome: ");
            out.println(scanner.nextLine());
            System.out.print("Endereço: ");
            out.println(scanner.nextLine());
            System.out.print("Reserva: ");
            out.println(scanner.nextLine());
            System.out.println(server.readLine());
        }
    }

    public static void updateCliente(String serverIp, int serverPort, Scanner scanner) throws IOException {
        try (Socket conn = new Socket(serverIp, serverPort);
             PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
             BufferedReader server = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            out.println("UPDATE_CLIENTE");
            System.out.println("Atualizar Cliente:");
            System.out.print("CPF: ");
            out.println(scanner.nextLine());
            System.out.print("Nome: ");
            out.println(scanner.nextLine());
            System.out.print("Endereço: ");
            out.println(scanner.nextLine());
            System.out.print("Reserva: ");
            out.println(scanner.nextLine());
            System.out.println(server.readLine());
        }
    }

    public static void getCliente(String serverIp, int serverPort, Scanner scanner) throws IOException {
        try (Socket conn = new Socket(serverIp, serverPort);
             PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
             BufferedReader server = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            out.println("GET_CLIENTE");
            System.out.println("Obter Cliente:");
            System.out.print("CPF: ");
            out.println(scanner.nextLine());
            System.out.println(server.readLine());
        }
    }

    public static void removeCliente(String serverIp, int serverPort, Scanner scanner) throws IOException {
        try (Socket conn = new Socket(serverIp, serverPort);
             PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
             BufferedReader server = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            out.println("DELETE_CLIENTE");
            System.out.println("Remover Cliente:");
            System.out.print("CPF: ");
            out.println(scanner.nextLine());
            System.out.println(server.readLine());
        }
    }

    public static void listarClientes(String serverIp, int serverPort, Scanner scanner) throws IOException {
        try (Socket conn = new Socket(serverIp, serverPort);
             PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
             BufferedReader server = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            out.println("LIST_CLIENTE");
            int temp = Integer.parseInt(server.readLine());
            System.out.println("Cliente(s): " + temp);
            for (int i = 0; i < temp; i++) {
                System.out.println(server.readLine());
            }
        }
    }
}
