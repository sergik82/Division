package ru.mysteps.java.fox;


import java.util.ArrayList;
import java.util.List;

public class Formatter {
    public String build(DivisionData data) {
        StringBuilder collector = new StringBuilder();
        calculateSpaces(data);
        collector.append(drawHead(data, calculateSpaces(data)));
        collector.append(drawBody(data, calculateSpaces(data)));
        return collector.toString();
    }

    private List<Integer> calculateSpaces(DivisionData data) {
        List<Integer> blanks = new ArrayList<>();
        int space;
        if (String.valueOf(data.getFirstPart()).length() >
            String.valueOf(data.getSteps().get(0).getDeductible()).length()) {
            space = 1;
            blanks.add(space);
        } else {
            space = 0;
            blanks.add(space);
        }
        for (int i = 1; i < data.getSteps().size(); i++) {
//            between differenceLength and deductLength
            if (i > 1 && String.valueOf(data.getSteps().get(i).getPart() - data.getSteps().get(i).getDeductible()).length() <
                String.valueOf(data.getSteps().get(i).getDeductible()).length()) {
                space ++;
                blanks.add(space);
            }
//            if difference == zero in steps
            if (i > 1 && data.getSteps().get(i).getPart() - data.getSteps().get(i).getDeductible() == 0) {
                space += String.valueOf(data.getSteps().get(i - 1).getDeductible()).length();
                blanks.add(space);
            }
             else {
                blanks.add(space);
            }
        }
        return blanks;
    }

    private StringBuilder drawHead(DivisionData data,
                                   List<Integer> blanks) {
        StringBuilder sb1 = new StringBuilder();
        if (data.getDivisible() < data.getDivisor()) {
            System.out.println("Divisible should be more than divisor!!!");
        } else {
            //first line
            sb1.append("_");
            sb1.append(data.getDivisible() + "|" + data.getDivisor() + "\n");
            //second line
            sb1.append(" ");
            sb1.append(" ".repeat(blanks.get(0)) + data.getSteps().get(0).getDeductible());
            sb1.append(" ".repeat((String.valueOf(data.getDivisible()).length() - (blanks.get(0) +
                (String.valueOf(data.getSteps().get(0).getDeductible()).length())))));
            sb1.append("|");
            sb1.append(String.valueOf(data.getResult()).replaceAll("\\d", "-") + "\n");
            //third line
            sb1.append(" ");
            sb1.append(" ".repeat(blanks.get(0)) +
                String.valueOf(data.getSteps().get(0).getDeductible()).replaceAll("\\d", "-"));
            sb1.append(" ".repeat((String.valueOf(data.getDivisible()).length() - (blanks.get(0) +
                (String.valueOf(data.getSteps().get(0).getDeductible()).length())))));
            sb1.append("|");
            sb1.append(data.getResult() + "\n");
        }
        return sb1;
    }

    private StringBuilder drawBody(DivisionData data,
                                   List<Integer> blanks) {
        StringBuilder sb2 = new StringBuilder();
//            START OF DIVISION STEPS
        for (int i = 0; i < data.getSteps().size(); i++) {
            if (data.getSteps().get(i).getPart() != null) {
                sb2.append(" ".repeat(blanks.get(i)) + "_" + data.getSteps().get(i).getPart() + "\n");
                sb2.append(" " + " ".repeat(blanks.get(i)) + data.getSteps().get(i).getDeductible() + "\n");
                sb2.append(" " + " ".repeat(blanks.get(i)));
                sb2.append("-".repeat(String.valueOf(data.getSteps().get(i).getDeductible()).length()) + "\n");
            }
            if (i == data.getSteps().size() - 1) {
                sb2.append(" " + " ".repeat(blanks.get(i)) + data.getSurplus());
            }
        }
        return sb2;
    }

}