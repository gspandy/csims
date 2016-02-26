package com.gtm.csims.business.managment.exception;
/**
 * 功能： 实现的是management 包下业务异常的处理
 * @author 杨毅
 * 时间： 2010-11-6
 */
public class ManagmentException extends Exception{
	
	/**
	 * 序列化类的实例Id
	 */
	private static final long serialVersionUID = 1L;
    
	ManagmentException(String message){
		super(message);
	}
	
	 public ManagmentException(){
	    	super();
	 }
	 
	 public ManagmentException(String message,Throwable cause){
	    	super(message,cause);
	 }
	 public ManagmentException(Throwable cause){
	    	super(cause);
	 }
}
