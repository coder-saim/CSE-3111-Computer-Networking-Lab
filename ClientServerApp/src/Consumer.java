import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    BlockingQueue<String> queue;
    String name;
    Thread t;

    Consumer(BlockingQueue<String> queue, String name){
        this.queue = queue;
        this.name = name;
        t = new Thread(this,name);
        t.start();
    }

    @Override
    public void run() {
        System.out.println(name + " Started...");
        while (true){
            try {

                if(queue.isEmpty()){
                    System.out.println("Queue is empty...");
                    Thread.sleep(2000);
                }

                System.out.println(name + " is eating cake " + queue.take());
                Thread.sleep(300);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }
}