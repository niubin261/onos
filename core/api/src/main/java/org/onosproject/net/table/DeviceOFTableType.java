package org.onosproject.net.table;

import org.onosproject.floodlightpof.protocol.table.OFTableType;
import org.onosproject.net.DeviceId;

import java.util.Objects;

/**
 * OFTableType of the given deviceId.
 */
public class DeviceOFTableType {

    private DeviceId deviceId;
    private OFTableType ofTableType;
    public DeviceOFTableType(DeviceId deviceId, OFTableType ofTableType) {
        this.deviceId = deviceId;
        this.ofTableType = ofTableType;
    }
    public DeviceId getDeviceId() {
        return this.deviceId;
    }
    public OFTableType getOfTableType() {
        return this.ofTableType;
    }

    @Override
    public String toString() {

        return deviceId.toString() + "OfTableType:" + ofTableType;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) {
            return true;
        }
        if(obj==null){
            return false;
        }
        if(this.getClass()!=obj.getClass()){
            return false;
        }
        DeviceOFTableType other=(DeviceOFTableType)obj;
        return Objects.equals(this.deviceId,other.deviceId)&&Objects.equals(this.ofTableType,other.ofTableType);
    }

    @Override
    public int hashCode() {
        return deviceId.hashCode() + ofTableType.hashCode();
    }
}
