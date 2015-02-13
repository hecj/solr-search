package com.freedom.search.webapp.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freedom.search.web.controller.base.BaseController;
import com.freedom.search.webapp.services.LbEssayService;


@Controller
@RequestMapping("webapp/essay/essay.htm")
public class LbEssayController extends BaseController {
	
	@Resource
	private LbEssayService essayService;

	public void setEssayService(LbEssayService essayService) {
		this.essayService = essayService;
	}
	
	
	
}
