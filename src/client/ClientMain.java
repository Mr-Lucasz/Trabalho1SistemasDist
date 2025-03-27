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
            System.out.println("16. Sair");
            int option = scanner.nextInt();
            scanner.nextLine();

            String mensagem = "";
            switch (option) {
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
                    System.out.println("Inserir Cliente:");
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String endereco = scanner.nextLine();
                    System.out.print("Reserva: ");
                    int reserva = scanner.nextInt();
                    scanner.nextLine();
                    mensagem = "INSERT_CLIENTE;" + cpf + ";" + nome + ";" + endereco + ";" + reserva;
                }
                case 7 -> {
                    System.out.println("Atualizar Cliente:");
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String endereco = scanner.nextLine();
                    System.out.print("Reserva: ");
                    int reserva = scanner.nextInt();
                    scanner.nextLine();
                    mensagem = "UPDATE_CLIENTE;" + cpf + ";" + nome + ";" + endereco + ";" + reserva;
                }
                case 8 -> {
                    System.out.println("Obter Cliente:");
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    mensagem = "GET_CLIENTE;" + cpf;
                }
                case 9 -> {
                    System.out.println("Remover Cliente:");
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    mensagem = "DELETE_CLIENTE;" + cpf;
                }
                case 10 -> mensagem = "LIST_CLIENTE";
                case 11 -> {
                    System.out.println("Inserir Funcionário:");
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String endereco = scanner.nextLine();
                    System.out.print("Salário: ");
                    int salario = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Função: ");
                    String funcao = scanner.nextLine();
                    mensagem = "INSERT_FUNCIONARIO;" + cpf + ";" + nome + ";" + endereco + ";" + salario + ";" + funcao;
                }
                case 12 -> {
                    System.out.println("Atualizar Funcionário:");
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String endereco = scanner.nextLine();
                    System.out.print("Salário: ");
                    int salario = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Função: ");
                    String funcao = scanner.nextLine();
                    mensagem = "UPDATE_FUNCIONARIO;" + cpf + ";" + nome + ";" + endereco + ";" + salario + ";" + funcao;
                }
                case 13 -> {
                    System.out.println("Obter Funcionário:");
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    mensagem = "GET_FUNCIONARIO;" + cpf;
                }
                case 14 -> {
                    System.out.println("Remover Funcionário:");
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    mensagem = "DELETE_FUNCIONARIO;" + cpf;
                }
                case 15 -> mensagem = "LIST_FUNCIONARIO";
                case 16 -> {
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