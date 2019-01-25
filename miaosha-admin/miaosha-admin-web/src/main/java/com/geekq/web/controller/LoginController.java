package com.geekq.web.controller;

import com.geekq.admin.entity.Logininfo;
import com.geekq.admin.service.ILogininfoService;
import com.geekq.common.enums.Constants;
import com.geekq.common.utils.resultbean.ResultGeekQ;
import com.geekq.common.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author 邱润泽
 * 登录模块
 */
@Controller
public class LoginController  extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private ILogininfoService iLogininfoService;

    @RequestMapping("/login" )
    @ResponseBody
    public ResultGeekQ<Boolean> dologin(HttpServletResponse response,
                                        HttpServletRequest request,
                                        String username, String password) {
        ResultGeekQ<Boolean> result = ResultGeekQ.build();
        ResultGeekQ<Logininfo>  login = this.iLogininfoService.login(username, password,
                Constants.USERTYPE_NORMAL,request.getRemoteAddr());
        if(ResultGeekQ.isSuccess(login)){
            result.withError(login.getCode(),login.getMessage());
        }
        return result;
    }
}