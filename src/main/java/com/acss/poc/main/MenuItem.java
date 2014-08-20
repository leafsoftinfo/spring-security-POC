package com.acss.poc.main;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author gvargas.local
 *
 */
@XmlRootElement(name="MenuItem")
@XmlAccessorType(XmlAccessType.FIELD)
public class MenuItem {
	@XmlAttribute
	private String id;
	@XmlAttribute(name="text")
	private String name;
	@XmlAttribute
	private String url;
	@XmlAttribute
	private String access;
	
	@XmlElementRef(name="MenuItem")
	private List<MenuItem> menuItems;
	
	@XmlTransient
	private boolean hasChildren;
	
	public MenuItem(){
		menuItems = new ArrayList<MenuItem>();
	}
	
	
	//Contructor
	public MenuItem(String id, String name, String url, String access) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.access = access;
	}

	public boolean hasChildren(){
		
		//true if not empty.
		return !menuItems.isEmpty();
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getAccess() {
		return access;
	}


	public void setAccess(String access) {
		this.access = access;
	}


	public List<MenuItem> getMenuItems() {
		return menuItems;
	}


	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}


	public boolean isHasChildren() {
		return !menuItems.isEmpty();
	}


	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}
	
	
	
	
}
