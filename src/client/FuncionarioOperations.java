package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FuncionarioOperations {

    public static void insertFuncionario(BufferedReader server, PrintWriter out, Scanner scanner) throws IOException {
        out.println("INSERT_FUNCIONARIO");
        System.out.print("NUM. HOTEL: ");
        out.println(scanner.nextLine());
        String str = server.readLine();
        if (checkHotel(str)) {
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
        }
    }

    public static void updateFuncionario(BufferedReader server, PrintWriter out, Scanner scanner) throws IOException {
        out.println("UPDATE_FUNCIONARIO");
        System.out.print("NUM. HOTEL: ");
        out.println(scanner.nextLine());
        String str = server.readLine();
        if (checkHotel(str)) {
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

    public static void getFuncionario(BufferedReader server, PrintWriter out, Scanner scanner) throws IOException {
        out.println("GET_FUNCIONARIO");
        System.out.print("NUM. HOTEL: ");
        out.println(scanner.nextLine());
        String str = server.readLine();
        if (checkHotel(str)) {
            System.out.println("Obter Funcionário:");
            System.out.print("CPF: ");
            out.println(scanner.nextLine());
            System.out.println(server.readLine());
        }
    }

    public static void deleteFuncionario(BufferedReader server, PrintWriter out, Scanner scanner) throws IOException {
        out.println("DELETE_FUNCIONARIO");
        System.out.print("NUM. HOTEL: ");
        out.println(scanner.nextLine());
        String str = server.readLine();
        if (checkHotel(str)) {
            System.out.println("Remover Funcionário:");
            System.out.print("CPF: ");
            out.println(scanner.nextLine());
            System.out.println(server.readLine());
        }
    }

    public static void listFuncionario(BufferedReader server, PrintWriter out, Scanner scanner) throws IOException {
        out.println("LIST_FUNCIONARIO");
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