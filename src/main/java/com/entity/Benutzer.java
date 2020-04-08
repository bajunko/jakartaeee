package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Benutzer")

@NamedQuery(name = Benutzer.FIND_ALL_BENUTZER, query = "select t from Benutzer  t order by t.name") //select * from TodoUserTable...
@NamedQuery(name = Benutzer.FIND_BENUTZER_BY_ID, query = "select t from Benutzer t where t.id = :id")
@NamedQuery(name = Benutzer.FIND_BENUTZER_BY_NAME, query = "select t from Benutzer t where t.name like :name ")
@NamedQuery(name = Benutzer.DELETE_BENUTZER_BY_ID, query = "delete from Benutzer t where t.id = :id ")
public class Benutzer extends AbstractEntity {
	
    public static final String FIND_ALL_BENUTZER = "Benutzer.findAll";
    public static final String FIND_BENUTZER_BY_ID = "Benutzer.findById";
    public static final String FIND_BENUTZER_BY_NAME = "Benutzer.findByName";
    public static final String DELETE_BENUTZER_BY_ID = "Benutzer.deleteById";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Transient
    private String salt;
	
    @Column(length = 100)
    @NotEmpty(message = "A name must be set")
    private String name; //varchar 255 user@domain.com


    @NotNull(message = "Password cannot be empty")
    @Size(min = 8, max = 100, message = "Password must be a min of 8 and max of 100 characters")
    //@Pattern(regexp = "", message = "Password must have at least one upper case, " +
    //        "one lower case, a digit and must contain at least one of $%&#!")
    private String passwort; //#%AdhhddS87987fjvjof
    
    @NotNull(message = "Privilegt must be choose")
    private boolean privilegt;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public boolean isPrivilegt() {
		return privilegt;
	}

	public void setPrivilegt(boolean privilegt) {
		this.privilegt = privilegt;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	

}
