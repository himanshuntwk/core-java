public class ThreadComm {

  public static void main(String[] args) throws InterruptedException {

    ThreadCommSharedClass sharedClass = new ThreadCommSharedClass();

    Thread thread1 = new Thread(() -> {
      sharedClass.methodA(1);
    });

    Thread thread3 = new Thread(() -> {
      sharedClass.methodA(3);
    });

    Thread thread2 = new Thread(() -> {
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      sharedClass.methodB();
    });

    thread1.start();
    thread2.start();
    thread3.start();
    thread1.join();
    thread2.join();
    thread3.join();

    System.out.println("Finished.....");

  }
}

class ThreadCommSharedClass {

  public synchronized void methodA(int number) {
    try {
      System.out.println("Method A started " + number);
      wait();
      System.out.println("Notification received....");
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public synchronized void methodB() {
    System.out.println("Processing done....");
//    notify();
    notifyAll();
  }
}