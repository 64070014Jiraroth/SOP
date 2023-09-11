package com.example.lab05_2;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

//@Service
@RestController
public class WordPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    protected Word words = new Word();

    @RequestMapping(value = "/addBad/{s}", method = RequestMethod.POST)
    public ArrayList<String> addBadWord(@PathVariable("s") String s) {
        words.badWords.add(s);
        return words.badWords;
    }
    @RequestMapping(value = "/delBad/{s}", method = RequestMethod.GET)
    public ArrayList<String> deleteBadWord(@PathVariable("s") String s) {
        words.badWords.remove(s);
        return words.badWords;
    }
    @RequestMapping(value = "addGood/{s}", method = RequestMethod.POST)
    public ArrayList<String> addGoodWord(@PathVariable("s") String s) {
        words.goodWords.add(s);
        return words.goodWords;
    }
    @RequestMapping(value = "delGood/{s}", method = RequestMethod.GET)
    public ArrayList<String> deleteGoodWord(@PathVariable("s") String s) {
        words.goodWords.remove(s);
        return words.goodWords;
    }
    @RequestMapping(value = "proof/{s}", method = RequestMethod.POST)
    public String proofSentence(@PathVariable("s") String s) {
        int good = 0;
        int bad = 0;
        for (String goodWord : words.goodWords) {
            if(s.contains(goodWord)) {
                good += 1;
            }
        }
        for (String badWord : words.badWords) {
            if(s.contains(badWord)) {
                bad += 1;
            }
        }
        if(bad == 0 && good != 0) {
            rabbitTemplate.convertAndSend("Direct", "good", s);
            return "Found Good Word";
        }
        else if (good == 0 && bad != 0) {
            rabbitTemplate.convertAndSend("Direct", "bad", s);
            return "Found Bad Word";
        }
        else {
            rabbitTemplate.convertAndSend("Fanout", "", s);
            return "Found Bad and Good Word";
        }
    }

//    , produces = MediaType.APPLICATION_JSON_VALUE
    @RequestMapping(value = "/getSentence", method = RequestMethod.GET)
    public Sentence getSentence() {
        System.out.println("RequestMapping getSentence");
        Object out = rabbitTemplate.convertSendAndReceive("Direct", "queue", "");
        return ((Sentence) out);

    }

}
