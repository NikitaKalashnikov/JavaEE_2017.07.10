package ru.spec.java.ee;

import java.util.concurrent.Future;

import javax.ejb.Remote;

@Remote
public interface MySingletonRemote {

	Future<Long> increment();

}
