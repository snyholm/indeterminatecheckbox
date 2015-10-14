package com.vaadin.sebastian.indeterminatecheckbox;

import com.vaadin.data.util.converter.Converter.ConversionException;
import com.vaadin.sebastian.indeterminatecheckbox.client.indeterminatecheckbox.IndeterminateCheckBoxServerRpc;
import com.vaadin.sebastian.indeterminatecheckbox.client.indeterminatecheckbox.IndeterminateCheckBoxState;
import com.vaadin.ui.AbstractField;

public class IndeterminateCheckBox extends AbstractField<Boolean> {

    private static final long serialVersionUID = -4235583287773626960L;
    private static final String STYLENAME = "v-indeterminate-checkbox";

    IndeterminateCheckBoxServerRpc rpc = new IndeterminateCheckBoxServerRpc() {

        private static final long serialVersionUID = 1L;

        @Override
        public void setValue(Boolean value) {
            IndeterminateCheckBox.this.setValue(value);
        }
    };

    public IndeterminateCheckBox() {
        setStyleName(STYLENAME);
        registerRpc(rpc);
    }

    public IndeterminateCheckBox(String caption) {
        this();
        setCaption(caption);
    }

    public IndeterminateCheckBox(String caption, Boolean initialState) {
        this();
        setCaption(caption);
        setValue(initialState);
    }

    @Override
    protected IndeterminateCheckBoxState getState() {
        return (IndeterminateCheckBoxState) super.getState();
    }

    /**
     * Sets the {@link Boolean} value for the field. Use <code>null</code> to
     * set it to indeterminate.
     */
    @Override
    public void setValue(Boolean value)
            throws com.vaadin.data.Property.ReadOnlyException,
            ConversionException {

        try {
            super.setValue(value);
            getState().value = value;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Returns the {@link Boolean} value for this field. Returns
     * <code>null</code> if the field is set to indeterminate.
     */
    @Override
    public Boolean getValue() {
        return getInternalValue();
    }

    @Override
    public Class<? extends Boolean> getType() {
        return Boolean.class;
    }
}
