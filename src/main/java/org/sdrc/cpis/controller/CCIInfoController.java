package org.sdrc.cpis.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.htmlparser.jericho.Source;

import org.sdrc.cpis.domains.CCITransactionDetails;
import org.sdrc.cpis.domains.CCITypeDetails;
import org.sdrc.cpis.models.CCIInfoMapModel;
import org.sdrc.cpis.models.CasteModel;
import org.sdrc.cpis.services.CCIInfoService;
import org.sdrc.cpis.util.CCIInfoValueObject;
import org.sdrc.cpis.util.GeoLocationObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.XMLResource;

import com.lowagie.text.DocumentException;

/**
 * 
 * @author Pratyush Kumar Rath
 * My first Controller
 * pratyush@sdrc.co.in
 *
 */


@Controller
public class CCIInfoController {
	
	@Autowired
	private CCIInfoService cciInfoService;

	/*@RequestMapping("cci_info")
	@ResponseBody
	public CCIInfoMapModel cciInfoMapModel(){
		CCIInfoMapModel model = new CCIInfoMapModel();
		return model;
	}
*/	
	@RequestMapping("/getMstData")
	@ResponseBody
	public List<CCIInfoMapModel> getMstData(){
		List<CCIInfoMapModel> mstData= cciInfoService.fetchCCIMstData();
		return mstData;
		
	}
	@RequestMapping("/getCCIDetails")
	@ResponseBody
	public CCIInfoValueObject cciInfoMap(Model model){
		List<CCIInfoMapModel> mstData= cciInfoService.fetchCCIMstData();
		List<CCITypeDetails> cciTypes = cciInfoService.getCCITypes();
		List<GeoLocationObject> googleMapData = new ArrayList<GeoLocationObject>();
		for(CCIInfoMapModel cciModel : mstData){
			googleMapData.add(cciModel.getGeoLocationObject());
		}
		CCIInfoValueObject object=new CCIInfoValueObject();
		object.setCciTypes(cciTypes);
		object.setCciDetails(mstData);
		object.setGoogleMapData(googleMapData);
//		model.addAttribute("masterData", mstData);
//		model.addAttribute("cciTypes",cciTypes);
		System.out.println(object);
		return object;
		
	}
	
	@RequestMapping("/getCCITypes")
	@ResponseBody
	public List<CCITypeDetails> getCCITypes(){
		
		return cciInfoService.getCCITypes();
	}
	
	@RequestMapping("/downloadCCIFactsheet")
	public void downloadCCIFactsheet(HttpServletResponse response) throws MalformedURLException, IOException, DocumentException{
		
		File file=null;
		String sourceUrlString="file:D://cciInfoMap.jsp";
//		if (sourceUrlString.indexOf(':')==-1) sourceUrlString="file:"+sourceUrlString;
        Source source=new Source(new URL(sourceUrlString));
        String renderedText=source.getRenderer().toString();
        
        Document document = XMLResource.load( new ByteArrayInputStream(renderedText.getBytes() ) ).getDocument();

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument( document, null );

        renderer.layout();

        String fileNameWithPath = "D:/" + "PDF-XhtmlRendered.pdf";
        FileOutputStream fos = new FileOutputStream( fileNameWithPath );
        renderer.createPDF( fos );
        fos.close();
        System.out.println( "File 1: '" + fileNameWithPath + "' created." );
        
        
		try {
			String filePath=fileNameWithPath;
			file = new File(filePath);
			InputStream inputStream;
			inputStream = new FileInputStream(file);
			response.setContentType("application/octet-stream");
			
		    response.setHeader("Content-Disposition", "fileName="+file.getName());
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		} catch (FileNotFoundException e) 
		{
			response.sendError(404,"File Deleted Or Modified");
		//	response.set
		e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		catch (NullPointerException e)
		{
			response.sendError(404,"File Deleted Or Modified");
			e.printStackTrace();
		}
		finally {
			try {
				response.getOutputStream().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping("/updateCCITransactional")
	@ResponseBody
	public List<CCITransactionDetails> updateCCITransactional(){
		return cciInfoService.updateCCITransactional();
	}
	
	@RequestMapping("/getHRDetails")
	@ResponseBody
	public List<Object> getHRDetails(){
		return cciInfoService.getAllHRDetails();
	}
	
	@RequestMapping("/getCasteData")
	@ResponseBody
	public List<CasteModel> getCasteData(){
		return cciInfoService.findByCaste();
	}
	
	
	@RequestMapping("/getAgeData")
	@ResponseBody
	public List<CasteModel> getAgeData(){
		return cciInfoService.findByAge();
	}
}
