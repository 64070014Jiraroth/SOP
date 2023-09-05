package com.example.lab6.view;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "mainPage.it")
public class MainWizardView extends VerticalLayout {

    public MainWizardView() {
        TextField name = new TextField();
        name.setPlaceholder("Fullname");

        Label gen = new Label("Gender :");
        RadioButtonGroup<String> gender = new RadioButtonGroup<>();
        gender.setItems("Male", "Female");
        HorizontalLayout h1 = new HorizontalLayout();
        h1.add();
        ComboBox position = new ComboBox<>();
        position.setItems("Student", "Teacher");
        position.setPlaceholder("Position");

        TextField dollars = new TextField("Dollars");
        dollars.setPrefixComponent(new Span("$"));
        ComboBox school = new ComboBox();
        school.setItems("Hogwarts", "Beauxbatons", "Durmstrang");
        school.setPlaceholder("School");
        ComboBox house = new ComboBox();
        house.setItems("Gryffindor", "Ravenclaw", "Hufflepuff", "Slyther");
        house.setPlaceholder("House");

        Button prev = new Button("<<");
        Button next = new Button(">>");
        Button create = new Button("Create");
        Button update = new Button("Update");
        Button delete = new Button("Delete");

        HorizontalLayout h2 = new HorizontalLayout();
        h2.add(prev, create, update, delete, next);

        add(name, gen, gender, position, dollars, school, house, h2);

        create.addClickListener(event -> {
           String getName = name.getValue();
        });

    }
}