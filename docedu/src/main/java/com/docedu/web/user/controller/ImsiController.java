package com.docedu.web.user.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.docedu.web.user.service.ImsiService;
import com.docedu.web.user.vo.Criteria;
import com.docedu.web.user.vo.ImsiVO;

@Controller
public class ImsiController {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired
	private final ImsiService imsiService;

	@Inject
	public ImsiController(ImsiService imsiService) {
		this.imsiService = imsiService;
	}
	@ResponseBody
    @RequestMapping(value = "/insertimsi.do", method = RequestMethod.POST)
	public String insertimsiGet(@ModelAttribute ImsiVO imsiVO) {
		sqlSessionTemplate.insert("imsi.imsiinsert",imsiVO);
		return "redirect:/imsipage.do";
	}
	@RequestMapping(value = "/imsipage.do")
	public ModelAndView imsilistGet(Model model) throws Exception {
		ModelAndView mav = new ModelAndView();
		Criteria cr = null;
		List<ImsiVO> showList = new ArrayList<ImsiVO>();
		showList = imsiService.imsilist(cr);
		mav.addObject("ImsiList", showList);
		return mav;
	}
	
	@RequestMapping("/imsiview.do")
	public ModelAndView imsiviewGet(@RequestParam int imsi_seq) {
		ModelAndView mav = new ModelAndView();
		ImsiVO imsiVO;
		imsiVO = imsiService.imsiread(imsi_seq);
		mav.addObject("imsi_view", imsiVO);
		return mav;
	}
}
