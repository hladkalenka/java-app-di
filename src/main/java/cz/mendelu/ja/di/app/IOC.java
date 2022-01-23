package cz.mendelu.ja.di.app;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class IOC {
    public static Set<Object> setInstaces = new HashSet<>();

    public static <A> A create(Class<A> appClass) {
        try {
            var instance = appClass.getDeclaredConstructor().newInstance();
            var fields = appClass.getDeclaredFields();

            createAppBeansRecursively(fields, instance);

            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void createAppBeansRecursively(Field[] fields, Object instance) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        for (var field : fields) {
            Object actualInst = null;
            if (field.getAnnotation(MyAtributeAnnotation.class) != null) {
                if (isBeanAlreadyCreated(field, instance)) {
                    field.setAccessible(true);
                    Object findInst = setInstaces.stream().
                            filter(inst -> inst.getClass().equals(field.getType())).findFirst().get();
                    field.set(instance, findInst);
                    actualInst = findInst;

                } else {
                    field.setAccessible(true);
                    Object newInst = field.getType().getDeclaredConstructor().newInstance();
                    setInstaces.add(newInst);
                    field.set(instance, newInst);
                    actualInst = newInst;
                }

                boolean containsAnotatedField = Arrays.stream(field.getType().getDeclaredFields())
                        .filter(field1 -> field1.getAnnotation(MyAtributeAnnotation.class) != null)
                        .findAny().isPresent();

                if (containsAnotatedField && !isBeanAlreadyCreated(field, actualInst)) {
                    createAppBeansRecursively(field.getType().getDeclaredFields(), actualInst);
                }
            }
        }
    }

    private static boolean isBeanAlreadyCreated(Field field, Object actualInst) {
        return setInstaces.stream()
                .filter(i -> i.getClass().equals(field.getType()) && !i.getClass().equals(actualInst.getClass()))
                .findAny()
                .isPresent();
    }

}
