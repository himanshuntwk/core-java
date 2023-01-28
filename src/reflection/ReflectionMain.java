import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectionMain {

  public static void main(String[] args) throws NoSuchMethodException,
      InvocationTargetException, InstantiationException, IllegalAccessException {

    Constructor<ReflectionDemo> reflectionDemoConstructor =
        ReflectionDemo.class.getDeclaredConstructor(int.class);
    // required for private constructors or constructors which are not accessible based on access modifiers,
    // helps in singleton approaches
    reflectionDemoConstructor.setAccessible(true);
    ReflectionDemo demo = reflectionDemoConstructor.newInstance(10);
    demo.testMethod();
  }
}
