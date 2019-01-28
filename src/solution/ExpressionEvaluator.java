package solution;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Stack;

/**
 * 
 * @author ???
 * @version ???
 * 
 */
public class ExpressionEvaluator
{

    public static final Pattern UNSIGNED_DOUBLE =
            Pattern.compile("((\\d+\\.?\\d*)|(\\.\\d+))([Ee][-+]?\\d+)?.*?");
    public static final Pattern CHARACTER = Pattern.compile("\\S.*?");

    /**
     * Takes an infix expression and converts it to postfix.
     * 
     * @param expression
     *            The infix expression.
     * @return the postfix expression.
     */
    public String toPostfix(String expression)
    {
        // ... other local variables
        Scanner input = new Scanner(expression);
        String next;
        char symbol;
        String pfExp = "";
        Stack<Character> conversion = new Stack<>();

        while (input.hasNext())
        {
            if (input.hasNext(UNSIGNED_DOUBLE))
            {
                next = input.findInLine(UNSIGNED_DOUBLE);
                pfExp += next + " ";
            }
            else
            {
                next = input.findInLine(CHARACTER);
                symbol = next.charAt(0);

                switch (symbol)
                {
                   case '(' :
                       conversion.push(symbol);
                       break;
                   case '+' :
                   case '-' :
                   case '/' :
                   case '*' :
                       while(!conversion.empty() && !conversion.peek().equals('(')
                          && canPop(symbol, conversion.peek()))
                       {
                           char op = conversion.pop();
                           pfExp += op + " ";
                       }

                       conversion.push(symbol);
                       break;
                   case ')' :
                       while(!conversion.peek().equals('('))
                       {
                           pfExp += conversion.pop() + " ";
                       }
                       if (!conversion.peek().equals(')'))
                       {
                           conversion.pop();
                       }
                       break;
                }
            }
        }
        while (!conversion.empty())
        {
            pfExp += conversion.pop() + " ";
        }
        System.out.println(pfExp);
        return  pfExp;
    }

    public boolean canPop(char first, char second)
    {
        if (second == '*' || second == '/')
        {
            return true;
        }
        return first != '*' && first != '/';
    }

    /**
     * Evaluates a postfix expression and returns the result.
     * 
     * @param postfixExpression
     *            The postfix expression.
     * @return The result of the expression.
     */
    public double evaluate(String postfixExpression)
    {
        // other local variables you may need
        Scanner input = new Scanner(postfixExpression);
        String next;
        double n;
        char operator;
        double answer = Double.NaN;
        Stack<Double> evaluation = new Stack<>();
        double a;
        double b;

        while (input.hasNext())
        {

            if (input.hasNext(UNSIGNED_DOUBLE))
            {
                n = input.nextDouble();
                evaluation.push(n);
            }
            else
            {
                operator = input.next().charAt(0);
                System.out.println(evaluation.peek());
                a = evaluation.pop();
                b = evaluation.pop();

                switch(operator)
                {
                    case '+' :
                        evaluation.push((double)a + b);
                        break;
                    case '-' :
                        evaluation.push((double)b - a);
                        break;
                    case '*' :
                        evaluation.push((double)b * a);
                        break;
                    case '/' :
                        evaluation.push((double)b / a);
                        break;
                }
            } 
        }
        return (double) evaluation.pop();
    }
}
//Evaluate before the purge
	/*
            if (input.hasNext(UNSIGNED_DOUBLE))
            {
                next = input.findInLine(UNSIGNED_DOUBLE);
                // TODO: do what you want to with a String that
                // holds a number
                evaluation.push(next);
            }
            else
            {
                next = input.findInLine(CHARACTER);
                operator = next.charAt(0);

                // TODO: do what you want to with an operator
                // such as *, /, +, -
		a = evaluation.pop();
		b = evaluation.pop();
                                
                switch(operator)
                {
                    case '+' :
                        evaluation.push(a + b);
                        break;
                    case '-' :
                        evaluation.push(b - a);
                        break;
                    case '*' :
                        evaluation.push(b * a);
                        break;
                    case '/' :
                        evaluation.push(b / a);
                        break;
                }
            }
        }
        try
        {
            return Double.parseDouble((String) evaluation.pop());
        }
        catch (ClassCastException e)
        {
            if (!evaluation.empty())
            {
                return (double) evaluation.pop();
            }
            else
            {
                return answer;
            }
        }
        */
