package registrador;

import java.io.Serializable;
import java.util.ArrayList;

public class Horario implements Serializable
{
    ArrayList<Integer> hora, minuto;

    public Horario(ArrayList<Integer> hora, ArrayList<Integer> minuto)
    {
        this.hora = hora;
        this.minuto = minuto;
    }
    public Horario()
    {
        this.hora = new ArrayList<Integer>();
        this.minuto = new ArrayList<Integer>();
    }
    public ArrayList<Integer> getHoras() { return this.hora; }
    public ArrayList<Integer> getMinutos() { return this.minuto; }
    public boolean adicionarHorarioDeDesligamento(int hora, int minuto)
    {
        for(int i = 0; i < this.hora.size(); i++)
        {
            if(this.hora.get(i).equals(hora))
            {
                if(this.minuto.get(i).equals(minuto))
                {
                    return false;
                }
            }
        }
        this.hora.add(hora);
        this.minuto.add(minuto);
        return true;
    }
    public void resetarHorario()
    {
        this.hora = null;
        this.minuto = null;
    }
    public boolean removerHorarioDeDesligamento(int hora, int minuto)
    {
        for(int i = 0; i < this.hora.size(); i++)
        {
            if(this.hora.get(i).equals(hora))
            {
                if(this.minuto.get(i).equals(minuto))
                {
                    this.hora.remove(i);
                    this.minuto.remove(i);
                    return true;
                }
            }
        }
        return false;
    }
}
