package registrador;

import java.io.Serializable;
import java.util.Vector;

public class ColecaoBlocos implements Serializable
{
    private Vector<Bloco> blocos;
    
    public ColecaoBlocos() { blocos = new Vector<Bloco>(); }
    public boolean adicionaBloco(Bloco bloco)
    {
        for(int i = 0; i < blocos.size(); i++)
        {
            if(blocos.get(i).equals(bloco))
            {
                return false;
            }
        }
        blocos.add(bloco);
        return true;
    }
    public Bloco getBloco(int i) { return blocos.get(i); }
    public void limparColecao() { blocos = null; }
    public int listagemBlocos()
    {
        int i;
        for(i = 0; i < blocos.size(); i++)
        {
            System.out.println(blocos.get(i).toString());
        }
        return i;
    }
    public boolean removeBloco(Bloco bloco)
    {
        for (int i = 0; i < blocos.size(); i++)
        {
            if(blocos.get(i).equals(bloco))
            {
                blocos.remove(i);
                return true;
            }
        }
        return false;
    }
    public int qtdBlo() { return blocos.size(); }
    public Bloco pesquisaPeloNome(String nome)
    {
        int flag = pesquisaIndicePeloNome(nome);
        if(flag >= 0)
        {
            return getBloco(flag);
        }
        return null;
    }
    public int pesquisaIndicePeloNome(String nome)
    {
        int i;
        for(i = 0; i < blocos.size(); i++)
        {
            if(blocos.get(i).getNome().equals(nome))
            {
                return i;
            }
        }
        return -1;
    }
}
