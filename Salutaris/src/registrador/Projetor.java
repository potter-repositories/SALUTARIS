package registrador;

import servidor.Comando;

import java.io.Serializable;
import java.util.ArrayList;

public class Projetor extends Dispositivo implements Serializable
{
    public Projetor(String ID, String nome, String endereco, String tecnologia, ArrayList<Comando> comandos)
    {
        super(ID, nome, endereco, tecnologia, comandos);
    }

    public String toString()
    {
        String comandosFormatados = "";
        for(int i = 0; i < comandos.size(); i++)
        {
            comandosFormatados += comandos.get(i);
        }
        return "Projetor\nID: " + super.ID + "\nNome: " + super.nome + "\nEndereço: " + super.endereco + "\nTecnologia: " + super.tecnologia + "\nComandos: " + comandosFormatados + "\nExceção: " + super.excecao;
    }

    public boolean equals(Dispositivo projetor)
    {
        if(super.ID.equals(((Projetor)projetor).getID()))
        {
            return true;
        }
        return false;
    }

}
