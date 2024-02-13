package org.vaadin.example;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ElementConstants;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 */
@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        final Dialog dialog = new Dialog();
        dialog.add(new VerticalLayout(
          new Text("We want this dialog to stay open. But after you close the notification, it will disappear for some reason"),
          new Button("Press Me", e -> this.showNotification("Thanks. Now close this notification.."))));
        dialog.open();
    }

    private void showNotification(String message) {
        final Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        notification.setPosition(Notification.Position.MIDDLE);
        notification.setDuration(0);    // infinite

        final HorizontalLayout topRow = new HorizontalLayout();
        topRow.setAlignItems(FlexComponent.Alignment.CENTER);
        topRow.add(new Div(new Text(message)));
        final Button closeButton = new Button(new Icon("lumo", "cross"), e -> notification.close());
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        closeButton.getElement().setAttribute(ElementConstants.ARIA_LABEL_ATTRIBUTE_NAME, "Close");
        topRow.add(closeButton);
        notification.add(topRow);

        notification.open();
    }
}
