package service;

import model.Hotel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;



public class HotelService {

    private static final List<Hotel> hoteis = new ArrayList<>();
    private static Hotel hotel;


    public void insertHotel(BufferedReader in, PrintWriter out) throws IOException {
        String nome = in.readLine();
        String endereco = in.readLine();
        int quartos = Integer.parseInt(in.readLine());
        int vagas = Integer.parseInt(in.readLine());
        double classificacao = Double.parseDouble(in.readLine());
        Hotel temp = new Hotel(nome, endereco, quartos, vagas, classificacao);
        hoteis.add(temp);
        out.println("Hotel cadastrado");
    }

    public void updateHotel(BufferedReader in, PrintWriter out) throws IOException {
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
            out.println("Hotel atualizado");
        } else {
            out.println("Hotel não cadastrado");
        }
    }

    public void getHotel(PrintWriter out) {
        out.println(hotel.toString());
    }

    public void deleteHotel(PrintWriter out) {
        if (hotel == null) {
            out.println("Hotel não selecionado");
        } else {
            hoteis.remove(hotel);
            hotel = null;
            out.println("Hotel excluido");
        }
    }

    public void listHotel(PrintWriter out) {
        if (!hoteis.isEmpty()) {
            out.println(hoteis.size());
            for (Hotel h : hoteis) {
                out.println(h.toString());
            }
        } else {
            out.println("Hoteis não cadastrados");
        }
    }

    public void selectHotel(BufferedReader in, PrintWriter out) throws IOException {
        if (!hoteis.isEmpty()) {
            out.println(hoteis.size());
            for (Hotel h : hoteis) {
                out.println(h.toString());
            }
        } else {
            out.println("Hoteis não cadastrados");
        }
        int num = Integer.parseInt(in.readLine());
        if (!hoteis.isEmpty()) {
            if ((num - 1) >= 0) {
                hotel = hoteis.get(num - 1);
                out.println("Hotel " + hotel.getNome() + " selecionado");
            } else {
                out.println("Selecione um número válido");
            }
        } else {
            out.println("Cadastre um hotel");
        }
    }

}
