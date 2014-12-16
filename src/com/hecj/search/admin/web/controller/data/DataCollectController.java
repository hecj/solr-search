package com.hecj.search.admin.web.controller.data;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hecj.search.admin.services.DataCollectService;
import com.hecj.search.admin.vo.DataCollectParams;
import com.hecj.search.admin.vo.DataField;
import com.hecj.search.util.CodeConvertUtil;
import com.hecj.search.util.Log4jUtil;
import com.hecj.search.util.StringUtil;
import com.hecj.search.web.controller.base.BaseController;
/**
 * @类功能说明：数据搜集Controller类
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-16 上午12:15:25
 * @版本：V1.0
 */
@Controller
@RequestMapping("admin/data/dataCollect.htm")
public class DataCollectController extends BaseController{
	
	@Resource
	private DataCollectService dataCollectService;
	
	public void setDataCollectService(DataCollectService dataCollectService) {
		this.dataCollectService = dataCollectService;
	}

	@ResponseBody
	@RequestMapping(params="operator=submitDataCollect")
	public String submitDataCollect(String data){
		data = CodeConvertUtil.decode(data);
		Log4jUtil.log(data);
		try{
			
			JSONObject jsonObj = JSONObject.fromObject(data);
			String baseURL = jsonObj.getString("baseURL");
			String pageParams = jsonObj.getString("pageParams");
			int start = StringUtil.isStrEmpty(jsonObj.getString("start"))?0:jsonObj.getInt("start");
			int end = StringUtil.isStrEmpty(jsonObj.getString("end"))?0:jsonObj.getInt("end");
			int step = StringUtil.isStrEmpty(jsonObj.getString("step"))?0:jsonObj.getInt("step");
			String baseSelect = jsonObj.getString("baseSelect");
			String dataBaseType = jsonObj.getString("dataBaseType");
			String encode = jsonObj.getString("encode");
			String tableName = jsonObj.getString("tableName");
			JSONArray jsonArr = jsonObj.getJSONArray("fieldList");
			/*
			 * 解析字段
			 */
			List<DataField> dataFields = new ArrayList<DataField>();
			for(int i=0;i<jsonArr.size();i++){
				JSONObject obj = jsonArr.getJSONObject(i);
				DataField mDataField = new DataField();
				mDataField.setFieldSelect(obj.getString("fieldSelect"));
				mDataField.setPattern(obj.getString("pattern"));
				mDataField.setOldPlace(obj.getString("oldPlace"));
				mDataField.setNewPlace(obj.getString("newPlace"));
				mDataField.setFieldName(obj.getString("fieldName"));
				mDataField.setFieldType(obj.getString("fieldType"));
				mDataField.setFieldLenth(obj.getInt("fieldLenth"));
				dataFields.add(mDataField);
			}
			
			/*
			 * 爬虫参数
			 */
			DataCollectParams mCollectParams = new DataCollectParams();
			mCollectParams.setBaseSelect(baseSelect);
			mCollectParams.setEncode(encode);
			mCollectParams.setBaseURL(baseURL);
			mCollectParams.setPageParams(pageParams);
			mCollectParams.setDataBaseType(dataBaseType);
			mCollectParams.setTableName(tableName);
			mCollectParams.setDataFields(dataFields);
			mCollectParams.setEnd(end);
			mCollectParams.setStart(start);
			mCollectParams.setStep(step);
			
			dataCollectService.dataCollectService(mCollectParams);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		/*
		 * return
		 */
		JSONObject reJson = new JSONObject();
		reJson.put("message", "success");
		return reJson.toString();
	}	
}
