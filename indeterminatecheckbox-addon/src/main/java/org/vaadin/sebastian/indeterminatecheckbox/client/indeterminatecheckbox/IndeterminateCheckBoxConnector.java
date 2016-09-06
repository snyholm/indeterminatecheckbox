package org.vaadin.sebastian.indeterminatecheckbox.client.indeterminatecheckbox;

import org.vaadin.sebastian.indeterminatecheckbox.IndeterminateCheckBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.VCaption;
import com.vaadin.client.VTooltip;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractFieldConnector;
import com.vaadin.client.ui.Icon;
import com.vaadin.shared.ui.Connect;

@Connect(IndeterminateCheckBox.class)
public class IndeterminateCheckBoxConnector extends AbstractFieldConnector {

    private static final long serialVersionUID = -2321682985906664914L;

    @Override
    protected void init() {
        getWidget().setConnector(this);
        getWidget().addStyleName("v-checkbox");
    }

    @Override
    protected Widget createWidget() {
        return GWT.create(IndeterminateCheckBoxWidget.class);
    }

    @Override
    public IndeterminateCheckBoxWidget getWidget() {
        return (IndeterminateCheckBoxWidget) super.getWidget();
    }

    @Override
    public IndeterminateCheckBoxState getState() {
        return (IndeterminateCheckBoxState) super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);

        if (stateChangeEvent.hasPropertyChanged("value")) {
            getWidget().setValue(getState().value, false);
        }

        if (null != getState().errorMessage) {
            getWidget().setAriaInvalid(true);

            if (getWidget().errorIndicatorElement == null) {
                getWidget().errorIndicatorElement = DOM.createSpan();
                getWidget().errorIndicatorElement.setInnerHTML("&nbsp;");
                DOM.setElementProperty(getWidget().errorIndicatorElement,
                        "className", "v-errorindicator");
                DOM.appendChild(getWidget().getElement(),
                        getWidget().errorIndicatorElement);
                DOM.sinkEvents(getWidget().errorIndicatorElement,
                        VTooltip.TOOLTIP_EVENTS | Event.ONCLICK);
            } else {
                getWidget().errorIndicatorElement.getStyle().clearDisplay();
            }
        } else if (getWidget().errorIndicatorElement != null) {
            getWidget().errorIndicatorElement.getStyle()
                    .setDisplay(Display.NONE);

            getWidget().setAriaInvalid(false);
        }

        getWidget().setAriaRequired(isRequired());
        if (isReadOnly()) {
            getWidget().setEnabled(false);
        }

        if (getWidget().icon != null) {
            getWidget().getElement().removeChild(getWidget().icon.getElement());
            getWidget().icon = null;
        }
        Icon icon = getIcon();
        if (icon != null) {
            getWidget().icon = icon;
            DOM.insertChild(getWidget().getElement(), icon.getElement(), 1);
            icon.sinkEvents(VTooltip.TOOLTIP_EVENTS);
            icon.sinkEvents(Event.ONCLICK);
        }

        VCaption.setCaptionText(getWidget(), getState());

    }

    public void sendValue(Boolean value) {
        getRpcProxy(IndeterminateCheckBoxServerRpc.class).setValue(value);
    }

    @Override
    public boolean delegateCaptionHandling() {
        return false;
    }

}
