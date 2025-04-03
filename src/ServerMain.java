

import java.io.IOException;

import servidor.Servidor;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        Servidor servidor = new Servidor();
        servidor.startServer();
    }
}