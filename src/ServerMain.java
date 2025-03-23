import servidor.Servidor;

import java.io.IOException;

public class ServerMain {
    //Esse aq inicia o servidor ent vai precisar de uma main pro cliente
    //Na main do cliente ele vai interagir com o servidor
    public static void main(String[] args) throws IOException {
        Servidor servidor = new Servidor();
        servidor.startServer();
    }
}