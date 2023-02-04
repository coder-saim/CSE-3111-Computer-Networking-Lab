import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerBlockingQ {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(4);
        Producer producer = new Producer(queue,"Producer");
        Consumer consumer = new Consumer(queue,"C1");
        Consumer consumer2 = new Consumer(queue,"C2");

    }
}
