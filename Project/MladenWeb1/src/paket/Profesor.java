package paket;


public class Profesor implements Runnable{

    public String Name;

    @Override
    public void run() {

        while (!ProjectDefensesThread.studentsOfProfessor.isEmpty()) {

            try {
                Thread sThread = new Thread(ProjectDefensesThread.studentsOfProfessor.remove(0));
                sThread.start();
                sThread.join();///kada startuje student,tada zapravo krece odbrana,i cekamo da se ta odbrana zavrsi


               //zbog remove(0),prazna lista
            } catch (IndexOutOfBoundsException e) {
                Thread.currentThread().interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Profesor(String name){
        this.Name = name;
    }
    ///get
    public String getName() {
        return Name;
    }
}

