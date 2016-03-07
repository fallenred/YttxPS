package com.yttx.yttxps.web.action.pic;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.Pic;
import com.yttx.yttxps.model.vo.PicListWebRequest;
import com.yttx.yttxps.service.IPicService;
import com.yttx.yttxps.web.action.BaseController;


@Controller
@Scope("prototype")
@RequestMapping("pic/")
public class PicController extends BaseController {
	
static Logger logger = LoggerFactory.getLogger(PicController.class);
	
	@Autowired
	private IPicService picService;

	@RequestMapping(value="picpage.htm")
	public String  openPicPage(@RequestParam("resNo") String resNo,
			@RequestParam("resName") String resName,
			@RequestParam("resType") String resType,
			Model model) throws UnsupportedEncodingException
    {  
		model.addAttribute("resNo", resNo);
		model.addAttribute("resName",resName);
		model.addAttribute("resType", resType);
		return "pic/pic";
    }

	/**
	 * 分页查询图片信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findPic.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindPic(Pic pic)
    {  
		List<Pic> list=null;
		try {
			list = picService.findByResNo(pic.getResNo());
		}catch(Exception e) {
			JsonResult.jsonError(e.getMessage());
		}
		return JsonResult.jsonData(list);
    }
	
	/**
	 * 新图片信息
	 * @param pic
	 * @return
	 */
	@RequestMapping(value="addPic.htm", method = RequestMethod.POST)
	@ResponseBody
	public  Object ajaxaddPic(Pic pic,
			@RequestParam(value = "files", required = true) MultipartFile[] files)
    {  
		logger.debug("当前新增对象 {}", pic.getSeq());
		StringBuffer path = new StringBuffer();
		path.append("/").append(pic.getResType()).append("/");
		path.append(pic.getResNo()).append("/");
		logger.debug("图片path{}", path.toString());
		try{
			for(MultipartFile file:files){
				if(!file.isEmpty()){
					String fileurl = resourceConvertURL(path.toString(), file);
					Thread.sleep(1000);
					pic.setSrcFile(fileurl);
					picService.insert(pic);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return JsonResult.jsonError("新增失败:" + e.getMessage());
		}
		return  JsonResult.jsonOk();
    }


	/**
	 * 资源快照图片上传
	 * @param pic
	 * @return
	 */
	@RequestMapping(value="addPic4CKEditor.htm", method = RequestMethod.POST)
	@ResponseBody
	public void ajaxaddPic4CKEditor(@RequestParam("upload") MultipartFile multipartFile,
			HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("X-Frame-Options", "SAMEORIGIN");
		
		String fileurl = resourceConvertURL("", multipartFile);
		logger.debug("快照图片上传srcfile{}", fileurl);
		String CKEditorFuncNum = request.getParameter("CKEditorFuncNum");
		PrintWriter out = null;
		String s = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction("+CKEditorFuncNum+", '"+fileurl+"');</script>";
		try {
			out = response.getWriter();
			out.print(s);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			out.print(s);
			out.flush();
		}
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
	public Object ajaxdelPic(@RequestParam(value = "index") BigDecimal  index, String srcfile)
    {  
		logger.debug("当前删除key {}", index);
		try{
			picService.delete(index);
		}
		catch(Exception e){
			return JsonResult.jsonError("删除失败");
		}
		// 尝试删除资源服务器的资源
		deleteResourceByURL(srcfile);
		return JsonResult.jsonOk();
    }
	
	/**
	 * 修改图片的显示顺序
	 */
	@RequestMapping(value="updateSeqs.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object updatePicsSeq(PicListWebRequest req)
	{
		try {
			picService.updateSeqs(req.getPiclist());
		} catch (Exception e) {
			return JsonResult.jsonError(e.getMessage());
		}
		return JsonResult.jsonOk();
	}
}
