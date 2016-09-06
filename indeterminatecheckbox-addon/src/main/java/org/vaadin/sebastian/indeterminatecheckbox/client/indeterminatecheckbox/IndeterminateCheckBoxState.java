package org.vaadin.sebastian.indeterminatecheckbox.client.indeterminatecheckbox;

import com.vaadin.shared.ui.checkbox.CheckBoxState;

public class IndeterminateCheckBoxState extends CheckBoxState {

    private static final long serialVersionUID = 2096319488570974173L;

    {
        primaryStyleName = "v-indeterminate-checkbox";
    }

    public Boolean value = false;

    public boolean isUserToggleable = false;
}
