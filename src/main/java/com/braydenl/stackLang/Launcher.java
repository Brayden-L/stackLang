package com.braydenl.stackLang;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Scanner;


public class Launcher {

    static Scanner scanner = new Scanner(System.in);
    static LinkedHashMap<String, Object> stack;
    static int line = 0;
    static String input;
    static boolean running = true;

    public static void main(String[] args) {
        stack = new LinkedHashMap<>();
        System.out.println("Use command quit to quit.");
        while (running) {
            System.out.print("[" + (line) + "]>>> ");
            input = scanner.nextLine();
            process(input);
            line++;
        }
    }

    static void process(String instruction) {
        String[] tokens = instruction.split(" ", 0);
        switch (tokens[0]) {
            case "let":
                switch (tokens[1]) {
                    case "int":
                        stack.put(tokens[2], new BigInteger(tokens[3]));
                        break;

                    case "float":
                        stack.put(tokens[2], new BigDecimal(tokens[3]));
                        break;

                    case "char":
                        stack.put(tokens[2], tokens[3].replace("'", "").toCharArray()[0]);
                        break;

                    case "str":
                        stack.put(tokens[2], instruction.split("\"", 0)[1]);
                        break;

                    case "bool":
                        if (!tokens[3].equals("true") && !tokens[3].equals("false")) {
                            System.out.println("Invalid boolean " + tokens[3] + " at line " + line + ". Value must be true or false");
                            break;
                        }
                        stack.put(tokens[2], Boolean.valueOf(tokens[3]));
                        break;

                    case "PosInf":
                        stack.put(tokens[2], Double.POSITIVE_INFINITY);
                        break;

                    case "NegInf":
                        stack.put(tokens[2], Double.NEGATIVE_INFINITY);
                        break;

                    case "NaN":
                        stack.put(tokens[2], Double.NaN);
                        break;

                    default:
                        try {
                            System.out.println("Invalid type " + tokens[1] + " at line " + line + ".");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("No type declared following let at line " + line + ".");
                        }
                }
                break;

            case "func":

                break;

            case "clear":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                break;

            case "quit":
                System.out.println("Come back soon!");
                running = false;
                return;

            case "print":
                if (stack.get(tokens[1]) == null) {
                    System.out.println("Item not on the stack.");
                    break;
                }
                System.out.println(stack.get(tokens[1]).toString());
                break;

            case "drop":
                if (stack.get(tokens[1]) == null) {
                    System.out.println("Item not on the stack.");
                    break;
                }
                stack.remove(tokens[1]);
                System.out.println("Dropped " + tokens[1] + " from the stack.");
                break;

            case "while":

                break;

            case "if":

                break;

            case "stack-dump":
                if (stack.isEmpty()) {
                    System.out.println("Nothing on the stack!");
                    break;
                }
                int greatestLength = 0;
                for (String item : stack.keySet()) {
                    if (item.length() > greatestLength) {
                        greatestLength = item.length();
                    }
                }
                for (String item : stack.keySet()) {
                    int padding;
                    padding = greatestLength - item.length() + 1;
                    String spaces = " ".repeat(padding);
                    String type = String.valueOf(stack.get(item).getClass());
                    if (type.contains("BigInteger"))    type = "int";
                    if (type.contains("BigDecimal"))    type = "float";
                    if (type.contains("Character"))     type = "char";
                    if (type.contains("String"))        type = "str";
                    if (type.contains("Boolean"))       type = "bool";
                    try {
                        if (((Double) stack.get(item)).isInfinite() && !stack.get(item).toString().contains("-")) type = "PosInf";
                        if (((Double) stack.get(item)).isInfinite() && stack.get(item).toString().contains("-")) type = "NegInf";
                        if (((Double) stack.get(item)).isNaN()) type = "NaN";
                    } catch (ClassCastException ignored) {}
                    if (type.equals("str")) {
                        System.out.println(item + spaces + "-> \"" + stack.get(item) + "\"    " + type);
                    } else {
                        System.out.println(item + spaces + "-> " + stack.get(item) + "    " + type);
                    }
                }
                break;

            case "stack-clear":
                stack.clear();
                System.out.println("Cleared the stack!");
                break;

            default:
                try {
                    System.out.println("Invalid instruction " + tokens[0] + " at line " + line + ".");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\n");
                }
                break;
        }
    }

}
