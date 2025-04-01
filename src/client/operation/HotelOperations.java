package client.operation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HotelOperations {

    public static void criarHotel(String serverIp, int serverPort, Scanner scanner) throws IOException {
        System.out.println("Tentando conectar ao servidor em " + serverIp + ":" + serverPort);
        try (Socket conn = new Socket(serverIp, serverPort);
             PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
             BufferedReader server = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            out.println("INSERT_HOTEL");
            System.out.println("Criar Hotel:");
            System.out.print("Nome: ");
            out.println(scanner.nextLine());
            System.out.print("Endereco: ");
            out.println(scanner.nextLine());
            System.out.print("Quartos: ");
            out.println(scanner.nextLine());
            System.out.print("Vagas: ");
            out.println(scanner.nextLine());
            System.out.print("Classificação: ");
            out.println(scanner.nextLine());
            System.out.println(server.readLine());
        }
    }
    
    public static void updateHotel(String serverIp, int serverPort, Scanner scanner) throws IOException {
        try (Socket conn = new Socket(serverIp, serverPort);
             PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
             BufferedReader server = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            out.println("UPDATE_HOTEL");
            System.out.println("Atualizar Hotel:");
            System.out.print("Nome: ");
            out.println(scanner.nextLine());
            System.out.print("Endereco: ");
            out.println(scanner.nextLine());
            System.out.print("Quartos: ");
            out.println(scanner.nextLine());
            System.out.print("Vagas: ");
            out.println(scanner.nextLine());
            System.out.print("Classificação: ");
            out.println(scanner.nextLine());
            System.out.println(server.readLine());
        }
    }

    public static void selectHotel(String serverIp, int serverPort, Scanner scanner) throws IOException {
        try (Socket conn = new Socket(serverIp, serverPort);
             PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
             BufferedReader server = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            out.println("SELECT_HOTEL");
            int temp = Integer.parseInt(server.readLine());
            System.out.println("Hotéis: ");
            for (int i = 0; i < temp; i++) {
                System.out.println((i + 1) + ". " + server.readLine());
            }
            System.out.println("Numero:");
            out.println(scanner.nextLine());
            System.out.println(server.readLine());
        }
    }

    public static void getHotel(String serverIp, int serverPort, Scanner scanner) throws IOException {
        try (Socket conn = new Socket(serverIp, serverPort);
             PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
             BufferedReader server = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            out.println("GET_HOTEL");
            System.out.println(server.readLine());
        }
    }

    public static void removeHotel(String serverIp, int serverPort, Scanner scanner) throws IOException {
        try (Socket conn = new Socket(serverIp, serverPort);
             PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
             BufferedReader server = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            out.println("DELETE_HOTEL");
            System.out.println(server.readLine());
        }
    }
}
