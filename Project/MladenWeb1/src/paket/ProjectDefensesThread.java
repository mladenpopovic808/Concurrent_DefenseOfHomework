package paket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ProjectDefensesThread implements Runnable {

    public volatile static Thread asistentThread;
    public volatile static Thread profesorThread1;
    public volatile static Thread profesorThread2;

    public static CyclicBarrier professorBarrier=new CyclicBarrier(2);
    public static volatile Semaphore professorSemaphore=new Semaphore(2,true);
    public static volatile Semaphore assistantSemaphore=new Semaphore(1,true);

    public static volatile List<Student> studentsOfProfessor;
    public static volatile List<Student> studentsOfAssistant;
    public static volatile ExecutorService threadPool;


    @Override
    public void run() {
        threadPool= Executors.newFixedThreadPool(3);
        threadPool.submit(asistentThread);
        threadPool.submit(profesorThread1);
        threadPool.submit(profesorThread2);
        threadPool.shutdown();
    }

    public ProjectDefensesThread(int numberOfStudents){
        Profesor p=new Profesor("Profesor 11");
        profesorThread1=new Thread(p);
        profesorThread2=new Thread(p);
        asistentThread=new Thread(new Asistent("Asistent 00"));

        ///Synchronized liste,da ne bih morao da pravim lockove
        studentsOfProfessor= Collections.synchronizedList(new ArrayList<>());
        studentsOfAssistant= Collections.synchronizedList(new ArrayList<>());

        for(int i=0;i<numberOfStudents;i++){
            ///u konstruktoru ce mu se dodeliti vreme dolaska i tempo odbrane
            Student s=new Student("Student "+i);
            Thread t=new Thread(s);
            t.setName(s.getName());

            ///dodeljivanje studenata profesoru ili asistentu
            if(Math.random()<0.5){
                studentsOfProfessor.add(s);
                s.setProfessorName(profesorThread1.getName());
                s.setBraniKod(Student.PROFFESOR);
            }
            else{
                studentsOfAssistant.add(s);
                s.setProfessorName(asistentThread.getName());
                s.setBraniKod(Student.ASISTENT);
            }
        }
        ///sortiramo po vremenu dolaska.
        studentsOfProfessor.sort((o1, o2) -> o1.getArrivalTime()-o2.getArrivalTime());
        studentsOfAssistant.sort((o1, o2) -> o1.getArrivalTime()-o2.getArrivalTime());
    }
}
