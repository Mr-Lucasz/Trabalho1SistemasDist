package client;

import client.operation.*;
import java.io.IOException;
import java.util.Scanner;

public class ClientMain {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        boolean isHotelSelected = false;
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Conectando ao servidor em " + SERVER_IP + ":" + SERVER_PORT);
                int option = getUserOption(scanner, isHotelSelected);
                if (option == 17) {
                    System.out.println("Saindo...");
                    break;
                }
                if (option == 3) { 
                    try {
                        HotelOperations.selectHotel(SERVER_IP, SERVER_PORT, scanner);
                        isHotelSelected = true; 
                    } catch (IOException e) {
                        System.out.println("Erro ao selecionar o hotel: " + e.getMessage());
                    }
                } else if (option >= 6 && option <= 16 && !isHotelSelected) {
                    System.out.println("Por favor, selecione um hotel antes de realizar esta operação.");
                } else {
                    handleUserOption(option, scanner);
                }
        }
    }

    private static int getUserOption(Scanner scanner, boolean isHotelSelected) {
        System.out.println("Escolha uma operação:");
        System.out.println("1. Criar Novo Hotel");
        System.out.println("2. Atualizar Hotel Atual");
        System.out.println("3. Escolher Hotel");
        System.out.println("4. Informações Hotel Atual");
        System.out.println("5. Remover Hotel Atual");

        if (isHotelSelected) {
            System.out.println("6. Inserir Cliente");
            System.out.println("7. Atualizar Cliente");
            System.out.println("8. Obter Cliente");
            System.out.println("9. Remover Cliente");
            System.out.println("10. Listar Clientes");
            System.out.println("11. Inserir Funcionário");
            System.out.println("12. Atualizar Funcionário");
            System.out.println("13. Obter Funcionário");
            System.out.println("14. Remover Funcionário");
            System.out.println("15. Listar Funcionários");
            System.out.println("16. Listar Pessoas");
        } else {
            System.out.println("6-16. [Indisponível até selecionar um hotel]");
        }

        System.out.println("17. Sair");
        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    private static void handleUserOption(int option, Scanner scanner) {
        try {
            switch (option) {
                case 1 -> HotelOperations.criarHotel(SERVER_IP, SERVER_PORT, scanner);
                case 2 -> HotelOperations.updateHotel(SERVER_IP, SERVER_PORT, scanner);
                case 3 -> HotelOperations.selectHotel(SERVER_IP, SERVER_PORT, scanner);
                case 4 -> HotelOperations.getHotel(SERVER_IP, SERVER_PORT, scanner);
                case 5 -> HotelOperations.removeHotel(SERVER_IP, SERVER_PORT, scanner);
                case 6 -> ClienteOperations.insertCliente(SERVER_IP, SERVER_PORT, scanner);
                case 7 -> ClienteOperations.updateCliente(SERVER_IP, SERVER_PORT, scanner);
                case 8 -> ClienteOperations.getCliente(SERVER_IP, SERVER_PORT, scanner);
                case 9 -> ClienteOperations.removeCliente(SERVER_IP, SERVER_PORT, scanner);
                case 10 -> ClienteOperations.listarClientes(SERVER_IP, SERVER_PORT, scanner);
                case 11 -> FuncionarioOperations.insertFuncionario(SERVER_IP, SERVER_PORT, scanner);
                case 12 -> FuncionarioOperations.updateFuncionario(SERVER_IP, SERVER_PORT, scanner);
                case 13 -> FuncionarioOperations.getFuncionario(SERVER_IP, SERVER_PORT, scanner);
                case 14 -> FuncionarioOperations.deleteFuncionario(SERVER_IP, SERVER_PORT, scanner);
                case 15 -> FuncionarioOperations.listFuncionario(SERVER_IP, SERVER_PORT, scanner);
                case 16 -> FuncionarioOperations.listAll(SERVER_IP, SERVER_PORT, scanner);
                default -> System.out.println("Opção inválida.");
            }
        } catch (IOException e) {
            System.err.println("Erro de comunicação com o servidor: " + e.getMessage());
        }
    }

    public static boolean checkHotel(String str) {
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