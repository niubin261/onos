/*
 * Copyright 2015-present Open Networking Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onosproject.netconf;

import org.onlab.packet.IpAddress;
import org.onosproject.net.DeviceId;
import org.onosproject.netconf.config.NetconfDeviceConfig;
import org.onosproject.netconf.config.NetconfSshClientLib;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * Represents a Netconf device information.
 */
public class NetconfDeviceInfo {

    public static final Logger log = LoggerFactory
            .getLogger(NetconfDeviceInfo.class);

    private String name;
    private String password;
    private IpAddress ipAddress;
    private int port;
    private char[] key;
    //File keyFile @deprecated 1.9.0
    @Deprecated
    private File keyFile;
    private Optional<NetconfSshClientLib> sshClientLib;
    private OptionalInt connectTimeoutSec;
    private OptionalInt replyTimeoutSec;
    private OptionalInt idleTimeoutSec;
    private DeviceId deviceId;


    /**
     * Information for contacting the controller.
     *
     * @param name      the connection type
     * @param password  the password for the device
     * @param ipAddress the ip address
     * @param port      the tcp port
     */
    public NetconfDeviceInfo(String name, String password, IpAddress ipAddress,
                             int port) {
        checkArgument(!name.equals(""), "Empty device username");
        checkNotNull(port > 0, "Negative port");
        checkNotNull(ipAddress, "Null ip address");
        this.name = name;
        this.password = password;
        this.ipAddress = ipAddress;
        this.port = port;
        this.sshClientLib = Optional.empty();
        this.connectTimeoutSec = OptionalInt.empty();
        this.replyTimeoutSec = OptionalInt.empty();
        this.idleTimeoutSec = OptionalInt.empty();
    }

    /**
     * Information for contacting the controller.
     *
     * @param name      the connection type
     * @param password  the password for the device
     * @param ipAddress the ip address
     * @param port      the tcp port
     * @param keyString the string containing a DSA or RSA private key
     *                  of the user in OpenSSH key format
     *                  <br>
     *                  (Pre 1.9.0 behaviour: {@code keyString} can be file path
     *                  to a file containing DSA or RSA private key of the user
     *                  in OpenSSH key format)
     */
    public NetconfDeviceInfo(String name, String password, IpAddress ipAddress,
                             int port, String keyString) {
        checkArgument(!name.equals(""), "Empty device name");
        checkNotNull(port > 0, "Negative port");
        checkNotNull(ipAddress, "Null ip address");
        this.name = name;
        this.password = password;
        this.ipAddress = ipAddress;
        this.port = port;
        this.key = keyString.toCharArray();
        this.keyFile = new File(keyString);
        this.sshClientLib = Optional.empty();
        this.connectTimeoutSec = OptionalInt.empty();
        this.replyTimeoutSec = OptionalInt.empty();
        this.idleTimeoutSec = OptionalInt.empty();
    }

    /**
     * Convenieince constructor that converts all known fields from NetCfg data.
     * @param netconfConfig NetCf configuration
     */
    public NetconfDeviceInfo(NetconfDeviceConfig netconfConfig) {
        checkArgument(!netconfConfig.username().isEmpty(), "Empty device name");
        checkNotNull(netconfConfig.port() > 0, "Negative port");
        checkNotNull(netconfConfig.ip(), "Null ip address");

        this.name = netconfConfig.username();
        this.password = netconfConfig.password();
        this.ipAddress = netconfConfig.ip();
        this.port = netconfConfig.port();
        if (netconfConfig.sshKey() != null && !netconfConfig.sshKey().isEmpty()) {
            this.key = netconfConfig.sshKey().toCharArray();
        }
        this.keyFile = new File(netconfConfig.sshKey());
        if (netconfConfig.sshClient().isPresent()) {
            this.sshClientLib = Optional.of(NetconfSshClientLib.getEnum(netconfConfig.sshClient().get()));
        } else {
            this.sshClientLib = Optional.empty();
        }
        this.connectTimeoutSec = netconfConfig.connectTimeout();
        this.replyTimeoutSec = netconfConfig.replyTimeout();
        this.idleTimeoutSec = netconfConfig.idleTimeout();
    }

