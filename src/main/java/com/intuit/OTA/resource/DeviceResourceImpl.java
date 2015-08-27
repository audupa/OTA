package com.intuit.OTA.resource;

import com.intuit.OTA.dao.DeviceDAO;
import com.intuit.OTA.dao.UserDAO;
import com.intuit.OTA.dto.DeviceDO;
import com.intuit.OTA.model.Device;
import com.intuit.OTA.model.User;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: audupa
 * Date: 8/27/15
 * Time: 2:29 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class DeviceResourceImpl implements DeviceResource {


    @Autowired
    private DeviceDAO deviceDAO;

    @Autowired
    private Mapper mapper;

    @Override
    public Device createDevice(Device device) throws Exception {
        DeviceDO deviceDO = mapper.map(device, DeviceDO.class);
        deviceDAO.create(deviceDO);
        device.setId(deviceDO.getId());
        return device;
    }

    @Override
    public List<Device> getAllDevices() throws Exception {
        List<DeviceDO> deviceDOList = deviceDAO.findManyBy(null);;

        List<Device> list = new ArrayList<Device>();
        if (deviceDOList != null) {
            for (DeviceDO n : deviceDOList) {
                Device m = mapper.map(n, Device.class);
                list.add(m);
            }
        }
        return list;
    }
}
