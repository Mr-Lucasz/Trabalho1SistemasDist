package servidor;

import controller.ClienteController;
import controller.FuncionarioController;
import controller.HotelController;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private final ClienteController clienteController = new ClienteController();
    private final FuncionarioController funcionarioController = new FuncionarioController();
    private final HotelController hotelController = new HotelController();

    public void startServer() throws IOException {
        try (ServerSocket server = new ServerSocket(80)) {
            System.out.println("Aguardando conexão");
            server.setReuseAddress(true);
            while (true) {
                Socket connection = server.accept();
                System.out.println("Cliente conectado: " + connection.getInetAddress());
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        PrintWriter out = new PrintWriter(connection.getOutputStream(), true)) {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        try {
                            System.out.println("Ação recebida: " + msg);
                            if (msg.startsWith("INSERT_") || msg.startsWith("UPDATE_") || msg.startsWith("GET_")
                                    || msg.startsWith("DELETE_") || msg.startsWith("LIST_")) {
                                if (msg.contains("CLIENTE")) {
                                    clienteController.handleRequest(msg, in, out);
                                } else if (msg.contains("FUNCIONARIO")) {
                                    funcionarioController.handleRequest(msg, in, out);
                                } else if (msg.contains("HOTEL")) {
                                    hotelController.handleRequest(msg, in, out);
                                } else {
                                    out.println("Ação inválida");
                                }
                            } else {
                                out.println("Ação inválida");
                            }
                        } catch (Exception e) {
                            System.out.println("Erro ao processar requisição: " + e.getMessage());
                        }
                    }
                }
            }
        }
    }
}