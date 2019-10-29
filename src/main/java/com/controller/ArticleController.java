package com.controller;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.multi.MultiPanelUI;

import org.apache.poi.hssf.record.MulBlankRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bean.Article;
import com.bean.Category;
import com.bean.Channels;
import com.bean.ImageBean;
import com.bean.User;
import com.comon.ArticleType;
import com.comon.ConstClass;
import com.google.gson.Gson;
import com.service.ArticleService;
import com.service.CatService;
import com.service.IndexSrevice;

/**
 * @作者： 段梦华
 * @时间：2019年10月21日
 * 
 * 与文章相关联的Controller
 */

@Controller
@RequestMapping("article")
public class ArticleController {
	
	//与文章相关的service
	@Autowired
	ArticleService service;
	
	//与频道相关联的service
	@Autowired
	IndexSrevice chanService;
	
	//与栏目相关的service
	@Autowired
	CatService catService;
	
	
	
	//根据id查找文章
	@RequestMapping("show.do")
	public String show(Integer id,HttpServletRequest request){
		Article article = service.findById(id);
		request.setAttribute("article", article);
		if(article.getArticleType()==ArticleType.HTML) {
			request.setAttribute("article", article);
			return "article/detail";
		}else {
			Gson gson = new Gson();
			article.setImgList(gson.fromJson(article.getContent(), List.class));
			request.setAttribute("article", article);
			return "article/slieimgarticle";
		}
	}
	
	
	//跳转到发布文章页面并且传入追加的频道
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(HttpServletRequest request){
		
		//获取所有频道
		List<Channels> allChnls = chanService.findchannels();
		request.setAttribute("channels", allChnls);
		
		//跳往添加文章页面
		return "article/publish";
	}
	
	//跳转到添加图片文章的页面
	@RequestMapping(value = "addimg",method=RequestMethod.GET)
	public String addimg(HttpServletRequest request) {
		List<Channels> allChnls = chanService.findchannels();
		request.setAttribute("channels", allChnls);
		return "article/publishimg";
	}
	
	//添加文章
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(HttpServletRequest request,Article article,MultipartFile file) throws IllegalStateException, IOException{
		
		//获取文件的原名称
		 String originalFilename = file.getOriginalFilename();
		 //获取文件的拓展名
		 String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
		 
		 //获取日期
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		 //新路径
		 String path = "D:/CMS/备战考试/大项目/pic图片/"+sdf.format(new Date());
		 File pathfile = new File(path);
		 
		 
		 if(!pathfile.exists()){
			 pathfile.mkdir();
		 }
		 
		 String filename = path +UUID.randomUUID().toString()+suffixName;
		 File disFile = new File(filename);
		 
		 //文件另存到这个目录之下
		 file.transferTo(disFile);
		 
		 article.setPicture(filename.substring(22));
		 
		 //获取作者
		 User longuser = (User)request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		 article.setUserId(longuser.getId());
		 //添加文章
		 service.add(article);
		return "article/publish";
	}
	
	//添加图片类型的文章
	@RequestMapping(value = "addimg",method=RequestMethod.POST)
	public String addimg(HttpServletRequest request,Article article, 
			@RequestParam("file") MultipartFile file,//标题图片
			@RequestParam("imgs") MultipartFile[] imgs,// 文章中图片
			@RequestParam("imgsdesc") String[]  imgsdesc// 文章中图片的描述
			) throws IllegalStateException, IOException {
		
		
		article.setArticleType(ArticleType.IMAGE);
		
		processFile(file,article);
		List<ImageBean> imgBeans =  new ArrayList<ImageBean>();
		
		for (int i = 0; i < imgs.length; i++) {
			String picUrl = processFile(imgs[i]);//
			if(!"".equals(picUrl)) {
				ImageBean imageBean = new ImageBean(imgsdesc[i],picUrl);
				imgBeans.add(imageBean);
			}
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(imgBeans);// 文章的内容
		article.setContent(json);//
		
		
		//获取作者
		User loginUser = (User)request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		article.setUserId(loginUser.getId());
		
		service.add(article);
		
		return "article/publish";
		
	}
	
	
	//根据频道获取相应的分类  用于发布文章或者修改文章的下拉框
	@RequestMapping(value="listCatByChnl",method=RequestMethod.GET)
	@ResponseBody
	public List<Category> getCatByChnl(int chnlId){
		System.out.println("得到的分类id为："+chnlId);
		
		List<Category> chnlList = catService.findCatBychnId(chnlId);
		return chnlList;
	}
	
	
	
	// 跳转到修改的页面
	@RequestMapping(value = "update",method=RequestMethod.GET)
	public String update(HttpServletRequest request,Integer id) {
		List<Channels> allChnls = chanService.findchannels();
		Article article = service.findById(id);
		
		request.setAttribute("article", article);
		request.setAttribute("content1", article.getContent());
		request.setAttribute("channels", allChnls);
		return "my/update";
		
	}
	
	/**
	 * 处理每一个图片集合中的文件
	 */
	private String processFile(MultipartFile file) throws IllegalStateException, IOException {

		// 原来的文件名称
		System.out.println("file.isEmpty() :" + file.isEmpty()  );
		System.out.println("file.name :" + file.getOriginalFilename());
		
		if(file.isEmpty()||"".equals(file.getOriginalFilename()) || file.getOriginalFilename().lastIndexOf('.')<0 ) {
			return "";
		}
			
		String originName = file.getOriginalFilename();
		String suffixName = originName.substring(originName.lastIndexOf('.'));
		SimpleDateFormat sdf=  new SimpleDateFormat("yyyyMMdd");
		String path = "D:/CMS/备战考试/大项目/pic图片/" + sdf.format(new Date());
		File pathFile = new File(path);
		if(!pathFile.exists()) {
			pathFile.mkdir();
		}
		String destFileName = 		path + "/" +  UUID.randomUUID().toString() + suffixName;
		File distFile = new File( destFileName);
		file.transferTo(distFile);//文件另存到这个目录下边
		return destFileName.substring(22);
		
		
	}
	
	/**
	 * 处理文章的图片
	 */
	private void processFile(MultipartFile file,Article article) throws IllegalStateException, IOException {

		// 原来的文件名称
		System.out.println("file.isEmpty() :" + file.isEmpty()  );
		System.out.println("file.name :" + file.getOriginalFilename());
		
		if(file.isEmpty()||"".equals(file.getOriginalFilename()) || file.getOriginalFilename().lastIndexOf('.')<0 ) {
			article.setPicture("");
			return;
		}
			
		String originName = file.getOriginalFilename();
		String suffixName = originName.substring(originName.lastIndexOf('.'));
		SimpleDateFormat sdf=  new SimpleDateFormat("yyyyMMdd");
		String path = "D:/CMS/备战考试/大项目/pic图片/" + sdf.format(new Date());
		File pathFile = new File(path);
		if(!pathFile.exists()) {
			pathFile.mkdir();
		}
		String destFileName = 		path + "/" +  UUID.randomUUID().toString() + suffixName;
		File distFile = new File( destFileName);
		file.transferTo(distFile);//文件另存到这个目录下边
		article.setPicture(destFileName.substring(22));
	}
	
	//真正修改
	@RequestMapping(value = "update",method=RequestMethod.POST)
	@ResponseBody
	public boolean update(HttpServletRequest request,Article article, MultipartFile file) throws IllegalStateException, IOException {
		
		
		processFile(file,article);
		//获取作者
		User loginUser = (User)request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		article.setUserId(loginUser.getId());
		
		int result = service.update(article);
		
		return result > 0;
		
	}
	

}
