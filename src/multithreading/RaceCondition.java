public class RaceCondition {
  public static void main(String[] args) throws InterruptedException {

    SharedClass sharedObject = new SharedClass();

    Thread addThread = new Thread(() -> {
      int i = 0;
      while (i < 10000) {
        sharedObject.addOne();
        i++;
      }
    });

    Thread minusThread = new Thread(() -> {
      int i = 0;
      while (i < 10000) {
        sharedObject.minusOne();
        i++;
      }
    });

    addThread.start();
    minusThread.start();
    addThread.join();
    minusThread.join();
    System.out.println("Final Result === " + sharedObject.getResult());
  }
}

class SharedClass {
  int x = 0;

  public void addOne() {
      x++;
  }

  public synchronized void minusOne() {
      x--;
  }

  public int getResult() {
    return x;
  }
}