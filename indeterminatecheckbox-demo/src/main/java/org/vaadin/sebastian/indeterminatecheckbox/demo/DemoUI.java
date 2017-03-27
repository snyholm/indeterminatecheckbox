package org.vaadin.sebastian.indeterminatecheckbox.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import javax.servlet.annotation.WebServlet;
import org.vaadin.sebastian.indeterminatecheckbox.testcomponents.BasicTestsLayout;
import org.vaadin.sebastian.indeterminatecheckbox.testcomponents.DataBindingLayout;
import org.vaadin.sebastian.indeterminatecheckbox.testcomponents.MenuBar;

@SuppressWarnings("serial")
@Theme("checkboxdemo")
public class DemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class,
            widgetset = "org.vaadin.sebastian.indeterminatecheckbox.IndeterminateCheckboxWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        layout.addComponent(new MenuBar(this));

        HorizontalLayout wrap = new HorizontalLayout();
        wrap.setWidth("100%");
        wrap.addComponent(new BasicTestsLayout());
        wrap.addComponent(new DataBindingLayout());
        layout.addComponent(wrap);
    }

}
