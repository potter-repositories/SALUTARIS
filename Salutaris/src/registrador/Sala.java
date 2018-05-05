package registrador;

import java.io.Serializable;
import java.util.ArrayList;

public class Sala extends InsBloSal implements Serializable
{
    private ColecaoDispositivos coldis = null;
    private Horario horario;
    private ArrayList<Integer> hora = new ArrayList<Integer>();
    private ArrayList<Integer> minuto = new ArrayList<Integer>();

    public Sala(String nome)
    {
        super(nome);
        coldis = new ColecaoDispositivos();
        this.horario = new Horario(hora,minuto);
    }

    public ColecaoDispositivos getColdis() { return coldis; }
    public int qtdDisp() { return coldis.qtdDisp(); }
    public String toString() { return "Sala: " + nome; }
    public boolean equals(Sala sala) { return this.nome.equals(sala.getNome()); }
}
