package com.vaadin.sebastian.indeterminatecheckbox.client.indeterminatecheckbox;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.vaadin.client.VTooltip;
import com.vaadin.client.ui.Icon;
import com.vaadin.client.ui.aria.AriaHelper;
import com.vaadin.client.ui.aria.HandlesAriaInvalid;
import com.vaadin.client.ui.aria.HandlesAriaRequired;

public class IndeterminateCheckBoxWidget extends CheckBox implements
        HandlesAriaInvalid, HandlesAriaRequired, NativePreviewHandler {

    /**
     * Style to make the indeterminate style work with Valo.
     */
    private static final String VALO_INDETERMINATE_CLASSNAME = "valo-indeterminate";

    private IndeterminateCheckBoxConnector connector;

    public Element errorIndicatorElement;

    public Icon icon;

    private InputElement input;

    public IndeterminateCheckBoxWidget() {
        sinkEvents(VTooltip.TOOLTIP_EVENTS | Event.MOUSEEVENTS);
        Event.addNativePreviewHandler(this);

        input = (InputElement) getElement().getFirstChildElement();
    }

    public void setConnector(IndeterminateCheckBoxConnector connector) {
        this.connector = connector;
    }

    private Element getCheckBoxElement() {
        return getElement().getFirstChildElement();
    }

    @Override
    public void setAriaRequired(boolean required) {
        AriaHelper.handleInputRequired(getCheckBoxElement(), required);
    }

    @Override
    public void setAriaInvalid(boolean invalid) {
        AriaHelper.handleInputInvalid(getCheckBoxElement(), invalid);
    }

    public Boolean handleToggle() {
        Boolean value = connector.getState().value;

        boolean isToggleTrue = value == null
                || (!connector.getState().isUserToggleable && !value);

        if (isToggleTrue) {
            input.setChecked(true);
            input.setPropertyBoolean("indeterminate", false);
            addOrRemoveValoStyleIfValo(false);
            return true;
        } else {
            boolean isToggleIndeterminate = !value
                    && connector.getState().isUserToggleable;

            if (isToggleIndeterminate) {
                input.setPropertyBoolean("indeterminate", true);
                input.setChecked(false);
                addOrRemoveValoStyleIfValo(true);
                return null;
            } else {
                input.setChecked(false);
                input.setPropertyBoolean("indeterminate", false);
                return false;
            }
        }

    }

    private native boolean isValoTheme(Element element)/*-{
                                                       var property = window.getComputedStyle(element, ':after').getPropertyValue('font-family');
                                                       return (property.indexOf("ThemeIcons") > -1) || (property.indexOf("FontAwesome") > -1);   
                                                       }-*/;

    public void addOrRemoveValoStyleIfValo(boolean isIndeterminate) {

        boolean isValoTheme = isValoTheme(input.getNextSiblingElement());

        if (isValoTheme) {
            String className = getElement().getClassName();
            if (isIndeterminate
                    && !className.contains(VALO_INDETERMINATE_CLASSNAME)) {
                getElement().addClassName(VALO_INDETERMINATE_CLASSNAME);
            } else if (!isIndeterminate) {
                getElement().removeClassName(VALO_INDETERMINATE_CLASSNAME);
            }
        }
    }

    private boolean ignoreEvent = false;

    @Override
    public void onPreviewNativeEvent(NativePreviewEvent event) {

        Element target = event.getNativeEvent().getEventTarget().cast();

        boolean isWidgetClicked = (event.getTypeInt() == Event.ONCLICK)
                && (target == getElement() || target == input
                        || target == input.getNextSibling());

        if (isWidgetClicked && !ignoreEvent) {

            ignoreEvent = true;
            event.cancel();

            // So we don't get multiple events from both input and label when
            // clicked once.
            Scheduler.get().scheduleDeferred(new ScheduledCommand() {

                @Override
                public void execute() {
                    ignoreEvent = false;
                }
            });

            Boolean newValue = handleToggle();
            connector.sendValue(newValue);
        }
    }

    @Override
    public void setValue(Boolean value, boolean fireEvents) {
        if (value == null) {
            input.setPropertyBoolean("indeterminate", true);
            input.setChecked(false);
            addOrRemoveValoStyleIfValo(true);
            if (fireEvents) {
                ValueChangeEvent.fire(this, value);
            }

        } else {
            input.setPropertyBoolean("indeterminate", false);
            super.setValue(value, fireEvents);
            addOrRemoveValoStyleIfValo(false);
        }

    }
}
