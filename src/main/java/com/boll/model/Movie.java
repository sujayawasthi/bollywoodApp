package com.boll.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

@Entity
public class Movie implements Serializable {

	private static final long serialVersionUID = -5107118120726968764L;

	public Movie() {
	}

	public Movie(String name, String description, String photoUrl, String era) {
		this.era = era;
		this.name = name;
		this.description = description;
		this.photoUrl = photoUrl;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	String name;

	String description;

	String era;

	@Lob
	byte[] photo;

	String photoUrl;

	@ManyToMany
	List<Actor> actors;

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getEra() {
		return era;
	}

	public void setEra(String era) {
		this.era = era;
	}
}
