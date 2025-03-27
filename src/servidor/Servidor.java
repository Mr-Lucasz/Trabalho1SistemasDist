package servidor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Funcionario;
import model.Hotel;
import model.Pessoa;

public class Servidor {

    // private static List<Pessoa> pessoas;
    private static Hotel hotel;

    public void startServer() throws IOException {
        
        try (ServerSocket server = new ServerSocket(80)) {
            System.out.println("Aguardando conexão");
            server.setReuseAddress(true);
            while (true) {
                Socket connection = server.accept();
                BufferedReader in = new BufferedReader((new InputStreamReader(connection.getInputStream())));
                PrintWriter out = new PrintWriter(connection.getOutputStream(), true);

                String msg;
                while ((msg = in.readLine()) != null) {
                    System.out.println(msg);
                    switch (msg) {
                        case "INSERT_FUNCIONARIO" ->
                            inserirFuncionario(in, out);
                        case "INSERT_CLIENTE" ->
                            inserirCliente(in, out);
                        case "UPDATE_FUNCIONARIO" ->
                            updateFuncionario(in, out);
                        case "UPDATE_CLIENTE" ->
                            updateCliente(in, out);
                        case "GET_FUNCIONARIO" ->
                            getFuncionario(in, out);
                        case "GET_CLIENTE" ->
                            getCliente(in, out);
                        case "DELETE_FUNCIONARIO" ->
                            deleteFuncionario(in, out);
                        case "DELETE_CLIENTE" ->
                            deleteCliente(in, out);
                        case "LIST_FUNCIONARIO" ->
                            listAllFuncionario(in, out);
                        case "LIST_CLIENTE" ->
                            listAllCliente(in, out);
                        case "LIST_ALL" ->
                            listAllPessoa(in, out);
                        case "INSERT_HOTEL" ->
                            insertHotel(in, out);
                        case "UPDATE_HOTEL" ->
                            updateHotel(in, out);
                        case "GET_HOTEL" ->
                            getHotel(in, out);
                        case "DELETE_HOTEL" ->
                            deleteHotel(in, out);
                        case "LIST_HOTEL" ->
                            listHotel(in, out);
                        case "INSERT_PESSOA" ->
                            inserirPessoa(in, out);
                        case "UPDATE_PESSOA" ->
                            updatePessoa(in, out);
                        case "GET_PESSOA" ->
                            getPessoa(in, out);
                        case "DELETE_PESSOA" ->
                            deletePessoa(in, out);
                        case "LIST_PESSOA" ->
                            listAllPessoas(in, out);
                        default ->
                            out.println("Erro");
                    }
                }
            }

        }
    }

