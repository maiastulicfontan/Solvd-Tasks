package com.solvd.hospital.models.interfaces;

@FunctionalInterface
public interface Calculator<T,N> {
	 N calculate(T type);
}
