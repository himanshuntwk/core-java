public class ThreadCoordination {
  public static void main(String[] args) throws InterruptedException {

    Thread customThread = new Thread(() -> {
      Thread.currentThread().setName("Custom-Thread");
      try {
        Thread.sleep(5000);
        System.out.println("Printing message from custom thread :: " + Thread.currentThread().getName());
      } catch (InterruptedException e) {
        System.out.println("Message printed if thread interrupted....");
      }
    });
//    customThread.setDaemon(true);
    customThread.start();
//    customThread.interrupt();
//    customThread.join();
    System.out.println("Printing from main thread...");
  }
}
