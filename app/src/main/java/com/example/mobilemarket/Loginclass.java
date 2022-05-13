package com.example.mobilemarket;

public class Loginclass {
    //Deals with everythinh that has to connect to the network
    String email;
    String password;

    public Loginclass(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //todo
    public static boolean userExists(){
        return false;
    }
    //todo
    public static boolean passwordmatch(){
        return false;
    }

}
