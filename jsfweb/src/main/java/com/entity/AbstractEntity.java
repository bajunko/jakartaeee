package com.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
   
	private static final long serialVersionUID = 7271406442777781310L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id; 



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
