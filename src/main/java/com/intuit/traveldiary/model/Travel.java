package com.intuit.traveldiary.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: AUDUPA
 * Date: 4/21/15
 * Time: 2:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Travel {

    private Long id;
    private Long userId;
    private String location;
    private Long rating;
    private String review;
    private Date dateOfTravel;
    private Date createdTs;
    private Date lastUpdatedTs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Date getDateOfTravel() {
        return dateOfTravel;
    }

    public void setDateOfTravel(Date dateOfTravel) {
        this.dateOfTravel = dateOfTravel;
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
