package com.example.managerproducts.model;

/**
 * Created by usuario on 14/11/16.
 */

public class Error {
    public static final int OK = 0;
    public static final int PASS_DIGIT = 10;
    public static final int PASS_CASE = 11;
    public static final int PASS_LENGTH = 12;
    public static final int DATA_EMPTY = 13;
    public static final int INVALID_MAIL = 14;
    public static int errCode;
    public static String errMessage;

    static {
        //Aqui va a venir un constructor
    }
}
