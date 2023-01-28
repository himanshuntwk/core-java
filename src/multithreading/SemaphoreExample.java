import java.util.concurrent.Semaphore;

public class SemaphoreExample {

  public static void main(String[] args) throws InterruptedException {
    SemaphoreSharedClass sharedClass = new SemaphoreSharedClass();

    Thread producer = new Thread(() -> {
      try {
        while (true) {
          System.out.println("Available permits :: " + sharedClass.getSemaphore().availablePermits());
          sharedClass.getSemaphore().acquire();
          Thread.sleep(1000);
          sharedClass.producer();
          sharedClass.getSemaphore().release();
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    Thread consumer1 = new Thread(() -> {
      try {
        while (true) {
          sharedClass.getSemaphore().acquire();
          Thread.sleep(500);
          sharedClass.consumer(1);
          sharedClass.getSemaphore().release();
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    Thread consumer2 = new Thread(() -> {
      try {
        while (true) {
          sharedClass.getSemaphore().acquire();
          Thread.sleep(500);
          sharedClass.consumer(2);
          sharedClass.getSemaphore().release();
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });
    Thread consumer3 = new Thread(() -> {
      try {
        while (true) {
          sharedClass.getSemaphore().acquire();
          Thread.sleep(500);
          sharedClass.consumer(3);
          sharedClass.getSemaphore().release();
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    producer.start();
    consumer1.start();
    consumer2.start();
    consumer3.start();
    producer.join();
    consumer1.join();
    consumer2.join();
    consumer3.join();
    System.out.println("....");
  }
}

class SemaphoreSharedClass {

  Semaphore semaphore = new Semaphore(2);

  public Semaphore getSemaphore() {
    return semaphore;
  }

  public void producer() {
    System.out.println("Produced data");
  }

  public void consumer(int number) {
    System.out.println("Consumed data :: " + number);
  }
}