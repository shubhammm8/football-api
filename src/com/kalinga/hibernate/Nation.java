package com.kalinga.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Nation {
	@Id
	@Column(name="nid")
	private int nid;
	@Column(name="Name")
	private String Name;

	public int getNid() {
		return nid;
	}

	public void setNid(int pid) {
		this.nid = pid;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
	

}
