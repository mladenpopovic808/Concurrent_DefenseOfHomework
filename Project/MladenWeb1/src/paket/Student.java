package paket;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Student implements Runnable{

    public String name;
    public int arrivalTime;
    public int tempoOfDefense;
    public String professorName;
    public String braniKod; ///ne znam kako da nazovem :D,da li student brani kod profesora ili asistenta

    public static String PROFFESOR="PROFESSOR";
    public static String ASISTENT="ASISTENT";

    public Random rand=new Random();

    public Student(String name){
        this.name = name;
        arrivalTime=(int) (Math.random() * 1000);
        tempoOfDefense=(int) (Math.random() * 500) + 500;///milisekundi
    }

    @Override
    public void run() {

        try {
            Thread.sleep(tempoOfDefense); ///simulira vreme odbrane
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int mark = rand.nextInt(6)+5;

        try {
            if (braniKod.equals(PROFFESOR)) {

                ProjectDefensesThread.professorSemaphore.acquire();
                ProjectDefensesThread.professorBarrier.await(Main.defenseTimeMiliseconds-arrivalTime,TimeUnit.MILLISECONDS); ///ceka od momenta kad je dosao pa do kraja.

                if(Main.defenseTime.isAlive()){
                    printDefenseInfo(mark);
                    addMarks(mark);
                }else{
                    didntMakeOnTime();
                }
                ProjectDefensesThread.professorSemaphore.release();

            } else if (braniKod.equals(ASISTENT)) {

                ProjectDefensesThread.assistantSemaphore.acquire();

               if(Main.defenseTime.isAlive()){
                     printDefenseInfo(mark);
                     addMarks(mark);
               }else{
                     didntMakeOnTime();
               }
                ProjectDefensesThread.assistantSemaphore.release();
            }

        }catch (InterruptedException | BrokenBarrierException e){
            e.printStackTrace();

        }catch (TimeoutException x){
            System.out.println("Student Thread: " + name + " Dosao u : " + arrivalTime + " Pregledac " + braniKod +
                    " TTC: " + tempoOfDefense +"ms" + " Ocena: " + "OSTAO ZAKLJUCAN U PROFESORSKOJ BARIJERI!");
        }
    }

    public void didntMakeOnTime(){
        System.out.println("Student Thread: " + name + " Dosao u : " + arrivalTime + " Pregledac " + braniKod +
                " TTC: " + tempoOfDefense + "ms" + " Ocena: " + "NIJE STIGAO!");
    }

    public void addMarks(int mark){
        Main.markSum.addAndGet(mark); ///dodajemo na sumu svih ocena.
        Main.doneStudents.addAndGet(1); //dodajemo 1 studenta koji je zavrsio odbranu

    }

    public void printDefenseInfo(int mark){
        System.out.println("Student Thread: " + name + " Dosao u : " + arrivalTime + " Pregledac " + braniKod +
                " TTC: " + tempoOfDefense +"ms" + " Ocena: " + mark);
    }

    public void setBraniKod(String braniKod) {
        this.braniKod = braniKod;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public String getName() {
        return name;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

}

