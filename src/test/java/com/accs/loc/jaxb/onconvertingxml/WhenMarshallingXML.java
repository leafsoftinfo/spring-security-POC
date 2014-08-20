package com.accs.loc.jaxb.onconvertingxml;

import static org.junit.Assert.assertEquals;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import com.acss.poc.main.MenuItem;

public class WhenMarshallingXML {
	
	@Test
	public void shouldReturnObjectOnValidXMLFile() throws JAXBException{
		//arrange
		JAXBContext jc = JAXBContext.newInstance(MenuItem.class);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        File xml = new File("src/main/resources/META-INF/props/menu-definitions.xml");
        MenuItem a = (MenuItem) unmarshaller.unmarshal(xml);
        
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        //act
        marshaller.marshal(a, System.out);
        
        //assert 2 is the default written on the xml.
        assertEquals(2,a.getMenuItems().size());
        
        for(MenuItem menu : a.getMenuItems()){
        	if(menu.hasChildren()){
        		for(MenuItem submenu:menu.getMenuItems()){
        			if(submenu.hasChildren()){
        				for(MenuItem submenu_2 : submenu.getMenuItems()){
        					System.out.println(submenu_2.getName());
        				}
        			}else
        				System.out.println(submenu.getName());
        		}
        	}else
        		System.out.println(menu.getName());
        }
        
        
	}
}
