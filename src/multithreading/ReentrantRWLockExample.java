import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantRWLockExample {

  // 1 read lock can be acquired by multiple threads
  // 2 read lock cannot be acquired if write lock is already acquired
  // 3 write lock can be acquired if read lock is not acquired
  // 4 write lock can be acquire by only one thread.
  public static void main(String[] args) throws InterruptedException {

    SharedReentrantRWClass sharedObject = new SharedReentrantRWClass();

    Thread readThread1 = new Thread(() -> {
      while (true) {
        ReentrantReadWriteLock.ReadLock readLock = sharedObject.getRwLock().readLock();
        if (readLock.tryLock()) {
          System.out.println(Thread.currentThread().getName() + "::" + sharedObject.getData());
          readLock.unlock();
        } else {
          System.out.println("Did not get Lock since write in progress...");
        }
      }
    });

    Thread updateThread = new Thread(() -> {
      while (true) {
        try {
          if (sharedObject.getRwLock().writeLock().tryLock()) {
            Thread.sleep(100);
            sharedObject.setData(sharedObject.getData() + 1);
            sharedObject.getRwLock().writeLock().unlock();
          } else {
            System.out.println("Did not get Lock since read in progress...");
          }
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });

    readThread1.start();
    updateThread.start();
    readThread1.join();
    updateThread.join();;
    System.out.println("Finished....");
  }
}

class SharedReentrantRWClass {
  int data = 1;
  private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

  public ReentrantReadWriteLock getRwLock() {
    return rwLock;
  }

  public int getData() {
    return data;
  }

  public void setData(int data) {
    this.data = data;
  }
}