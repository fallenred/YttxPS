package com.yttx.yttxps.web.action.pic;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.Pic;
import com.yttx.yttxps.model.vo.PicRequest;
import com.yttx.yttxps.service.IPicService;
import com.yttx.yttxps.web.action.BaseController;
import com.yttx.yttxps.web.action.LoginController;

@Controller
@Scope("prototype")
@RequestMapping("pic/")
public class PicController extends BaseController {
	
static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private IPicService picService;
	
	 /**
	 * 分页查询图片信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findPic.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindPic(PicRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getPic());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyPic(map);
		try {
		List<Pic> list = picService.selectSelectivePage(map);
		map.put("rows", list);
		map.put("result", "ok");
		}
		catch(Exception e) {
			e.printStackTrace();
			return (Map<String, Object>) JsonResult.jsonError("查询图片失败:" + e.getMessage());
		}
		return map;
    }
	
	/**
	 * 新图片信息
	 * @param pic
	 * @return
	 */
	@RequestMapping(value="addPic.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxaddPic(Pic pic,
			@RequestParam(value = "file", required = true) MultipartFile file)
    {  
		logger.debug("当前新增对象 {}", pic.getNo());
		
		StringBuffer path = new StringBuffer();
		path.append("/").append(pic.getBelongtype()).append("/");
		if(pic.getSubtype() != null && pic.getSubtype().length() > 0) {
			path.append(pic.getSubtype()).append("/");
		}
		path.append(pic.getNo()).append("/");
		logger.debug("图片path{}", path.toString());
		
		String fileurl = resourceConvertURL(path.toString(), file);
		logger.debug("图片srcfile{}", fileurl);
		pic.setSrcfile(fileurl);
		
		try{
			int ret = picService.insert(pic);
		}
		catch(Exception e){
			e.printStackTrace();
			return (Map<String, Object>) JsonResult.jsonError("新增失败:" + e.getMessage());
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 新图片信息
	 * @param pic
	 * @return
	 */
	@RequestMapping(value="addLinePic.htm", method = RequestMethod.POST)
	public void uploadFile(@RequestParam("upload") 
		MultipartFile file,
		HttpServletRequest request,
		HttpServletResponse response)
    {  
		logger.debug("当前新增图片对象 {}", file);
		
		StringBuffer path = new StringBuffer();
		path.append("/").append("line").append("/");
		logger.debug("图片path{}", path.toString());
		
		String fileurl = resourceConvertURL(path.toString(), file);
		logger.debug("图片srcfile{}", fileurl);
		
		response.setContentType("text/html;charset=UTF-8");
//      response.setHeader("X-Frame-Options", "SAMEORIGIN");
        String CKEditorFuncNum = request.getParameter("CKEditorFuncNum");
        PrintWriter out;
        String s = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction("+CKEditorFuncNum+", '"+fileurl+"');</script>";
        try {
            out = response.getWriter();
            out.print(s);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
    }
	
	/**
	 * 删除图片信息
	 * @param scenic
	 * @return
	 */
	@RequestMapping(value="delPic.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelPic(@RequestParam(value = "index") BigDecimal  index, String srcfile)
    {  
		logger.debug("当前删除key {}", index);
		try{
			int ret = picService.delete(index);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		// 尝试删除资源服务器的资源
		deleteResourceByURL(srcfile);
		return (Map<String, Object>) JsonResult.jsonOk();
    }
}
