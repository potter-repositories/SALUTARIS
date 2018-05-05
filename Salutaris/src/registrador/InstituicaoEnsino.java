package registrador;

import java.io.Serializable;

public class InstituicaoEnsino extends Instituicao implements Serializable
{
    private String campus;

    public InstituicaoEnsino(String nome, String cidade, String campus)
    {
        super(nome,cidade);
        this.campus = campus;
    }
    public String getCampus() { return this.campus; }
    public String toString() { return "---Instituição de Ensino---\n\n" + super.toString() + "\nCampus: " + campus + "\n"; }
    public boolean equals(InstituicaoEnsino instituicaoEnsino)
    {
        if(super.equals((Instituicao)instituicaoEnsino) && this.campus.equals(instituicaoEnsino.getCampus()))
        {
            return true;
        }
        return false;
    }
}
