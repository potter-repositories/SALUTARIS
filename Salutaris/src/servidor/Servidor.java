package servidor;

import registrador.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Servidor
{
    public static void main(String[] args) throws Exception
    {
        ColecaoDispositivos coldis = null;
        String IP = "10.0.2.158";
        coldis = adquirirColdis(coldis, IP);
        if (args[0].equals("Exibir"))
        {
            if (args[1].equals("Dispositivos"))
            {
                coldis.listagemDispositivos();
            }
            else if (args[1].equals("Maquinas"))
            {
                coldis.listagemMaquinas();
            }
            else if (args[1].equals("ArCondicionados"))
            {
                coldis.listagemArCondicionados();
            }
            else if (args[1].equals("Projetores"))
            {
                coldis.listagemProjetores();
            }
        }
        else if (args[0].equals("Desligar"))
        {
            Socket disposistivo = null;
            ObjectOutputStream desligar = null;
            for (int i = 0; i < coldis.getColMaq().qtdDisp(); i++)
            {
                IP = ((Maquina) coldis.getColMaq().getDispositivo(i)).getEndereco();
                try
                {
                    disposistivo = new Socket(IP, 55000);
                    desligar = new ObjectOutputStream(disposistivo.getOutputStream());
                    desligar.writeObject(new Comando("maq01w"));
                    desligar.close();
                }
                catch (IOException e)
                {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    public static ColecaoDispositivos adquirirColdis(ColecaoDispositivos coldis, String IP)
    {
        Socket atualiza;
        ObjectOutputStream oout;
        ObjectInputStream oin;
        try
        {
            atualiza = new Socket(IP, 51000);
            oout = new ObjectOutputStream(atualiza.getOutputStream());
            oin = new ObjectInputStream(atualiza.getInputStream());
            oout.writeObject(new Stringo("true"));
            oout.writeObject(new InstituicaoEnsino("IFPB","João Pessoa","João Pessoa"));
            oout.writeObject(new Bloco("Informática"));
            oout.writeObject(new Sala("Sala de Apoio à Informática 04"));
            coldis = (ColecaoDispositivos) oin.readObject();
            coldis.listagemDispositivos();
            atualiza.close();
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        return coldis;
    }
}
