package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            Scanner scanner = new Scanner(System.in);
            String serverIp = "192.168.1.23";
            System.out.println("Informe o IP:");
            serverIp = scanner.nextLine();

            while (true) {
                System.out.println("Escolha uma operação:");
                System.out.println("1. Criar Novo Hotel");
                System.out.println("2. Atualizar Hotel");
                System.out.println("3. Listar Hotéis");
                System.out.println("4. Obter Hotel");
                System.out.println("5. Remover Hotel");
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
                int option = scanner.nextInt();
                scanner.nextLine();
                Socket conn = new Socket(serverIp, 80);
                try (PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
                    BufferedReader server = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    switch (option) {
                        case 1 -> insertHotel(server, out, scanner);
                        case 2 -> updateHotel(server, out, scanner);
                        case 3 -> listHotel(server, out, scanner);
                        case 4 -> getHotel(server, out, scanner);
                        case 5 -> deleteHotel(server, out, scanner);
                        case 6 -> insertCliente(server, out, scanner);
                        case 7 -> updateCliente(server, out, scanner);
                        case 8 -> getCliente(server, out, scanner);
                        case 9 -> deleteCliente(server, out, scanner);
                        case 10 -> listCLiente(server, out, scanner);
                        case 11 -> insertFuncionario(server, out, scanner);
                        case 12 -> updateFuncionario(server, out, scanner);
                        case 13 -> getFuncionario(server, out, scanner);
                        case 14 -> deleteFuncionario(server, out, scanner);
                        case 15 -> listFuncionario(server, out, scanner);
                        case 16 -> listPessoas(server, out, scanner);
                        case 17 -> {
                            System.out.println("Saindo...");
                            scanner.close();
                            conn.close();
                            return;
                        }
                        default -> {
                            System.out.println("Opção inválida.");
                            continue;
                        }
                    }
                    conn.close();
                }
        }
    }
}
    public static void insertHotel(BufferedReader server, PrintWriter out, Scanner scanner) throws IOException {
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
    public static  void getHotel(BufferedReader server, PrintWriter out, Scanner scanner) throws IOException {
        out.println("GET_HOTEL");
        System.out.print("NUM. HOTEL: ");
        out.println(scanner.nextLine());
        System.out.println(server.readLine());
    }
    public static void deleteHotel(BufferedReader server, PrintWriter out, Scanner scanner) throws IOException {
        out.println("DELETE_HOTEL");
        System.out.print("NUM. HOTEL: ");
        out.println(scanner.nextLine());
        System.out.println(server.readLine());
    }
    private static void listHotel(BufferedReader server, PrintWriter out, Scanner scanner) throws IOException {
        out.println("LIST_HOTEL");
        int temp = Integer.parseInt(server.readLine());
        System.out.println(temp);
        if(temp!=0)
            for(int i=0;i<temp; i++)
            {
                System.out.println(server.readLine());
            }
    }
    public static void insertCliente(BufferedReader server, PrintWriter out, Scanner scanner) throws IOException {
        out.println("INSERT_CLIENTE");
        System.out.println("Inserir Cliente:");
        System.out.print("NUM. HOTEL: ");
        out.println(scanner.nextLine());
        String str = server.readLine();
        if(checkHotel(str))
        {
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
        if(checkHotel(str))
        {
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
    public static  void getCliente(BufferedReader server, PrintWriter out, Scanner scanner) throws IOException {
        out.println("GET_CLIENTE");
        System.out.print("NUM. HOTEL: ");
        out.println(scanner.nextLine());
        String str = server.readLine();
        if(checkHotel(str))
        {
            System.out.println("Obter Cliente:");
            System.out.print("CPF: ");
            out.println(scanner.nextLine());
            System.out.println(server.readLine());
        }
    }
    public static void deleteCliente(BufferedReader server, PrintWriter out, Scanner scanner) throws IOException {
        out.println("DELETE_CLIENTE");
        System.out.print("NUM. HOTEL: ");
        out.println(scanner.nextLine());
        String str = server.readLine();
        if(checkHotel(str))
        {
            System.out.println("Remover Cliente:");
            System.out.print("CPF: ");
            out.println(scanner.nextLine());
            System.out.println(server.readLine());
        }
    }
    public static void listCLiente(BufferedReader server, PrintWriter out, Scanner scanner) throws IOException {
        out.println("LIST_CLIENTE");
        System.out.print("NUM. HOTEL: ");
        out.println(scanner.nextLine());
        String str = server.readLine();
        if(checkHotel(str))
        {
            int temp = Integer.parseInt(server.readLine());
            System.out.println(temp);
            for(int i=0;i<temp; i++)
            {
                System.out.println(server.readLine());
            }
        }
    }
    public static void insertFuncionario(BufferedReader server, PrintWriter out, Scanner scanner) throws IOException {
        out.println("INSERT_FUNCIONARIO");
        System.out.print("NUM. HOTEL: ");
        out.println(scanner.nextLine());
        String str = server.readLine();
        if(checkHotel(str))
        {
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
        if(checkHotel(str))
        {
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
        if(checkHotel(str))
        {
            System.out.println("Adquirir Funcionário: ");
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
        if(checkHotel(str))
        {
            System.out.println("Remover Funcionário: ");
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
        if(checkHotel(str))
        {
            int temp = Integer.parseInt(server.readLine());
            System.out.println(temp);
            for(int i=0;i<temp; i++)
            {
                System.out.println(server.readLine());
            }
        }

    }
    public static void listPessoas(BufferedReader server, PrintWriter out, Scanner scanner) throws IOException {
        out.println("LIST_ALL");
        System.out.print("NUM. HOTEL: ");
        out.println(scanner.nextLine());
        String str = server.readLine();
        if(checkHotel(str))
        {
            int temp = Integer.parseInt(server.readLine());
            System.out.println(temp);
            for (int i=0;i<temp; i++)
            {
                System.out.println(server.readLine());
            }
        }
    }
    public static boolean checkHotel(String str){
        if(str.equals("0"))
        {
            System.out.println("Sem hotéis cadastrados");
            return false;
        }
        if(str.equals("1"))
        {
            System.out.println("Hotel não encontrado");
            return false;
        }
        return true;
    }
}