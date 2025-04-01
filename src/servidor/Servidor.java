package servidor;

import controller.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private final HotelController hotelController = new HotelController();
    private final FuncionarioController funcionarioController = new FuncionarioController();
    private final ClienteController clienteController = new ClienteController();

    public void startServer() throws IOException {
        try (ServerSocket server = new ServerSocket(80)) {
            System.out.println("Aguardando conexão");
            server.setReuseAddress(true);
            while (true) {
                Socket connection = server.accept();
                System.out.println("Cliente: " + connection.getInetAddress());
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                     PrintWriter out = new PrintWriter(connection.getOutputStream(), true)) {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println(msg);
                        if (msg.startsWith("INSERT_HOTEL") || msg.startsWith("UPDATE_HOTEL") || msg.startsWith("GET_HOTEL") ||
                                msg.startsWith("DELETE_HOTEL") || msg.startsWith("LIST_HOTEL") || msg.startsWith("SELECT_HOTEL")) {
                            hotelController.handleRequest(msg, in, out);
                        } else if (msg.startsWith("INSERT_FUNCIONARIO") || msg.startsWith("UPDATE_FUNCIONARIO") ||
                                msg.startsWith("GET_FUNCIONARIO") || msg.startsWith("DELETE_FUNCIONARIO") ||
                                msg.startsWith("LIST_FUNCIONARIO")) {
                            funcionarioController.handleRequest(msg, in, out);
                        } else if (msg.startsWith("INSERT_CLIENTE") || msg.startsWith("UPDATE_CLIENTE") ||
                                msg.startsWith("GET_CLIENTE") || msg.startsWith("DELETE_CLIENTE") ||
                                msg.startsWith("LIST_CLIENTE")) {
                            clienteController.handleRequest(msg, in, out);
                        } else {
                            out.println("Erro");
                        }
                    }
                }
            }
        }
    }
}