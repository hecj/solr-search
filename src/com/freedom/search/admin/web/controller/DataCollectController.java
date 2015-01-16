package com.freedom.search.admin.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import com.freedom.search.admin.entity.LzDataCollectParams;
import com.freedom.search.admin.entity.LzDataField;
import com.freedom.search.admin.services.DataCollectService;
import com.freedom.search.admin.vo.VoDataCollectParams;
import com.freedom.search.admin.vo.VoDataField;
import com.freedom.search.hibernate.util.UUIDUtil;
import com.freedom.search.util.CodeConvertUtil;
import com.freedom.search.util.EasyGridData;
import com.freedom.search.util.Log4jUtil;
import com.freedom.search.util.MessageCode;
import com.freedom.search.util.ObjectToJson;
import com.freedom.search.util.Pagination;
import com.freedom.search.util.Result;
import com.freedom.search.util.StringUtil;
import com.freedom.search.web.controller.base.BaseController;
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
			
			Set<LzDataField> dataFields = new HashSet<LzDataField>();
			/*
			 * 爬虫参数
			 */
			LzDataCollectParams mCollectParams = new LzDataCollectParams();
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
				LzDataField mDataField = new LzDataField();
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
	public String searchDataCollect(Integer page,Integer rows,String encode){
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
			if(!StringUtil.isStrEmpty(encode)){
				mMap.put("encode", encode);
			}
			
			Result result = dataCollectService.searchDataCollectByPagination(mMap);
			if(result.isSuccess()){
				
				List<VoDataCollectParams> datas = new ArrayList<VoDataCollectParams>();
				for(Object o : result.getData()){
					LzDataCollectParams d = (LzDataCollectParams)o;
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
				LzDataCollectParams dataCollectParams = dataCollectService.searchDataCollectParams(id);
				request.setAttribute("dataCollectParams", dataCollectParams);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "admin/jsp/datacollect/dataCollectManager/dataCollectMessage";
	}
	
	@RequestMapping(params="operator=delete")
	public void deleteDataCollect(String id,HttpServletResponse response){
		try{
			if(!StringUtil.isStrEmpty(id)){
				dataCollectService.deleteDataCollectParams(id);
				write(response,ObjectToJson.object2json(new MessageCode("success","处理成功!")));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		write(response,ObjectToJson.object2json(new MessageCode("fail","删除失败!")));
	}
	
	@RequestMapping(params="operator=toEdit")
	public String toDataCollectEdit(String id,String type,HttpServletRequest request,HttpServletResponse response){
		try{
			if(!StringUtil.isStrEmpty(id)){
				LzDataCollectParams dataCollectParams = dataCollectService.searchDataCollectParams(id);
				if(type.equals("1")){
					request.setAttribute("dataCollectParams", dataCollectParams);
				}else if(type.equals("2")){
					List<VoDataField> list = new ArrayList<VoDataField>();
					for(LzDataField d:dataCollectParams.getDataFields()){
						VoDataField vo = new VoDataField();
						vo.setFieldLenth(d.getFieldLenth());
						vo.setFieldName(d.getFieldName());
						vo.setFieldSelect(d.getFieldSelect());
						vo.setFieldType(d.getFieldType());
						vo.setId(d.getId());
						vo.setNewPlace(d.getNewPlace());
						vo.setOldPlace(d.getOldPlace());
						vo.setPattern(d.getPattern());
						vo.setSelectMethod(d.getSelectMethod());
						vo.setTargetAttr(d.getTargetAttr());
						list.add(vo);
					}
					//排序
					Collections.sort(list, new Comparator<VoDataField>() {
						public int compare(VoDataField v1, VoDataField v2) {
							return v1.getId().compareTo(v2.getId());
						}
					});
					write(response, ObjectToJson.object2json(list));
					return null;
				}
			
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "admin/jsp/datacollect/dataCollectManager/dataCollectEdit";
	}
	
	@RequestMapping(params="operator=edit")
	public void editDataCollect(String data,HttpServletResponse response){
		try{
			if(!StringUtil.isStrEmpty(data)){
				System.out.println(data);
				JSONObject jsonObj = JSONObject.fromObject(data);
				String id = jsonObj.getString("id");
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
				
				Set<LzDataField> dataFields = new HashSet<LzDataField>();
				/*
				 * 爬虫参数
				 */
				LzDataCollectParams mCollectParams = new LzDataCollectParams();
				mCollectParams.setId(id);
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
					LzDataField mDataField = new LzDataField();
					if(StringUtil.isObjectEmpty(obj.get("id"))){
						mDataField.setId(UUIDUtil.autoUUID());
					}else{
						mDataField.setId(obj.getString("id"));
					}
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
				
				dataCollectService.editDataCollectParams(mCollectParams);
				
				write(response,ObjectToJson.object2json(new MessageCode("success","处理成功!")));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		write(response,ObjectToJson.object2json(new MessageCode("fail","修改失败!")));
	}
	
	@RequestMapping(params="operator=add")
	public void addDataCollect(String data,HttpServletResponse response){
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
			
			Set<LzDataField> dataFields = new HashSet<LzDataField>();
			/*
			 * 爬虫参数
			 */
			LzDataCollectParams mCollectParams = new LzDataCollectParams();
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
				LzDataField mDataField = new LzDataField();
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
			
			dataCollectService.addDataCollectParams(mCollectParams);
			write(response,ObjectToJson.object2json(new MessageCode("success","处理成功!")));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		/*
		 * return
		 */
		write(response,ObjectToJson.object2json(new MessageCode("fail","添加失败!")));
	}
	
	@RequestMapping(params="operator=toAdd")
	public String toAdd(){
		return "admin/jsp/datacollect/dataCollectManager/dataCollectAdd";
	}
	
}
