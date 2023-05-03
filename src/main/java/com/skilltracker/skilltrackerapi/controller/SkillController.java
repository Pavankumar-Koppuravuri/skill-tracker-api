package com.skilltracker.skilltrackerapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilltracker.skilltrackerapi.entity.Skill;
import com.skilltracker.skilltrackerapi.exception.ResourceNotFoundException;
import com.skilltracker.skilltrackerapi.repository.SkillRepository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;



@RestController
@RequestMapping("/skills")
public class SkillController {
    @Autowired
    private SkillRepository skillRepository;

    @PostMapping
    public Skill createSkill(@RequestBody Skill skill) {
        return skillRepository.save(skill);
    }

    @GetMapping("/{id}")
    public Skill getSkill(@PathVariable Long id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id " + id));
    }
    
    @GetMapping
    public List<Skill> getAllSkills() {
    	List<Skill> skills = new ArrayList<>();
        skillRepository.findAll().forEach(skills::add);
        return skills;
    }

    @PutMapping("/{id}")
    public Skill updateSkill(@PathVariable Long id, @RequestBody Skill skill) {
        return skillRepository.findById(id)
                .map(existingSkill -> {
                    existingSkill.setName(skill.getName());
                    existingSkill.setLevel(skill.getLevel());
                    return skillRepository.save(existingSkill);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSkill(@PathVariable Long id) {
        return skillRepository.findById(id)
                .map(skill -> {
                    skillRepository.delete(skill);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id " + id));
    }
}


