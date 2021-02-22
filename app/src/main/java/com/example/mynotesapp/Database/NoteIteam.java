package com.example.mynotesapp.Database;

public class NoteIteam {
    private String title;
    private String notee;

    public NoteIteam(String title, String notee) {
        this.title = title;
        this.notee = notee;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotee() {
        return notee;
    }

    public void setNotee(String notee) {
        this.notee = notee;
    }
}
