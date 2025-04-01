package client;

import client.operation.*;

import java.io.IOException;
import java.util.Scanner;

public class ClientMain {
    private static final String SERVER_IP = "192.168.1.23";
    private static final int SERVER_PORT = 80;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                int option = getUserOption(scanner);
                if (option == 17) {
                    System.out.println("Saindo...");
                    break;
                }
                handleUserOption(option, scanner);
            }
        }
    }

    private static int getUserOption(Scanner scanner) {
        System.out.println("Escolha uma operação:");
        System.out.println("1. Criar Novo Hotel");
        System.out.println("2. Atualizar Hotel Atual");
        System.out.println("3. Escolher Hotel");
        System.out.println("4. Informações Hotel Atual");
        System.out.println("5. Remover Hotel Atual");
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
        System.out.println("17. Sair");
        return scanner.nextInt();
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
}