package registrador;

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.GregorianCalendar;

public class RegistraMaquinas extends Thread
{
    private ColecaoInstituicoes colinst;
    private ColecaoDispositivos coldis;
    //private ColecaoIP servidores;
    private int porta;

    public RegistraMaquinas(ColecaoInstituicoes colinst, ColecaoDispositivos coldis, int porta)
    {
        this.colinst = colinst;
        this.coldis = coldis;
        this.porta = porta;
    }
    public void run()
    {
        GregorianCalendar calendar = new GregorianCalendar();
        Maquina maquina = null;
        ServerSocket servidor = null;
        try
        {
            servidor = new ServerSocket(porta);
            Socket cliente = null;
            while(true)
            {
                cliente = servidor.accept();
                DataInputStream bin = null;
                ObjectInputStream oin = null;
                bin = new DataInputStream(cliente.getInputStream());
                if(bin.readBoolean())
                {
                    oin = new ObjectInputStream(cliente.getInputStream());
                    maquina = (Maquina)oin.readObject();
                    coldis.adicionaDispositivo(maquina);
                    colinst.gravaArquivo();
                    colinst.recuperArquivo();
                    atualizaServidor(colinst);
                }
                bin.close();
            }
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
    
    public static void atualizaServidor(ColecaoInstituicoes colinst) throws Exception
    {
    	colinst.recuperArquivo();
        Socket clienteServ = null;
        ObjectInputStream oinServ = null;
        ObjectOutputStream ooutServ = null;
        clienteServ = new Socket("IP do Servidor",48000);
        oinServ = new ObjectInputStream(clienteServ.getInputStream());
        ooutServ = new ObjectOutputStream(clienteServ.getOutputStream());
        ooutServ.writeObject(new Stringo("SEND"));
        Instituicao inst = colinst.procuraInstEns((InstituicaoEnsino)(oinServ.readObject()));
        Bloco bloco = inst.getColblo().pesquisaPeloNome(((Bloco)oinServ.readObject()).getNome());
        Sala sala = bloco.getColsal().pesquisaPeloNome(((Sala)oinServ.readObject()).getNome());
        ColecaoDispositivos coldis = sala.getColdis();
        ooutServ.writeObject(coldis);
        oinServ.close();
        ooutServ.close();
        clienteServ.close();
    }
}
