package registrador;

import servidor.Comando;

import java.io.Serializable;
import java.util.ArrayList;

public class Maquina extends Dispositivo implements Serializable
{
    public Maquina(String ID, String nome, String endereco, String tecnologia, ArrayList<Comando> comandos)
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
        return "Máquina\nID: " + super.ID + "\nNome: " + super.nome + "\nEndereço: " + super.endereco + "\nTecnologia: " + super.tecnologia + "\nComandos: " + comandosFormatados + "\nExceção: " + super.excecao;
    }

    public boolean equals(Dispositivo maquina)
    {
        if(ID.equals(maquina.getID()))
        {
            return true;
        }
        return false;
    }
}
