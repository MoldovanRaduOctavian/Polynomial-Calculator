import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    private JPanel contentPane;

    private JPanel numbersPanel;
    private JLabel firstNumberLabel;
    private JTextField firstNumberTextField;
    private JLabel secondNumberLabel;
    private JTextField secondNumberTextField;
    private JLabel blankLabel1;
    private JLabel blankLabel2;

    private JPanel resultPanel;
    private JLabel resultLabel;
    private JTextArea resultTextArea;

    private JPanel buttonPane;
    private JButton addButton;
    private JButton subButton;
    private JButton mulButton;
    private JButton divButton;
    private JButton derivativeButton;
    private JButton integralButton;

    Controller controller = new Controller(this);

    public View(String name) {
        super(name);
        this.prepareGui();
    }

    public void prepareGui(){
        this.setSize(300, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.contentPane = new JPanel(new GridLayout(3, 2));
        this.prepareNumbersPanel();
        this.prepareResultPanel();
        this.prepareButtonPanel();
        this.setContentPane(this.contentPane);
    }

    private void prepareResultPanel() {
        this.resultPanel = new JPanel();
        this.resultPanel.setLayout(new GridLayout(2, 2));
        this.resultLabel = new JLabel("Result: ", JLabel.CENTER);
        this.resultPanel.add(this.resultLabel);
        this.resultTextArea = new JTextArea();
        this.resultTextArea.setEditable(false);
        this.resultPanel.add(this.resultTextArea);
        this.contentPane.add(this.resultPanel);
    }

    private void prepareNumbersPanel() {
        this.numbersPanel = new JPanel();
        this.numbersPanel.setLayout(new GridLayout(3, 2));
        this.firstNumberLabel = new JLabel("First Polynomial: ", JLabel.CENTER);
        this.numbersPanel.add(this.firstNumberLabel);
        this.firstNumberTextField = new JTextField();
        this.numbersPanel.add(this.firstNumberTextField);
        this.secondNumberLabel = new JLabel("Second Polynomial: ", JLabel.CENTER);
        this.numbersPanel.add(this.secondNumberLabel);
        this.secondNumberTextField = new JTextField();
        this.numbersPanel.add(this.secondNumberTextField);
        this.blankLabel1 = new JLabel("", JLabel.CENTER);
        this.numbersPanel.add(this.blankLabel1);
        this.blankLabel2 = new JLabel("", JLabel.CENTER);
        this.numbersPanel.add(this.blankLabel2);
        this.contentPane.add(this.numbersPanel);
    }

    private void prepareButtonPanel(){
        this.buttonPane = new JPanel();
        this.buttonPane.setLayout(new GridLayout(4, 2));
        this.addButton = new JButton("Addition");
        this.addButton.setActionCommand("add");
        this.addButton.addActionListener(this.controller);
        this.buttonPane.add(this.addButton);
        this.subButton = new JButton("Subtraction");
        this.subButton.setActionCommand("sub");
        this.subButton.addActionListener(this.controller);
        this.buttonPane.add(this.subButton);
        this.mulButton = new JButton("Multiplication");
        this.mulButton.setActionCommand("mul");
        this.mulButton.addActionListener(this.controller);
        this.buttonPane.add(this.mulButton);
        this.divButton = new JButton("Division");
        this.divButton.setActionCommand("div");
        this.divButton.addActionListener(this.controller);
        this.buttonPane.add(this.divButton);
        this.derivativeButton = new JButton("Derivative");
        this.derivativeButton.setActionCommand("derivative");
        this.derivativeButton.addActionListener(this.controller);
        this.buttonPane.add(this.derivativeButton);
        this.integralButton = new JButton("Integral");
        this.integralButton.setActionCommand("integral");
        this.integralButton.addActionListener(this.controller);
        this.buttonPane.add(this.integralButton);
        this.contentPane.add(this.buttonPane);
    }

    public JTextField getFirstNumberTextField() {
        return firstNumberTextField;
    }

    public JTextField getSecondNumberTextField() {
        return secondNumberTextField;
    }

    public void setResultTextArea(String s){
        this.resultTextArea.setText(s);
    }


}