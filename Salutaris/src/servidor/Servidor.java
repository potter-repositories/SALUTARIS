package servidor;

import registrador.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.GregorianCalendar;
import java.util.Scanner;

import cliente.GetNetworkAddress;

public class Servidor
{	
    public static void main(String[] args) throws Exception
    {
        ColecaoDispositivos coldis = null;
        String IP = "192.168.142.1";
        RecebeAtualiza recAtu = new RecebeAtualiza(coldis,IP);
        recAtu.start();
        Scanner input = new Scanner(System.in);
        menu(input,coldis, IP);
    }
    
    public static void menu(Scanner input, ColecaoDispositivos coldis, String IP)
    {
    	System.out.println("Escolha uma das opções abaixo:\n"
    					 + "1 - Exibir Dispositivos\n"
    					 + "2 - Exibir Máquinas\n"
    					 + "3 - Exibir Condicionadores de Ar\n"
    					 + "4 - Exibir Projetores"
    					 + "5 - Desligar Dispositivos");
    	int opcao = lerOpcao(input,1,5);
    	switch(opcao)
    	{
    		case 1:
    			coldis.listagemDispositivos();
    			break;
    		case 2:
    			coldis.listagemMaquinas();
    			break;
    		case 3:
    			coldis.listagemArCondicionados();
    			break;
    		case 4:
    			coldis.listagemProjetores();
    			break;
    		case 5:
    			Socket disposistivo = null;
                ObjectOutputStream desligar = null;
                for (int i = 0; i < coldis.getColMaq().qtdDisp(); i++)
                {
                	if(((Maquina) coldis.getColMaq().getDispositivo(i)).getExcecao() != true)
                	{
                		IP = ((Maquina) coldis.getColMaq().getDispositivo(i)).getEndereco();
                		try
                    	{
                        	disposistivo = new Socket(IP, 55000);
                        	desligar = new ObjectOutputStream(disposistivo.getOutputStream());
                        	desligar.writeObject(new Comando("Desligar"));
                        	desligar.close();
                    	}
                    	catch (IOException e)
                    	{
                    		System.err.println(e.getMessage());
                    	}
                	}
                }                
    	}
    }
    
    private static int lerOpcao(Scanner input, int iniciall, int finall)
    {

        int opcao;
        if(!input.hasNextInt())
        {
            System.out.printf("Digite um número válido: \n");
            input.nextLine();
            return lerOpcao(input,iniciall,finall);
        }
        opcao = input.nextInt();
        input.nextLine();
        if(opcao < iniciall || opcao > finall)
        {
            System.out.printf("Digite um número entre '" + iniciall + "' e '" + finall + "' : \n");
            return lerOpcao(input,iniciall,finall);
        }
        return opcao;
    }
}
