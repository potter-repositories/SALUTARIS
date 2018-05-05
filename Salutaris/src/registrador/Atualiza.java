package registrador;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Atualiza extends Thread
{
    ColecaoInstituicoes colinst = new ColecaoInstituicoes();
    public Atualiza() throws Exception
    {
        try
        {
            colinst.recuperArquivo();
        }
        catch(Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    public void run()
    {
        ServerSocket updater = null;
        ObjectOutputStream oout = null;
        ObjectInputStream oin = null;
        Socket servidor = null;
        int porta = 51000;
        try
        {
            while(true)
            {
                updater = new ServerSocket(porta);
                servidor = updater.accept();
                oin = new ObjectInputStream(servidor.getInputStream());
                oout = new ObjectOutputStream(servidor.getOutputStream());
                if(((Stringo)oin.readObject()).getStringo().equals("true"))
                {
                    colinst.recuperArquivo();
                    Instituicao inst = colinst.procuraInst((InstituicaoEnsino)(oin.readObject()));
                    Bloco bloco = inst.getColblo().pesquisaPeloNome(((Bloco)oin.readObject()).getNome());
                    Sala sala = bloco.getColsal().pesquisaPeloNome(((Sala)oin.readObject()).getNome());
                    ColecaoDispositivos coldis = sala.getColdis();
                    oout.writeObject(coldis);
                }
                servidor.close();
                //porta++;
            }
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}
