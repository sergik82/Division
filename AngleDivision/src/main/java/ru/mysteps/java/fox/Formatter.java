package ru.mysteps.java.fox;


public class Formatter {
    public String build(DivisionData data) {
        StringBuilder collector = new StringBuilder();
        if (data.getDivisible() < data.getDivisor()) {
            collector.append(String.format("%" + data.getSpaces().get(0) + "d%s%d%n", data.getDivisible(), "|",
                data.getDivisor()));
            collector.append(String.format("%d%s%" + data.getSpaces().get(0) + "s%n",
                data.getSteps().get(0).getDeductible(),
                "|", "").replace(' ', '-'));
            collector.append(String.format("%" + data.getSpaces().get(0) + "s|%d%n", "", data.getResult()));
        } else {
            //first line
            collector.append("_");
            collector.append(String.format("%d%s%d%n", data.getDivisible(), "|", data.getDivisor()));
            //second line
            collector.append(" ");
            if (data.getSpaces().size() > 1) {
                collector.append(String.format("%" + data.getSpaces().get(0) + "d%" +
                        ((String.valueOf(data.getDivisible()).length()) - data.getSpaces().get(0)) + "s|",
                    data.getSteps().get(0).getDeductible(), ""));
            } else {
                collector.append(String.format("%" + data.getSpaces().get(0) + "d%s|",
                    data.getSteps().get(0).getDeductible(), ""));
            }
            collector.append(String.format("%d%n", data.getResult()).replaceAll("\\d", "-"));
            collector.append(" ");
            //third line
            collector.append(String.format("%" + data.getSpaces().get(0) + "d",
                    data.getSteps().get(0).getDeductible()).replaceAll("\\d", "-"));
            if (data.getSpaces().size() > 1) {
                collector.append(String.format("%" +
                    (String.valueOf(data.getDivisible()).length() - data.getSpaces().get(0)) + "s|", ""));
            } else {
                collector.append("|");
            }
            collector.append(String.format("%d%n", data.getResult()));
//             START OF DIVISION STEPS
            for (int i = 1; i < data.getSteps().size(); i++) {
                if (data.getSteps().get(i).getPart() != null) {
                    collector.append(String.format("_%" + data.getSpaces().get(i) + "d%n",
                        data.getSteps().get(i).getPart()));
                    collector.append(String.format(" %" + data.getSpaces().get(i) + "d%n",
                        data.getSteps().get(i).getDeductible()));
                    collector.append(String.format(" %" + data.getSpaces().get(i) + "s%n",
                        "-".repeat(data.getSteps().get(i).getDeductible().toString().length())));
                    if(i==data.getSteps().size()-1){
                        collector.append(String.format("%"+data.getSpaces().get(i)+"s%d","",data.getSurplus()));
                    }
                }
            }
        }
        return collector.toString();
    }
}