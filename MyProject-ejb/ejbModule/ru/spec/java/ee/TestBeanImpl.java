package ru.spec.java.ee;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;

@Stateless(mappedName = "echo")
@LocalBean
public class TestBeanImpl implements TestBeanRemote {

	@Override
	public String echo(String msg) {
		System.out.println(msg);
		return "re:" + msg;
	}

	@Produces
	public long setRnd() {
		return 0;
	}
}
