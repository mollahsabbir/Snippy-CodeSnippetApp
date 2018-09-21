package com.tritekcorp.user.snippy;

import java.io.Serializable;

enum Language
{
    PSEUDOCODE("pseudoCode",0), HTML("html",1), CSS("css",2), C("c",3), CPP("cpp",4), CSHARPD("cSharp",5),
    PYTHON("python",6), JAVA("java",7),
    JAVASCRIPT("javaScript",8), SQL("sql",9), PHP("php",10), SHELL("shell",11), QUOTE("quote",99) , OTHER("other",100);

    private String stringValue;
    private int intValue;
    private Language(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    public int getValue() {
        return intValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}

public class Snippet implements Serializable{
    public String title;
    public String body;
    public Language type;

    public Snippet(String title, String body, Language type) {
        this.title = title;
        this.body = body;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Language getType() {
        return type;
    }

    public void setType(Language type) {
        this.type = type;
    }

    @Override
    public String toString() {
//        return "Snippet{" +
//                "title='" + title + '\'' +
//                ", body='" + body + '\'' +
//                ", type=" + type +
//                '}';

        return "" + type +">>> "+title;

        //This function is used to display the title in the list view.
    }
}

