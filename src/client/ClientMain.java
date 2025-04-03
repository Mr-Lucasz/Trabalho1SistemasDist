package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o IP:");
        String serverIp = scanner.nextLine();

        while (true) {
            boolean hasHotels = checkHotels(serverIp);

            // Exibir opções dinamicamente
            System.out.println("Escolha uma operação:");
            System.out.println("1. Criar Novo Hotel");
            System.out.println("2. Atualizar Hotel");
            System.out.println("3. Listar Hotéis");
            System.out.println("4. Obter Hotel");
            System.out.println("5. Remover Hotel");

            if (hasHotels) {
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
            }

            System.out.println("16. Sair");
            int option = scanner.nextInt();
            scanner.nextLine();

            // Conectar ao servidor e executar a operação selecionada
            try (Socket conn = new Socket(serverIp, 80);
                    PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
                    BufferedReader server = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {

                if (!hasHotels && option >= 6 && option <= 15) {
                    System.out.println("Opção inválida. Nenhum hotel cadastrado.");
                    continue;
                }

                switch (option) {
                    case 1 -> HotelOperations.criarHotel(server, out, scanner);
                    case 2 -> HotelOperations.updateHotel(server, out, scanner);
                    case 3 -> HotelOperations.listHotel(server, out);
                    case 4 -> HotelOperations.getHotel(server, out, scanner);
                    case 5 -> HotelOperations.removeHotel(server, out, scanner);
                    case 6 -> ClienteOperations.insertCliente(server, out, scanner);
                    case 7 -> ClienteOperations.updateCliente(server, out, scanner);
                    case 8 -> ClienteOperations.getCliente(server, out, scanner);
                    case 9 -> ClienteOperations.removeCliente(server, out, scanner);
                    case 10 -> ClienteOperations.listarClientes(server, out, scanner);
                    case 11 -> FuncionarioOperations.insertFuncionario(server, out, scanner);
                    case 12 -> FuncionarioOperations.updateFuncionario(server, out, scanner);
                    case 13 -> FuncionarioOperations.getFuncionario(server, out, scanner);
                    case 14 -> FuncionarioOperations.deleteFuncionario(server, out, scanner);
                    case 15 -> FuncionarioOperations.listFuncionario(server, out, scanner);
                    case 16 -> {
                        System.out.println("Saindo...");
                        return;
                    }
                    default -> System.out.println("Opção inválida.");
                }
            }
        }
    }

    private static boolean checkHotels(String serverIp) throws IOException {
        try (Socket conn = new Socket(serverIp, 80);
                PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
                BufferedReader server = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            out.println("LIST_HOTEL");
            int hotelCount = Integer.parseInt(server.readLine());
            // Consumir as linhas restantes
            for (int i = 0; i < hotelCount; i++) {
                server.readLine();
            }
            return hotelCount > 0;
        }
    }
}