public class Monom implements Comparable<Monom>
{
    private double co;
    private int exp;

    public Monom()
    {
        this.co = 0;
        this.exp = 0;
    }

    public Monom(double co, int exp)
    {
        this.co = co;
        this.exp = exp;
    }

    public double getCo() {
        return co;
    }

    public void setCo(double co) {
        this.co = co;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    @Override
    public int compareTo(Monom o)
    {
        if (this.exp < o.exp) return 1;
        else if (this.exp > o.exp) return -1;
        return 0;
    }
}
