package service;

import model.Hotel;
import repository.HotelRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class HotelService {

     private final List<Hotel> hoteis = HotelRepository.getHoteis();

    public void inserirHotel(BufferedReader in, PrintWriter out) throws IOException {
        String nome = in.readLine();
        String endereco = in.readLine();
        int quartos = Integer.parseInt(in.readLine());
        int vagas = Integer.parseInt(in.readLine());
        double classificacao = Double.parseDouble(in.readLine());
        Hotel hotel = new Hotel(nome, endereco, quartos, vagas, classificacao);
        hoteis.add(hotel);
        out.println("Hotel inserido com sucesso");
    }

    public void atualizarHotel(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine()) - 1;
        if (hoteis.isEmpty()) {
            out.println(0);
            return;
        }
        if (temp < 0 || temp >= hoteis.size()) {
            out.println(1);
            return;
        }
        Hotel hotel = hoteis.get(temp);
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

    public void obterHotel(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine()) - 1;
        if (hoteis.isEmpty()) {
            out.println(0);
            return;
        }
        if (temp < 0 || temp >= hoteis.size()) {
            out.println(1);
            return;
        }
        Hotel hotel = hoteis.get(temp);
        out.println(hotel.toString());
    }

    public void removerHotel(BufferedReader in, PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.readLine()) - 1;
        if (hoteis.isEmpty()) {
            out.println(0);
            return;
        }
        if (temp < 0 || temp >= hoteis.size()) {
            out.println(1);
            return;
        }
        hoteis.remove(temp);
        out.println("Hotel removido com sucesso");
    }

    public void listarHoteis(BufferedReader in, PrintWriter out) {
        if (hoteis.isEmpty()) {
            out.println(0);
            return;
        }
        out.println(hoteis.size());
        for (Hotel hotel : hoteis) {
            out.println(hotel.toString());
        }
    }
}