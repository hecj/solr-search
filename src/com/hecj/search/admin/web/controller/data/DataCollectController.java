package com.hecj.search.admin.web.controller.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hecj.search.admin.entity.DataCollectParams;
import com.hecj.search.admin.entity.DataField;
import com.hecj.search.admin.services.DataCollectService;
import com.hecj.search.admin.vo.VoDataCollectParams;
import com.hecj.search.hibernate.util.UUIDUtil;
import com.hecj.search.util.CodeConvertUtil;
import com.hecj.search.util.EasyGridData;
import com.hecj.search.util.Log4jUtil;
import com.hecj.search.util.MessageCode;
import com.hecj.search.util.ObjectToJson;
import com.hecj.search.util.Pagination;
import com.hecj.search.util.Result;
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
			String IP = jsonObj.getString("IP");
			Integer PORT = StringUtil.isStrEmpty(jsonObj.getString("PORT"))?null:jsonObj.getInt("PORT");
			String baseURL = jsonObj.getString("baseURL");
			String pageParams = jsonObj.getString("pageParams");
			Integer start = StringUtil.isStrEmpty(jsonObj.getString("start"))?null:jsonObj.getInt("start");
			Integer end = StringUtil.isStrEmpty(jsonObj.getString("end"))?null:jsonObj.getInt("end");
			Integer step = StringUtil.isStrEmpty(jsonObj.getString("step"))?null:jsonObj.getInt("step");
			String baseSelect = jsonObj.getString("baseSelect");
			String dataBaseType = jsonObj.getString("dataBaseType");
			String encode = jsonObj.getString("encode");
			String tableName = jsonObj.getString("tableName");
			JSONArray jsonArr = jsonObj.getJSONArray("fieldList");
			
			Set<DataField> dataFields = new HashSet<DataField>();
			/*
			 * 爬虫参数
			 */
			DataCollectParams mCollectParams = new DataCollectParams();
			mCollectParams.setId(UUIDUtil.autoUUID());
			mCollectParams.setIP(IP);
			mCollectParams.setPORT(PORT);
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
			/*
			 * 解析字段
			 */
			for(int i=0;i<jsonArr.size();i++){
				JSONObject obj = jsonArr.getJSONObject(i);
				DataField mDataField = new DataField();
				mDataField.setId(UUIDUtil.autoUUID());
				mDataField.setFieldSelect(obj.getString("fieldSelect"));
				mDataField.setSelectMethod(obj.getString("selectMethod"));
				mDataField.setTargetAttr(obj.getString("targetAttr"));
				mDataField.setPattern(obj.getString("pattern"));
				mDataField.setOldPlace(obj.getString("oldPlace"));
				mDataField.setNewPlace(obj.getString("newPlace"));
				mDataField.setFieldName(obj.getString("fieldName"));
				mDataField.setFieldType(obj.getString("fieldType"));
				mDataField.setFieldLenth(obj.getInt("fieldLenth"));
				mDataField.setDataCollectParams(mCollectParams);
				dataFields.add(mDataField);
			}
			
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
	
	@ResponseBody
	@RequestMapping(params="operator=seacherDataCollect")
	public String searchDataCollect(Integer page,Integer rows){
		try{
			Pagination mPagination = new Pagination(10);
			if(!StringUtil.isObjectEmpty(page)){
				mPagination.setCurrPage(page.longValue());
			}
			if(!StringUtil.isObjectEmpty(page)){
				mPagination.setPageSize(rows);
			}
			Map<String,Object> mMap = new HashMap<String,Object>();
			mMap.put("pagination", mPagination);
			
			Result result = dataCollectService.searchDataCollectByPagination(mMap);
			if(result.isSuccess()){
				
				List<VoDataCollectParams> datas = new ArrayList<VoDataCollectParams>();
				for(Object o : result.getData()){
					DataCollectParams d = (DataCollectParams)o;
					VoDataCollectParams vo = new VoDataCollectParams();
					vo.setId(d.getId());
					vo.setBaseSelect(d.getBaseSelect());
					vo.setBaseURL(d.getBaseURL());
					vo.setDataBaseType(d.getDataBaseType());
					vo.setEncode(d.getEncode());
					vo.setEnd(d.getEnd());
					vo.setIP(d.getIP());
					vo.setPageParams(d.getPageParams());
					vo.setPORT(d.getPORT());
					vo.setStart(d.getStart());
					vo.setStep(d.getStep());
					vo.setTableName(d.getTableName());
					datas.add(vo);
				}
				return new EasyGridData(result.getPagination().getCountSize(),datas).toJSON();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return new EasyGridData().toJSON();
	}
	@RequestMapping(params="operator=toDataCollectMessage")
	public String toDataCollectMessage(String id,HttpServletRequest request){
		try{
			if(!StringUtil.isStrEmpty(id)){
				DataCollectParams dataCollectParams = dataCollectService.searchDataCollectParams(id);
				request.setAttribute("dataCollectParams", dataCollectParams);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "admin/jsp/datacollect/dataCollectMessage";
	}
	
	@RequestMapping(params="operator=delete")
	public void deleteDataCollect(String id,HttpServletResponse response){
		try{
			if(!StringUtil.isStrEmpty(id)){
//				dataCollectService.deleteDataCollectParams(id);
				write(response,ObjectToJson.object2json(new MessageCode("success","处理成功!")));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		write(response,ObjectToJson.object2json(new MessageCode("fail","删除失败!")));
	}
	
}
