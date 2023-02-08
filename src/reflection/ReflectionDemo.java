public class ReflectionDemo {
  private int data;
  private Temp tempx;
  private ReflectionDemo(int num) {
    this.data = num;
  }

  public void testMethod() {
    System.out.println("Current data value == " + this.data);
  }
}


class Temp {
  int datax;
}