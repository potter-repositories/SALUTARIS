package registrador;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RegistraServidores extends Thread
{
	ColecaoIPHost colIPHost;
	public RegistraServidores(ColecaoIPHost colIPHost)
	{
		this.colIPHost = colIPHost;
	}
	
	public void run()
	{
		ServerSocket registraServidores;
		Socket servidor;
		ObjectInputStream oin;
		ObjectOutputStream oout;
		try
		{
			while(true)
			{
				registraServidores = new ServerSocket(47000);
				servidor = registraServidores.accept();
				oin = new ObjectInputStream(servidor.getInputStream());
				oout = new ObjectOutputStream(servidor.getOutputStream());
				colIPHost.adicionarIPHost((IPHost)oin.readObject());
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
}
