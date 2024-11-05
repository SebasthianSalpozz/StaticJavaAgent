package org.example;

public class FibonacciCalculator {
    public int calculateFibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
    }

    public static void main(String[] args) {
        FibonacciCalculator calculator = new FibonacciCalculator();
        int number = 10;
        System.out.println("Fibonacci(" + number + ") = " + calculator.calculateFibonacci(number));
    }
}

