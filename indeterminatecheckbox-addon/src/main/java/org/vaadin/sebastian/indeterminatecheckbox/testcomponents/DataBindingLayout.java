package org.vaadin.sebastian.indeterminatecheckbox.testcomponents;

import org.vaadin.sebastian.indeterminatecheckbox.IndeterminateCheckBox;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class DataBindingLayout extends VerticalLayout {

    public DataBindingLayout() {
        createComponents();
    }

    private void createComponents() {

        CssLayout wrapper = new CssLayout();
        wrapper.setWidth("100%");

        TestBean bean = new TestBean();
        BeanItem<TestBean> item = new BeanItem<TestBean>(bean);
        IndeterminateCheckBox box = new IndeterminateCheckBox("Bound box");
        box.setUserCanToggleIndeterminate(true);
        box.setRequired(true);
        box.setRequiredError("Is required");
        box.setPropertyDataSource(item.getItemProperty("value"));
        box.setValue(null);

        Label label = new Label();
        label.setPropertyDataSource(item.getItemProperty("value"));

        wrapper.addComponent(box);
        wrapper.addComponent(label);
        addComponent(wrapper);
    }
}
