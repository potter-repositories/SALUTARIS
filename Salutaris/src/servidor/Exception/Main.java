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
        String IP = "192.168.142.1";
        coldis = adquirirColdis(coldis, IP);
        boolean sentence;
        if(args[0].equals("Adicionar"))
        {
        	sentence = true;
        }
        else
        {
        	sentence = false;
        }
        for(int i = 1; i < args.length; i++)
        {
            Maquina maquina = coldis.pesquisaMaquina(args[i]);
            ObjectOutputStream oout;
            try
            {
                Socket cliente = new Socket(maquina.getEndereco(), 61000);
                oout = new ObjectOutputStream(cliente.getOutputStream());
                oout.writeBoolean(sentence);
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
