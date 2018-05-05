package registrador;

import java.io.Serializable;

public abstract class InsBloSal implements Serializable
{
    String nome;

    public InsBloSal(String nome)
    {
        this.nome = nome;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
