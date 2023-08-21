package com.ticket.api;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ticket.show.service.TagService;

@Component
public class XmlParser {
	
	@Autowired
	private TagService tagService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void parseXmlString(String xmlString) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xmlString.getBytes()));

        // root tag
        doc.getDocumentElement().normalize();
        logger.info("#####root tag: " + doc.getDocumentElement().getNodeName());
        
        // parshing tag
        NodeList nList = doc.getElementsByTagName("db");
        logger.info("#####tag count: " + nList.getLength());
        
        // DB insert
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                
                // 공연 목록을 100개 조회하고 DB에 넣는다.
                // 공연 상세를 100번 조회하고 DB에 넣는다. (nullable한 요소들)
                // 조회 때마다 공연 시설 ID도 따로 저장하고, ID로 공연 시설 상세 조회하고(100번),
                // 극장 DB에 넣고 생성된 id를 리턴해 공연에 넣는다.
                
                
                
                //int theaterId = ;
                String theaterName = tagService.getTagValue("fcltynm", element);
                
                String name = tagService.getTagValue("prfnm", element);
                String genre = tagService.getTagValue("genrenm", element);
                String startDate = tagService.getTagValue("prfpdfrom", element);
                String endDate = tagService.getTagValue("prfpdto", element);
                //String validStartDate = ;// startDate or new Date()
                //String validEndDate = ; // 원래는 티켓팅 풀린 날짜까지 해야 하지만 지금은 endDate로 넣기
                //int time // 공연상세
                //int age // 공연상세
                //String imagePath = tagService.getTagValue("poster", element);
                //String bannerImagePath // 공연상세
                //String infoImagePath // 공연상세
                //String discountImagePath // 공연상세
                
                
                
                // Call a method to save 'dataToSave' into the database
                saveDataToDatabase(dataToSave);
            }
        }
    }
}
