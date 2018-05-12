package registrador;

import servidor.Comando;

import java.io.Serializable;
import java.util.ArrayList;

public class ArCondicionado extends Dispositivo implements Serializable
{
    public ArCondicionado(String ID, String nome, String endereco, String tecnologia, ArrayList<Comando> comandos)
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
        return "Ar Condicionado\nID: " + super.ID + "\nNome: " + super.nome + "\nEndereço: " + super.endereco + "\nTecnologia: " + super.tecnologia + "\nComandos: " + comandosFormatados + "\nExceção: " + super.excecao;
    }

    public boolean equals(Dispositivo arCondicionado)
    {
        if(super.ID.equals(((ArCondicionado)arCondicionado).getID()))
        {
            return true;
        }
        return false;
    }

}
