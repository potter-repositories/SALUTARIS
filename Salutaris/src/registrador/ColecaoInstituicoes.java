package registrador;

import java.io.*;
import java.util.Vector;

public class ColecaoInstituicoes implements Serializable
{
    private Vector<Instituicao> instituicoes;

    public ColecaoInstituicoes() { instituicoes = new Vector<Instituicao>(); }
    public boolean adicionaEmpresa(Empresa empresa)
    {
        for(int i = 0; i < instituicoes.size(); i++)
        {
            if(instituicoes.get(i).equals(empresa) && ((Empresa)instituicoes.get(i)).getUnidade().equals(empresa.getUnidade()))
            {
                return false;
            }
        }
        instituicoes.add(empresa);
        return true;
    }
    public boolean adicionaInstituicaoEnsino(InstituicaoEnsino instituicaoEnsino)
    {
        for(int i = 0; i < instituicoes.size(); i++)
        {
            if(instituicoes.get(i).equals(instituicaoEnsino) && ((InstituicaoEnsino)instituicoes.get(i)).getCampus().equals(instituicaoEnsino.getCampus()))
            {
                return false;
            }
        }
        instituicoes.add(instituicaoEnsino);
        return true;
    }
    public Instituicao getInst(int i) { return instituicoes.get(i); }
    public void limparColecao() { instituicoes = null; }

    public int listagemInstituicoes()
    {
        int i;
        for(i = 0; i < instituicoes.size(); i++)
        {
            System.out.println(instituicoes.get(i).toString());
        }
        return i;
    }
    public int listagemEmpresas()
    {
        int i;
        for(i = 0; i < instituicoes.size(); i++)
        {

            if(instituicoes.get(i) instanceof Empresa)
            {
                System.out.println(instituicoes.get(i).toString());
            }
        }
        return i;
    }
    public int listagemInstEns()
    {
        int i;
        for(i = 0; i < instituicoes.size(); i++)
        {
            if(instituicoes.get(i) instanceof InstituicaoEnsino)
            {
                System.out.println(instituicoes.get(i).toString());
            }
        }
        return i;
    }
    public boolean removeInstituicao(Instituicao instituicao)
    {
        for (int i = 0; i < instituicoes.size(); i++)
        {
            if (!instituicoes.get(i).equals(instituicao))
            {
                instituicoes.remove(instituicao);
                return true;
            }
        }
        return false;
    }
    public boolean removeEmpresa(Empresa empresa)
    {
        for(int i = 0; i < instituicoes.size(); i++)
        {
            if(instituicoes.get(i).equals(empresa) && ((Empresa)instituicoes.get(i)).getUnidade().equals(empresa.getUnidade()))
            {
                instituicoes.remove(empresa);
                return true;
            }
        }
        return false;
    }
    public boolean removeInstEns(InstituicaoEnsino instituicaoEnsino)
    {
        for(int i = 0; i < instituicoes.size(); i++)
        {
            if(instituicoes.get(i).equals(instituicaoEnsino) && ((InstituicaoEnsino)instituicoes.get(i)).getCampus().equals(instituicaoEnsino.getCampus()))
            {
                System.out.println(((InstituicaoEnsino)instituicoes.get(i)).getCampus().equals(instituicaoEnsino.getCampus()));
                instituicoes.remove(instituicaoEnsino);
                return true;
            }
        }
        return false;
    }
    public int qtdIns() { return instituicoes.size(); }
    public Instituicao pesquisaPeloNome(String nome)
    {
        int flag = pesquisaIndicePeloNome(nome);
        if(flag >= 0)
        {
            return getInst(flag);
        }
        return null;
    }
    public int pesquisaIndicePeloNome(String nome)
    {
        int i;
        for(i = 0; i < instituicoes.size(); i++)
        {
            if(instituicoes.get(i).getNome().equals(nome))
            {
                return i;
            }
        }
        return -1;
    }
    public Instituicao procuraInst(Instituicao inst)
    {
        for(int i = 0; i < instituicoes.size(); i++)
        {
            if(instituicoes.get(i).equals(inst))
            {
                return instituicoes.get(i);
            }
        }
        return null;
    }

    public Empresa procuraEmpresa(Empresa empresa)
    {
        ColecaoInstituicoes colEmp = getColEmp();
        for(int i = 0; i < colEmp.qtdIns(); i++)
        {
            if((colEmp.getInst(i)).equals(empresa) && ((Empresa)colEmp.getInst(i)).getUnidade().equals(empresa.getUnidade()))
            {
                return (Empresa)colEmp.getInst(i);
            }
        }
        return null;
    }

    public InstituicaoEnsino procuraInstEns(InstituicaoEnsino instEns)
    {
        ColecaoInstituicoes colInstEns = getColInstEns();
        for(int i = 0; i < colInstEns.qtdIns(); i++)
        {
            if((colInstEns.getInst(i)).equals(instEns) && ((InstituicaoEnsino)colInstEns.getInst(i)).getCampus().equals(instEns.getCampus()))
            {
                return (InstituicaoEnsino)colInstEns.getInst(i);
            }
        }
        return null;
    }
    private ColecaoInstituicoes getColEmp()
    {
        ColecaoInstituicoes colEmp = new ColecaoInstituicoes();
        for(int i = 0; i < instituicoes.size(); i++)
        {
            if(instituicoes.get(i) instanceof Empresa)
            {
                colEmp.adicionaEmpresa((Empresa)instituicoes.get(i));
            }
        }
        return colEmp;
    }
    private ColecaoInstituicoes getColInstEns()
    {
        ColecaoInstituicoes colInstEns = new ColecaoInstituicoes();
        for(int i = 0; i < instituicoes.size(); i++)
        {
            if(instituicoes.get(i) instanceof InstituicaoEnsino)
            {
                colInstEns.adicionaInstituicaoEnsino((InstituicaoEnsino)instituicoes.get(i));
            }
        }
        return colInstEns;
    }
    public void recuperArquivo() throws Exception
    {
        File file;
        FileInputStream fin;
        ObjectInputStream oin;
        try
        {
            file = new File("D:/Pen-Card Amway/IFPB/Salutaris/conteudo.dat");
            if(file.exists())
            {
                fin = new FileInputStream(file);
                oin = new ObjectInputStream(fin);
                Vector<Instituicao> vector = (Vector<Instituicao>) oin.readObject();
                if(vector != null)
                {
                    instituicoes = vector;
                }
                else
                {
                    instituicoes = new Vector<Instituicao>();
                }
                oin.close();
                fin.close();
            }
        }
        catch(Exception e)
        {
            throw new Exception("Não foi possível recuperar o arquivo de backup.");
        }
    }
    public void gravaArquivo() throws Exception
    {
        File file;
        FileOutputStream fout;
        ObjectOutputStream oout;
        try
        {
            file = new File("D:/Pen-Card Amway/IFPB/Salutaris/conteudo.dat");
            fout = new FileOutputStream(file);
            oout = new ObjectOutputStream(fout);
            file.createNewFile();
            oout.writeObject(instituicoes);
            oout.close();
            fout.close();
        }
        catch(Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }
}
