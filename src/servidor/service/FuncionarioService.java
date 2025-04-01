package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.Funcionario;
import model.Hotel;
import model.Pessoa;

public class FuncionarioService {

    private static Hotel hotel;

    public void inserirFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        String cpf = in.readLine();
        String nome = in.readLine();
        String endereco = in.readLine();
        int salario = Integer.parseInt(in.readLine());
        String funcao = in.readLine();
        Funcionario funcionario = new Funcionario(cpf, nome, endereco, salario, funcao);
        hotel.getPessoas().add(funcionario);
        out.println("Funcionário cadastrado");
    }

    public void updateFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        String cpf = in.readLine();
        Funcionario funcionario = findFuncionarioByCpf(cpf);
        if (funcionario != null) {
            String nome = in.readLine();
            String endereco = in.readLine();
            int salario = Integer.parseInt(in.readLine());
            String funcao = in.readLine();
            funcionario.setNome(nome);
            funcionario.setEndereco(endereco);
            funcionario.setSalario(salario);
            funcionario.setFuncao(funcao);
            out.println("Funcionário atualizado");
        } else {
            out.println("Funcionário não encontrado");
        }
    }

    public void getFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        String cpf = in.readLine();
        Funcionario funcionario = findFuncionarioByCpf(cpf);
        if (funcionario != null) {
            out.println(funcionario.toString());
        } else {
            out.println("Funcionário não encontrado");
        }
    }

    public void deleteFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        String cpf = in.readLine();
        Funcionario funcionario = findFuncionarioByCpf(cpf);
        if (funcionario != null) {
            hotel.getPessoas().remove(funcionario); // Remove diretamente da lista de pessoas
            out.println("Funcionário removido");
        } else {
            out.println("Funcionário não encontrado");
        }
    }

    public void listAllFuncionario(PrintWriter out) {
        List<Funcionario> funcionarios = new ArrayList<>();
        for (Pessoa pessoa : hotel.getPessoas()) {
            if (pessoa instanceof Funcionario) {
                funcionarios.add((Funcionario) pessoa);
            }
        }
        if (funcionarios.isEmpty()) {
            out.println("Nenhum funcionário cadastrado.");
            return;
        }
        out.println(funcionarios.size());
        for (Funcionario funcionario : funcionarios) {
            out.println(funcionario.toString());
        }
    }

    private Funcionario findFuncionarioByCpf(String cpf) {
        for (Pessoa pessoa : hotel.getPessoas()) {
            if (pessoa instanceof Funcionario && pessoa.getCpf().equals(cpf)) {
                return (Funcionario) pessoa;
            }
        }
        return null;
    }
}