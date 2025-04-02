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
    private static  List<Hotel> hoteis = new ArrayList<>();
    //private static Hotel hotel;
    public void startServer() throws IOException {

        try (ServerSocket server = new ServerSocket(80)) {
            System.out.println("Aguardando conexão");
            server.setReuseAddress(true);
            while (true) {
                Socket connection = server.accept();
                System.out.println("CLiente: " + connection.getInetAddress());
                try(BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream())); PrintWriter out = new PrintWriter(connection.getOutputStream(), true)) {
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
                            case "SELECT_HOTEL" ->
                                    selectHotel(in, out);
                            default ->
                                    out.println("Erro");
                        }
                }
            }
        }
    }
}

    private static void selectHotel(BufferedReader in, PrintWriter out) throws IOException {
        if (!hoteis.isEmpty())
        {
            out.println(hoteis.size());
            for (Hotel h : hoteis){
                out.println(h.toString());
            }
        }
        else
            out.println("Hoteis não cadastrados");
        int num = Integer.parseInt(in.readLine());
        if(!hoteis.isEmpty())
        {
            if((num-1)>=0)
            {
                Hotel hotel=hoteis.get(num-1);
                out.println("Hotel " + hotel.getNome() + " selecionado");
            }
            else
                out.println("Selecione um número válido");
        } else
            out.println("Cadastre um hotel");
    }

    private static void inserirFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine());
        Hotel hotel= hoteis.get(temp-1);
        String cpf = in.readLine();
        String nome = in.readLine();
        String endereco = in.readLine();
        int salario = Integer.parseInt(in.readLine());
        String funcao = in.readLine();
        Funcionario funcionario = new Funcionario(cpf, nome, endereco, salario, funcao);
        hotel.addFuncionario(funcionario);
        out.println("FUncionario cadastrado");
    }

    private static void updateFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine());
        Hotel hotel= hoteis.get(temp-1);
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
        int temp = Integer.parseInt(in.readLine());
        Hotel hotel= hoteis.get(temp-1);
        String cpf = in.readLine();
        Funcionario funcionario = null;
        List<Pessoa> temp2 = hotel.getPessoas();
        for (Pessoa pessoa : temp2) {
            if (pessoa.getCpf().equals(cpf) && pessoa instanceof Funcionario)
                funcionario = (Funcionario) pessoa;
        }
        if (funcionario != null)
            out.println(funcionario.toString());
        else
            out.println("Funcionario não encontrado");
    }

    private static void deleteFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine());
        Hotel hotel= hoteis.get(temp-1);
        String cpf = in.readLine();

        // Funcionario funcionario = getFuncionario(cpf);
        if (hotel.removeFuncionario(cpf)) {
            // pessoas.remove(funcionario);
            out.println("Funcionário removido");
        } else {
            out.println("Funcionário não encontrado");
        }
    }

    private static void listAllFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        int temp2 = Integer.parseInt(in.readLine());
        Hotel hotel= hoteis.get(temp2-1);
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
        int temp = Integer.parseInt(in.readLine());
        Hotel hotelT= hoteis.get(temp-1);
        String cpf = in.readLine();
        String nome = in.readLine();
        String endereco = in.readLine();
        int reserva = Integer.parseInt(in.readLine());
        Cliente cliente = new Cliente(cpf, nome, endereco, reserva);
        // pessoas.add(cliente);
        hotelT.addCliente(cliente);
        out.println("Cliente cadastrado");
    }

    private static void updateCliente(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine());
        Hotel hotel= hoteis.get(temp-1);
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
        int temp2 = Integer.parseInt(in.readLine());
        Hotel hotel= hoteis.get(temp2-1);
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

    private static void deleteCliente(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine());
        Hotel hotel= hoteis.get(temp-1);
        String cpf = in.readLine();

        // Cliente cliente = getCliente(cpf);
        if (hotel.removeCliente(cpf)) {
            // pessoas.remove(cliente);
            out.println("Cliente removido");
        } else {
            out.println("Cliente não encontrado");
        }
    }

    private static void listAllCliente(BufferedReader in, PrintWriter out) throws IOException {
        int temp2 = Integer.parseInt(in.readLine());
        Hotel hotel= hoteis.get(temp2-1);
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

    private static void listAllPessoa(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine());
        Hotel hotel= hoteis.get(temp-1);
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
        Hotel temp = new Hotel(nome, endereco, quartos, vagas, classificacao);
        hoteis.add(temp);
        //out.println("Hotel cadastrado");

//        if (hotel == null) {
//            hotel = new Hotel(nome, endereco, quartos, vagas, classificacao);
//
//            out.println("Hotel criado");
//        } else {
//            out.println("Hotel já cadastrado");
//        }
    }

    private static void updateHotel(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine());
        if(!hoteis.isEmpty())
        {
            if (temp < 1 || temp > hoteis.size())
                out.println("Hotel não encontrado");
            else
            {
                Hotel hotel= hoteis.get(temp-1);
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
                out.println("Hotel atualizado com sucesso");
            }
        } else
            out.println("Hotel não encontrado");
    }

    private static void getHotel(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine());
        if(!hoteis.isEmpty())
        {
            if (temp < 1 || temp > hoteis.size())
                out.println("Hotel não encontrado");
            else
            {
                Hotel hotel= hoteis.get(temp-1);
                out.println(hotel.toString());
            }
        } else
            out.println("Sem hotéis cadastrados");
    }

    private static void deleteHotel(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine());
        if(!hoteis.isEmpty())
        {
            if (temp < 1 || temp > hoteis.size())
                out.println("Hotel não encontrado");
            else
            {
                hoteis.remove(temp - 1);
                out.println("Hotel removido com sucesso");
            }
        } else
        {
            out.println("Sem hotéis cadastrados");
        }

    }

    private static void listHotel(BufferedReader in, PrintWriter out) {
        if (!hoteis.isEmpty())
        {
            out.println(hoteis.size());
            for (Hotel h : hoteis){
                out.println(h.toString());
            }
        }
        else
            out.println("Hoteis não cadastrados");
    }
}
