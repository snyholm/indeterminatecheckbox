package org.vaadin.sebastian.indeterminatecheckbox.testcomponents;

import org.vaadin.sebastian.indeterminatecheckbox.IndeterminateCheckBox;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class BasicTestsLayout extends VerticalLayout {

    private static final String ICON_URL = "https://dev.vaadin.com/svn/integration/eclipse/plugins/com.vaadin.integration.eclipse/icons/vaadin-icon-16.png";

    public BasicTestsLayout() {
        createComponents();
    }

    private void createComponents() {
        CheckBox basicBox = new CheckBox("Normal checkbox");
        basicBox.setIcon(new ExternalResource(ICON_URL));
        addComponent(basicBox);

        IndeterminateCheckBox box = new IndeterminateCheckBox(
                "Disabled Indeterminate Box set to True", true);
        box.setEnabled(false);
        addComponent(box);

        box = new IndeterminateCheckBox("Indeterminate Box set to False",
                false);
        addComponent(box);

        final IndeterminateCheckBox indeterminatBox = new IndeterminateCheckBox(
                "Indeterminate Box set to Indeterminate", null);
        addComponent(indeterminatBox);

        final IndeterminateCheckBox nextStateBox = new IndeterminateCheckBox(
                "Toggle Next State Box", null);
        addComponent(nextStateBox);

        final Button setNextValue = new Button();
        setNextValue.setCaption("Set next state");
        setNextValue.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                Boolean value = false;
                if (nextStateBox.getValue() == null) {
                    value = true;
                } else if (!nextStateBox.getValue()) {
                    value = null;
                }

                nextStateBox.setValue(value);
            }
        });
        addComponent(setNextValue);

        box = new IndeterminateCheckBox("Icon box");
        box.setUserCanToggleIndeterminate(true);
        box.setIcon(new ExternalResource(ICON_URL));
        addComponent(box);

        box = new IndeterminateCheckBox("Error box");
        box.setUserCanToggleIndeterminate(true);
        addComponent(box);

        final IndeterminateCheckBox togglebox = new IndeterminateCheckBox(
                "Toggle box");
        togglebox.setUserCanToggleIndeterminate(true);
        togglebox.addValueChangeListener(new ValueChangeListener() {

            @Override
            public void valueChange(ValueChangeEvent event) {
                Notification.show("Value of box : " + togglebox.getValue());
            }
        });
        addComponent(togglebox);

        final Button toggle = new Button();
        toggle.setCaption(
                "User toggle : " + togglebox.isUserCanToggleIndetermine());
        toggle.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                togglebox.setUserCanToggleIndeterminate(
                        !togglebox.isUserCanToggleIndetermine());
                toggle.setCaption("User toggle : "
                        + togglebox.isUserCanToggleIndetermine());
            }
        });
        addComponent(toggle);

    }

}
