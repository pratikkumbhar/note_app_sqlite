package com.example.mynotesapp.Database;

public class Holder {
    private int id;
    private String title;
    private String note;

    public Holder(int id, String title, String note) {
        this.id = id;
        this.title = title;
        this.note = note;
    }

    public Holder(int id) {
        this.id = id;
    }
    public Holder() {

    }

    public Holder(String title, String note) {
        this.title = title;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return title + note;
    }
}
