package service;

import model.Cliente;
import model.Hotel;
import model.Pessoa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {
    private static Hotel hotel;

    public void inserirCliente(BufferedReader in, PrintWriter out) throws IOException {
        String cpf = in.readLine();
        String nome = in.readLine();
        String endereco = in.readLine();
        int reserva = Integer.parseInt(in.readLine());
        Cliente cliente = new Cliente(cpf, nome, endereco, reserva);
        hotel.addCliente(cliente);
        out.println("Cliente cadastrado");
    }

    public void updateCliente(BufferedReader in, PrintWriter out) throws IOException {
        String cpf = in.readLine();
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

    public void getCliente(BufferedReader in, PrintWriter out) throws IOException {
        String cpf = in.readLine();
        Cliente cliente = hotel.getCliente(cpf);
        if (cliente != null) {
            out.println(cliente.toString());
        } else {
            out.println("Cliente não encontrado");
        }
    }

    public void deleteCliente(BufferedReader in, PrintWriter out) throws IOException {
        String cpf = in.readLine();
        if (hotel.removeCliente(cpf)) {
            out.println("Cliente removido");
        } else {
            out.println("Cliente não encontrado");
        }
    }

    public void listAllCliente(PrintWriter out) {
        List<Cliente> temp = new ArrayList<>();
        List<Pessoa> pessoas = hotel.getPessoas();
        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Cliente) {
                temp.add((Cliente) pessoa);
            }
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
}