import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ModifiersReflection {

  public static void main(String[] args) {

    System.out.println(Modifier.isPublic(ModifierDemo.class.getModifiers()));
    for(Field field : ModifierDemo.class.getDeclaredFields()) {
      if(Modifier.isPrivate(field.getModifiers()))
        System.out.println("Private :: " + field.getName());
    }

    for(Method method : ModifierDemo.class.getDeclaredMethods()) {
      if(Modifier.isSynchronized(method.getModifiers()))
        System.out.println("Synchronized :: " + method.getName());
      if(Modifier.isPrivate(method.getModifiers()))
        System.out.println("Private :: " + method.getName());
      if(Modifier.isStatic(method.getModifiers()))
        System.out.println("Static :: " + method.getName());
    }
  }
}

class ModifierDemo {
  private int field1;
  int field2;

  synchronized private void method1() {
    System.out.println("test method");
  }

  public static void method2() {
    System.out.println("Method 2");
  }

}