package controller;

import service.ClienteService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ClienteController {

    private final ClienteService clienteService = new ClienteService();

    public void handleRequest(String action, BufferedReader in, PrintWriter out) throws IOException {
        switch (action) {
            case "INSERT_CLIENTE" -> clienteService.inserirCliente(in, out);
            case "UPDATE_CLIENTE" -> clienteService.atualizarCliente(in, out);
            case "GET_CLIENTE" -> clienteService.obterCliente(in, out);
            case "DELETE_CLIENTE" -> clienteService.removerCliente(in, out);
            case "LIST_CLIENTE" -> clienteService.listarClientes(in, out);
            default -> out.println("Ação inválida");
        }
    }
}