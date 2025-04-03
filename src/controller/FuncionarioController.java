package controller;

import service.FuncionarioService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FuncionarioController {

    private final FuncionarioService funcionarioService = new FuncionarioService();

    public void handleRequest(String action, BufferedReader in, PrintWriter out) throws IOException {
        switch (action) {
            case "INSERT_FUNCIONARIO" -> funcionarioService.inserirFuncionario(in, out);
            case "UPDATE_FUNCIONARIO" -> funcionarioService.atualizarFuncionario(in, out);
            case "GET_FUNCIONARIO" -> funcionarioService.obterFuncionario(in, out);
            case "DELETE_FUNCIONARIO" -> funcionarioService.removerFuncionario(in, out);
            case "LIST_FUNCIONARIO" -> funcionarioService.listarFuncionarios(in, out);
            default -> out.println("Ação inválida");
        }
    }
}