package com.vaadin.sebastian.indeterminatecheckbox;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("checkboxdemo")
public class CheckboxdemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = CheckboxdemoUI.class, widgetset = "com.vaadin.sebastian.indeterminatecheckbox.IndeterminateCheckboxWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        IndeterminateCheckBox box = new IndeterminateCheckBox(
                "Indeterminate Box set to Indeterminate", null);
        layout.addComponent(box);
        box = new IndeterminateCheckBox("Indeterminate Box set to True", true);
        layout.addComponent(box);
        box = new IndeterminateCheckBox("Indeterminate Box set to False",
                false);
        layout.addComponent(box);

    }

}