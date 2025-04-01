package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Hotel;
import model.Pessoa;

public class ClienteService {
    private static Hotel hotel;

    public void inserirCliente(BufferedReader in, PrintWriter out) throws IOException {
        hotel = HotelService.getSelectedHotel(); // Atualiza o hotel primeiro
        if (hotel == null) {
            out.println("Erro: Nenhum hotel selecionado.");
            return;
        }
        String cpf = in.readLine();
        String nome = in.readLine();
        String endereco = in.readLine();
        String reservaStr = in.readLine();
    
        int reserva;
        try {
            reserva = Integer.parseInt(reservaStr);
        } catch (NumberFormatException e) {
            out.println("Erro: O campo 'reserva' deve ser um número inteiro.");
            return;
        }
        Cliente cliente = new Cliente(cpf, nome, endereco, reserva);
        hotel.getPessoas().add(cliente);
        out.println("Cliente cadastrado");
    }
    
    public void updateCliente(BufferedReader in, PrintWriter out) throws IOException {
        hotel = HotelService.getSelectedHotel(); // Atualiza a variável hotel
        if (hotel == null) {
            out.println("Erro: Nenhum hotel selecionado.");
            return;
        }
        String cpf = in.readLine();
        Cliente cliente = findClienteByCpf(cpf);
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
        hotel = HotelService.getSelectedHotel(); // Atualiza a variável hotel
        if (hotel == null) {
            out.println("Erro: Nenhum hotel selecionado.");
            return;
        }
        String cpf = in.readLine();
        Cliente cliente = findClienteByCpf(cpf);
        if (cliente != null) {
            out.println(cliente.toString());
        } else {
            out.println("Cliente não encontrado");
        }
    }

    public void deleteCliente(BufferedReader in, PrintWriter out) throws IOException {
        hotel = HotelService.getSelectedHotel(); // Atualiza a variável hotel
        if (hotel == null) {
            out.println("Erro: Nenhum hotel selecionado.");
            return;
        }
        String cpf = in.readLine();
        Cliente cliente = findClienteByCpf(cpf);
        if (cliente != null) {
            hotel.getPessoas().remove(cliente); // Remove diretamente da lista de pessoas
            out.println("Cliente removido");
        } else {
            out.println("Cliente não encontrado");
        }
    }

    public void listAllCliente(PrintWriter out) {
        hotel = HotelService.getSelectedHotel(); // Atualiza a variável hotel
        if (hotel == null) {
            out.println("Erro: Nenhum hotel selecionado.");
            return;
        }
        List<Cliente> clientes = new ArrayList<>();
        for (Pessoa pessoa : hotel.getPessoas()) {
            if (pessoa instanceof Cliente) {
                clientes.add((Cliente) pessoa);
            }
        }
        if (clientes.isEmpty()) {
            out.println("Nenhum cliente cadastrado.");
            return;
        }
        out.println(clientes.size());
        for (Cliente cliente : clientes) {
            out.println(cliente.toString());
        }
    }

    private Cliente findClienteByCpf(String cpf) {
        for (Pessoa pessoa : hotel.getPessoas()) {
            if (pessoa instanceof Cliente && pessoa.getCpf().equals(cpf)) {
                return (Cliente) pessoa;
            }
        }
        return null;
    }
}