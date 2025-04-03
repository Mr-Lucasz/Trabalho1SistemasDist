package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ClienteOperations {

    public static void insertCliente(BufferedReader server, PrintWriter out, Scanner scanner) throws IOException {
        out.println("INSERT_CLIENTE");
        System.out.println("Inserir Cliente:");
        System.out.print("NUM. HOTEL: ");
        out.println(scanner.nextLine());
        String str = server.readLine();
        if (checkHotel(str)) {
            System.out.print("CPF: ");
            out.println(scanner.nextLine());
            System.out.print("Nome: ");
            out.println(scanner.nextLine());
            System.out.print("Endereço: ");
            out.println(scanner.nextLine());
            System.out.print("Reserva: ");
            out.println(scanner.nextLine());
        }
    }

    public static void updateCliente(BufferedReader server, PrintWriter out, Scanner scanner) throws IOException {
        out.println("UPDATE_CLIENTE");
        System.out.print("NUM. HOTEL: ");
        out.println(scanner.nextLine());
        String str = server.readLine();
        if (checkHotel(str)) {
            System.out.println("Atualizar Cliente:");
            System.out.print("CPF do Cliente: ");
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

    public static void getCliente(BufferedReader server, PrintWriter out, Scanner scanner) throws IOException {
        out.println("GET_CLIENTE");
        System.out.print("NUM. HOTEL: ");
        out.println(scanner.nextLine());
        String str = server.readLine();
        if (checkHotel(str)) {
            System.out.println("Obter Cliente:");
            System.out.print("CPF: ");
            out.println(scanner.nextLine());
            System.out.println(server.readLine());
        }
    }

    public static void removeCliente(BufferedReader server, PrintWriter out, Scanner scanner) throws IOException {
        out.println("DELETE_CLIENTE");
        System.out.print("NUM. HOTEL: ");
        out.println(scanner.nextLine());
        String str = server.readLine();
        if (checkHotel(str)) {
            System.out.println("Remover Cliente:");
            System.out.print("CPF: ");
            out.println(scanner.nextLine());
            System.out.println(server.readLine());
        }
    }

    public static void listarClientes(BufferedReader server, PrintWriter out, Scanner scanner) throws IOException {
        out.println("LIST_CLIENTE");
        System.out.print("NUM. HOTEL: ");
        out.println(scanner.nextLine());
        String str = server.readLine();
        if (checkHotel(str)) {
            int temp = Integer.parseInt(server.readLine());
            System.out.println(temp);
            for (int i = 0; i < temp; i++) {
                System.out.println(server.readLine());
            }
        }
    }

    private static boolean checkHotel(String str) {
        if (str.equals("0")) {
            System.out.println("Sem hotéis cadastrados");
            return false;
        }
        if (str.equals("1")) {
            System.out.println("Hotel não encontrado");
            return false;
        }
        return true;
    }
}