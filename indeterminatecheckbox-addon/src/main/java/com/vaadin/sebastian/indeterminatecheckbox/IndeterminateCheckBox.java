package com.vaadin.sebastian.indeterminatecheckbox;

import com.vaadin.sebastian.indeterminatecheckbox.client.indeterminatecheckbox.IndeterminateCheckBoxServerRpc;
import com.vaadin.sebastian.indeterminatecheckbox.client.indeterminatecheckbox.IndeterminateCheckBoxState;
import com.vaadin.ui.AbstractField;

/**
 * A CheckBox with three states.
 *
 * @author Sebastian Nyholm @ Vaadin Ltd.
 * @since January 2016
 * @version 2.0
 *
 */
public class IndeterminateCheckBox extends AbstractField<Boolean> {

    private static final long serialVersionUID = -4235583287773626960L;

    IndeterminateCheckBoxServerRpc rpc = new IndeterminateCheckBoxServerRpc() {

        private static final long serialVersionUID = 1L;

        @Override
        public void setValue(Boolean value) {
            IndeterminateCheckBox.this.setValue(value, true);
        }
    };

    public IndeterminateCheckBox() {
        this("", false);
    }

    public IndeterminateCheckBox(String caption) {
        this(caption, false);
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public IndeterminateCheckBox(String caption, Boolean initialState) {
        setCaption(caption);
        setValue(initialState);
        registerRpc(rpc);

        getState().value = initialState;
    }

    @Override
    protected IndeterminateCheckBoxState getState() {
        return (IndeterminateCheckBoxState) super.getState();
    }

    @Override
    public Class<? extends Boolean> getType() {
        return Boolean.class;
    }

    @Override
    protected void setInternalValue(Boolean value) {
        super.setInternalValue(value);
        getState().value = value;
    }

    /**
     * Sets the option for the user to be able to toggle into the indeterminate
     * state. The order of the states are <code>false</code>,
     * <code>indeterminate</code>, <code>true</code>. The default value is
     * <code>false</code>; only the developer can set the indeterminate state.
     *
     * @param isUserToggleable Is the user allowed to toggle to the
     * indeterminate state.
     */
    public void setUserCanToggleIndeterminate(boolean isUserToggleable) {
        getState().isUserToggleable = isUserToggleable;
    }

    /**
     * Returns <code>true</code> if the user can toggle into the indeterminate
     * state. See {@link #setUserCanToggleIndeterminate(boolean)}
     *
     * @return Is the user allowed to toggle to the indeterminate state.
     */
    public boolean isUserCanToggleIndetermine() {
        return getState().isUserToggleable;
    }

    /**
     * The checkbox can never be empty.
     *
     * @return always false.
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Boolean getValue() {
        return getState().value;
    }

}
