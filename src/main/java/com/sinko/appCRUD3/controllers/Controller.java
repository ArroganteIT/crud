package com.sinko.appCRUD3.controllers;

import com.sinko.appCRUD3.dao.PersonDAO;
import com.sinko.appCRUD3.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
@RequestMapping(value = "/people")
public class Controller {

    private final PersonDAO personDAO;

    @Autowired
    public Controller(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String showPeople(Model model) {
        model.addAttribute("people", personDAO.getPeople());
        return "people/showPeople";
    }

    @GetMapping(value = "/{id}")
    public String showPerson(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("person", personDAO.getPerson(id));
        return "people/showPerson";
    }

    @GetMapping(value = "/createPersonPage")
    public String createPersonPage(@ModelAttribute(value = "person") Person person) {
        return "people/createPersonPage";
    }

    @PatchMapping
    public String create(@ModelAttribute(value = "person") Person person) {
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping(value = "/{id}/edit")
    public String edit(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("person", personDAO.getPerson(id));
        return "people/edit";
    }

    @PatchMapping(value = "/{id}")
    public String update(@ModelAttribute(value = "person") Person newPerson, @PathVariable(value = "id") int id) {
        personDAO.update(id, newPerson);
        return "redirect:/people";
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable(value = "id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}

