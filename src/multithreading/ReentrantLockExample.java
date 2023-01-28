import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

  public static void main(String[] args) throws InterruptedException {

    SharedReentrantClass sharedObject = new SharedReentrantClass();

    Thread readThread = new Thread(() -> {
      while (true) {
        try {
          Thread.sleep(50);
          sharedObject.getLockObj().lock();
          System.out.println(System.currentTimeMillis() + "::" + sharedObject.getData() + "::");
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        } finally {
          sharedObject.getLockObj().unlock();
        }
      }
    });

    Thread updateThread = new Thread(() -> {
      while (true) {
        try {
          Thread.sleep(100);
          if (sharedObject.getLockObj().tryLock()) {
            sharedObject.setData(sharedObject.getData() + 1);
            sharedObject.getLockObj().unlock();
          } else {
            System.out.println("Lock not available...");
          }
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });

    readThread.start();
    updateThread.start();
    readThread.join();
    updateThread.join();
    System.out.println("Finished....");
  }
}

class SharedReentrantClass {
  int data = 1;
  private Lock lockObj = new ReentrantLock();

  public int getData() {
    return data;
  }

  public void setData(int data) {
    this.data = data;
  }

  public Lock getLockObj() {
    return lockObj;
  }
}