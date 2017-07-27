package com.activenet.rpn.exception;

/**
 * ExpressionException
 *
 * @author hexiaohu@bravowhale.com
 * @date 2017-07-26 17:38
 */
public class ExpressionException extends  RuntimeException{
    public ExpressionException(){
     super();
    }

    public ExpressionException(String messsage){
        super(messsage);
    }
}
