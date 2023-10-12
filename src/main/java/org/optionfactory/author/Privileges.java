package org.optionfactory.author;

public class Privileges {
    private Privileges(){
    }

    public static String admin() {
        return "admin";
    }

    public static String user() {
        return "user";
    }

    public static String mod() {
        return "role";
    }
}
