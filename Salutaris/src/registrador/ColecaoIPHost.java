package registrador;

import java.io.Serializable;
import java.util.ArrayList;

public class ColecaoIPHost implements Serializable
{
	private static final long serialVersionUID = 6143416640244494880L;
	ArrayList<IPHost> colIPHost;
	
	public ColecaoIPHost()
	{
		
	}
	
	public boolean adicionarIPHost(IPHost iphost)
	{
		for(int i = 0; i < colIPHost.size(); i++)
		{
			if(iphost.equals(colIPHost.get(i)))
			{
				return false;
			}
		}
		colIPHost.add(iphost);
		return true;
	}
	
	public IPHost getIPHost(int i)
	{
		return colIPHost.get(i);
	}
	
	public int qtdIPHost()
	{
		return colIPHost.size();
	}
	
	public boolean removerIPHost(IPHost iphost)
	{
		for(int i = 0; i < colIPHost.size(); i++)
		{
			if(iphost.equals(colIPHost.get(i)))
			{
				colIPHost.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public void listagemIPHost()
	{
		System.out.println("Servidores:\n");
		for(int i = 0; i < colIPHost.size(); i++)
		{
			System.out.println(colIPHost.get(i).toString());
		}
	}
}
