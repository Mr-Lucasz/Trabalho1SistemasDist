package client;

import java.io.IOException;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClientSocket clientSocket = new ClientSocket();
        String serverIp = "127.0.0.1"; 

        while (true) {
            System.out.println("Escolha uma operação:");
            System.out.println("1. Inserir Pessoa");
            System.out.println("2. Atualizar Pessoa");
            System.out.println("3. Obter Pessoa");
            System.out.println("4. Remover Pessoa");
            System.out.println("5. Listar Pessoas");
            System.out.println("6. Sair");
            int option = scanner.nextInt();
            scanner.nextLine(); 

            String mensagem = "";
            switch (option ) {
                case 1 -> {
                    System.out.println("Inserir Pessoa:");
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String endereco = scanner.nextLine();
                    mensagem = "INSERT_PESSOA;" + cpf + ";" + nome + ";" + endereco;
                }
                case 2 -> {
                    System.out.println("Atualizar Pessoa:");
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String endereco = scanner.nextLine();
                    mensagem = "UPDATE_PESSOA;" + cpf + ";" + nome + ";" + endereco;
                }
                case 3 -> {
                    System.out.println("Obter Pessoa:");
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    mensagem = "GET_PESSOA;" + cpf;
                }
                case 4 -> {
                    System.out.println("Remover Pessoa:");
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    mensagem = "DELETE_PESSOA;" + cpf;
                }
                case 5 -> mensagem = "LIST_PESSOA";
                case 6 -> {
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                }
                default -> {
                    System.out.println("Opção inválida.");
                    continue;
                }
            }

            try {
                String response = clientSocket.conectar(serverIp, mensagem);
                System.out.println("Resposta do servidor: " + response);
            } catch (IOException e) {
                System.out.println("Erro ao conectar com o servidor: " + e.getMessage());
            }
        }
    }
}