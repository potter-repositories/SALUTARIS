package cliente;

import registrador.Maquina;
import servidor.Comando;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import static cliente.Cliente.checagemDeDados;

public class ApplyException extends Thread
{

    public ApplyException()
    {

    }

    public void run()
    {
        try
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

            ServerSocket exception;
            Socket applyException;
            DataInputStream entrada;
            exception = new ServerSocket(61000);
            while(true)
            {
                applyException = exception.accept();
                entrada = new DataInputStream(applyException.getInputStream());
                boolean exceptionSignal;
                exceptionSignal = entrada.readBoolean();
                entrada.close();
                applyException.close();
                maquina.setExecao(exceptionSignal);
                checagemDeDados(file, tempFile, fout, tempFout, oout, tempOout, saidaObj, fin, oin, maquina, hostIP, hostPort, cliente, saidaBool);
            }
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}
