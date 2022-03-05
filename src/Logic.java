import java.util.ArrayList;
import java.util.TreeSet;

public class Logic
{
    public static Polinom add(Polinom p1, Polinom p2)
    {
        p1.reduce();
        p2.reduce();

        ArrayList<Monom> aux = new ArrayList<Monom>();
        for (Monom m : p1.getData())
            aux.add(new Monom(m.getCo(), m.getExp()));

        for (Monom m : p2.getData())
            aux.add(new Monom(m.getCo(), m.getExp()));

        Polinom ret = new Polinom(aux);
        ret.reduce();

        return ret;
    }

    public static Polinom sub(Polinom p1, Polinom p2)
    {
        p1.reduce();
        p2.reduce();

        ArrayList<Monom> aux = new ArrayList<Monom>();
        for (Monom m : p1.getData())
            aux.add(new Monom(m.getCo(), m.getExp()));

        for (Monom m : p2.getData())
            aux.add(new Monom(-m.getCo(), m.getExp()));

        Polinom ret = new Polinom(aux);
        ret.reduce();

        return ret;
    }

    public static Polinom mul(Polinom p1, Polinom p2)
    {
        p1.reduce();
        p2.reduce();

        ArrayList<Monom> aux = new ArrayList<Monom>();

        for (Monom m : p1.getData())
            for (Monom n : p2.getData())
                aux.add(new Monom(m.getCo() * n.getCo(), m.getExp() + n.getExp()));

        Polinom ret = new Polinom(aux);
        ret.reduce();

        return ret;
    }


    public static ArrayList<Polinom> div(Polinom p1, Polinom p2)
    {
        ArrayList<Monom> aux = new ArrayList<Monom>();
        ArrayList<Polinom> ret = new ArrayList<Polinom>();
        p1.reduce();
        p2.reduce();

        for (Monom m : p1.getData())
            aux.add(new Monom(m.getCo(), m.getExp()));

        Polinom r = new Polinom(aux);
        Polinom q = new Polinom();
        Polinom t = null;
        Monom mt = new Monom();

        while (r.getData().size() != 0 && r.degree() >= p2.degree())
        {
            mt.setCo(r.lead().getCo()/p2.lead().getCo());
            mt.setExp(r.lead().getExp() - p2.lead().getExp());
            t = new Polinom(mt);

            q = Logic.add(q, t);
            r = Logic.sub(r, Logic.mul(t, p2));
        }
        ret.add(q);
        ret.add(r);
        return ret;
    }

    public static String divStr(Polinom p1, Polinom p2)
    {
        ArrayList<Polinom> aux = Logic.div(p1, p2);
        return "cat: " + aux.get(0).toString() + " rest: " +aux.get(1).toString();
    }

    public static Polinom derivative(Polinom p1)
    {
        ArrayList<Monom> aux = new ArrayList<Monom>();
        p1.reduce();

        for (Monom m : p1.getData())
        {
            if (m.getExp() == 0)
                aux.add(new Monom(0, 0));

            else
                aux.add(new Monom(m.getCo() * m.getExp(), m.getExp() - 1));
        }

        Polinom ret = new Polinom(aux);
        ret.reduce();

        return ret;
    }

    public static Polinom integral(Polinom p1)
    {
        ArrayList<Monom> aux = new ArrayList<Monom>();
        p1.reduce();

        for (Monom m : p1.getData())
            aux.add(new Monom(m.getCo()/(m.getExp() + 1), m.getExp() + 1));

        Polinom ret = new Polinom(aux);
        ret.reduce();

        return ret;
    }

}
