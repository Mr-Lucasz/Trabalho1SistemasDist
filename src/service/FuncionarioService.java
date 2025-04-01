package service;

import model.Funcionario;
import model.Hotel;
import model.Pessoa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioService {

    private static Hotel hotel;

    public void inserirFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        String cpf = in.readLine();
        String nome = in.readLine();
        String endereco = in.readLine();
        int salario = Integer.parseInt(in.readLine());
        String funcao = in.readLine();
        Funcionario funcionario = new Funcionario(cpf, nome, endereco, salario, funcao);
        hotel.addFuncionario(funcionario);
        out.println("Funcionario cadastrado");
    }

    public void updateFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        String cpf = in.readLine();
        Funcionario funcionario = hotel.getFuncionario(cpf);
        if (funcionario != null) {
            String nome = in.readLine();
            String endereco = in.readLine();
            int salario = Integer.parseInt(in.readLine());
            String funcao = in.readLine();
            funcionario.setNome(nome);
            funcionario.setFuncao(funcao);
            funcionario.setEndereco(endereco);
            funcionario.setSalario(salario);
            out.println("Funcionario atualizado");
        } else {
            out.println("Funcionario não encontrado");
        }
    }

    public void getFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        String cpf = in.readLine();
        Funcionario funcionario = hotel.getFuncionario(cpf);
        if (funcionario != null) {
            out.println(funcionario.toString());
        } else {
            out.println("Funcionario não encontrado");
        }
    }

    public void deleteFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        String cpf = in.readLine();
        if (hotel.removeFuncionario(cpf)) {
            out.println("Funcionário removido");
        } else {
            out.println("Funcionário não encontrado");
        }
    }

    public void listAllFuncionario(PrintWriter out) {
        List<Funcionario> temp = new ArrayList<>();
        List<Pessoa> pessoas = hotel.getPessoas();
        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Funcionario) {
                temp.add((Funcionario) pessoa);
            }
        }
        if (temp.isEmpty()) {
            out.println("Nenhum funcionário cadastrado.");
            return;
        }
        out.println(temp.size());
        for (Funcionario funcionario : temp) {
            out.println(funcionario.toString());
        }
    }

}
