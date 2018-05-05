package registrador;

import servidor.Comando;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Dispositivo implements Serializable
{
    String ID;
    String nome;
    String endereco;
    String tecnologia;
    boolean excecao;
    ArrayList<Comando> comandos;

    public Dispositivo(String ID, String nome, String endereco, String tecnologia, ArrayList<Comando> comandos)
    {
        this.ID = ID;
        this.nome = nome;
        this.endereco = endereco;
        this.tecnologia = tecnologia;
        this.excecao = false;
        this.comandos = comandos;
    }

    public String getID() { return ID; }

    public String getNome() { return nome; }

    public String getEndereco() { return endereco; }

    public String getTecnologia() { return tecnologia; }

    public boolean getExcecao() { return excecao; }

    public ArrayList<Comando> getComandos() { return comandos; }

    public void setID(String ID) { this.ID = ID; }

    public void setNome(String nome) { this.nome = nome; }

    public void setEndereco(String endereco) { this.endereco = endereco; }

    public void setTecnologia(String tecnologia) { this.tecnologia = tecnologia; }

    public void setExecao(boolean excecao) { this.excecao = excecao; }

    public void setComandos(ArrayList<Comando> comandos) { this.comandos = comandos; }
}
