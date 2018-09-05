package com.ge.digital.quality.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ge.digital.gearbox.common.response.BizErrorResponse;
import com.ge.digital.gearbox.common.response.NormalResponse;
import com.ge.digital.gearbox.common.response.RestResponseCode;
import com.ge.digital.quality.entity.AlarmBlackList;
import com.ge.digital.quality.entity.AlarmMessage;
import com.ge.digital.quality.entity.ProcessParamThreshold;
import com.ge.digital.quality.entity.ProcessTimeThreshold;
import com.ge.digital.quality.entity.QualityItem;
import com.ge.digital.quality.entity.QualitySpecification;
import com.ge.digital.quality.excel.entity.AlarmMessageExcelSupport;
import com.ge.digital.quality.service.UploadingService;
import com.ge.digital.quality.util.ClassHelper;
import com.ge.digital.schedule.entity.ModelBase;
import com.ge.digital.schedule.excelutil.ExcelUploadSupport;

import com.ge.digital.schedule.excelutil.MasterDataType;


@RestController
@RequestMapping("/api/masterdata")
public class MasterDataController {

	private static final Logger logger = LoggerFactory.getLogger(MasterDataController.class);

	@Autowired
	UploadingService uploadingService;

	@GetMapping("/quality/findLastUpdateON")
	public Object findLastUpdateON() {
		NormalResponse rsp = new NormalResponse();
		rsp.setBody(uploadingService.findLastUpdateOn());
		return rsp;
	}

	@GetMapping("/findAll")
	public Object findAll(@RequestParam("type") String type) {
		NormalResponse rsp = new NormalResponse();
		rsp.setBody(uploadingService.findAll(type));
		return rsp;
	}

