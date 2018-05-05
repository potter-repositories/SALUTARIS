package registrador;

import java.io.Serializable;

public class Stringo implements Serializable
{
    String s = null;
    public Stringo(String s)
    {
        this.s = s;
    }

    public String getStringo()
    {
        return this.s;
    }
}
