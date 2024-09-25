package com.example.application.views.helloworld;

import com.vaadin.componentfactory.timeline.Timeline;
import com.vaadin.componentfactory.timeline.model.Item;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoIcon;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@PageTitle("Hello World")
@Route(value = "")
@RouteAlias(value = "")
public class HelloWorldView extends HorizontalLayout {


    public HelloWorldView() {
        setSizeFull();
        VerticalLayout layout = new VerticalLayout();
        Grid<String> grid = new Grid<>();
        List<String> letters = new ArrayList<>();
        for (char c = 'a'; c < 'z'; c++) {
            letters.add(c + "");
        }
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setItems(letters);
        layout.add(comboBox);
        GridListDataView<String> stringGridListDataView = grid.setItems(letters);
        Button filterButton = new Button("Filter list");
        filterButton.addClickListener(e -> stringGridListDataView.addFilter(s -> s.equals("a")));
        layout.add(filterButton);
        grid.addColumn(s -> "Foo").setHeader("Id");
        grid.addColumn(s -> "Bar").setHeader("First name");
        grid.addColumn(s -> "Baz").setHeader("Last name");
        grid.addColumn(s -> "Bizzle").setHeader("Birth date");
        grid.addColumn(s -> "Quux").setHeader("Phone");
        grid.addColumn(s -> "Zyzzyx").setHeader("Address");
        grid.addColumn(p -> "Eius ducimus numquam provident est. Est dolorum odit soluta quia accusamus error fugiat. Alias beatae ut maiores eos.").setHeader("Lorem");
        grid.addComponentColumn(p -> {
            Button button = new Button(LumoIcon.PHONE.create());
            button.addThemeVariants(ButtonVariant.LUMO_ICON);
            return button;
        }).setHeader("Call").setFrozenToEnd(true);
        layout.add(grid);
        add(layout);
        // create items
        Item item1 = new Item(LocalDateTime.of(2021, 8, 11, 2, 30, 00),
                LocalDateTime.of(2021, 8, 11, 8, 00, 00), "Item 1");
        item1.setId("1");

        Item item2 = new Item(LocalDateTime.of(2021, 8, 11, 9, 00, 00),
                LocalDateTime.of(2021, 8, 11, 17, 00, 00), "Item 2");
        item2.setId("2");

        Item item3 = new Item(LocalDateTime.of(2021, 8, 12, 0, 30, 00),
                LocalDateTime.of(2021, 8, 12, 3, 00, 00), "Item 3");
        item3.setId("3");

        Item item4 = new Item(LocalDateTime.of(2021, 8, 12, 4, 30, 00),
                LocalDateTime.of(2021, 8, 12, 20, 00, 00), "Item 4");
        item4.setId("4");

        Item item5 = new Item(LocalDateTime.of(2021, 8, 12, 21, 30, 00),
                LocalDateTime.of(2021, 8, 13, 01, 15, 00), "Item 5");
        item5.setId("5");

        List<Item> items = Arrays.asList(item1, item2, item3, item4, item5);

        // make them editable
        items.forEach(i -> {
            i.setEditable(true);
            i.setUpdateTime(true);
        });

        // create timeline
        Timeline timeline = new Timeline(items);

        // set timeline range
        timeline.setTimelineRange(
                LocalDateTime.of(2021, 8, 10, 00, 00, 00), LocalDateTime.of(2021, 8, 15, 00, 00, 00));

        // set multiselection to timeline
        timeline.setMultiselect(true);

        add(timeline);
    }

}
