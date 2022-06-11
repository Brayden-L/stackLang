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

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void process(String instruction) {
        String[] tokens = instruction.split(" ", 0);
        switch (tokens[0]) {

            case "let":
                if (tokens.length != 4) {
                    System.out.println("Not enough stuff in this instruction.");
                    return;
                }
                switch (tokens[1]) {
                    case "int":
                        stack.put(tokens[2], BigInteger.valueOf(Long.parseLong(tokens[3])));
                        break;

                    case "float":
                        stack.put(tokens[2], BigDecimal.valueOf(Float.parseFloat(tokens[3])));
                        break;

                    case "char":
                        stack.put(tokens[2], tokens[3].toCharArray()[0]);
                        break;

                    case "str":

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
                clearScreen();
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
                System.out.println(stack.get(tokens[1]));
                break;

            case "drop":

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
