package com.example.security;

import java.util.Scanner;

public class I_vending_machine {

    static final int COKE = 500, CIDER = 700, FANTA = 300, WATER = 200;

    public static void printMenu(int totalMoney) {
        System.out.println("================================= 자판기 ================================");
        System.out.println("[1]콜라-500원 [2]사이다-700원 [3]환타-300원 [4]물-200원 [5]돈넣기 [6]종료");
        System.out.println("현재 금액 : " + totalMoney + "원");
        System.out.println("==========================================================================");
    }

    public static void printException() {
        System.out.println("\n잔돈이 부족합니다.");
    }

    public static int getMoney() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("돈을 넣으시오 -> ");

        return scanner.nextInt();
    }

    public static int getChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("원하는 메뉴를 선택하시오 -> ");

        return scanner.nextInt();
    }

    public static int calcMoney(int totalMoney, int price) {
        return totalMoney - price;
    }

    public static void main(String[] args) {

        int totalMoney = 0;

        while ( true ) {
            printMenu(totalMoney);
            int choice = getChoice();
            int result = -1;
            switch (choice) {
                case 1:
                    result = calcMoney(totalMoney, COKE);
                    if (result < 0) {
                        printException();
                    } else {
                        totalMoney = result;
                        System.out.println("\n콜라가 나왔습니다.");
                    }
                    break;
                case 2:
                    result = calcMoney(totalMoney, CIDER);
                    if (result < 0) {
                        printException();
                    } else {
                        totalMoney = result;
                        System.out.println("\n사이다가 나왔습니다.");
                    }
                    break;
                case 3:
                    result = calcMoney(totalMoney, FANTA);
                    if (result < 0) {
                        printException();
                    } else {
                        totalMoney = result;
                        System.out.println("\n환타가 나왔습니다.");
                    }
                    break;
                case 4:
                    result = calcMoney(totalMoney, WATER);
                    if (result < 0) {
                        printException();
                    } else {
                        totalMoney = result;
                        System.out.println("\n물이 나왔습니다.");
                    }
                    break;
                case 5:
                    totalMoney += getMoney();
                    break;
                case 6:
                    System.out.println("\n잔돈 " + totalMoney + "원이 반환되었습니다.");
                    return;
            }

        }

    }
}
