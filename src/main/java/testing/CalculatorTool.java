package testing;

import java.util.*;

public class CalculatorTool {
    private String initialExpression;
    private List<String> splitExpression;
    List<Integer> numbers = new ArrayList<>();
    List<String> unitsArray = new ArrayList<>();
    List<String> operators = new ArrayList<>();


    public CalculatorTool(String initialExpression) {
        this.initialExpression = initialExpression;
        split();
    }

    public Map<String,Integer> units = new HashMap<String,Integer>() {
        {
            put("mm", 1);
            put("cm", 2);
            put("dm", 3);
            put("m", 4);
            put("km", 5);
        }
    };
    public List<String> split() {
        String expression;
        expression = initialExpression.trim();
        splitExpression = Arrays.asList(expression.split(" "));
        return splitExpression;
    }
    public List<Integer> findNumbers() {
        for (int i = 0; i < splitExpression.size(); i+=3) {
            numbers.add(Integer.valueOf(splitExpression.get(i)));
        }
        return numbers;
    }
    public List<String> findUnits() {
        for (int i = 1; i < splitExpression.size(); i+=3) {
            unitsArray.add(splitExpression.get(i));
        }
        return unitsArray;
    }
    public List<String> findOperators() {
        for (int i = 2; i < splitExpression.size(); i+=3) {
            operators.add(splitExpression.get(i));
        }
        return operators;
    }

    public String findSmallestUnit() {
        int min = 5;
        String smallestUnit = "";
            for (String unit : unitsArray) {
        int value = units.get(unit);
        if (value < min) {
            min = value;
            smallestUnit = unit;
        }
    }
    return smallestUnit;
}

    public int convertToSmallestUnit(String smallestUnit, int value, String unit) {
        switch(smallestUnit) {
            case "mm" :
                return convertToMillimeters(value,unit);
            case "cm" :
                return convertToCentimeters(value,unit);
            case "dm" :
                return convertToDecimeters(value,unit);
            case "m" :
                return convertToMeters(value,unit);
        }
        return value;
    }
    public int convertToMillimeters(int value, String unit) {
        int factor = 1;
        switch(unit) {
            case "cm":
                factor = 10;
                break;
                case "dm":
                factor = 100;
                break;
                case "m":
                factor = 1000;
                break;
                case "km":
                factor = 1000000;
                break;
        }
        return value * factor;
    }

    public int convertToCentimeters(int value, String unit) {
        int factor = 1;
        switch(unit) {
            case "dm":
                factor = 10;
                break;
            case "m":
                factor = 100;
                break;
            case "km":
                factor = 100000;
                break;
        }
        return value * factor;
    }

    public int convertToDecimeters(int value, String unit) {
        int factor = 1;
        switch(unit) {
            case "m":
                factor = 10;
                break;
            case "km":
                factor = 10000;
                break;
        }
        return value * factor;
    }

    public int convertToMeters(int value, String unit) {
        if(unit.equals("km")){
            value = value * 1000;
        }
        return value;
    }

    public String calculate() {
        split();
        findNumbers();
        findUnits();
        findOperators();
        List<Integer> convertedValuesList = new ArrayList<>();
        String smallestUnit = findSmallestUnit();
        for (int i = 0; i < numbers.size(); i++) {
            int convertedValue = convertToSmallestUnit(smallestUnit, numbers.get(i), unitsArray.get(i));
            convertedValuesList.add(convertedValue);
        }
        int result = convertedValuesList.get(0);
        for (int i = 0; i < operators.size(); i++) {
            int value = convertedValuesList.get(i+1);
            if (operators.get(i).equals("+")) {
                result += value;
            } else if(operators.get(i).equals("-")) {
                result -= value;
            }
        }
        return initialExpression + "= " + result + " " + smallestUnit;
    }
}
