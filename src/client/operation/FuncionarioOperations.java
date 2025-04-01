package client.operation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class FuncionarioOperations {
    public static void insertFuncionario(String serverIp, int serverPort, Scanner scanner) throws IOException {
        try (Socket conn = new Socket(serverIp, serverPort);
             PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
             BufferedReader server = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            out.println("INSERT_FUNCIONARIO");
            System.out.println("Inserir Funcionário:");
            System.out.print("CPF: ");
            out.println(scanner.nextLine());
            System.out.print("Nome: ");
            out.println(scanner.nextLine());
            System.out.print("Endereço: ");
            out.println(scanner.nextLine());
            System.out.print("Salário: ");
            out.println(scanner.nextLine());
            System.out.print("Função: ");
            out.println(scanner.nextLine());
            System.out.println(server.readLine());
        }
    }

    public static void updateFuncionario(String serverIp, int serverPort, Scanner scanner) throws IOException {
        try (Socket conn = new Socket(serverIp, serverPort);
             PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
             BufferedReader server = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            out.println("UPDATE_FUNCIONARIO");
            System.out.println("Atualizar Funcionário:");
            System.out.print("CPF: ");
            out.println(scanner.nextLine());
            System.out.print("Nome: ");
            out.println(scanner.nextLine());
            System.out.print("Endereço: ");
            out.println(scanner.nextLine());
            System.out.print("Salário: ");
            out.println(scanner.nextLine());
            System.out.print("Função: ");
            out.println(scanner.nextLine());
            System.out.println(server.readLine());
        }
    }

    public static void getFuncionario(String serverIp, int serverPort, Scanner scanner) throws IOException {
        try (Socket conn = new Socket(serverIp, serverPort);
             PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
             BufferedReader server = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            out.println("GET_FUNCIONARIO");
            System.out.println("Adquirir Funcionário: ");
            System.out.print("CPF: ");
            out.println(scanner.nextLine());
            System.out.println(server.readLine());
        }
    }

    public static void deleteFuncionario(String serverIp, int serverPort, Scanner scanner) throws IOException {
        try (Socket conn = new Socket(serverIp, serverPort);
             PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
             BufferedReader server = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            out.println("DELETE_FUNCIONARIO");
            System.out.println("Remover Funcionário: ");
            System.out.print("CPF: ");
            out.println(scanner.nextLine());
            System.out.println(server.readLine());
        }
    }

    public static void listFuncionario(String serverIp, int serverPort, Scanner scanner) throws IOException {
        try (Socket conn = new Socket(serverIp, serverPort);
             PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
             BufferedReader server = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            out.println("LIST_FUNCIONARIO");
            int temp = Integer.parseInt(server.readLine());
            System.out.println("Funcionário(s): " + temp);
            for (int i = 0; i < temp; i++) {
                System.out.println(server.readLine());
            }
        }
    }

    public static void listAll(String serverIp, int serverPort, Scanner scanner) throws IOException {
        try (Socket conn = new Socket(serverIp, serverPort);
             PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
             BufferedReader server = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            out.println("LIST_ALL");
            int temp = Integer.parseInt(server.readLine());
            System.out.println("Pessoas(s): " + temp);
            for (int i = 0; i < temp; i++) {
                System.out.println(server.readLine());
            }
        }
    }
}
