package registrador;

import java.io.Serializable;
import java.util.ArrayList;

public class IPHost implements Serializable
{
	String Host;
	String IP;
	
	public IPHost(String IP, String Host)
	{
		this.IP = IP;
		this.Host = Host;
	}
	
	public String getIP()
	{
		return this.IP;
	}
	
	public String getHost()
	{
		return this.Host;
	}
	
	public void setIP(String IP)
	{
		this.IP = IP;
	}
	
	public void setHost(String Host)
	{
		this.Host = Host;
	}
	
	public String toString()
	{
		return "Servidor " + this.Host + "\nIP: " + this.IP + "\n";
	}
	
	public boolean equals(IPHost iphost)
	{
		if(this.IP.equals(iphost.getIP()) && this.Host.equals(iphost.getHost()))
		{
			return true;
		}
		return false;
	}
}
