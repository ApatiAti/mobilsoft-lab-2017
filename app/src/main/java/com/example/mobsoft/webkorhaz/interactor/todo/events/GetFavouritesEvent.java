package com.example.mobsoft.webkorhaz.interactor.todo.events;

import com.example.mobsoft.webkorhaz.model.Todo;

import java.util.List;



public class GetFavouritesEvent {
	private int code;
	private List<Todo> todos;
	private Throwable throwable;

	//<editor-fold desc="Constructors|Getters|Setters">

	public GetFavouritesEvent() {
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public List<Todo> getTodos() {
		return todos;
	}

	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

//</editor-fold>
}
