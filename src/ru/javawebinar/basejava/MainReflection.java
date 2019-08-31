package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException {
        Resume resume = new Resume("New Person");
        Field field = resume.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(resume));
        field.set(resume, "new_uuid");
        // TODO : invoke resume.toString via reflection
        System.out.println(resume);

        System.out.println(field.get(resume));

        try {
            Method toString = resume.getClass().getMethod("toString");

            try {
                Object invoke = toString.invoke(resume);
                System.out.println(invoke);

            } catch (IllegalArgumentException e) {  }
            catch (IllegalAccessException e) { }
            catch (InvocationTargetException e) { }

        } catch (SecurityException e) { }
        catch (NoSuchMethodException e) { }



    }
}