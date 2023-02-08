import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodReflection {

  public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
    Method[] methods = MethodDemo.class.getDeclaredMethods();

    for (Method method : methods) {
      System.out.println(method.getName() + "parameter count :: " + method.getParameterCount());
      if(method.getParameterCount() == 2)
        method.invoke(new MethodDemo(), 1,2);
    }

    Method privateMethod = MethodDemo.class.getDeclaredMethod("privateMethod");
    privateMethod.setAccessible(true);
    privateMethod.invoke(new MethodDemo());

  }
}

class MethodDemo {
  void oneParamMethod(int param1) {
    System.out.println("111 :: " + param1);
  }

  void twoParamMethod(int param1, int param2) {
    System.out.println("222 :: " + param1);
    System.out.println("222 :: " + param2);
  }

  void noParamMethod() {
    System.out.println("No Param");
  }
  private void privateMethod() {
    System.out.println("Private method invoked.");
  }
}
