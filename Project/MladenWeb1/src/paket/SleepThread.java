package paket;


public class SleepThread implements Runnable{

    public int milis;

    @Override
    public void run() {
        try {
            Thread.sleep(milis); ///5 sekundi traje odbrana
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public SleepThread(int milis){
        this.milis = milis;
    }



}

