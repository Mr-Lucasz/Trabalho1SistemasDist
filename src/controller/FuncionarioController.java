package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import service.FuncionarioService;

public class FuncionarioController {

    private final FuncionarioService funcionarioService = new FuncionarioService();

    public void handleRequest(String action, BufferedReader in, PrintWriter out) throws IOException {
        switch (action) {
            case "INSERT_FUNCIONARIO" -> funcionarioService.inserirFuncionario(in, out);
            case "UPDATE_FUNCIONARIO" -> funcionarioService.updateFuncionario(in, out);
            case "GET_FUNCIONARIO" -> funcionarioService.getFuncionario(in, out);
            case "DELETE_FUNCIONARIO" -> funcionarioService.deleteFuncionario(in, out);
            case "LIST_FUNCIONARIO" -> funcionarioService.listAllFuncionario(out);
            default -> out.println("Erro");
        }
    }
}
