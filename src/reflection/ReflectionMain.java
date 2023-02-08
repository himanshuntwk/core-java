import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

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

    // Getting fields information

    Field[] declaredFields = ReflectionDemo.class.getDeclaredFields();
    System.out.println("Declared fields ==> " + declaredFields.length + "::" + Arrays.toString(declaredFields));
    Field temp = declaredFields[0]; // Fetching the "data" field
    temp.setAccessible(true); // Since data field is private
    System.out.println("Value of " + temp.get(demo));
//    printFieldsInformation(ReflectionDemo.class);
//    printFieldsInformation(Color.class);


  }


  static <T> void printFieldsInformation(Class<T> clazz) {
    for(Field field : clazz.getDeclaredFields()) {
      System.out.println(field.getName() + " :: " + field.getType() + " :: " + field.isSynthetic());
    }
  }

}




enum Color {
  RED, BLUE, GREEN
}