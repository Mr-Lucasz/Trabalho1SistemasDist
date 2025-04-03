package service;

import model.Funcionario;
import model.Hotel;
import repository.HotelRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioService {

    private final List<Hotel> hoteis = HotelRepository.getHoteis();

    public void inserirFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine()) - 1;
        if (hoteis.isEmpty()) {
            out.println(0);
            return;
        }
        if (temp < 0 || temp >= hoteis.size()) {
            out.println(1);
            return;
        }
        out.println(-1);
        Hotel hotel = hoteis.get(temp);
        String cpf = in.readLine();
        String nome = in.readLine();
        String endereco = in.readLine();
        int salario = Integer.parseInt(in.readLine());
        String funcao = in.readLine();
        Funcionario funcionario = new Funcionario(cpf, nome, endereco, salario, funcao);
        hotel.addFuncionario(funcionario);
    }

    public void atualizarFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine()) - 1;
        if (hoteis.isEmpty()) {
            out.println(0);
            return;
        }
        if (temp < 0 || temp >= hoteis.size()) {
            out.println(1);
            return;
        }
        out.println(-1);
        Hotel hotel = hoteis.get(temp);
        String cpf = in.readLine();
        Funcionario funcionario = hotel.getFuncionario(cpf);
        if (funcionario != null) {
            String nome = in.readLine();
            String endereco = in.readLine();
            int salario = Integer.parseInt(in.readLine());
            String funcao = in.readLine();
            funcionario.setNome(nome);
            funcionario.setEndereco(endereco);
            funcionario.setSalario(salario);
            funcionario.setFuncao(funcao);
            out.println("Funcionário atualizado com sucesso");
        } else {
            out.println("Funcionário não encontrado");
        }
    }

    public void obterFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine()) - 1;
        if (hoteis.isEmpty()) {
            out.println(0);
            return;
        }
        if (temp < 0 || temp >= hoteis.size()) {
            out.println(1);
            return;
        }
        out.println(-1);
        Hotel hotel = hoteis.get(temp);
        String cpf = in.readLine();
        out.println(hotel.getStringFuncionario(cpf));
    }

    public void removerFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine()) - 1;
        if (hoteis.isEmpty()) {
            out.println(0);
            return;
        }
        if (temp < 0 || temp >= hoteis.size()) {
            out.println(1);
            return;
        }
        out.println(-1);
        Hotel hotel = hoteis.get(temp);
        String cpf = in.readLine();
        out.println(hotel.removeFuncionario(cpf));
    }

    public void listarFuncionarios(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine()) - 1;
        if (hoteis.isEmpty()) {
            out.println(0);
            return;
        }
        if (temp < 0 || temp >= hoteis.size()) {
            out.println(1);
            return;
        }
        out.println(-1);
        Hotel hotel = hoteis.get(temp);
        List<Funcionario> funcionarios = new ArrayList<>();
        for (var pessoa : hotel.getPessoas()) {
            if (pessoa instanceof Funcionario) {
                funcionarios.add((Funcionario) pessoa);
            }
        }
        if (funcionarios.isEmpty()) {
            out.println(0);
            return;
        }
        out.println(funcionarios.size());
        for (Funcionario funcionario : funcionarios) {
            out.println(funcionario.toString());
        }
    }
}