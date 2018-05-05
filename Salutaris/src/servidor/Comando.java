package servidor;

import java.io.Serializable;

public class Comando implements Serializable
{
    private String ID;
    private String descricao;
    private String comando;
    private Integer horario;

    public Comando(String ID, int horario)
    {
        this.ID = ID;
        this.horario = horario;
    }

    public Comando(String ID)
    {
        this.ID = ID;
    }

    public String getID() { return this.ID; }
    public String getHorario()
    {
        String horaFormatada = (horario/100 >= 10? "" + horario/100: "0" + (horario/100));
        String minutoFormatado = (horario%100 >= 10? "" + horario%100: "0" + (horario%100));
        return (("Hora de desligamento: " + horaFormatada + ":" + minutoFormatado));
    }
    public String[] getComando()
    {
        switch(this.ID)
        {
            case "maq01w":
                String[] comandoWin = new String[3];
                comandoWin[0] = "cmd";
                comandoWin[1] = "/c";
                comandoWin[2] = "shutdown -s";
                return comandoWin;
            case "maq01l":
                String[] comandoLin = new String[1];
                comandoLin[0] = "poweroff";
                return comandoLin;
            case "maq01m":
                String[] comandoMac = new String[1];
                comandoMac[0] = "shutdown";
                return comandoMac;
        }
        return null;
    }
    public String[] getDescr(String ID)
    {
        String[] descricao = new String[1];
        switch(ID)
        {
            case "maq01w":
                descricao[0] = "Desligamento do sistema Windows.";
                return descricao;
            case "maq01l":
                descricao[0] = "Desligamento do sistema Linux.";
                return descricao;
            case "maq01m":
                descricao[0] = "Desligamento do sistema MacOS.";
                return descricao;
        }
        return null;
    }
    public void setID(String ID) { this.ID = ID; }
    public void setHorario(int horario) { this.horario = horario; }
}

/*
    Lista exemplo de futuros comandos:

    Máquinas:
    maq01w - desligar o windows;
    maq01l - desligar o linux;
    maq01m - desligar o mac;
    maq02 - não desligar;

    Ar Condidionado:
    arc01 - ligar/desligar;
    arc02 - diminuir a temperatura;
    arc03 - aumentar a temperatura;

    Projetor:
    pro01 - ligar/desligar;
    pro02 - procurar fonte;
    pro03 - freeze;
 */