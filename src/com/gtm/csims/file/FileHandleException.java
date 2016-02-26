package com.gtm.csims.file;

/**
 * 文件处理异常类
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
@SuppressWarnings("serial")
public class FileHandleException extends RuntimeException {

	public FileHandleException() {
		super();
	}

	public FileHandleException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public FileHandleException(String arg0) {
		super(arg0);
	}

	public FileHandleException(Throwable arg0) {
		super(arg0);
	}
}
