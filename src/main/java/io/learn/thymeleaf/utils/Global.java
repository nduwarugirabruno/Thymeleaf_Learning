package io.learn.thymeleaf.utils;

public class Global {

    public static boolean invalidCreatedStudent(String firstName, String lastName, String email) {
        if (firstName == null) return true;
        if (lastName == null) return true;
        return email == null;
    }

}
