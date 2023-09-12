package com.example.lab06.controller;

import com.example.lab06.pojo.Wizard;
import com.example.lab06.repository.WizardService;
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
    @RequestMapping(value = "/updateWizard", method = RequestMethod.POST)
    public boolean updateWizard(
            @RequestParam("oldName") String oldName,
            @RequestParam("gender") String gender,
            @RequestParam("name") String name,
            @RequestParam("school") String school,
            @RequestParam("house") String house,
            @RequestParam("money") Double money,
            @RequestParam("position") String position){
        Wizard wizard = wizardService.retrieveWizardByName(oldName);
        if (wizard != null) {
            wizardService.updateWizard(new Wizard(wizard.get_id(), gender, name, school, house, money, position));
            return true;
        }
        else { return false; }
    }
    @RequestMapping(value = "/deleteWizard", method = RequestMethod.POST)
    public boolean deleteWizard(
//            @RequestParam("name")String name,
            @RequestParam("id") String id){
        Wizard wizard = wizardService.retrieveWizardByName(id);
        return wizardService.deleteWizard(wizard);
    }


}
