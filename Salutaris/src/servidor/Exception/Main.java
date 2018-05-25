package servidor.Exception;

import registrador.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Main
{
    public static void main(String[] args)
    {
    	try
    	{
	        ColecaoDispositivos coldis = null;
	        String IP = "192.168.142.1";
	        coldis = lerArquivoColDis();
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
	        	//CRIAR COMUNICAÇÃO COM O REGISTRADOR
	        }
    	}
    	catch(Exception e)
    	{
    		System.err.println(e.getMessage());
    	}
    }
    
    public static ColecaoDispositivos lerArquivoColDis() throws Exception
	{
	    File file;
	    FileInputStream fin;
	    ObjectInputStream oin;
        file = new File("D:/conteudo.dat");
        if(file.exists())
        {
        	fin = new FileInputStream(file);
            oin = new ObjectInputStream(fin);
            ColecaoDispositivos coldis = (ColecaoDispositivos) oin.readObject();
            if(coldis == null)
            {
                coldis = new ColecaoDispositivos();
            }
            oin.close();
            fin.close();
            return coldis;
        }
        return null;
	}
}
