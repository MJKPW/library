package com.example.kodillalibrary.domain.copy;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "book_copy")
@AllArgsConstructor
public class BookCopy {

    private int id;
    private int titleId;
    private boolean status;

    public BookCopy() { this.status = true; }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "title_id")
    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    @NotNull
    @Column(name = "status")
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookCopy{" +
                "id=" + id +
                ", titleId=" + titleId +
                ", status=" + status +
                '}';
    }
}
