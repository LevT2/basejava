package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException {
        Resume r = new Resume("New Person");
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        // TODO : invoke r.toString via reflection
        System.out.println(r);

        System.out.println(field.get(r));

        try {
            Method toString = r.getClass().getMethod("toString");

            try {
                Object invoke = toString.invoke(r);
                System.out.println(invoke);

            } catch (IllegalArgumentException e) {  }
            catch (IllegalAccessException e) { }
            catch (InvocationTargetException e) { }

        } catch (SecurityException e) { }
        catch (NoSuchMethodException e) { }



    }
}