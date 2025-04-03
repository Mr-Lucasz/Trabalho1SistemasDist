
package controller;

import service.HotelService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class HotelController {

    private final HotelService hotelService = new HotelService();

    public void handleRequest(String action, BufferedReader in, PrintWriter out) throws IOException {
        switch (action) {
            case "INSERT_HOTEL" -> hotelService.inserirHotel(in, out);
            case "UPDATE_HOTEL" -> hotelService.atualizarHotel(in, out);
            case "GET_HOTEL" -> hotelService.obterHotel(in, out);
            case "DELETE_HOTEL" -> hotelService.removerHotel(in, out);
            case "LIST_HOTEL" -> hotelService.listarHoteis(in, out);
            default -> out.println("Ação inválida");
        }
    }
}
