package mydatastructure.Stack;


/*
Algorithm:
1) Declare a character stack S.
2) Now traverse the expression string exp.
    a) If the current character is a starting bracket (‘(‘ or ‘{‘ or ‘[‘) then push it to stack.
    b) If the current character is a closing bracket (‘)’ or ‘}’ or ‘]’) then pop from stack and if the popped character is the matching starting bracket then fine else parenthesis are not balanced.
3) After complete traversal, if there is some starting bracket left in stack then “not balanced”

Implementation:
Link : https://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/
 */
// Java program for checking
// balanced Parenthesis

public class BalancedParan
{
    static class stack
    {
        int top=-1;
        char items[] = new char[100];

        void push(char x)
        {
            if (top == 99)
            {
                System.out.println("Stack full");
            }
            else
            {
                items[++top] = x;
            }
        }

        char pop()
        {
            if (top == -1)
            {
                System.out.println("Underflow error");
                return '\0';
            }
            else
            {
                char element = items[top];
                top--;
                return element;
            }
        }

        boolean isEmpty()
        {
            return (top == -1) ? true : false;
        }
    }

    /* Returns true if character1 and character2
       are matching left and right Parenthesis */
    static boolean isMatchingPair(char character1, char character2)
    {
        if (character1 == '(' && character2 == ')')
            return true;
        else if (character1 == '{' && character2 == '}')
            return true;
        else if (character1 == '[' && character2 == ']')
            return true;
        else
            return false;
    }

    /* Return true if expression has balanced
       Parenthesis */
    static boolean areParenthesisBalanced(char exp[])
    {
        /* Declare an empty character stack */
        stack st=new stack();

       /* Traverse the given expression to
          check matching parenthesis */
        for(int i=0;i<exp.length;i++)
        {

          /*If the exp[i] is a starting
            parenthesis then push it*/
            if (exp[i] == '{' || exp[i] == '(' || exp[i] == '[')
                st.push(exp[i]);

          /* If exp[i] is an ending parenthesis
             then pop from stack and check if the
             popped parenthesis is a matching pair*/
            if (exp[i] == '}' || exp[i] == ')' || exp[i] == ']')
            {

              /* If we see an ending parenthesis without
                 a pair then return false*/
                if (st.isEmpty())
                {
                    return false;
                }

             /* Pop the top element from stack, if
                it is not a pair parenthesis of character
                then there is a mismatch. This happens for
                expressions like {(}) */
                else if ( !isMatchingPair(st.pop(), exp[i]) )
                {
                    return false;
                }
            }

        }

       /* If there is something left in expression
          then there is a starting parenthesis without
          a closing parenthesis */

        if (st.isEmpty())
            return true; /*balanced*/
        else
        {   /*not balanced*/
            return false;
        }
    }

    /* UTILITY FUNCTIONS */
    /*driver program to test above functions*/
    public static void main(String[] args)
    {
        char exp[] = {'{','(',')','}','[',']'};
        if (areParenthesisBalanced(exp))
            System.out.println("Balanced ");
        else
            System.out.println("Not Balanced ");
    }

}
