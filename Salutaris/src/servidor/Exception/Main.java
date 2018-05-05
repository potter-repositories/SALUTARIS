package servidor.Exception;

import registrador.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static servidor.Servidor.adquirirColdis;

public class Main
{
    public static void main(String[] args)
    {
        ColecaoDispositivos coldis = null;
        String IP = "10.0.2.158";
        coldis = adquirirColdis(coldis, IP);
        for(int i = 0; i < args.length; i++)
        {
            String ID = coldis.pesquisaIDMaquina(args[i]);
            Maquina maquina = coldis.pesquisaMaquina(ID);
            ObjectOutputStream oout;
            try
            {
                Socket cliente = new Socket(maquina.getEndereco(), 61000);
                oout = new ObjectOutputStream(cliente.getOutputStream());
                if(args[i].equals("true"))
                {
                    oout.writeBoolean(true);
                }
                else
                {
                    oout.writeBoolean(false);
                }
                oout.close();
                cliente.close();
            }
            catch(Exception e)
            {
                System.err.println(e.getMessage());
            }
        }
    }
}
