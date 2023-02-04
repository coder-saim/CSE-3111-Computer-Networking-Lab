
class NewThread implements Runnable{

    Thread t;
    int x;

    NewThread(int x){
        t = new Thread(this,"Runnable Thread");
        this.x = x;
        t.start();
    }

    @Override
    public void run() {
        for(int i=0;i<5;i++){
            System.out.println(x + "-> Child Thread " + i);

            // ensuring context switch
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Thread Exiting....");
    }
}

public class RunnableThread {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main Thread Started...");
        NewThread newThread = new NewThread(1);
        NewThread newThreads = new NewThread(2);

        System.out.println("Thread 1 is alive " + newThread.t.isAlive());
        System.out.println("Thread 2 is alive " + newThreads.t.isAlive());

        try {
            newThread.t.join();
            newThreads.t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Main Thread Stopped...");
    }
}
