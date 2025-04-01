package servidor.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import service.ClienteService;

public class ClienteController {
    private final ClienteService clienteService = new ClienteService();

    public void handleRequest(String action, BufferedReader in, PrintWriter out) throws IOException {
        switch (action) {
            case "INSERT_CLIENTE" -> clienteService.inserirCliente(in, out);
            case "UPDATE_CLIENTE" -> clienteService.updateCliente(in, out);
            case "GET_CLIENTE" -> clienteService.getCliente(in, out);
            case "DELETE_CLIENTE" -> clienteService.deleteCliente(in, out);
            case "LIST_CLIENTE" -> clienteService.listAllCliente(out);
            default -> out.println("Erro");
        }
    }
}