	@RequestMapping(value = "/uploadMasterData", method = RequestMethod.POST)
	public Object uploadMasterData(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "isDelete", required = false) String isDelete, @RequestParam("type") String type,
			HttpServletResponse response) {
		NormalResponse rsp = new NormalResponse();
		BizErrorResponse bizError = new BizErrorResponse();
		Class clazz = ClassHelper.getClassFromType(type);
		try {

			if (isNotDelete(isDelete)) {
				List<? extends ExcelUploadSupport> objList = uploadingService.excel2Object(file, clazz);
				rsp.setBody(assignInsertUpdateRaw(objList));
			} else {
				List<? extends ExcelUploadSupport> objList = uploadingService.excel2ObjectDelete(file, clazz);
				rsp.setBody(assignDeleteRaw(objList));
			}
		} catch (RuntimeException | IOException e) {
			bizError.setResCode(RestResponseCode.UPLOAD_EXCEPTION.getCode());
			bizError.setErrMsg(RestResponseCode.UPLOAD_EXCEPTION.getLabel());
			e.printStackTrace();
			logger.error("line uploading error!", e);
			return bizError;
		}
		return rsp;
	}

	@RequestMapping(value = "/saveMasterData", method = RequestMethod.POST)
	public Object saveMasterdata(HttpServletResponse res, @RequestParam("type") String type,
			@RequestParam(value = "isDelete", required = false) String isDelete,
			@RequestParam("batchUploadId") Long batchUploadId) {
		NormalResponse rsp = new NormalResponse();
		boolean isDel = isNotDelete(isDelete) ? false : true;
		uploadingService.saveObject(batchUploadId, isDel, type);
		rsp.setBody(null);
		logger.info("save success.batchuploadId:" + batchUploadId);
		return rsp;
	}

	@RequestMapping(value = "/downloadObjs", method = RequestMethod.GET)
	public void downloadObjs(HttpServletResponse res, @RequestParam("type") String type) {
		File file = null;
		file = uploadingService.objects2excel(type);
		file2ResponseByte(res, file);
		logger.info("down load success");
	}

	private void file2ResponseByte(HttpServletResponse response, File file) {
		try {
			String filePath = file.getAbsolutePath();// æ–‡ä»¶è·¯å¾„
			String[] fp = filePath.split("\\\\");
			String fileName = fp[fp.length - 1];// æ–‡ä»¶å??ç§°

			response.setHeader("conent-type", "application/octet-stream");
			response.setContentType("application/octet-stream");
			String prefixname = fileName.substring(
					fileName.indexOf("temp-") == -1 ? 0 : (fileName.lastIndexOf("temp-") + 5),
					fileName.indexOf("_download") == -1 ? fileName.lastIndexOf(".")
							: fileName.lastIndexOf("_download"));
			String postfixname = fileName.substring(fileName.lastIndexOf("."));
			String newfilename = "";
			if (fileName.indexOf(AlarmMessage.class.getSimpleName()) != -1) {
				newfilename = AlarmMessage.class.getSimpleName();

			} else if(fileName.indexOf(AlarmBlackList.class.getSimpleName()) != -1) {
				newfilename = AlarmBlackList.class.getSimpleName();
			} else if(fileName.indexOf(ProcessTimeThreshold.class.getSimpleName()) != -1) {
				newfilename = ProcessTimeThreshold.class.getSimpleName();
			}else if(fileName.indexOf(ProcessParamThreshold.class.getSimpleName()) != -1) {
				newfilename = ProcessParamThreshold.class.getSimpleName();
			}else if(fileName.indexOf(QualityItem.class.getSimpleName()) != -1) {
				newfilename = QualityItem.class.getSimpleName();
			}else if(fileName.indexOf(QualitySpecification.class.getSimpleName()) != -1) {
				newfilename = QualitySpecification.class.getSimpleName();

			}

			response.setHeader("Content-Disposition",
					"attachment; filename=" + new String((newfilename + ".xlsx").getBytes("ISO-8859-1"), "UTF-8"));

			OutputStream os = response.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(os);

			InputStream is = null;

			is = new FileInputStream(filePath);
			BufferedInputStream bis = new BufferedInputStream(is);

			int length = 0;
			byte[] temp = new byte[1 * 1024 * 10];

			while ((length = bis.read(temp)) != -1) {
				bos.write(temp, 0, length);
			}
			bos.flush();
			bis.close();
			bos.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class UploadWrapper {
		private List<? extends ModelBase> list;

		public List<? extends ModelBase> getList() {
			return list;
		}

		public void setList(List<? extends ModelBase> list) {
			this.list = list;
		}

		public int getInsertCount() {
			return insertCount;
		}

		public void setInsertCount(int insertCount) {
			this.insertCount = insertCount;
		}

		public int getUpdateCount() {
			return updateCount;
		}

		public void setUpdateCount(int updateCount) {
			this.updateCount = updateCount;
		}

		public int getDeleteCount() {
			return deleteCount;
		}

		public void setDeleteCount(int deleteCount) {
			this.deleteCount = deleteCount;
		}

		private int insertCount;
		private int updateCount;
		private int deleteCount;
	}

	private UploadWrapper assignInsertUpdateRaw(List list) {
		UploadWrapper uw = new UploadWrapper();
		uw.setList(list);
		int insertCount = 0;
		int updateCount = 0;
		for (Object obj : uw.getList()) {
			if (obj instanceof ModelBase) {
				ModelBase eu = (ModelBase) obj;
				if (eu.getId() != null && !eu.getId().equals("")) {
					updateCount++;
				} else {
					insertCount++;
				}
			}
		}
		uw.setInsertCount(insertCount);
		uw.setUpdateCount(updateCount);
		return uw;
	}

	private UploadWrapper assignDeleteRaw(List list) {
		UploadWrapper uw = new UploadWrapper();
		uw.setList(list);
		int deleteCount = 0;
		for (Object obj : uw.getList()) {
			if (obj instanceof ModelBase) {
				ModelBase eu = (ModelBase) obj;

				if (eu.getId() != null && !eu.getId().equals("")) {
					deleteCount++;
				}
			}
		}
		uw.setDeleteCount(deleteCount);
		return uw;
	}


	private Class getClassFromType(String type) {
		if (type.equals(MasterDataType.AlarmMessage.getValue())) {
			return AlarmMessageExcelSupport.class;
		}

		return null;
	}


	private boolean isNotDelete(String isDelete) {
		return isDelete == null || isDelete.equals("false");
	}

}
