package com.skilltracker.skilltrackerapi.entity;

import java.util.List;
import java.util.Map;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Person {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Skill> skills;

    @ElementCollection
    private Map<Long, SkillLevel> skillLevels;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public Map<Long, SkillLevel> getSkillLevels() {
		return skillLevels;
	}

	public void setSkillLevels(Map<Long, SkillLevel> skillLevels) {
		this.skillLevels = skillLevels;
	}

    // getters and setters
    
}

