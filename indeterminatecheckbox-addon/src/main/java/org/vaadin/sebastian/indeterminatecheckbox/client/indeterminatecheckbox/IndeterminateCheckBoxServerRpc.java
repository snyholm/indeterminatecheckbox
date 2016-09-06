package org.vaadin.sebastian.indeterminatecheckbox.client.indeterminatecheckbox;

import com.vaadin.shared.communication.ServerRpc;

public interface IndeterminateCheckBoxServerRpc extends ServerRpc {

    public void setValue(Boolean value);

}
