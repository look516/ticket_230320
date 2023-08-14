package com.ticket.show;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticket.show.bo.ShowBO;
import com.ticket.show.domain.ShowView;
import com.ticket.show.service.TagService;

@RequestMapping("/show")
@Controller
public class ShowController {
	
	@Autowired
	private ShowBO showBO;
	
	@GetMapping("/show_detail_view")
	public String showDetailView(
			@RequestParam("showId") int showId,
			//HttpServletRequest request,
			Model model) {
		
		// showView select by showId
		ShowView show = showBO.generateShowViewByShowId(showId);
		model.addAttribute("show", show);
		
		// 리뷰 작성이 클릭되는 타이밍에 이걸 수행하자
		// showId, showName 리뷰에서 들고가기 - 항상 유지되는 것인지? 어느 시점에 버려야 하는지?
		// + 최근 본 => cookie로 해결?
		//HttpSession session = request.getSession();
		//session.setAttribute("showId", show.getShow().getId());
		//session.setAttribute("showName", show.getShow().getName());
		
		model.addAttribute("view", "show/showDetail");
		return "template/layout";
	}
	
	
	// genre를 param으로 안 넘기고 select 해오는 방법?
	@GetMapping("/show_list_view")
	public String showListView(
			@RequestParam("genre") String genre,
			Model model) {
		model.addAttribute("genre", genre);
		
		// select show list by genre
		List<ShowView> showList = showBO.generateShowViewList(genre);
		
		model.addAttribute("showList", showList);
		model.addAttribute("view", "show/showList");
		return "template/layout";
	}
	
	
	@GetMapping("/show_tab_view")
	public String showTabView(
			@RequestParam Map<String, Object> param,
			@RequestParam("index") String index,
			Model model) {
		String url = "show/tab/" + index;
		int showId = (int)param.get("showId");
		
		ShowView show = showBO.generateShowViewByShowId(showId);
		model.addAttribute("show", show);
		
		return url;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 보류
	@Autowired
	private TagService tagService;
	
	/*
	@GetMapping("/show_list_view")
	public String showListView(Model model) throws ParserConfigurationException, SAXException, IOException {
		
		String url = "http://www.kopis.or.kr/openApi/restful/pblprfr";
		String service = "bd89dc80bd9f43338c9d75e7fae03669";
		String stdate = "20230101"; // 시작일
		String eddate = "20230801"; // 종료일
		String rows = "10"; // 개수
		String cpage = "1"; // 현재 페이지
		
		
		String parsingUrl = url + "?service=" + service + "&stdate=" + stdate
				+ "&eddate=" + eddate + "&rows=" + rows + "&cpage=" + cpage;
		
		
		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
		Document doc = dBuilder.parse(parsingUrl);
		
		// root tag => 최상위 태그(dbs)
		doc.getDocumentElement().normalize();
		model.addAttribute("root", doc.getDocumentElement().getNodeName());
		
		// 파싱 tag => 가져올 태그(db)
		// 전체 공연 개수인지 아니면 한 공연의 태그 개수인지
		NodeList nList = doc.getElementsByTagName("db");
		model.addAttribute("num", nList.getLength());
		
		List<Map> shows = new ArrayList<>();
		for(int temp = 0; temp < nList.getLength(); temp++){		
			Node nNode = nList.item(temp);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
								
				Element eElement = (Element) nNode;
				Map<String, Object> show = new HashMap<>();
				show.put("prfnm", tagService.getTagValue("prfnm", eElement));
				show.put("poster", tagService.getTagValue("poster", eElement));
				show.put("fcltynm", tagService.getTagValue("fcltynm", eElement));
				show.put("prfpdfrom", tagService.getTagValue("prfpdfrom", eElement));
				show.put("prfpdto", tagService.getTagValue("prfpdto", eElement));
				
				shows.add(show);
					
				//System.out.println(eElement.getTextContent())
			}	// if end
		}	// for end
		//shows라는 list를 model에 넣어주자.
		model.addAttribute("shows", shows);
		
		model.addAttribute("view", "show/showList");
		
		return "template/layout";
	}
	*/
}
