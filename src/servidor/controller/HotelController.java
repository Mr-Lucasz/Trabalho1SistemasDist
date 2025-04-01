package servidor.controller;

import servidor.service.HotelService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class HotelController {

    private final HotelService hotelService = new HotelService();

    public void handleRequest(String action, BufferedReader in, PrintWriter out) throws IOException {
        switch (action) {
            case "INSERT_HOTEL" -> hotelService.insertHotel(in, out);
            case "UPDATE_HOTEL" -> hotelService.updateHotel(in, out);
            case "GET_HOTEL" -> hotelService.getHotel(out);
            case "DELETE_HOTEL" -> hotelService.deleteHotel(out);
            case "LIST_HOTEL" -> hotelService.listHotel(out);
            case "SELECT_HOTEL" -> hotelService.selectHotel(in, out);
            default -> out.println("Erro");
        }
    }
}
