package org.hdcd.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.hdcd.common.util.UploadFileUtils;
import org.hdcd.domain.Pds;
import org.hdcd.prop.ShopProperties;
import org.hdcd.service.PdsService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/pds")
public class PdsController {
	
	
	private final PdsService pdsService;
	
	private final ShopProperties shopProperties;
	
	
	@GetMapping("/list")
	public void list(Model model) throws Exception {
		List<Pds> itemList = this.pdsService.list();
		model.addAttribute("itemList", itemList);
	}
	
	
	@GetMapping("/register")
	 public String registerForm(Model model) {
		model.addAttribute(new Pds());
		return "pds/register";
	}
	
	
	@PostMapping("/register")
	public String register(Pds pds, RedirectAttributes rttr) throws Exception {
		this.pdsService.register(pds);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/pds/list";
	}
	
	
	
	@GetMapping("/read")
	public String read(Integer itemId, Model model) throws Exception {
		Pds pds = this.pdsService.read(itemId);
		model.addAttribute(pds);
		return "pds/read";
	}
	
	
	@GetMapping("/modify")
	public String modifyForm(Integer itemId, Model model) throws Exception {
		Pds pds = this.pdsService.read(itemId);
		model.addAttribute(pds);
		return "pds/modify";
	}
	
	
	@GetMapping("/remove")
	public String removeForm(Integer itemId, Model model) throws Exception {
		Pds pds = this.pdsService.read(itemId);
		model.addAttribute(pds);
		return "pds/remove";
	}
	
	
	@PostMapping("/remove")
	public String remove (Pds pds, RedirectAttributes rttr) throws Exception {
		this.pdsService.remove(pds.getItemId());
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/pds/list";
	}
	
	
	@ResponseBody
	@PostMapping(value="/uploadAjax", produces="text/plain;charset=UTF-8")
	public ResponseEntity<String>  uploadAjax(MultipartFile file) throws Exception {
		String savedName =  UploadFileUtils.uploadFile(shopProperties.getUploadPath(),
									file.getOriginalFilename(), file.getBytes());
		
		return new ResponseEntity<String>(savedName, HttpStatus.CREATED);
	}
	
	
	
	@ResponseBody
	@GetMapping("/downloadFile")
	public ResponseEntity<byte[]> downloadFile(String fullName) throws Exception {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		pdsService.updateAttachDownCnt(fullName);
		
		try {
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(shopProperties.getUploadPath() + fullName);
			
			String fileName = fullName.substring(fullName.indexOf("_") + 1);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition",  "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		
		return entity;
		
	}
	
	
	@ResponseBody
	@GetMapping("/getAttach/{itemId}")
	public List<String> getAttach(@PathVariable("itemId") Integer itemId) throws Exception {
		return pdsService.getAttach(itemId);
	}
	
	
	
	
	
	
	

}
