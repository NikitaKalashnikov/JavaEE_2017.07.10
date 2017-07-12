package ru.spec.java.ee;

import javax.ejb.Remote;

@Remote
public interface TestBeanRemote {

	String echo(String msg);

}
