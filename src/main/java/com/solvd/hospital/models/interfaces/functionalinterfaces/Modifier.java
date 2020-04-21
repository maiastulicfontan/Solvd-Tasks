package com.solvd.hospital.models.interfaces.functionalinterfaces;

@FunctionalInterface
public interface Modifier<T,U> {
	void modify(T t, U u);
}
