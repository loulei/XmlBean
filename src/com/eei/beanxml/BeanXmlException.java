package com.eei.beanxml;

/**
 * Custom Exception
 */
public class BeanXmlException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3475465690527881091L;

	public BeanXmlException(String message) {
        super(message);
    }

    public BeanXmlException(Exception base) {
        super(base);
    }
}