    private static void inserirFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        String cpf = in.readLine();
        String nome = in.readLine();
        String endereco = in.readLine();
        int salario = Integer.parseInt(in.readLine());
        String funcao = in.readLine();
        Funcionario funcionario = new Funcionario(cpf, nome, endereco, salario, funcao);
        hotel.addFuncionario(funcionario);
        // pessoas.add(funcionario);
        out.println("FUncionario cadastrado");
    }

    private static void updateFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        String cpf = in.readLine();
        // Funcionario funcionario = getFuncionario(cpf);
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

    private static void getFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        String cpf = in.readLine();
        Funcionario funcionario = null;
        List<Pessoa> temp = hotel.getPessoas();
        for (Pessoa pessoa : temp) {
            if (pessoa.getCpf().equals(cpf))
                funcionario = (Funcionario) pessoa;
        }
        if (funcionario != null)
            out.println(funcionario.toString());
        else
            out.println("Funcionario não encontrado");
    }

    // private static Funcionario getFuncionario(String cpf){
    // Funcionario funcionario = null;
    // for(Pessoa pessoa : pessoas)
    // {
    // if(pessoa.getCpf().equals(cpf) && pessoa instanceof Funcionario)
    // funcionario = (Funcionario) pessoa;
    // }
    // if(funcionario != null)
    // return funcionario;
    // else
    // return null;
    // }

    private static void deleteFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        String cpf = in.readLine();

        // Funcionario funcionario = getFuncionario(cpf);
        if (hotel.removeFuncionario(cpf)) {
            // pessoas.remove(funcionario);
            out.println("Funcionário removido");
        } else {
            out.println("Funcionário não encontrado");
        }
    }

    private static void listAllFuncionario(BufferedReader in, PrintWriter out) {
        List<Funcionario> temp = new ArrayList<>();
        List<Pessoa> pessoas = hotel.getPessoas();
        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Funcionario)
                temp.add((Funcionario) pessoa);
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

    private static void inserirCliente(BufferedReader in, PrintWriter out) throws IOException {
        String cpf = in.readLine();
        String nome = in.readLine();
        String endereco = in.readLine();
        int reserva = Integer.parseInt(in.readLine());
        Cliente cliente = new Cliente(cpf, nome, endereco, reserva);
        // pessoas.add(cliente);
        hotel.addCliente(cliente);
        out.println("Cliente cadastrado");
    }

    private static void updateCliente(BufferedReader in, PrintWriter out) throws IOException {
        String cpf = in.readLine();
        // Cliente cliente = getCliente(cpf);
        Cliente cliente = hotel.getCliente(cpf);
        if (cliente != null) {
            String nome = in.readLine();
            String endereco = in.readLine();
            int reserva = Integer.parseInt(in.readLine());
            cliente.setNome(nome);
            cliente.setEndereco(endereco);
            cliente.setReserva(reserva);

            out.println("Cliente atualizado");
        } else {
            out.println("Cliente não encontrado");
        }
    }

    private static void getCliente(BufferedReader in, PrintWriter out) throws IOException {
        String cpf = in.readLine();
        Cliente cliente = null;
        List<Pessoa> temp = hotel.getPessoas();
        for (Pessoa pessoa : temp) {
            if (pessoa.getCpf().equals(cpf) && pessoa instanceof Cliente)
                cliente = (Cliente) pessoa;
        }
        if (cliente != null)
            out.println(cliente.toString());
        else
            out.println("Cliente não encontrado");
    }

    // private static Cliente getCliente(String cpf){
    // Cliente cliente = null;
    // for(Pessoa pessoa : pessoas)
    // {
    // if(pessoa.getCpf().equals(cpf))
    // cliente = (Cliente) pessoa;
    // }
    // if(cliente != null)
    // return cliente;
    // else
    // return null;
    // }
    private static void deleteCliente(BufferedReader in, PrintWriter out) throws IOException {
        String cpf = in.readLine();

        // Cliente cliente = getCliente(cpf);
        if (hotel.removeCliente(cpf)) {
            // pessoas.remove(cliente);
            out.println("Cliente removido");
        } else {
            out.println("Cliente não encontrado");
        }
    }

    private static void listAllCliente(BufferedReader in, PrintWriter out) {
        List<Cliente> temp = new ArrayList<>();
        List<Pessoa> pessoas = hotel.getPessoas();
        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Cliente)
                temp.add((Cliente) pessoa);
        }
        if (temp.isEmpty()) {
            out.println("Nenhum cliente cadastrado.");
            return;
        }
        out.println(temp.size());
        for (Cliente cliente : temp) {
            out.println(cliente.toString());
        }
    }

    private static void listAllPessoa(BufferedReader in, PrintWriter out) {
        List<Pessoa> pessoas = hotel.getPessoas();
        if (pessoas.isEmpty()) {
            out.println("0");
            return;
        }
        out.println(pessoas.size());
        for (Pessoa pessoa : pessoas) {
            out.println(pessoa.toString());
        }
    }

    private static void insertHotel(BufferedReader in, PrintWriter out) throws IOException {
        String nome = in.readLine();
        String endereco = in.readLine();
        int quartos = Integer.parseInt(in.readLine());
        int vagas = Integer.parseInt(in.readLine());
        double classificacao = Double.parseDouble(in.readLine());
        if (hotel == null) {
            hotel = new Hotel(nome, endereco, quartos, vagas, classificacao);
            out.println("Hotel criado");
        } else {
            out.println("Hotel já cadastrado");
        }
    }

    private static void updateHotel(BufferedReader in, PrintWriter out) throws IOException {
        if (hotel != null) {
            String nome = in.readLine();
            String endereco = in.readLine();
            int quartos = Integer.parseInt(in.readLine());
            int vagas = Integer.parseInt(in.readLine());
            double classificacao = Double.parseDouble(in.readLine());
            hotel.setNome(nome);
            hotel.setEndereco(endereco);
            hotel.setQuartos(quartos);
            hotel.setVagas(vagas);
            hotel.setClassificacao(classificacao);

            out.println("Hoteal atualizado");
        } else {
            out.println("Hotel não cadastrado");
        }
    }

    private static void getHotel(BufferedReader in, PrintWriter out) {
        out.println(hotel.toString());
    }

    private static void deleteHotel(BufferedReader in, PrintWriter out) {
        if (hotel == null)
            out.println("Hotel não cadastrado");
        else {
            hotel = null;
            out.println("Hotel excluido");
        }
    }

    private static void listHotel(BufferedReader in, PrintWriter out) {
        if (hotel != null)
            out.println(hotel.toString());
        else
            out.println("Hotel não cadastrado");
    }

    private static void inserirPessoa(BufferedReader in, PrintWriter out) throws IOException {
        String cpf = in.readLine();
        String nome = in.readLine();
        String endereco = in.readLine();
        Pessoa pessoa = new Pessoa(cpf, nome, endereco);
        hotel.getPessoas().add(pessoa);
    }

    private static void updatePessoa(BufferedReader in, PrintWriter out) throws IOException {
        String cpf = in.readLine();
        Pessoa pessoa = getPessoaByCpf(cpf);
        if (pessoa != null) {
            String nome = in.readLine();
            String endereco = in.readLine();
            pessoa.setNome(nome);
            pessoa.setEndereco(endereco);
            out.println("Pessoa atualizada com sucesso");
        } else {
            out.println("Pessoa não encontrada");
        }
    }

    private static void getPessoa(BufferedReader in, PrintWriter out) throws IOException {
        if (hotel.getPessoas().isEmpty()) {
            out.println("Sem pessoas cadastradas");
            return;
        }
        String cpf = in.readLine();
        Pessoa pessoa = getPessoaByCpf(cpf);
        if (pessoa != null) {
            out.println(pessoa.toString());
        } else {
            out.println("Pessoa não encontrada");
        }
    }

    private static void deletePessoa(BufferedReader in, PrintWriter out) throws IOException {
        if (hotel.getPessoas().isEmpty()) {
            out.println("Sem pessoas cadastradas");
            return;
        }
        String cpf = in.readLine();
        Pessoa pessoa = getPessoaByCpf(cpf);
        if (pessoa != null) {
            hotel.getPessoas().remove(pessoa);
            out.println("Pessoa removida com sucesso");
        } else {
            out.println("Pessoa não encontrada");
        }
    }

    private static void listAllPessoas(BufferedReader in, PrintWriter out) {
        List<Pessoa> pessoas = hotel.getPessoas();
        if (pessoas.isEmpty()) {
            out.println("0");
            return;
        }
        out.println(pessoas.size());
        for (Pessoa pessoa : pessoas) {
            out.println(pessoa.toString());
        }
    }

    private static Pessoa getPessoaByCpf(String cpf) {
        for (Pessoa pessoa : hotel.getPessoas()) {
            if (pessoa.getCpf().equals(cpf)) {
                return pessoa;
            }
        }
        return null;
    }
}
