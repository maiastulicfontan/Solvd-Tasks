package com.solvd.solvdTasks.hospital.models.interfaces.functionalinterfaces;

@FunctionalInterface
public interface IModify<T,U> {
	void modify(T t, U u);
}
