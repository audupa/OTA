package com.intuit.OTA.dto;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: AUDUPA
 * Date: 4/21/15
 * Time: 2:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class BaseDO {

    protected Long id;
    protected Date createdTs;
    protected Date lastUpdatedTs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
