public class Deadlock {
  public static void main(String[] args) throws InterruptedException {

    SharedDeadlockClass sharedClass = new SharedDeadlockClass();
    Thread thread1 = new Thread(() -> {
      while(true) {
        sharedClass.methodAtoB();
      }
    });

    Thread thread2 = new Thread(() -> {
      while(true) {
        sharedClass.methodBtoA();
      }
    });

    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();

  }

}

class SharedDeadlockClass {
  Object objectA = new Object();
  Object objectB = new Object();

  public void methodAtoB() {
    synchronized (objectA) {
      System.out.println("Having lock on objectA. Going to acquire lock on objectB.");
      synchronized (objectB) {
        System.out.println("Having lock on objectB.");
      }
    }
  }

  public void methodBtoA() {
    synchronized (objectA) {
      System.out.println("Having lock on objectB. Going to acquire lock on objectA.");
      synchronized (objectB) {
        System.out.println("Having lock on objectA.");
      }
    }
  }
}