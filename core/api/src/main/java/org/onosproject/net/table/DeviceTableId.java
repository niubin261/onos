package org.onosproject.net.table;

import org.onosproject.net.DeviceId;

import static java.util.Objects.hash;

/**
 * TableId of the given DeviceId
 */
public class DeviceTableId {
    private DeviceId deviceId;
    private int tableId;
    public DeviceTableId(DeviceId deviceId, int tableid) {
        this.deviceId  = deviceId;
        this.tableId = tableid;
    }
    public int getTableId() {
        return this.tableId;
    }
    public DeviceId getDeviceId() {
        return this.deviceId;
    }

    @Override
    public String toString() {
        return this.deviceId.toString() + Integer.valueOf(tableId).toString();
    }

    @Override
    public int hashCode() {
        return this.deviceId.hashCode()+hash(tableId);
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj){return true;}
        if(obj==null){return false;}
        if(this.getClass()!=obj.getClass()){return false;}
        DeviceTableId other = (DeviceTableId)obj;
        return this.deviceId.equals(other.deviceId)&&(this.tableId==other.tableId);
    }
}
