package com.example.lab6.repository;

import com.example.lab6.pojo.Wizard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WizardService {
    @Autowired
    private WizardRepository repository;

    public WizardService(WizardRepository repository) {
        this.repository = repository;
    }
    public List<Wizard> retrieveWizards() {
        return repository.findAll();
    }
    public Wizard retrieveWizardByName(String name) {
        return repository.findByName(name);
    }
    public Wizard addWizard(Wizard wizard){
        return repository.save(wizard);
    }
}
