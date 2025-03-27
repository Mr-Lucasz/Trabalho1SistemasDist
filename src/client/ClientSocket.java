package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket {
    public String conectar(String ip, String mensagem) throws IOException {
        System.out.println("Conectando ao servidor...");
        try (Socket conn = new Socket(ip, 80)) {
            System.out.println("Conectado ao servidor.");
            PrintWriter out = new PrintWriter(conn.getOutputStream(), true); // true para autoflush
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            // Send msg in server
            out.println(mensagem);

            // Receber response
            StringBuilder resposta = new StringBuilder();
            String linha;
            while ((linha = in.readLine()) != null) {
                resposta.append(linha).append("\n");
            }

            return resposta.toString().trim();
        }
    }
}
