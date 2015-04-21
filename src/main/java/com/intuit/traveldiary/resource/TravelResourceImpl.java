package com.intuit.traveldiary.resource;


import com.intuit.traveldiary.dao.TravelDAO;
import com.intuit.traveldiary.dto.TravelDO;
import com.intuit.traveldiary.model.Travel;
import com.intuit.traveldiary.resource.TravelResource;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: AUDUPA
 * Date: 2/10/15
 * Time: 10:44 PM
 */
@Component
public class TravelResourceImpl implements TravelResource {

    @Autowired
    private TravelDAO travelDAO;

    @Autowired
    private Mapper mapper;

    @Override
    public Travel postTravelDetails(Travel travel) throws Exception {
        TravelDO travelDO = mapper.map(travel, TravelDO.class);
        travelDAO.create(travelDO);
        travel.setId(travelDO.getId());
        return travel;
    }

    @Override
    public Travel getTravelDetails(Long id) throws Exception {
        TravelDO travelDO = travelDAO.findById(id);
        Travel t = null;
        if (travelDO != null) {
            t = mapper.map(travelDO, Travel.class);
        }
        return t;
    }
}

