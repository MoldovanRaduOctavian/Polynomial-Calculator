import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Controller implements ActionListener
{
    private View view;

    public Controller(View v)
    {
        this.view = v;
    }

    // Trebuie aplicate mai multe filtre de verificare
    public boolean polyCheck(String s)
    {
        String pattern  = "0123456789x+-^ ";
        int ok = 0;

        for (int i=0; i<s.length(); i++)
            for (int j=0; j<pattern.length(); j++)
                if (s.charAt(i) == pattern.charAt(j))
                {
                    ok = 1;
                    break;
                }

        return ok != 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (!polyCheck(view.getFirstNumberTextField().getText()) || !polyCheck(view.getSecondNumberTextField().getText()))
        {
            view.setResultTextArea("INPUT ERROR!");
            return;
        }
        Polinom p1 = new Polinom(view.getFirstNumberTextField().getText());
        Polinom p2 = new Polinom(view.getSecondNumberTextField().getText());
        Polinom ret = null;
        String divRet = null;

        switch (command) {
            case "add" -> ret = Logic.add(p1, p2);
            case "sub" -> ret = Logic.sub(p1, p2);
            case "mul" -> ret = Logic.mul(p1, p2);
            case "div" -> divRet = Logic.divStr(p1, p2);
            case "derivative" -> ret = Logic.derivative(p1);
            case "integral" -> ret = Logic.integral(p1);
        }

        if (command.equals("div")) view.setResultTextArea(divRet);
        else {
            assert ret != null;
            view.setResultTextArea(ret.toString());
        }
    }
}
