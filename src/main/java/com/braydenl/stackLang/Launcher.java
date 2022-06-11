package com.braydenl.stackLang;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;


public class Launcher {

    static Scanner scanner = new Scanner(System.in);
    static HashMap<String, Object> stack;
    static int line = 0;
    static String input;
    static boolean running = true;

    public static void main(String[] args) {
        stack = new HashMap<>();
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

                    case "PosInf":
                        stack.put(tokens[2], Double.POSITIVE_INFINITY);
                        break;

                    case "NegInf":
                        stack.put(tokens[2], Double.NEGATIVE_INFINITY);
                        break;

                    case "NaN":
                        stack.put(tokens[2], Double.NaN);
                        break;

                    case "table":

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

            case "struct":

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
