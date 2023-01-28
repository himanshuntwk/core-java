public class ReflectionDemo {
  int data;

  private ReflectionDemo(int num) {
    this.data = num;
  }

  public void testMethod() {
    System.out.println("Current data value == " + this.data);
  }
}
