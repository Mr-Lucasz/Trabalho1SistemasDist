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
    public void startServer() throws IOException {

        try (ServerSocket server = new ServerSocket(80)) {
            System.out.println("Aguardando conexão");
            server.setReuseAddress(true);
            while (true) {
                Socket connection = server.accept();
                System.out.println("CLiente: " + connection.getInetAddress());
                try(BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    PrintWriter out = new PrintWriter(connection.getOutputStream(), true)) {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println(msg);
                        switch (msg) {
                            case "INSERT_FUNCIONARIO" ->
                                    insertFuncionario(in, out);
                            case "INSERT_CLIENTE" ->
                                    insertCliente(in, out);
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
                                    listFuncionario(in, out);
                            case "LIST_CLIENTE" ->
                                    listCliente(in, out);
                            case "LIST_ALL" ->
                                    listPessoas(in, out);
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
                            default ->
                                    out.println("Erro");
                        }
                }
            }
        }
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
            out.println("0");
    }

    private static void insertCliente(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine())-1;
        if(hoteis.isEmpty())
        {
            out.println(0);
            return;
        }
        if (temp < 0 || temp >= hoteis.size())
        {
            out.println(1);
            return;
        }
        out.println(-1);
        Hotel hotel= hoteis.get(temp);
        String cpf = in.readLine();
        String nome = in.readLine();
        String endereco = in.readLine();
        int reserva = Integer.parseInt(in.readLine());
        Cliente cliente = new Cliente(cpf, nome, endereco, reserva);
        hotel.addCliente(cliente);
    }

    private static void updateCliente(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine())-1;
        if(hoteis.isEmpty())
        {
            out.println(0);
            return;
        }
        if (temp < 0 || temp >= hoteis.size())
        {
            out.println(1);
            return;
        }
        out.println(-1);
        Hotel hotel= hoteis.get(temp);
        String cpf = in.readLine();
        Cliente cliente = hotel.getCliente(cpf);
        String nome = in.readLine();
        String endereco = in.readLine();
        int reserva = Integer.parseInt(in.readLine());
        if (cliente != null)
        {
            cliente.setNome(nome);
            cliente.setEndereco(endereco);
            cliente.setReserva(reserva);

            out.println("Cliente atualizado com sucesso");
        } else
        {
            out.println("Cliente não encontrado");
        }
    }

    private static void getCliente(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine())-1;
        if(hoteis.isEmpty())
        {
            out.println(0);
            return;
        }
        if (temp < 0 || temp >= hoteis.size())
        {
            out.println(1);
            return;
        }
        out.println(-1);
        Hotel hotel= hoteis.get(temp);
        String cpf = in.readLine();
        out.println(hotel.getStringCLiente(cpf));
    }

    private static void deleteCliente(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine())-1;
        if(hoteis.isEmpty())
        {
            out.println(0);
            return;
        }
        if (temp < 0 || temp >= hoteis.size())
        {
            out.println(1);
            return;
        }
        out.println(-1);
        Hotel hotel= hoteis.get(temp);
        String cpf = in.readLine();
        out.println(hotel.removeCliente(cpf));
    }

    private static void listCliente(BufferedReader in, PrintWriter out) throws IOException {
        int temp2 = Integer.parseInt(in.readLine())-1;
        if(hoteis.isEmpty())
        {
            out.println(0);
            return;
        }
        if (temp2 < 0 || temp2 >= hoteis.size())
        {
            out.println(1);
            return;
        }
        out.println(-1);
        Hotel hotel= hoteis.get(temp2);
        List<Cliente> temp = new ArrayList<>();
        List<Pessoa> pessoas = hotel.getPessoas();
        for (Pessoa pessoa : pessoas)
        {
            if (pessoa instanceof Cliente)
                temp.add((Cliente) pessoa);
        }
        if (temp.isEmpty())
        {
            out.println("0");
            return;
        }
        out.println(temp.size());
        for (Cliente cliente : temp)
        {
            out.println(cliente.toString());
        }
    }

    private static void insertFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine())-1;
        if(hoteis.isEmpty())
        {
            out.println(0);
            return;
        }
        if (temp < 0 || temp >= hoteis.size())
        {
            out.println(1);
            return;
        }
        out.println(-1);
        Hotel hotel= hoteis.get(temp);
        String cpf = in.readLine();
        String nome = in.readLine();
        String endereco = in.readLine();
        int salario = Integer.parseInt(in.readLine());
        String funcao = in.readLine();
        Funcionario funcionario = new Funcionario(cpf, nome, endereco, salario, funcao);
        hotel.addFuncionario(funcionario);
    }

    private static void updateFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine())-1;
        if(hoteis.isEmpty())
        {
            out.println(0);
            return;
        }
        if (temp < 0 || temp >= hoteis.size())
        {
            out.println(1);
            return;
        }
        out.println(-1);
        Hotel hotel= hoteis.get(temp);
        String cpf = in.readLine();
        Funcionario funcionario = hotel.getFuncionario(cpf);
        String nome = in.readLine();
        String endereco = in.readLine();
        int salario = Integer.parseInt(in.readLine());
        String funcao = in.readLine();
        if (funcionario != null)
        {
            funcionario.setNome(nome);
            funcionario.setFuncao(funcao);
            funcionario.setEndereco(endereco);
            funcionario.setSalario(salario);
            out.println("Funcionario atualizado com sucesso");
        } else
        {
            out.println("Funcionario não encontrado");
        }

    }

    private static void getFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine())-1;
        if(hoteis.isEmpty())
        {
            out.println(0);
            return;
        }
        if (temp < 0 || temp >= hoteis.size())
        {
            out.println(1);
            return;
        }
        out.println(-1);
        Hotel hotel= hoteis.get(temp);
        String cpf = in.readLine();
        out.println(hotel.getStringFuncionario(cpf));
    }

    private static void deleteFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine())-1;
        if(hoteis.isEmpty())
        {
            out.println(0);
            return;
        }
        if (temp < 0 || temp >= hoteis.size())
        {
            out.println(1);
            return;
        }
        out.println(-1);
        Hotel hotel= hoteis.get(temp);
        String cpf = in.readLine();
        out.println(hotel.removeFuncionario(cpf));
    }

    private static void listFuncionario(BufferedReader in, PrintWriter out) throws IOException {
        int temp2 = Integer.parseInt(in.readLine())-1;
        if(hoteis.isEmpty())
        {
            out.println(0);
            return;
        }
        if (temp2 < 0 || temp2 >= hoteis.size())
        {
            out.println(1);
            return;
        }
        out.println(-1);
        Hotel hotel= hoteis.get(temp2);
        List<Funcionario> temp = new ArrayList<>();
        List<Pessoa> pessoas = hotel.getPessoas();
        for (Pessoa pessoa : pessoas)
        {
            if (pessoa instanceof Funcionario)
                temp.add((Funcionario) pessoa);
        }
        if (temp.isEmpty())
        {
            out.println("0");
            return;
        }
        out.println(temp.size());
        for (Funcionario funcionario : temp)
        {
            out.println(funcionario.toString());
        }
    }

    private static void listPessoas(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine())-1;
        if(hoteis.isEmpty())
        {
            out.println(0);
            return;
        }
        if (temp < 0 || temp >= hoteis.size())
        {
            out.println(1);
            return;
        }
        out.println(-1);
        Hotel hotel= hoteis.get(temp);
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


}
