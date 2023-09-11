package com.example.lab06.view;


import com.example.lab06.pojo.Wizard;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@Route(value = "mainPage.it")
public class MainWizardView extends VerticalLayout {
    WebClient webClient = WebClient.create();
    TextField name = new TextField();
    RadioButtonGroup<String> gender = new RadioButtonGroup<>("Gender :");
    HorizontalLayout h1 = new HorizontalLayout();
    ComboBox<String> position = new ComboBox<>();
    NumberField dollars = new NumberField("Dollars");
    ComboBox<String> school = new ComboBox<>();
    ComboBox<String> house = new ComboBox<>();
    ArrayList<Wizard> wizards;
    int current = 0;
    public MainWizardView() {
        name.setPlaceholder("Fullname");
        gender.setItems("Male", "Female");
        position.setItems("Student", "Teacher");
        position.setPlaceholder("Position");


        dollars.setPrefixComponent(new Span("$"));
        school.setItems("Hogwarts", "Beauxbatons", "Durmstrang");
        school.setPlaceholder("School");
        house.setItems("Gryffindor", "Ravenclaw", "Hufflepuff", "Slytherin");
        house.setPlaceholder("House");

        Button prev = new Button("<<");
        Button next = new Button(">>");
        Button create = new Button("Create");
        Button update = new Button("Update");
        Button delete = new Button("Delete");
        h1.add(prev, create, update, delete, next);

        add(name, gender, position, dollars, school, house, h1);

        wizards = this.getWizards();

        this.setTextField(wizards.get(current));
        prev.addClickListener(buttonClickEvent -> {this.backward();});
        next.addClickListener(buttonClickEvent -> {this.forward();});

//        create.addClickListener(event -> {
//           Wizard wizard = new Wizard(String.valueOf(gender.getValue()), name.getValue(), String.valueOf(school.getValue()), String.valueOf(position.getValue()), String.valueOf(dollars.getValue()), String.valueOf(house.getValue()));
//            String output = String.valueOf(webClient.post().uri("http://localhost:8080/addWizard").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(wizard)).retrieve().bodyToMono(String.class).block());;
//            wizards = this.getWizards();
//            current = getWizards().size()-1;
//            System.out.println(output);
//
//        });
//        delete.addClickListener(buttonClickEvent -> {
//            String output = String.valueOf(webClient.post().uri("http://localhost:8080/deleteWizard/"+wizards.get(current).get_id()).retrieve().bodyToMono(String.class).block());
//            wizards = this.getWizards();
//            this.backward();
//            System.out.println(output);
//        });

    }

    private void forward() {
        current++;
        if (current >= wizards.size()) { current = wizards.size()-1; }
        this.setTextField(wizards.get(current));
    }
    private void backward() {
        current--;
        if (current < 0) {current = 0;}
        this.setTextField(wizards.get(current));
    }

    public void setTextField(Wizard wizard) {
        name.setValue(wizard.getName());
        String gender;
        if (wizard.getSex().equals("m")) {gender = "Male";}
        else {gender = "Female";}
        this.gender.setValue(gender);
        String position = wizard.getPosition();
        this.position.setValue(position.substring(0,1).toUpperCase()+position.substring(1));
        dollars.setValue(wizard.getMoney());
        school.setValue(wizard.getSchool());
        house.setValue(wizard.getHouse());

    }

    public ArrayList<Wizard> getWizards() {
        ArrayList<Wizard> response = webClient.get().uri("http://localhost:8080/wizards").retrieve().bodyToMono(ArrayList.class).block();
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Wizard> wizards = mapper.convertValue(response, new TypeReference<ArrayList<Wizard>>() {});
        return wizards;
    }
}