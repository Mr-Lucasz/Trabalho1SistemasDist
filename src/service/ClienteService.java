package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Hotel;

public class ClienteService {

    private static final List<Hotel> hoteis = new ArrayList<>();

    public void inserirCliente(BufferedReader in, PrintWriter out) throws IOException {
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
        int reserva = Integer.parseInt(in.readLine());
        Cliente cliente = new Cliente(cpf, nome, endereco, reserva);

        hotel.addCliente(cliente);
    }

    public void atualizarCliente(BufferedReader in, PrintWriter out) throws IOException {
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
        Cliente cliente = hotel.getCliente(cpf);
        if (cliente != null) {
            String nome = in.readLine();
            String endereco = in.readLine();
            int reserva = Integer.parseInt(in.readLine());
            cliente.setNome(nome);
            cliente.setEndereco(endereco);
            cliente.setReserva(reserva);
            out.println("Cliente atualizado com sucesso");
        } else {
            out.println("Cliente não encontrado");
        }
    }

    public void obterCliente(BufferedReader in, PrintWriter out) throws IOException {
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
        out.println(hotel.getStringCLiente(cpf));
    }

    public void removerCliente(BufferedReader in, PrintWriter out) throws IOException {
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
        out.println(hotel.removeCliente(cpf));
    }

    public void listarClientes(BufferedReader in, PrintWriter out) throws IOException {
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
        List<Cliente> clientes = new ArrayList<>();
        for (var pessoa : hotel.getPessoas()) {
            if (pessoa instanceof Cliente) {
                clientes.add((Cliente) pessoa);
            }
        }
        if (clientes.isEmpty()) {
            out.println(0);
            return;
        }
        out.println(clientes.size());
        for (Cliente cliente : clientes) {
            out.println(cliente.toString());
        }
    }
}