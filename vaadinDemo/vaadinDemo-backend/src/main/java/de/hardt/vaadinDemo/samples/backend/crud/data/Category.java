package de.hardt.vaadinDemo.samples.backend.crud.data;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class Category implements Serializable {
	private static final long serialVersionUID = 6316052713027937971L;
	
	@NotNull
    private int id;
    @NotNull
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
