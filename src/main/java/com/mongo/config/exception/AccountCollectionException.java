package com.mongo.config.exception;

public class AccountCollectionException extends Exception{
    public AccountCollectionException(String message){
        super(message);
    }

    public static String AccountAlreadyExists(String username){
        return "account with username: " + username + " is already Exists";
    }
    public static String LoginFail(){
        return "username or password does not match";
    }

}
