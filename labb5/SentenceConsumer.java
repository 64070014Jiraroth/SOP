package com.example.labb5;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class SentenceConsumer {
    private Sentence sentences;

    public SentenceConsumer() {
        this.sentences = new Sentence();
    }

    @RabbitListener(queues = "BadWordQueue")
    public void addBadSentence(String s){
//        sentences.badSentences.add(s);
        sentences.addBadSentences(s);
        System.out.println("In addBadSentence Method : "+ sentences.badSentences);
    }
    @RabbitListener(queues = "GoodWordQueue")
    public void addGoodSentence(String s){
//        sentences.goodSentences.add(s);
        sentences.addGoodSentences(s);
        System.out.println("In addGoodSentence Method : "+sentences.goodSentences);
    }

    @RabbitListener(queues = "GetQueue")
//    @SendTo("GetQueue")
    public Sentence getSentences() {
        System.out.println("@RabbitListener(queues = \"GetQueue\")");
        return this.sentences;
    }
}
