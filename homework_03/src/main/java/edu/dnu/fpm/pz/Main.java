package edu.dnu.fpm.pz;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static boolean uInterface(List list, int action) throws InvalidDataException {

        Scanner in = new Scanner(System.in);
        int index, element;
        switch (action) {
            case 0:
                return false;
            case 1:
                System.out.println("Enter value:");
                element =Integer.parseInt(in.nextLine());
                list.add(element);

                break;
            case 2:
                Object removed;
                System.out.println("Enter index:");
                index =Integer.parseInt(in.nextLine());
                removed = list.remove(index);
                System.out.println("Removed element: " + removed);
                break;
            case 3:
                list.print();
                break;
            case 4:
                System.out.println("Enter index:");
                index = Integer.parseInt(in.nextLine());
                System.out.println("Enter element:");
                element = Integer.parseInt(in.nextLine());
                list.replace(index, element);

                break;
            case 5:
                System.out.println("Enter index:");
                index = Integer.parseInt(in.nextLine());
                System.out.println("On index: "+index+" located element: "+list.get(index));
                break;
            case 6:
                System.out.println("Enter index:");
                index = Integer.parseInt(in.nextLine());
                System.out.println("Enter element:");
                element = Integer.parseInt(in.nextLine());
                list.insert(index, element);

                break;
            case 7:
                System.out.println(list.getSize());

                break;
            default:
                System.out.println("Wrong action!0_____0!");
                break;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> arrList = new ArrayList<Integer>();
        SingleList<Integer> singleList = new SingleList<Integer>();
        boolean repeat = true;
        while (repeat) {
            System.out.println("Chose action:");

            try {
                repeat = uInterface(singleList, Integer.parseInt(in.nextLine()));
            } catch (InvalidDataException e) {
                System.out.println(e);
            }

        }
    }
}
