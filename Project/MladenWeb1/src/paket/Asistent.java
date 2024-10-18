package paket;

public class Asistent implements Runnable{

    public String name;

    @Override
    public void run() {

        while (!ProjectDefensesThread.studentsOfAssistant.isEmpty()) {

            try {
                Thread sThread = new Thread(ProjectDefensesThread.studentsOfAssistant.remove(0));
                sThread.start();
                sThread.join();///kada startuje student,tada zapravo krece odbrana,i cekamo da se ta odbrana zavrsi

                ///zbog remove(0)
            } catch (IndexOutOfBoundsException e) {
                Thread.currentThread().interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public Asistent(String name){
        this.name = name;
    }
}
