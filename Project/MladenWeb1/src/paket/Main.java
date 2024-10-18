package paket;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static AtomicInteger markSum=new AtomicInteger(0);// suma ocena
    public static AtomicInteger doneStudents=new AtomicInteger(0);///studenti koji su zavrsili odbrane

    public static int defenseTimeMiliseconds=5000;
    public static Thread defenseTime=new Thread(new SleepThread(defenseTimeMiliseconds));

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        System.out.println("Unesite broj studenata");
        int numberOfStudents=sc.nextInt();

        Thread defenseThread = new Thread(new ProjectDefensesThread(numberOfStudents));

        System.out.println("Odbrana pocinje! (5 sekundi)");
        defenseTime.start();
        defenseThread.start();
        try {
            defenseTime.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Kraj odbrane!");
        System.out.println("Suma ocena svih studenata koji su zavrsili : " + markSum);
        System.out.println("Broj studenata koji su zavrsili odbranu : " + doneStudents);
        System.out.println("Prosek: " + markSum + " / "+doneStudents+" = " +(markSum.get()/(float)doneStudents.get()));

    }
}
