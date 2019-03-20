package com.elte.issuetracker.controllers;


import com.elte.issuetracker.entities.Issue;
import com.elte.issuetracker.repositories.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/issues")
public class IssueController {

    @Autowired
    private IssueRepository issueRepository;


    // list all issues

    @RequestMapping("")
    public String list(Model model){

        model.addAttribute("issue", issueRepository.findAll());
        return "list";
    }


    @GetMapping("/new")
    public String newIssue(Model model){
        model.addAttribute("issue", new Issue());
        return "newIssue";
    }


    @PostMapping("/new")
    public String addIssue(@Valid Issue issue, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "newIssue";
        }

        issueRepository.save(issue);
        return "redirect:/issues";
    }


    // Get specific Id
    @GetMapping("/{id}")
    public String showSingleID(@PathVariable int id, Model model) throws Exception{
        Optional<Issue> oIssue = issueRepository.findById(id);

        if(oIssue.isPresent()){
            model.addAttribute("issue", oIssue.get());
            return "singleIssue";
        }

        throw new Exception("No such issue ID!");

    }


    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) throws Exception{

        Optional<Issue> oIssue = issueRepository.findById(id);
        if(oIssue.isPresent()) {
            model.addAttribute("issue", oIssue.get());
            return "newissue";
        }
        throw new Exception("No such issue id");
    }

    @PostMapping("/{id}/edit")
    public String editIssue(@Valid Issue issue, @PathVariable int id, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "newissue";
        }
        //issue.setId(id);
        System.out.print("the id is " + issue.getId() + "    ");
        issueRepository.save(issue);
        return "redirect:/issues";
    }


   /* @PostMapping(path="/{id}/delete")
    public String deleteIssue(@Valid Issue issue, @PathVariable int id){
        issueRepository.delete(issue);
        return "redirect:/issues";
    }

    @GetMapping("/{id}/delete")
    public String deleteIssue(@Valid Issue issue){
        issueRepository.findAll();
        return "redirect:/issues";
    }
*/



}






















