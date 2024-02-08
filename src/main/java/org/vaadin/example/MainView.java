package org.vaadin.example;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 */
@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        Grid<String> grid = new Grid<>(String.class);
        grid.addColumn(String::toString)
          .setKey("value")
          .setWidth("200px")
          .setHeader("Value");
        ListDataProvider<String> dp = new ListDataProvider<>(new ArrayList<>());
        grid.setItems(dp);
        add(grid);
        VaadinSession session = VaadinSession.getCurrent();
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            session.access(() -> {
                dp.getItems().add("String #1");
                dp.getItems().add("String #2");
                dp.getItems().add("String #3");
                dp.refreshAll();
            });
        }).start();
    }
}
