package com.ticket.api;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

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
import com.ticket.showList.domain.ShowData;
import com.ticket.showList.domain.ShowList;

@Component
public class XmlParser {
	
	@Autowired
	private TagService tagService;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public List<ShowList> parseXmlString(String xmlString) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xmlString.getBytes()));

        // root tag
        doc.getDocumentElement().normalize();
        logger.info("#####root tag: " + doc.getDocumentElement().getNodeName());
        
        // parshing tag
        NodeList nList = doc.getElementsByTagName("db");
        logger.info("#####tag count: " + nList.getLength());
        
        // List<showList>
        List<ShowList> showListList = new ArrayList<>();
        
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
                String imagePath = tagService.getTagValue("poster", element);
                //String bannerImagePath // 공연상세
                //String infoImagePath // 공연상세
                //String discountImagePath // 공연상세
                
                
                ShowList showList = new ShowList();
                showList.setFcltynm(theaterName);
                showList.setPrfnm(name);
                showList.setGenrenm(genre);
                showList.setPrfpdfrom(startDate);
                showList.setPrfpdto(endDate);
                showList.setPoster(imagePath);
                
                showListList.add(showList);
            }
        }
        
        return showListList;
    }
	
	
	
	
	
	
	
	
	public ShowData parseShowDataXmlString(String xmlString) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xmlString.getBytes()));

        // root tag
        doc.getDocumentElement().normalize();
        logger.info("#####root tag: " + doc.getDocumentElement().getNodeName());
        
        // parshing tag
        NodeList nList = doc.getElementsByTagName("db");
        logger.info("#####tag count: " + nList.getLength());
        
        // ShowData
        ShowData showData = new ShowData();
        
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                
                //int theaterId = ;
                //String validStartDate = ;// startDate or new Date()
                //String validEndDate = ; // 원래는 티켓팅 풀린 날짜까지 해야 하지만 지금은 endDate로 넣기
                
                String theaterName = tagService.getTagValue("fcltynm", element);
                String name = tagService.getTagValue("prfnm", element);
                String genre = tagService.getTagValue("genrenm", element);
                String startDate = tagService.getTagValue("prfpdfrom", element);
                String endDate = tagService.getTagValue("prfpdto", element);
                String imagePath = tagService.getTagValue("poster", element);
                
                String time = tagService.getTagValue("prfruntime", element); // int로 파싱
                String age = tagService.getTagValue("prfage", element); // int로 파싱
                String bannerImagePath = tagService.getTagValue("poster", element);
                String infoImagePath = tagService.getTagValue("poster", element);
                String discountImagePath = tagService.getTagValue("poster", element);
                
				/* 현재는 임의로 같은 이미지 넣어둠 추후 styurl 이미지 가져와서 enum으로 넣기
				 * NodeList styurlList = element.getElementsByTagName("styurl");
				 * 
				 * for (int j = 0; j < styurlList.getLength(); j++) { Element sElement =
				 * (Element) styurlList.item(j); tagService.getTagValue("styurl", sElement); }
				 */
                
                
                showData.setFcltynm(theaterName);
                showData.setPrfnm(name);
                showData.setGenrenm(genre);
                showData.setPrfpdfrom(startDate);
                showData.setPrfpdto(endDate);
                showData.setPoster(imagePath);
                
                showData.setPrfruntime(time);
                showData.setPrfage(age);
                showData.setStyurl1(bannerImagePath);
                showData.setStyurl2(infoImagePath);
                showData.setStyurl3(discountImagePath);
                
                
            }
        }
        
        return showData;
    }
}
