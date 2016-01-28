package com.company;

public class Main {

    public static void main(String[] args) {

        for (int x = 2; x <= 100000; x++) {
            if (multipleOf(3, x) && multipleOf(5, x)) {
                System.out.println(x + " FizzBuzz");

            }else if (multipleOf(3, x)) {
                System.out.println(x + " Fizz");

            }else if(multipleOf(5,x)){
                System.out.println(x + " Buzz");

            }else {
                System.out.println(x);
            }
        }
    }

    public static boolean multipleOf(int multiple, int check) {
        return (check % multiple) == 0;

    }
}
