package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HotelOperations {

    public static void criarHotel(BufferedReader server, PrintWriter out, Scanner scanner) throws IOException {
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
    }

    public static void updateHotel(BufferedReader server, PrintWriter out, Scanner scanner) throws IOException {
        out.println("UPDATE_HOTEL");
        System.out.println("Atualizar Hotel:");
        System.out.print("NUM. HOTEL: ");
        out.println(scanner.nextLine());
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

    public static void getHotel(BufferedReader server, PrintWriter out, Scanner scanner) throws IOException {
        out.println("GET_HOTEL");
        System.out.print("NUM. HOTEL: ");
        out.println(scanner.nextLine());
        System.out.println(server.readLine());
    }

    public static void removeHotel(BufferedReader server, PrintWriter out, Scanner scanner) throws IOException {
        out.println("DELETE_HOTEL");
        System.out.print("NUM. HOTEL: ");
        out.println(scanner.nextLine());
        System.out.println(server.readLine());
    }

    public static void listHotel(BufferedReader server, PrintWriter out) throws IOException {
        out.println("LIST_HOTEL");
        int temp = Integer.parseInt(server.readLine());
        System.out.println(temp);
        if (temp != 0) {
            for (int i = 0; i < temp; i++) {
                System.out.println(server.readLine());
            }
        }
    }
}