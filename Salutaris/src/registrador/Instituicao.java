package registrador;

import java.io.Serializable;

public class Instituicao extends InsBloSal implements Serializable
{
    private String cidade;
    private ColecaoBlocos colblo = null;

    public Instituicao(String nome, String cidade)
    {
        super(nome);
        this.cidade = cidade;
        colblo = new ColecaoBlocos();
    }
    public String getCidade() { return this.cidade; }
    public ColecaoBlocos getColblo() { return colblo; }
    public int qtdBlo() { return colblo.qtdBlo(); }
    public String toString() { return "Nome: " + super.nome + "\nCidade: " + this.cidade; }
    public boolean equals(Instituicao instituicao)
    {
        if(this.nome.equals(instituicao.getNome()) && this.cidade.equals(instituicao.getCidade()))
        {
            return true;
        }
        return false;
    }
}
