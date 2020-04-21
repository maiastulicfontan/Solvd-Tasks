package com.solvd.hospital.models.interfaces.functionalinterfaces;

@FunctionalInterface
public interface Calculator<T,N> {
	 N calculate(T type);
}
