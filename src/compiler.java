
import javax.script.ScriptEngine;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class compiler {
    public static void main(String[] args) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {


        //file declaration
        String path = "/Users/sumanth/IdeaProjects/Compiler/src/code";
        File file = new File(path);

        //reader
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        Scanner s = new Scanner(System.in);

        st = br.readLine();


        //Variable holders
        HashMap<String, Integer> integers = new HashMap<>();
        HashMap<String, Double> floats = new HashMap<>();
        HashMap<String, String> Strings = new HashMap<>();
        HashMap<String, Boolean> booleans = new HashMap<>();

        //arraylist for holding code
        ArrayList<String> lines = new ArrayList<>();

        //araylist for holding forloop counters
        ArrayList<Integer> forloops = new ArrayList<>();
        ArrayList<String> forloopskeys = new ArrayList<>();

        //for loop boolean
        boolean forloopactive = false;
        boolean ifloopactive = false;


        //iterates through text file, adding the lines of code to an arraylist
        while ((st = br.readLine()) != null) {
            lines.add(st);
        }

        //main code part for iterating/running
        for (int i = 0; i < lines.size(); i++) {
            st = lines.get(i);

            if (st.contains("//")) {

            } else if (ifloopactive && st.indexOf("}") > 0) {
                ifloopactive = false;

            } else if (st.indexOf("}") > 0 && forloopactive) {
                if (integers.get(forloopskeys.get(forloopskeys.size() - 1)) > forloops.get(forloops.size() - 3) - 1) {
                    for (int j = 0; j < 3; j++) {
                        forloops.remove(forloops.size() - 1);
                    }
                    forloopactive = false;

                } else {
                    //puts back @ line
                    i = forloops.get(forloops.size() - 2);
                    //increment the val
                    integers.put(forloopskeys.get(forloopskeys.size() - 1), integers.getOrDefault(forloopskeys.get(forloopskeys.size() - 1), 0) + forloops.get(forloops.size() - 1));
                }
            } else if (st.indexOf("print", 0) > 0 && !ifloopactive) {
                //print case
                printing prints = new printing(st);
                prints.print(integers, booleans, floats, Strings);

            } else if (st.indexOf("int") > 0 && !ifloopactive) {
                 varDeclaration(st, "int", integers);
            } else if (st.indexOf("float", 0) > 0 && !ifloopactive) {
                varDeclaration(st, "float", floats);
            } else if (st.indexOf("String", 0) > 0 && !ifloopactive) {
                varDeclaration(st, "String", Strings);
            } else if (st.indexOf("boolean", 0) > 0 && !ifloopactive) {
                varDeclaration(st, "boolean", booleans);
            }
            //arithmetic cases
            else if (st.indexOf("add") > 0 && !ifloopactive) {
                arithmeticoperations add = new arithmeticoperations("add", st);
                add.arithmetics(integers, floats);
            } else if (st.indexOf("subtract") > 0 && !ifloopactive) {
                arithmeticoperations sub = new arithmeticoperations("subtract", st);
                sub.arithmetics(integers, floats);
            } else if (st.indexOf("division") > 0 && !ifloopactive) {
                arithmeticoperations div = new arithmeticoperations("division", st);
                div.arithmetics(integers, floats);
            } else if (st.indexOf("multiplication") > 0 && !ifloopactive) {
                arithmeticoperations mult = new arithmeticoperations("multiplication", st);
                mult.arithmetics(integers, floats);
            } else if (st.indexOf("modulus") > 0 && !ifloopactive) {
                arithmeticoperations mod = new arithmeticoperations("modulus", st);
                mod.arithmetics(integers, floats);

            } else if (st.indexOf("for") > 0 && !ifloopactive) {
                //creates forloop object and runs the method
                forloop loops = new forloop(st);
                loops.looping(i, forloopactive, forloopskeys, forloops, integers);
                forloopactive = true;

            } else if (st.indexOf("input") > 0 && !ifloopactive) {
                //creates input object and runs method
                input inputting = new input(st);
                inputting.inputting(s, booleans, integers, floats, Strings);

            } else if (st.indexOf("if") > 0 && !ifloopactive) {
                int start = st.indexOf("(") + 1;
                int end = st.indexOf(")");
                String conditional = st.substring(start, end);
                boolean happening = evaluateConditional(conditional, integers);

                if (!happening) {
                    ifloopactive = true;
                }
            }

        }
    }



            //conditional

    public static boolean evaluateConditional(String conditional,
                                              HashMap<String, Integer> integers) {
        String[] tokens = conditional.split(" ");
        String operator = tokens[1];
        int leftOperand = 0;
        int rightOperand = 0;
        if (tokens[0].contains("_") && tokens[2].contains("_")) {
            String first = tokens[0].substring(1);
            String second = tokens[2].substring(1);
            if (integers.containsKey(first)) {
                leftOperand = integers.get(first);
            } else {
                System.out.println("No val!");
                return false;
            }
            if (integers.containsKey(second)) {
                leftOperand = integers.get(second);
            }
        } else if (tokens[0].contains("_")) {
            String first = tokens[0].substring(1);
            if (integers.containsKey(first)) {
                leftOperand = integers.get(first);
            } else {
                System.out.println("No val!");
                return false;
            }
            rightOperand = Integer.parseInt(tokens[2]);
        } else if (tokens[2].contains("_")) {
            String second = tokens[2].substring(1);
            if (integers.containsKey(second)) {
                rightOperand = integers.get(second);
            } else {
                System.out.println("No val!");
                return false;
            }
            leftOperand = Integer.parseInt(tokens[0]);
        } else
        {
                leftOperand = Integer.parseInt(tokens[0]);
                rightOperand = Integer.parseInt(tokens[2]);
        }
            switch (operator) {
                case ">":
                    return leftOperand > rightOperand;
                case "<":
                    return leftOperand < rightOperand;
                case ">=":
                    return leftOperand >= rightOperand;
                case "<=":
                    return leftOperand <= rightOperand;
                case "==":
                    return leftOperand == rightOperand;
                case "!=":
                    return leftOperand != rightOperand;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);

        }
    }


    public static void varDeclaration(String st, String type, HashMap list) {
            String name;
            //creates the name value
        int beginIndex = st.indexOf(type) + type.length() + 1;
        if (st.indexOf("=") > 0) {
                 name = st.substring(beginIndex, st.indexOf("=") - 1);
            } else {
                 name = st.substring(beginIndex, st.indexOf(";"));
            }
            //parses string to integer value
            if (st.indexOf("=") > 0 && type.equals("int")) {
                int value = Integer.parseInt(st.substring(st.indexOf("=") + 2, st.indexOf(";")));

                //puts it in list
                list.put(name, value);
            } else if (st.contains("=")&& type.equals("String")) {
                String value = st.substring(st.indexOf("=") + 2, st.indexOf(";"));
                list.put(name, value);

            } else if (st.indexOf("=") > 0 && type.equals("boolean")) {
                boolean value = Boolean.parseBoolean(st.substring(st.indexOf("=") + 2, st.indexOf(";")));
                list.put(name, value);
            } else if (st.indexOf("=") > 0 && type.equals("float")) {
                double value = Double.parseDouble(st.substring(st.indexOf("=") + 2, st.indexOf(";")));
                list.put(name, value);
            }
            else {
                list.put(name,null);
            }
    }
}



