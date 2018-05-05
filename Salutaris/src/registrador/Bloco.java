package registrador;

import java.io.Serializable;

public class Bloco extends InsBloSal implements Serializable
{
    private ColecaoSalas colsal = null;

    public Bloco(String nome)
    {
        super(nome);
        colsal = new ColecaoSalas();
    }

    public ColecaoSalas getColsal() { return colsal; }
    public int qtdSal() { return colsal.qtdSal(); }
    public String toString() { return "Bloco: " + nome; }
    public boolean equals(Bloco bloco) { return this.nome.equals(bloco.getNome()); }
}
