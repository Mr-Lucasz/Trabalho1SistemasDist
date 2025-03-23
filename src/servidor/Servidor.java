package servidor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    BufferedReader in;
    PrintWriter out;
    public void startServer() throws IOException {
        ServerSocket server = new ServerSocket(80);
        System.out.println("Aguardando conexão");
        server.setReuseAddress(true);
        while(true)
        {
            try
            {
                Socket connection = server.accept();
                in = new BufferedReader((new InputStreamReader(connection.getInputStream())));
                out = new PrintWriter(connection.getOutputStream(), true);

                System.out.println("Aguardando mensagem");
                String msg = in.readLine();
                // a partir daqui pensei em chamar o controllergeral e enviar pra ele a mensagem que o client mandou
                //aí a partir de lá fazer os switch case

                connection.close();
            } catch (Exception e)
            {
                out.print("Server encerrado");
                server.close();
            }
        }

    }
}
