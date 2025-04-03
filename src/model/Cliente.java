package model;

public class Cliente extends Pessoa {
    private int reserva;

    public Cliente(String cpf, String nome, String endereco, int reserva) {
        super(cpf, nome, endereco);
        this.reserva = reserva;
    }

    public int getReserva() {
        return reserva;
    }

    public void setReserva(int reserva) {
        this.reserva = reserva;
    }

    @Override
    public String toString() {
        return super.toString() + ";" + reserva + ";CLIENTE";
    }
}