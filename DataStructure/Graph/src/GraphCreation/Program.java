package GraphCreation;

import java.util.*;

public class Program {
    public static int getNthFib(int n) {
        // Write your code here.
        int firstNumber = 0;
        int secondNumber = 1;
        int result = 0;
        Stack<Integer> stack = new Stack();
        stack.push(0);
        stack.push(1);
        for(int i=0;i<n-2;i++){
            result = firstNumber + secondNumber;
            stack.push(result);
            firstNumber = secondNumber;
            secondNumber= result;
        }
        stack.pop();
        int a = stack.pop();
        int b = stack.pop();
        return a+b;
    }

    public static void main(String[] args) {
        System.out.println(getNthFib(6));
    }

}
