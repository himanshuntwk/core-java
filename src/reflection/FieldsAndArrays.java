import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class FieldsAndArrays {

  public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
    int[] inputArray = {10, 20, 30, 40};
    inspectArray(inputArray);
    updateArray(inputArray);
    updateArray(new String("Not an array object"));
    createArray();
    FieldDemo fieldDemo = new FieldDemo();
    setFieldValues(fieldDemo);
    System.out.println("Values added through reflection :: " + fieldDemo.data);
  }

  public static void inspectArray(Object inputObject) {
    Class<?> clazz = inputObject.getClass();
    System.out.println("is array :: " + clazz.isArray() + " of type :: " + clazz.getComponentType().getName());
    System.out.println("Length :: " + Array.getLength(inputObject));
    System.out.println("Element at index 2 :: " + Array.get(inputObject, 2));
  }

  public static void updateArray(Object inputObject) {
    Class<?> clazz = inputObject.getClass();
    if (clazz.isArray()) {
      Array.set(inputObject, 0, 100);
      Array.set(inputObject, 1, 200);
      Array.set(inputObject, 2, 300);
      Array.set(inputObject, 3, 400);
      printArray("Updated Array", inputObject, 4);
    } else {
      System.out.println("Input object is not an array");
    }
  }

  public static void createArray() {

    Object intArray = Array.newInstance(int.class, 3);
    printArray("Reflection created array", intArray, 3);
  }

  static <T> T setFieldValues(T instance) throws NoSuchFieldException, IllegalAccessException {
    Class<?> aClass = instance.getClass();
    Field dataField = aClass.getDeclaredField("data");
    dataField.setAccessible(true);
    dataField.set(instance, 200);
    return instance;
  }

  static void printArray(String refString, Object arrayObject, int arrayLength) {
    int i = 0;
    System.out.print(refString + " :: ");
    while (i < arrayLength) {
      System.out.print(Array.get(arrayObject, i) + " ");
      i++;
    }
    System.out.println();
  }

}


class FieldDemo {
  int data;
}