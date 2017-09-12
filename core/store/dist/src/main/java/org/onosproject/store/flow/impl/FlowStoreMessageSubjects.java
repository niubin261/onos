package org.onosproject.store.flow.impl;

import org.onosproject.store.cluster.messaging.MessageSubject;

public class FlowStoreMessageSubjects {
    private FlowStoreMessageSubjects() {}

    public static final MessageSubject APPLY_BATCH_FLOWS
            = new MessageSubject("peer-forward-apply-batch");

    public static final MessageSubject GET_FLOW_ENTRY
            = new MessageSubject("peer-forward-get-flow-entry");

    public static final MessageSubject GET_DEVICE_FLOW_ENTRIES
            = new MessageSubject("peer-forward-get-device-flow-entries");

    public static final MessageSubject REMOVE_FLOW_ENTRY
            = new MessageSubject("peer-forward-remove-flow-entry");

    public static final MessageSubject REMOTE_APPLY_COMPLETED
            = new MessageSubject("peer-apply-completed");

    public static final MessageSubject FLOW_TABLE_BACKUP
            = new MessageSubject("peer-flow-table-backup");
}
