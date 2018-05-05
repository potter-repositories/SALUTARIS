package cliente;

import registrador.Maquina;
import servidor.Comando;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Cliente
{
    private static String OS = System.getProperty("os.name").toLowerCase();

    public static void main(String[] args) throws UnknownHostException
    {
        String ID = GetNetworkAddress.GetAddress("mac");
        String endereco = GetNetworkAddress.GetAddress("ip");
        String nome;
        String tecnologia = "ethernet";
        ArrayList<Comando> comandos = new ArrayList<Comando>();
        comandos.add(new Comando("maq01"));
        Maquina maquina;
        nome = InetAddress.getByName(endereco).getCanonicalHostName();
        String hostIP = "10.0.2.158";
        int hostPort = 60500;
        maquina = new Maquina(ID, nome, endereco, tecnologia, comandos);
        Socket cliente = null;
        DataOutputStream saidaBool = null;
        File file = null, tempFile = null;
        FileInputStream fin = null;
        FileOutputStream fout = null, tempFout = null;
        ObjectInputStream oin = null;
        ObjectOutputStream saidaObj = null, oout = null, tempOout = null;
        checagemDeDados(file,tempFile,fout,tempFout,oout,tempOout,saidaObj,fin,oin,maquina,hostIP,hostPort,cliente,saidaBool);

        int ssocketport = 55000;
        ServerSocket inServidor;
        Socket inCliente;
        ObjectInputStream entrada;
        try
        {
            inServidor = new ServerSocket(ssocketport);
            //noinspection InfiniteLoopStatement
            while(true)
            {
                inCliente = inServidor.accept();
                entrada = new ObjectInputStream(inCliente.getInputStream());
                Comando desligar;
                desligar = ((Comando)entrada.readObject());
                entrada.close();
                if(desligar.getComando().equals("Desligar"))
                {
                    desligar();
                }
            }
        }
        catch(IOException e)
        {
            System.err.println(e.getMessage());
        }
        catch(ClassNotFoundException e)
        {
            System.err.println(e.getMessage());
        }
    }

    private static void desligar() throws IOException
    {
        if(isWindows())
        {
            String[] commandWin = new String[3];
            commandWin[0] = "cmd";
            commandWin[1] = "/c";
            commandWin[2] = "shutdown -s";
            Runtime.getRuntime().exec(commandWin);
        }
        else if(isMac())
        {
            String commandMac = "shutdown";
            Runtime.getRuntime().exec(commandMac);
        }
        else if(isUnix())
        {
            String commandLin = "poweroff";
            Runtime.getRuntime().exec(commandLin);
        }
    }

    public static void checagemDeDados(File file, File tempFile, FileOutputStream fout, FileOutputStream tempFout, ObjectOutputStream oout, ObjectOutputStream tempOout, ObjectOutputStream saidaObj, FileInputStream fin, ObjectInputStream oin, Maquina maquina, String hostIP, int hostPort, Socket cliente, DataOutputStream saidaBool)
    {
        try
        {
            file = new File("conteudo.dat");
            fout = new FileOutputStream(file);
            oout = new ObjectOutputStream(fout);
            fin = new FileInputStream(file);
            oin = new ObjectInputStream(fin);
            tempFile = new File("conteudoTemp.dat");
            tempFout = new FileOutputStream(tempFile);
            tempOout = new ObjectOutputStream(tempFout);
            tempOout.writeObject(maquina);
            cliente = new Socket(hostIP, hostPort);
            saidaBool = new DataOutputStream(cliente.getOutputStream());
            //noinspection ResultOfMethodCallIgnored
            file.createNewFile();
            if(file.equals(tempFile))
            {
                saidaBool.writeBoolean(false);
            }
            else
            {
                saidaBool.writeBoolean(true);
                saidaObj = new ObjectOutputStream(cliente.getOutputStream());
                saidaObj.writeObject(maquina);
                oout.writeObject(maquina);
            }
            fin.close();
            oin.close();
            fout.close();
            oout.close();
            tempFout.close();
            tempOout.close();
            //noinspection ResultOfMethodCallIgnored
            tempFile.delete();
            cliente.close();
        }
        catch(IOException e)
        {
            System.err.println(e.getMessage());
        }
    }

    private static boolean isWindows()
    {
        return (OS.contains("win"));
    }

    private static boolean isMac()
    {
        return (OS.contains("mac"));
    }

    private static boolean isUnix()
    {
        return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));
    }
}
