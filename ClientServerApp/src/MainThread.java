public class MainThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = Thread.currentThread();

        System.out.println(thread);
        thread.setName("myThread");
        System.out.println(thread);

        for(int i=0;i<11;i++){
            System.out.println(i);
            Thread.sleep(1000);
        }
    }
}
