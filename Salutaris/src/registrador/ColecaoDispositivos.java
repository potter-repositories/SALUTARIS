package registrador;

import java.io.Serializable;
import java.util.Vector;

public class ColecaoDispositivos implements Serializable
{
    private Vector<Dispositivo> dispositivos;

    public ColecaoDispositivos() { dispositivos = new Vector<Dispositivo>(); }

    public boolean adicionaDispositivo(Dispositivo dispositivo)
    {
        for(int i = 0; i < dispositivos.size(); i++)
        {
            if(dispositivos.get(i).equals(dispositivo))
            {
                dispositivos.remove(i);
                break;
            }
        }
        dispositivos.add(dispositivo);
        return true;
    }

    public Dispositivo getDispositivo(int i) { return dispositivos.get(i); }

    public void limparColecao() { dispositivos = null; }

    public void excluirMaquinas()
    {
        for(int i = dispositivos.size() - 1; i >= 0; i--)
        {
            if(dispositivos.get(i) instanceof ArCondicionado)
            {
                removeDispositivo(dispositivos.get(i));
            }
        }
    }

    public int listagemDispositivos()
    {
        int i;
        for(i = 0; i < dispositivos.size(); i++)
        {
            System.out.println(dispositivos.get(i).toString());
        }
        return i;
    }

    public void listagemMaquinas()
    {
        for(int i = 0; i < dispositivos.size(); i++)
        {
            if(dispositivos.get(i) instanceof Maquina)
            {
                System.out.println((Maquina)dispositivos.get(i));
            }
        }
    }

    public void listagemArCondicionados()
    {
        for(int i = 0; i < dispositivos.size(); i++)
        {
            if(dispositivos.get(i) instanceof ArCondicionado)
            {
                System.out.println((ArCondicionado)dispositivos.get(i));
            }
        }
    }

    public void listagemProjetores()
    {
        for(int i = 0; i < dispositivos.size(); i++)
        {
            if(dispositivos.get(i) instanceof Projetor)
            {
                System.out.println((Projetor)dispositivos.get(i));
            }
        }
    }

    public boolean removeDispositivo(Dispositivo dispositivo)
    {
        for(int i = 0; i < dispositivos.size(); i++)
        {
            if(dispositivos.get(i).equals(dispositivo))
            {
                dispositivos.remove(dispositivo);
                return true;
            }
        }
        return false;
    }

    public Maquina pesquisaMaquina(String ID)
    {
        for(int i = 0; i < dispositivos.size(); i++)
        {
            if(dispositivos.get(i) instanceof Maquina)
            {
                Maquina dispTemp = (Maquina)dispositivos.get(i);
                if(dispTemp.getID().equals(ID))
                {
                    return dispTemp;
                }
            }
        }
        return null;
    }

    public String pesquisaIDMaquina(String nome)
    {
        for(int i = 0; i < dispositivos.size(); i++)
        {
            if(dispositivos.get(i) instanceof Maquina && dispositivos.get(i).getNome().equals(nome))
            {
                return dispositivos.get(i).getID();
            }
        }
        return null;
    }

    public int qtdDisp() { return dispositivos.size(); }

    public boolean isMaquina(int i)
    {
        if(dispositivos.get(i) instanceof Maquina)
        {
            return true;
        }
        return false;
    }

    public int qtdMaquina()
    {
        int j;
        for( j = 0; j < dispositivos.size(); j++)
        {
            if(isMaquina(j))
            {
                j++;
            }
        }
        return j;
    }

    public ColecaoDispositivos getColMaq()
    {
        ColecaoDispositivos colmaq = new ColecaoDispositivos();
        for(int i = 0; i < dispositivos.size(); i++)
        {
            if(isMaquina(i))
            {
                colmaq.adicionaDispositivo(dispositivos.get(i));
            }
        }
        if(colmaq.qtdDisp() == 0)
        {
            return null;
        }
        return colmaq;
    }

    public Dispositivo pesquisaPeloNome(String nome)
    {
        for(int i = 0; i < dispositivos.size(); i++)
        {
            if(dispositivos.get(i).getNome().equals(nome))
            {
                return dispositivos.get(i);
            }
        }
        return null;
    }

    public void excluirArCondicionados()
    {
        for(int i = dispositivos.size() - 1; i >= 0; i--)
        {
            if(dispositivos.get(i) instanceof ArCondicionado)
            {
                removeDispositivo(dispositivos.get(i));
            }
        }
    }

    public void excluirProjetores()
    {
        for(int i = dispositivos.size() - 1; i >= 0; i--)
        {
            if(dispositivos.get(i) instanceof Projetor)
            {
                removeDispositivo(dispositivos.get(i));
            }
        }
    }
}
