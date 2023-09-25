package com.example.lab06v2.repository;

import com.example.lab06v2.pojo.Wizard;
import com.example.lab06v2.pojo.Wizards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
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
    @Cacheable(value = "Wizard")
    public List<Wizard> retrieveWizards() {
        return repository.findAll();
    }
    @Cacheable(value = "Wizard")
    public Wizard retrieveWizardByName(String name) {
        return repository.findByName(name);
    }
    @CacheEvict(value = "Wizard")
    public String addWizard(Wizard wizard) {
        repository.save(wizard);
        return "Added Successfully";
    }

    @CachePut(value = "Wizard")
    public String updateWizard(String _id, Wizard updateWizard) {
        Optional<Wizard> findingWizard = repository.findById(_id);  //updateWizard.get_id()
        if (findingWizard.isPresent()) {
            Wizard wizard = findingWizard.get();
            wizard.setName(updateWizard.getName());
            wizard.setHouse(updateWizard.getHouse());
            wizard.setSchool(updateWizard.getSchool());
            wizard.setMoney(updateWizard.getMoney());
            wizard.setSex(updateWizard.getSex());
            wizard.setPosition(updateWizard.getPosition());
            repository.save(wizard);
            return "Updated Successfully";
        }
        return "Wizard not found";
    }

    @CacheEvict(value = "Wizard")
    public String deleteWizard(String _id) {
        Optional<Wizard> findingWizard = repository.findById(_id);
        if (findingWizard.isPresent()) {
            repository.deleteById(_id);
            return "Deleted Successfully";
        }
        return "Wizard not found";
    }


}
