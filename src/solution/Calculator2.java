package solution;

import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.EmptyStackException;

/**
* @author Ryon McAuley
* @version 4-5
*/
public class Calculator2
{
    private JFrame calculator;
    private float result;
    private String i = "                         ";
    private JLabel resultLabel;
    private JPanel panel;
    private JPanel buttonPanel;
    private JTextField infixExpression;
    private JButton calculateButton;
    private JButton clearButton;
    private String s = "error";

    ExpressionEvaluator eval = new ExpressionEvaluator();
    private double ans;

    /**
    *
    */
    public Calculator2()
    {
        initializeParts();
        addParts();
        calculator.pack();
        calculator.setVisible(true);
    }

    /**
    *
    */
    public void initializeParts()
    {
        calculator = new JFrame();
        resultLabel = new JLabel();
        resultLabel.setText("Result = " + s);
        panel = new JPanel();
        infixExpression = new JTextField();
        buttonPanel = new JPanel();
        initializeButtons();
        infixExpression.setName("infixExpression");
        calculator.setLocation(100, 100);
        calculator.setSize(500, 300);
        calculator.setTitle("Calculator");
    }

    /**
    *
    */
    public void initializeButtons()
    {
        calculateButton = new JButton();
        calculateButton.setName("calculateButton");
        calculateButton.setText("calculate");
        clearButton = new JButton();
        clearButton.setName("clearButton");
        clearButton.setText("clear");

        calc(calculateButton, infixExpression);
        clr(clearButton);

        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);

        calculator.add(buttonPanel, BorderLayout.CENTER);
    }

    /**
    *
    */
    public void addParts()
    {
        infixExpression.setText(i);
        panel.add(infixExpression, BorderLayout.PAGE_START);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(resultLabel);
        //calculator.add(resultLabel, BorderLayout.PAGE_END);
        
        calculator.add(panel, BorderLayout.PAGE_START);
    }

    /**
    *
    */
    public void calc(JButton calculate, JTextField exp)
    {
       ans = 0.0;
       calculate.addActionListener(new ActionListener()
       {
               public void actionPerformed(ActionEvent e)
               {
                   try
                   {
                       ans = eval.evaluate(eval.toPostfix(exp.getText()));
                       s = String.valueOf(ans);
                   }
                   catch (EmptyStackException u)
                   {
                       s = "error";
                   }
                   finally
                   {
                       resultLabel.setText("Result = " + s);
                   }
               }
       });
    }

    /**
    *
    */
    public void clr(JButton c)
    {
        c.addActionListener(new ActionListener()
        {
               public void actionPerformed(ActionEvent e)
               {
                   infixExpression.setText("");
                   resultLabel.setText("Result = ");
               }
        });
    }

    /**
    * @return JFrame the calculator frame
    */
    public JFrame getFrame()
    {
        return calculator;
    }

    /**
    *
    */
    public static void main(String[] args)
    {
        Calculator2 gui = new Calculator2();
    }
}
