package com.acss.poc.main;

import java.io.File;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.core.io.Resource;

import com.acss.hps.core.exception.MenuServiceException;


/**
 * Contains the Java representation of the menu-definition.xml
 * Marshals the menu-definition.xml on start-up for reuse.
 * @author gvargas.local
 *
 */
public class MenuService {
	
	private Resource file;
	private MenuItem menuItem;
	
	public MenuService(){}
	
	/**
	 * Marshalls the XML and returns a JAVA object MenuItem.
	 * @return
	 * @throws MenuServiceException 
	 * @throws JAXBException
	 * @throws IOException 
	 */
	@PostConstruct
	private void marshallMenuXML() throws MenuServiceException {
		try{
		JAXBContext jc = JAXBContext.newInstance(MenuItem.class);
		
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        
        File xml = file.getFile();
        
        	MenuItem menuItem = (MenuItem) unmarshaller.unmarshal(xml);
        	this.menuItem = menuItem;
        	
        }catch(JAXBException e){
        	throw new MenuServiceException("Error parsing the XML",e);
        } catch (IOException e) {
			throw new MenuServiceException("Problem with Resource occured",e);
		}

	}

	public MenuItem getMenuItem() {
		return menuItem;
	}

	public Resource getFile() {
		return file;
	}

	public void setFile(Resource file) {
		this.file = file;
	}
	
	
	
}
