package registrador;

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.GregorianCalendar;

public class RegistraMaquinas extends Thread
{
    private ColecaoInstituicoes colinst;
    private ColecaoDispositivos coldis;
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
                }
                bin.close();
            }
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}
