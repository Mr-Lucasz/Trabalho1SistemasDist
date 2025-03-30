import servidor.Servidor;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        Servidor servidor = new Servidor();
        servidor.startServer();
    }
}