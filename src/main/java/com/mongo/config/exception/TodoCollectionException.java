package com.mongo.config.exception;

public class TodoCollectionException extends Exception{

    public TodoCollectionException(String message){
        super(message);
    }

    public static String notFoundException(String id){
        return "Todo with id: "+ id + "notfound";
    }

    public static String toDoAlreadyExists(){
        return "Todo with given name is already exists";
    }
}
