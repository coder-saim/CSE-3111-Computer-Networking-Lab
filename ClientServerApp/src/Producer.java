import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{

    BlockingQueue<String> queue;
    String name;
    Thread t;

    Producer(BlockingQueue<String> queue, String name){
        this.queue = queue;
        this.name = name;
        t = new Thread(this,name);
        t.start();
    }

    @Override
    public void run() {
        System.out.println(name + " Started...");
        int i = 1;
        while (true){
            try {

                if(queue.size()>=4){
                    System.out.println("Queue is fulll...");
                    Thread.sleep(2000);
                }

                queue.put(name + i);
                System.out.println(name + " is Creating cake " + i);
                Thread.sleep(300);
                i++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }
}