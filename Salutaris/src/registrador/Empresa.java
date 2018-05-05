package registrador;

import java.io.Serializable;

public class Empresa extends Instituicao implements Serializable
{
    private String unidade;

    public Empresa(String nome, String cidade, String unidade)
    {
        super(nome,cidade);
        this.unidade = unidade;
    }
    public String getUnidade() { return this.unidade; }
    public String toString() { return "---Empresa---\n\n" + super.toString() + "\nUnidade: " + unidade + "\n"; }
    public boolean equals(Empresa empresa)
    {
        if(super.equals((Instituicao)empresa) && this.unidade.equals(empresa.getUnidade()))
        {
            return true;
        }
        return false;
    }
}
