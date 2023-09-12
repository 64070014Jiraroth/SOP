package com.example.labb5;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@Route(value = "index2")
public class MyView2 extends HorizontalLayout {

    public MyView2() {
        TextField addWord = new TextField();    addWord.setLabel("Add Word");
        Button addGoodWord = new Button("Add Good Word");
        Button addBadWord = new Button("Add Bad Word");

        ComboBox<String> goodWordBox = new ComboBox<>("Good Words");
        ComboBox<String> badWordBox = new ComboBox<>("Bad Words");

        VerticalLayout v1 = new VerticalLayout();
        addWord.setWidth("100%");   addGoodWord.setWidth("100%");   addBadWord.setWidth("100%"); goodWordBox.setWidth("100%");  badWordBox.setWidth("100%");

        v1.add(addWord, addGoodWord, addBadWord, goodWordBox, badWordBox);

        TextField addSen = new TextField();     addSen.setLabel("Add Sentence");
        Button addSenButton = new Button("Add Sentence");
        TextArea goodSen = new TextArea();      goodSen.setLabel("Good Sentence");
        TextArea badSen = new TextArea();       badSen.setLabel("Bad Sentence");

        Button showSen = new Button("Show Sentence");

        VerticalLayout v2 = new VerticalLayout();
        v2.add(addSen, addSenButton, goodSen, badSen, showSen);

        add(v1, v2);

        addGoodWord.addClickListener(event -> {

            ArrayList<String> out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/addGood/"+addWord.getValue())
                    .retrieve()
                    .bodyToMono(ArrayList.class)
                    .block();

            goodWordBox.setItems(out);
            goodWordBox.setValue(out.get(out.size() - 1));
            System.out.println("addGood : " + out);
            Notification notification = Notification.show("Insert "+ addWord.getValue() +" to Good word Lists Complete.");
            notification.setPosition(Notification.Position.BOTTOM_START);
            addWord.clear();


        });
        addBadWord.addClickListener(event -> {

            ArrayList<String> out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/addBad/"+addWord.getValue())
                    .retrieve()
                    .bodyToMono(ArrayList.class)
                    .block();


            badWordBox.setItems(out);
            badWordBox.setValue(out.get(out.size() - 1));
            System.out.println("addBad : " + out);
            Notification notification = Notification.show("Insert "+ addWord.getValue() +" to Bad word Lists Complete.");
            notification.setPosition(Notification.Position.BOTTOM_START);
            addWord.clear();


        });
        addSenButton.addClickListener(event-> {
            String out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/proof/"+addSen.getValue())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            System.out.println(out);
            Notification notification = Notification.show(out);
            notification.setPosition(Notification.Position.BOTTOM_START);


        });
        showSen.addClickListener(event-> {
            System.out.println("In 555");
            Sentence sentence = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/getSentence/")
                    .retrieve()
                    .bodyToMono(Sentence.class)
                    .block();

            System.out.println(sentence.goodSentences);

        });


    }







}
