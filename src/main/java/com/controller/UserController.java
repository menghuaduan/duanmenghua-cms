package com.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.Article;
import com.bean.User;
import com.comon.ConstClass;
import com.github.pagehelper.PageInfo;
import com.mmcro.utils.Md5Utils;
import com.service.ArticleService;
import com.service.UserService;
import com.utils.PageUtils;

/**
 * @作者： 段梦华
 * @时间：2019年10月16日
 */
@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	UserService service;
	
	
	//文章相关的services
	@Autowired
	ArticleService articleService;
	
	
	//去往登录页面
	@RequestMapping("tolong.do")
	public String tolong(){
		return "user/login";
	}
	
	//去往注册页面
	@RequestMapping("toregister.do")
	public String toregister(){
		return "user/register";
	}
	
	
	
	
	//注册账号
	@RequestMapping("register.do")
	public String register(User user,HttpServletRequest request){
		String md5 = Md5Utils.md5(user.getPassword(), user.getUsername());
		user.setPassword(md5);
		int i =  service.register(user);
		
		if(0 == i){
			return "redirect:toregister.do";
		}else{
			request.setAttribute("err","系统错误请稍后重试");
			return "redirect:tolong.do";
		}
	}
	
	//验证账号的唯一性
	@RequestMapping("check.do")
	@ResponseBody
	public boolean check(User user){
		User user2=service.findByUsername(user);
		
		if(user2==null){
			return true;
		}else{
			return false;
		}
	}
	
	//登录
	@RequestMapping("login.do")
	public String login(User user,HttpServletRequest request){
		User loginUser=service.findByUsername(user);
		if(loginUser==null){
			return "redirect:tolong.do";
		}
		System.out.println("登录查询得到的用户："+loginUser);
		//将用户明码转换为密码
		String md5 = Md5Utils.md5(user.getPassword(), user.getUsername());
		if(loginUser.getPassword().equals(md5)){
			//去往首页
			//return "redirect:../index/findchannels.do";
			
			
			//用户信息保存在session当中
			request.getSession().setAttribute(ConstClass.SESSION_USER_KEY, loginUser);
			//普通注册用户
			if(loginUser.getRole()==ConstClass.USER_ROLE_GENERAL) {
				return "redirect:home.do";	
			//管理员用户	
			}else if(loginUser.getRole()==ConstClass.USER_ROLE_ADMIN){
				return "redirect:../admin/index";	
			}else {
				// 其他情况
				return "redirect:tolong.do";
			}
		}else{
			//去往的登录页面
			return "redirect:tolong.do";
		}
		
	}
	
	//退出登录
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute(ConstClass.SESSION_USER_KEY);
		return "user/login";
	}

	//普通用户去往个人中心
	@RequestMapping("home.do")
	public String userHome(){
		return "my/home";
	}
	
	
	//进入个人中心 获取我的文章
	@RequestMapping("myarticlelist")
	public String myarticles(HttpServletRequest request,
			@RequestParam(defaultValue="1") Integer page) {
		
		User loginUser =(User) request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		
		PageInfo<Article>  pageArticles = articleService.listArticleByUserId(loginUser.getId(),page);
		
		PageUtils.page(request, "/user/myarticlelist", 10, pageArticles.getList(), (long)pageArticles.getSize(), pageArticles.getPageNum());
		request.setAttribute("pageArticles", pageArticles);
		
		//转跳到我的列表
		return "/my/list";
	} 
	
	
	/* 删除用户自己的文章
	 * @param id 文章id
	 */
	@RequestMapping("delArticle")
	@ResponseBody
	public boolean delArticle(Integer id) {
		return articleService.remove(id)>0;
	}
	
	//查询所有的用户
		@RequestMapping("list")
		public String finduser(HttpServletRequest request){
			
			List<User> list = service.finduser();
			request.setAttribute("list", list);
			return "admin/user/list";
		}
	//改变用户的状态
	@RequestMapping("disabled")
	@ResponseBody
	public Boolean chageuser(User user){
		int i = service.changeUser(user);
		return i>0;
		
	}
	
	
}
