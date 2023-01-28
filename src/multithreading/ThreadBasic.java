public class ThreadBasic {

  public static void main(String[] args) {
    Thread lambdaThread = new Thread(() -> {
      Thread.currentThread().setName("Lambda-Thread");
      System.out.println("Printing from lambda thread ::" + Thread.currentThread().getName());
    });

    Thread runnableThread = new Thread(new RunnableThread());
    ClassThread classThread = new ClassThread();
    lambdaThread.start();
    runnableThread.start();
    classThread.start();
    System.out.println("Printing from main thread :: " + Thread.currentThread().getName());
  }
}

class RunnableThread implements Runnable {

  @Override
  public void run() {
    Thread.currentThread().setName("Runnable-Thread");
    System.out.println("Printing from runnable thread ::" + Thread.currentThread().getName());
  }
}

class ClassThread extends Thread {
  @Override
  public void run() {
    this.setName("Class-Thread");
    System.out.println("Printing from class thread ::" + this.getName());
  }
}