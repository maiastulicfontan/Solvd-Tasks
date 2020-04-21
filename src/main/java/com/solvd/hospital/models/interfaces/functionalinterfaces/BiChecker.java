package com.solvd.hospital.models.interfaces.functionalinterfaces;

@FunctionalInterface
public interface BiChecker<T,U> {
	boolean check(T t, U u);
}