    /**
     * Allows the NETCONF SSH Client library to be set.
     *
     * @param sshClientLib An enumerated value
     */
    public void setSshClientLib(Optional<NetconfSshClientLib> sshClientLib) {
        this.sshClientLib = sshClientLib;
    }

    /**
     * Allows the NETCONF SSH session initial connect timeout to be set.
     *
     * @param connectTimeoutSec value in seconds
     */
    public void setConnectTimeoutSec(OptionalInt connectTimeoutSec) {
        this.connectTimeoutSec = connectTimeoutSec;
    }

    /**
     * Allows the NETCONF SSH session replies timeout to be set.
     *
     * @param replyTimeoutSec value in seconds
     */
    public void setReplyTimeoutSec(OptionalInt replyTimeoutSec) {
        this.replyTimeoutSec = replyTimeoutSec;
    }

    /**
     * Allows the NETCONF SSH session idle timeout to be set.
     *
     * @param idleTimeoutSec value in seconds
     */
    public void setIdleTimeoutSec(OptionalInt idleTimeoutSec) {
        this.idleTimeoutSec = idleTimeoutSec;
    }

    /**
     * Exposes the name of the controller.
     *
     * @return String name
     */
    public String name() {
        return name;
    }

    /**
     * Exposes the password of the controller.
     *
     * @return String password
     */
    public String password() {
        return password;
    }

    /**
     * Exposes the ip address of the controller.
     *
     * @return IpAddress ip address
     */
    public IpAddress ip() {
        return ipAddress;
    }

    /**
     * Exposes the port of the controller.
     *
     * @return port number
     */
    public int port() {
        return port;
    }

    /**
     * Exposes the key of the controller.
     *
     * @return {@code char[]} containing a DSA or RSA private key of the user
     *         in OpenSSH key format
     *         or null if device is not configured to use public key authentication
     */
    public char[] getKey() {
        return key;
    }

    /**
     * Exposes the keyFile of the controller.
     *
     * @return File object pointing to a file containing a DSA or RSA
     *         private key of the user in OpenSSH key format,
     *         or null if device is not configured to use public key authentication
     * @deprecated 1.9.0
     */
    @Deprecated
    public File getKeyFile() {
        return keyFile;
    }

    /**
     * Exposes the Client library implementation.
     *
     * @return Enumerated value
     */
    public Optional<NetconfSshClientLib> sshClientLib() {
        return sshClientLib;
    }

    /**
     * Exposes the device specific connect timeout.
     *
     * @return The timeout value in seconds
     */
    public OptionalInt getConnectTimeoutSec() {
        return connectTimeoutSec;
    }

    /**
     * Exposes the device specific reply timeout.
     *
     * @return The timeout value in seconds
     */
    public OptionalInt getReplyTimeoutSec() {
        return replyTimeoutSec;
    }

    /**
     * Exposes the device specific idle timeout.
     *
     * @return The timeout value in seconds
     */
    public OptionalInt getIdleTimeoutSec() {
        return idleTimeoutSec;
    }

    /**
     * Return the info about the device in a string.
     * String format: "netconf:name@ip:port"
     *
     * @return String device info
     */
    @Override
    public String toString() {
        return "netconf:" + name + "@" + ipAddress + ":" + port;
    }

    /**
     * Return the DeviceId about the device containing the URI.
     *
     * @return DeviceId
     */
    public DeviceId getDeviceId() {
        if (deviceId == null) {
            try {
                deviceId = DeviceId.deviceId(new URI("netconf", ipAddress.toString() + ":" + port, null));
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException("Unable to build deviceID for device " + toString(), e);
            }
        }
        return deviceId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ipAddress, port, name);
    }

    @Override
    public boolean equals(Object toBeCompared) {
        if (toBeCompared instanceof NetconfDeviceInfo) {
            NetconfDeviceInfo netconfDeviceInfo = (NetconfDeviceInfo) toBeCompared;
            if (netconfDeviceInfo.name().equals(name)
                    && netconfDeviceInfo.ip().equals(ipAddress)
                    && netconfDeviceInfo.port() == port
                    && netconfDeviceInfo.password().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
