package com.namoo.ns1.web.controller.towner;

import static com.namoo.ns1.web.util.StringUtil.isEmpty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.TownerService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;
import com.namoo.ns1.service.shared.exception.NamooExceptionFactory;
import com.namoo.ns1.web.controller.common.DefaultController;
import com.namoo.ns1.web.controller.common.PageTranfer;

@WebServlet("/towner/regist.do")
public class TownerRegistController extends DefaultController {
	//
	private static final long serialVersionUID = 8075594201606793335L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		String name = req.getParameter("inputName");
		String email = req.getParameter("inputEmail");
		String password = req.getParameter("inputPassword");
		String password2 = req.getParameter("inputPassword2");
		
		if (isEmpty(name) || isEmpty(email) || isEmpty(password) || isEmpty(password2)) {
			NamooExceptionFactory.createRuntime("모든 정보를 입력하세요.");
		}
		
		if (!password.equals(password2)) {
			NamooExceptionFactory.createRuntime("패스워드를 확인하세요.");
		}

		// 회원가입
		TownerService townerService = NamooClubServiceFactory.getInstance().getTownerService();
		townerService.registTowner(name, email, password);

		// 정보 메시지
		String message = "회원가입이 완료되습니다. 로그인 후 사용가능합니다.";
		String linkURL = "/towner/login.xhtml";
		PageTranfer.getInstance(req, resp).information(message, linkURL);
	}
}
