package com.niit.model;

import org.springframework.data.annotation.Transient;

public class KanbanDTO {

//    @Transient
//    public static final String SEQUENCE_NAME="user_sequence";


    private int kanbanId;
    private String kanbanTitle;
    private String email;

    public KanbanDTO() {
    }

    public KanbanDTO(int kanbanId, String kanbanTitle, String email) {
        this.kanbanId = kanbanId;
        this.kanbanTitle = kanbanTitle;
        this.email = email;
    }

    public int getKanbanId() {
        return kanbanId;
    }

    public void setKanbanId(int kanbanId) {
        this.kanbanId = kanbanId;
    }

    public String getKanbanTitle() {
        return kanbanTitle;
    }

    public void setKanbanTitle(String kanbanTitle) {
        this.kanbanTitle = kanbanTitle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
