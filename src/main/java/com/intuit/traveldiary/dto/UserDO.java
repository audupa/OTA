package com.intuit.traveldiary.dto;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: AUDUPA
 * Date: 4/21/15
 * Time: 3:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserDO extends BaseDO {

    private String firstName;
    private String lastName;
    private String emailId;
    private Long profilePhotoId;
    private String birthday;

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
}
