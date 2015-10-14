package com.vaadin.sebastian.indeterminatecheckbox.client.indeterminatecheckbox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.VCheckBox;
import com.vaadin.client.ui.checkbox.CheckBoxConnector;
import com.vaadin.sebastian.indeterminatecheckbox.IndeterminateCheckBox;
import com.vaadin.shared.ui.Connect;

@Connect(IndeterminateCheckBox.class)
public class IndeterminateCheckBoxConnector extends CheckBoxConnector
        implements ClickHandler {

    private static final long serialVersionUID = -2321682985906664914L;
    protected HandlerRegistration clickHandlerRegistration;

    @Override
    protected void init() {
        super.init();
        clickHandlerRegistration = getWidget().addClickHandler(this);
    }

    @Override
    protected Widget createWidget() {
        return GWT.create(VCheckBox.class);
    }

    @Override
    public VCheckBox getWidget() {
        return (VCheckBox) super.getWidget();
    }

    @Override
    public IndeterminateCheckBoxState getState() {
        return (IndeterminateCheckBoxState) super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);

        if (stateChangeEvent.hasPropertyChanged("value")) {
            Boolean value = getState().value;
            if (value != null) {
                getWidget().setValue(value);
                getWidget().removeStyleName("valo-indeterminate");
            } else {
                Element element = getWidget().getElement();

                if (element != null) {
                    setIndeterminate(element);
                }
            }
        }
    }

    /**
     * Valo uses the FontAwesome and the :after element to style the checkbox.
     * We need to figure out if FontAwesome is used, in which case we are most
     * likely using Valo and we need to add the style.
     * 
     * @param element
     */
    public native void setIndeterminate(Element element)/*-{
                                                        element.firstChild.indeterminate = true;
                                                        var label = element.childNodes[1];
                                                        var fontfamily = window.getComputedStyle(label, ':after').getPropertyValue('font-family');
                                                        if(fontfamily.indexOf("FontAwesome") > -1){
                                                           element.className = element.className + " valo-indeterminate"; 
                                                        }
                                                        }-*/;

    public native int getValue(Element element)/*-{
                                               if(element.firstChild.indeterminate == true){
                                               return -1;
                                               }else if(element.firstChild.checked == true){
                                               return 1;
                                               }else{
                                               return 0;
                                               }
                                               }-*/;

    @Override
    public void onClick(ClickEvent event) {
        if (!isEnabled()) {
            return;
        }

        int value = getValue(getWidget().getElement());
        Boolean bool = getBoolean(value);

        if (getState().value == null) {
            if (bool != null) {
                sendUpdate(bool);
            }
        } else {
            if (getState().value != bool) {
                sendUpdate(bool);
            }
        }
    }

    private Boolean getBoolean(int value) {
        if (value == -1) {
            return null;
        } else if (value == 0) {
            return false;
        } else {
            return true;
        }
    }

    private void sendUpdate(Boolean value) {
        getRpcProxy(IndeterminateCheckBoxServerRpc.class).setValue(value);
    }

}
