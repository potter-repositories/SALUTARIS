package servidor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import cliente.GetNetworkAddress;
import registrador.Bloco;
import registrador.ColecaoDispositivos;
import registrador.IPHost;
import registrador.InstituicaoEnsino;
import registrador.Sala;
import registrador.Stringo;

public class RecebeAtualiza extends Thread
{
	ColecaoDispositivos coldis;
	String IPRegistrador;
	public RecebeAtualiza(ColecaoDispositivos coldis, String IP)
	{
		this.coldis = coldis;
		IPRegistrador = IP;
	}
	
	public void run()
	{
		ServerSocket servidor;
		Socket registrador, exc;
		ObjectInputStream oin;
		ObjectOutputStream oout, ooutExc;
		String IP = "", newIP;
		newIP = GetNetworkAddress.GetAddress("ip");
		try
		{
			servidor = new ServerSocket(48000);
			while(true)
			{
				newIP = GetNetworkAddress.GetAddress("ip");
				if(!newIP.equals(IP))
				{
					IP = newIP;
					registraServidor(IPRegistrador, new IPHost(IP, "Sala de Apoio à Informática 04"));
				}
				registrador = servidor.accept();
				oin = new ObjectInputStream(registrador.getInputStream());
				oout = new ObjectOutputStream(registrador.getOutputStream());
				if(((Stringo)oin.readObject()).getStringo().equals("SEND"))
				{
					oout.writeObject(new InstituicaoEnsino("IFPB","João Pessoa","João Pessoa"));
		            oout.writeObject(new Bloco("Informática"));
		            oout.writeObject(new Sala("Sala de Apoio à Informática 04"));
		            coldis = (ColecaoDispositivos) oin.readObject();
		            salvArquivoColDis(coldis);
				}
				registrador.close();
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void registraServidor(String IP, IPHost IPHost) throws Exception
	{
		Socket registrador;
		ObjectOutputStream ooutServ;
		registrador = new Socket(IP, 47000);
		ooutServ = new ObjectOutputStream(registrador.getOutputStream());
		ooutServ.writeObject(IPHost);
		ooutServ.close();
		registrador.close();
	}
	
	public void salvArquivoColDis(ColecaoDispositivos coldis) throws Exception
	{
	    File file;
	    FileOutputStream fout;
	    ObjectOutputStream oout;
	    file = new File("D:/conteudo.dat");
	    fout = new FileOutputStream(file);
	    oout = new ObjectOutputStream(fout);
	    file.createNewFile();
	    oout.writeObject(coldis);
	    oout.close();
	    fout.close();
	}
}
