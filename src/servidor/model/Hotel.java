package servidor.model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String nome;
    private String endereco;
    private int quartos;
    private int vagas;
    private double classificacao;
    private List<Pessoa> pessoas;

    public Hotel(String nome, String endereco, int quartos, int vagas, double classificacao) {
        this.nome = nome;
        this.endereco = endereco;
        this.quartos = quartos;
        this.vagas = vagas;
        this.classificacao = classificacao;
        this.pessoas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getQuartos() {
        return quartos;
    }

    public void setQuartos(int quartos) {
        this.quartos = quartos;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    @Override
    public String toString() {
        return "NOME: " + nome + "  ENDERECO: " + endereco + "  QUARTOS: " + quartos + "  VAGAS: " + vagas + "  CLASSIFICAÇÃO: " + classificacao;
    }
}