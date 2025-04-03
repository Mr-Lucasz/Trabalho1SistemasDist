package repository;

import model.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelRepository {
    private static final List<Hotel> hoteis = new ArrayList<>();

    public static List<Hotel> getHoteis() {
        return hoteis;
    }
}