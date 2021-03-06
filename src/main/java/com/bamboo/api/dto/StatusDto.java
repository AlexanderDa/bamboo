package com.bamboo.api.dto;

public class StatusDto {

    private int id;
    private String name;

    public StatusDto() {

    }

    public StatusDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

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
        return "Status{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
