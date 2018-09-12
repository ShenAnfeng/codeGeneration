package com.rkylin.wallet.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class BaseController {

	/*@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		//对于需要转换为Date类型的属性，使用DateEditor进行处理   
		binder.registerCustomEditor(Date.class, new DateEditor());
	}*/

}
