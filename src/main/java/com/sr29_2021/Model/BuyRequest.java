package com.sr29_2021.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BuyRequest {
    private Integer id;
    private Integer amount;
    private String reason;
    private LocalDateTime date;
    private Status status;
    private String denialComment;
    private Integer staffId;
    private User user;
    private Integer vaxId;
    private Vax vax;

    public BuyRequest(Integer id,
                      Integer amount,
                      String reason,
                      LocalDateTime date,
                      Status status,
                      String denialComment,
                      User user,
                      Vax vax) {
        this.id = id;
        this.amount = amount;
        this.reason = reason;
        this.date = date;
        this.status = status;
        this.denialComment = denialComment;
        this.user = user;
        this.vax = vax;
        this.staffId = user.getId();
        this.vaxId = vax.getId();
    }

    public BuyRequest() {
        this.date = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDenialComment() {
        return denialComment;
    }

    public void setDenialComment(String denialComment) {
        this.denialComment = denialComment;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.staffId = user.getId();
    }

    public Integer getVaxId() {
        return vaxId;
    }

    public void setVaxId(Integer vaxId) {
        this.vaxId = vaxId;
    }

    public Vax getVax() {
        return vax;
    }

    public void setVax(Vax vax) {
        this.vax = vax;
        this.vaxId = vax.getId();
    }
}
