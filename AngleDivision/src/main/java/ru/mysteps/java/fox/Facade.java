package ru.mysteps.java.fox;

public class Facade {

    Calculator calculator = new Calculator();
    Formatter formatter = new Formatter();

    public String divide(int divisible,
                         int divisor) {

        return formatter.build(calculator.calculate(divisible, divisor));
    }
}


