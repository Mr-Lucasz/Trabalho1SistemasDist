package ControllerServer;

import java.io.IOException;
import java.io.PrintWriter;

public class ControllerGeral {
    protected String request;
    protected PrintWriter out;

    public ControllerGeral(String request, PrintWriter out)
    {
        this.request = request;
        this.out = out;
    }

    public void selecionaClasse() throws IOException
    {
       //aqui a ideia é ter um switch case que muda dependendo do que foi requisitado no client
    }
}
