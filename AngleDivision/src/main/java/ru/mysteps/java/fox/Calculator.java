package ru.mysteps.java.fox;

public class Calculator {

    public DivisionData calculate(int divisible,
                                  int divisor) {
        int surplus = divisible % divisor;
        int result = divisible / divisor;
        int numLength = String.valueOf(divisible).length();
        int[] numbers = new int[numLength];
        DivisionData data = new DivisionData(divisible, divisor, result, surplus);

        for (int i = 0; i <= numLength - 1; i++) {
            numbers[i] = String.valueOf(divisible).charAt(i) - '0';
        }
        int part = numbers[0];
        int repeat = numbers.length;
        int deductible = 0;
        Step step;
        int space = 0;

        for (int j = 1; j <= repeat; j++) {
            step = new Step();

            saveSpacesFewDivisible(part, divisor, divisible, data);
            if (divisible < divisor) {
                break;
            }
            if (part == 0) {
                space++;
            }
            if (part == 0) {
                space=1;
            }
            if (part == 0) {
                space--;
            }

            if (part >= divisor) {
                deductible = (part / divisor) * divisor;
                step.setPart(part);
                step.setDeductible(deductible);
                saveSpases(part, deductible, space, data);
                part = part % divisor;
                data.setSteps(step);
            }

            if (part < divisor && j < repeat) {
                part = Integer.parseInt(part + "" + numbers[j]);
            }
        }
        return data;
    }

    public void saveSpases(int part,
                           int deductible,
                           int space,
                           DivisionData data) {
        if (String.valueOf(part).length() > String.valueOf(deductible).length()) {
            space = String.valueOf(part).length();
            data.setSpaces(space);
        }
        if (String.valueOf(part).length() <= String.valueOf(deductible).length()) {
            space++;
            data.setSpaces(space);
        }


    }

    public void saveSpacesFewDivisible(int part,
                                       int divisor,
                                       int divisible,
                                       DivisionData data) {
        if (divisible < divisor) {
            Step step = new Step();
            int space = 0;
            step.setPart(part);
            step.setDeductible(divisor);
            space = String.valueOf(divisor).length();
            data.setSpaces(space);
            data.setSteps(step);
        }
    }
}


