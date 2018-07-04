package org.onosproject.provider.pof.group.impl;

import org.onlab.packet.Ip4Address;
import org.onlab.packet.Ip6Address;
import org.onosproject.core.GroupId;
import org.onosproject.floodlightpof.protocol.OFBucket;
import org.onosproject.floodlightpof.protocol.OFGroupMod;
import org.onosproject.floodlightpof.protocol.factory.BasicFactory;
import org.onosproject.floodlightpof.protocol.factory.OFBucketFactory;
import org.onosproject.net.DeviceId;
import org.onosproject.net.PortNumber;
import org.onosproject.net.driver.DefaultDriverData;
import org.onosproject.net.driver.DefaultDriverHandler;
import org.onosproject.net.driver.Driver;
import org.onosproject.net.driver.DriverService;
import org.onosproject.net.flow.TrafficTreatment;
import org.onosproject.net.flow.instructions.ExtensionTreatment;
import org.onosproject.net.flow.instructions.Instruction;
import org.onosproject.net.flow.instructions.Instructions;
import org.onosproject.net.flow.instructions.L0ModificationInstruction;
import org.onosproject.net.flow.instructions.L2ModificationInstruction;
import org.onosproject.net.flow.instructions.L3ModificationInstruction;
import org.onosproject.net.group.GroupBucket;
import org.onosproject.net.group.GroupBuckets;
import org.onosproject.net.group.GroupDescription;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;



public class GroupModBuilder {

    private GroupBuckets buckets;
    private GroupId groupId;
    private GroupDescription.Type type;
    private OFBucketFactory factory;
    private Long xid;
    private Optional<DriverService> driverService;

    private final Logger log = getLogger(getClass());

    private static final int OFPCML_NO_BUFFER = 0xffff;

    private TrafficTreatment trafficTreatment = null;

    private List<TrafficTreatment> trafficTreatments = null;
    private GroupModBuilder(GroupBuckets buckets, GroupId groupId,
                            GroupDescription.Type type, OFBucketFactory factory,
                            Optional<Long> xid) {
        this.buckets = buckets;
        trafficTreatments = getTrafficTreatments();
        this.groupId = groupId;
        this.type = type;
        this.factory = factory;
        this.xid = xid.orElse((long) 0);
    }

    private List<TrafficTreatment> getTrafficTreatments() {

        return null;
    }

    private GroupModBuilder(GroupBuckets buckets, GroupId groupId,
                            GroupDescription.Type type, OFBucketFactory factory,
                            Optional<Long> xid, Optional<DriverService> driverService) {
        this.buckets = buckets;
        this.groupId = groupId;
        this.type = type;
        this.factory = factory;
        this.xid = xid.orElse((long) 0);
        this.driverService = driverService;
    }
    /**
     * Creates a builder for GroupMod.
     *
     * @param buckets GroupBuckets object
     * @param groupId Group Id to create
     * @param type Group type
     * @param factory OFFactory object
     * @param xid transaction ID
     * @return GroupModBuilder object
     */
    public static GroupModBuilder builder(GroupBuckets buckets, GroupId groupId,
                                          GroupDescription.Type type, OFBucketFactory factory,
                                          Optional<Long> xid) {

        return new GroupModBuilder(buckets, groupId, type, factory, xid);
    }

    /**
     * Creates a builder for GroupMod.
     *
     * @param buckets GroupBuckets object
     * @param groupId Group Id to create
     * @param type Group type
     * @param factory OFFactory object
     * @param xid transaction ID
     * @param driverService driver Service
     * @return GroupModBuilder object
     */
    public static GroupModBuilder builder(GroupBuckets buckets, GroupId groupId,
                                          GroupDescription.Type type, OFBucketFactory factory,
                                          Optional<Long> xid, Optional<DriverService> driverService) {

        return new GroupModBuilder(buckets, groupId, type, factory, xid, driverService);
    }
    public OFGroupMod buildGroupAdd() {

        buildOFBucket(trafficTreatment);

        return null;
    }
    public OFGroupMod buildGroupDel() {
        return null;
    }
    public OFGroupMod buildGroupMod() {
        return null;
    }
    private OFBucket buildOFBucket(TrafficTreatment trafficTreatment) {
        return null;
    }

}
