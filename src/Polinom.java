import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.regex.*;
import java.util.StringTokenizer;

import static java.lang.Math.abs;

public class Polinom
{
    private ArrayList<Monom> data;

    public Polinom()
    {
        data = new ArrayList<Monom>();
    }

    public Polinom(ArrayList<Monom> data)
    {
        this.data = data;
    }

    public Polinom(Monom m)
    {
        data = new ArrayList<Monom>();
        data.add(m);
    }

    public ArrayList<Monom> getData() {
        return data;
    }

    public void setData(ArrayList<Monom> data) {
        this.data = data;
    }

    // Corectitudinea String ului este testata inainte
    public Polinom(String s) {
        s = s.replaceAll("\\s", "");
        Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
        Matcher matcher = pattern.matcher(s);
        String aux = null;
        ArrayList<Monom> mono = new ArrayList<Monom>();
        while (matcher.find()) {
            aux = matcher.group(1);
            StringTokenizer st = new StringTokenizer(aux, "x^");
            double co = 0;
            int exp = 0;
            if (st.countTokens() == 0)  {co = 1; exp = 1;}
            else if (st.countTokens() == 1) {
                String tok = st.nextToken();
                if (tok.equals("+")){ co = 1;exp = 1;}
                else if (tok.equals("-")){co = -1;exp = 1;}
                else if (aux.contains("^")) { co = 1; exp = Integer.parseInt(tok); }
                else if (aux.contains("x")){ co = Double.parseDouble(tok); exp = 1; }
                else { co = Double.parseDouble(tok);exp = 0; }
            }
            else if (st.countTokens() == 2){
                String tok = st.nextToken();
                if (tok.equals("+")) {co = 1; exp = Integer.parseInt(st.nextToken());}
                else if (tok.equals("-")) {co = -1; exp = Integer.parseInt(st.nextToken());}
                else {co = Double.parseDouble(tok);exp = Integer.parseInt(st.nextToken());}
            }
            mono.add(new Monom(co, exp));
        }
        this.data = mono;
    }

    public void reduce()
    {
        data.sort(Monom::compareTo);
        ArrayList<Monom> aux = new ArrayList<Monom>();
        TreeSet<Integer> vf = new TreeSet<Integer>();

        for (Monom m : data)
            vf.add(m.getExp());

        double co;
        int exp;
        for (int i : vf)
        {
            exp = i;
            co = 0;
            for (Monom m : data)
                if (m.getExp() == exp)
                    co += m.getCo();

            if (!(abs(co) < 1e-6))
                aux.add(new Monom(co, exp));
        }

        aux.sort(Monom::compareTo);
        data = aux;

    }

    @Override
    public String toString()
    {
        DecimalFormat df = new DecimalFormat("#.##");
        String ret = "";
        this.reduce();
        int i = 0;

        if (data.size() == 0) return "0";

        for (Monom m : data)
        {
            if (m.getCo() > 0 && i != 0) ret += "+";
            if (m.getExp() == 0) { ret += df.format(m.getCo());}
            else if (m.getExp() == 1) { ret += ((abs(abs(m.getCo()) - 1)< 1e-6)?(m.getCo() > 0?"":"-"):df.format(m.getCo())) + "x";}
            else { ret += ((abs(abs(m.getCo()) - 1 ) < 1e-6)?(m.getCo() > 0?"":"-"):df.format(m.getCo())) + "x^" + m.getExp();}

            i++;
        }
        return ret;
    }

    public int degree()
    {
        int ret = 0;
        for (Monom m : data)
            if (m.getExp() > ret)
                ret = m.getExp();

        return ret;
    }

    public Monom lead()
    {
        Monom ret = new Monom();
        this.reduce();

        for (Monom m : data)
            if (m.getExp() == this.degree())
            {
                ret.setCo(m.getCo());
                ret.setExp(m.getExp());
                break;
            }

        return ret;
    }

}
