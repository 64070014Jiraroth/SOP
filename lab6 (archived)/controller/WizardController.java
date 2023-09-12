package com.example.lab6.controller;

import com.example.lab6.pojo.Wizard;
import com.example.lab6.repository.WizardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class WizardController {

    @Autowired
    private WizardService wizardService;
    @RequestMapping(value = "/wizards", method = RequestMethod.GET)
    public ResponseEntity<?> getWizard() {
        List<Wizard> wizards = wizardService.retrieveWizards();
        return ResponseEntity.ok(wizards);
    }

    @RequestMapping(value = "/addWizard", method = RequestMethod.POST)
    public ResponseEntity<?> addWizard(
            @RequestParam("name") String name,
            @RequestParam("gender") String gender,
            @RequestParam("dollars") Double dollars,
            @RequestParam("school") String school,
            @RequestParam("house") String house,
            @RequestParam("position") String position){
        Wizard n = wizardService.addWizard(new Wizard(null,gender, name, school, house, dollars, position));
        return ResponseEntity.ok(n);
    }

}
