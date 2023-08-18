package com.ticket.api;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ticket.api.entity.ShowListAPIEntity;

@Component
public class XmlParser {
	public void parseXmlString(String xmlString) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new ByteArrayInputStream(xmlString.getBytes()));

        NodeList nodeList = document.getElementsByTagName("db"); // Change this according to your XML structure

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                //String dataToSave = element.getElementsByTagName("dataElementName").item(0).getTextContent(); // Adjust this to extract the specific data you need
                ShowListAPIEntity show = new ShowListAPIEntity();
                show.s
                
                // Call a method to save 'dataToSave' into the database
                saveDataToDatabase(dataToSave);
            }
        }
    }
}
