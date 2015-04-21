package com.intuit.traveldiary.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: AUDUPA
 * Date: 2/10/15
 * Time: 10:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;
    private Long profilePhotoId;
    private String birthday;
    private Date createdTs;
    private Date lastUpdatedTs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Long getProfilePhotoId() {
        return profilePhotoId;
    }

    public void setProfilePhotoId(Long profilePhotoId) {
        this.profilePhotoId = profilePhotoId;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public Date getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(Date createdTs) {
        this.createdTs = createdTs;
    }

    public Date getLastUpdatedTs() {
        return lastUpdatedTs;
    }

    public void setLastUpdatedTs(Date lastUpdatedTs) {
        this.lastUpdatedTs = lastUpdatedTs;
    }

}
