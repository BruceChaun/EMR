package com.emr.dbutil.impl;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import com.emr.dbutil.BioChemInfo;
import com.emr.dbutil.ClinicCheckInfo;
import com.emr.dbutil.DiagnoseInfo;
import com.emr.dbutil.DoctorAdvice;
import com.emr.dbutil.PatientBaseInfo;
import com.emr.dbutil.SaccharinInfo;
import com.emr.dbutil.SymptomInfo;
import com.emr.dbutil.UrineInfo;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DBProcess {
	
	private static final String path = "data/diabetes/";
	
	public static void patientBaseInfo() throws IOException, BiffException {
		String file = path + "糖尿病数据库_患者基本信息表(200).xls";
		File inputWorkbook = new File(file);
		Workbook w;
		
		w = Workbook.getWorkbook(inputWorkbook);
	    Sheet sheet = w.getSheet(0);
	    
	    PatientBaseInfo pbi = new PatientBaseInfo();
	    try {
			pbi.connect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    for (int i = 1; i < sheet.getRows(); i++) {
	    	int patientId = Integer.parseInt(
	    			sheet.getCell(0, i).getContents().replaceFirst("\\.\\d+", ""));
	    	int birthPlace = Integer.parseInt(sheet.getCell(1, i).getContents());;
	    	String dateOfBirth = sheet.getCell(2, i).getContents();
	    	dateOfBirth = dateOfBirth.replaceFirst(" 00:00:00", "");
	    	String sex = sheet.getCell(3, i).getContents();;
	    	String fb = "";
	    	pbi.insert(patientId, birthPlace, dateOfBirth, sex, fb);
	    }
	}
	
	public static void clincCheckInfo() throws IOException, BiffException {
		String file = path + "糖尿病数据库_临床检查信息表(200).xls";
		File inputWorkbook = new File(file);
		Workbook w;
		
		w = Workbook.getWorkbook(inputWorkbook);
	    Sheet sheet = w.getSheet(0);
	    
	    ClinicCheckInfo cci = new ClinicCheckInfo();
	    try {
			cci.connect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    for (int i = 1; i < sheet.getRows(); i++) {
	    	int patientId = Integer.parseInt(sheet.getCell(0, i).getContents());
	    	int examNo = Integer.parseInt(sheet.getCell(1, i).getContents());;
	    	String examDateTime = sheet.getCell(2, i).getContents();
	    	String examPara = sheet.getCell(3, i).getContents();
	    	String desc = sheet.getCell(4, i).getContents();
	    	String impression = sheet.getCell(5, i).getContents();
	    	String recommend = sheet.getCell(6, i).getContents();
	    	String memo = sheet.getCell(7, i).getContents();
	    	cci.insert(patientId, examNo, examDateTime, examPara, 
	    			desc, impression, recommend, memo);
	    }
	}

	public static void urineInfo() throws IOException, BiffException {
		String file = path + "糖尿病数据库_尿常规信息表(1507).xls";
		File inputWorkbook = new File(file);
		Workbook w;
		
		w = Workbook.getWorkbook(inputWorkbook);
	    Sheet sheet = w.getSheet(0);
	    
	    UrineInfo ui = new UrineInfo();
	    try {
			ui.connect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    for (int i = 1; i < sheet.getRows(); i++) {
	    	ui.ztc = sheet.getCell(0, i).getContents();
	    	ui.sqxh = sheet.getCell(1, i).getContents();
	    	ui.bb = sheet.getCell(7, i).getContents();
	    	ui.bbsm = sheet.getCell(8, i).getContents();
	    	ui.zd = sheet.getCell(9, i).getContents();
	    	ui.rq = sheet.getCell(10, i).getContents();
	    	ui.nbxb = Float.parseFloat(sheet.getCell(12, i).getContents());
	    	ui.nbzcd = Float.parseFloat(sheet.getCell(14, i).getContents());
	    	ui.ndydxsy = sheet.getCell(15, i).getContents();
	    	ui.ndhsdxsy = sheet.getCell(16, i).getContents();
	    	ui.nhxb = Float.parseFloat(sheet.getCell(17, i).getContents());
	    	ui.nxmxb = sheet.getCell(18, i).getContents();
	    	ui.ntdxsy = sheet.getCell(19, i).getContents();
	    	ui.nyjj = Float.parseFloat(sheet.getCell(20, i).getContents());
	    	ui.nysjd = Float.parseFloat(sheet.getCell(21, i).getContents());
	    	ui.nyys = sheet.getCell(22, i).getContents();
	    	ui.nyyxsysy = sheet.getCell(23, i).getContents();
	    	ui.nzd = sheet.getCell(24, i).getContents();
	    	ui.nttsy = sheet.getCell(25, i).getContents();
	    	ui.ndbdxsy = sheet.getCell(30, i).getContents();
	    	ui.insert();
	    }
	}
	
	public static void bioChemInfo() throws IOException, BiffException {
		String file = path + "糖尿病数据库_生化信息表(500).xls";
		File inputWorkbook = new File(file);
		Workbook w;
		
		w = Workbook.getWorkbook(inputWorkbook);
	    Sheet sheet = w.getSheet(0);
	    
	    BioChemInfo bci = new BioChemInfo();
	    try {
			bci.connect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    for (int i = 1; i < sheet.getRows(); i++) {
	    	bci.ztc = sheet.getCell(0, i).getContents();
	    	bci.sqxh = sheet.getCell(1, i).getContents();
	    	bci.bb = sheet.getCell(7, i).getContents();
	    	bci.bbsm = sheet.getCell(8, i).getContents();
	    	bci.zd = sheet.getCell(9, i).getContents();
	    	bci.rq = sheet.getCell(10, i).getContents();
	    	bci.basajzym = sheet.getCell(12, i).getContents();
	    	bci.tdasajzym = sheet.getCell(13, i).getContents();
	    	bci.zdb = sheet.getCell(14, i).getContents();
	    	bci.xqbdb = sheet.getCell(15, i).getContents();
	    	bci.zdhs = sheet.getCell(16, i).getContents();
	    	bci.zjdhs = sheet.getCell(17, i).getContents();
	    	bci.jxlsm = sheet.getCell(18, i).getContents();
	    	bci.ns = sheet.getCell(19, i).getContents();
	    	bci.gaxjzym = sheet.getCell(20, i).getContents();
	    	bci.js = sheet.getCell(21, i).getContents();
	    	bci.ptt = sheet.getCell(22, i).getContents();
	    	bci.gysz = sheet.getCell(23, i).getContents();
	    	bci.xqns = sheet.getCell(24, i).getContents();
	    	bci.zdgc = sheet.getCell(25, i).getContents();
	    	bci.jsjm = sheet.getCell(26, i).getContents();
	    	bci.rstqm = sheet.getCell(27, i).getContents();
	    	bci.g = sheet.getCell(28, i).getContents();
	    	bci.n = sheet.getCell(29, i).getContents();
	    	bci.j = sheet.getCell(30, i).getContents();
	    	bci.lhw = sheet.getCell(31, i).getContents();
	    	bci.wjl = sheet.getCell(32, i).getContents();
	    	bci.m = sheet.getCell(33, i).getContents();
	    	bci.zfm = sheet.getCell(34, i).getContents();
	    	bci.gmdzdbdgc = sheet.getCell(35, i).getContents();
	    	bci.t = sheet.getCell(36, i).getContents();
	    	bci.bbhtjhl = sheet.getCell(37, i).getContents();
	    	bci.insert();
	    }
	}
	
	public static void sacchrinInfo() throws IOException, BiffException {
		String file = path + "糖尿病数据库_糖化信息表(444).xls";
		File inputWorkbook = new File(file);
		Workbook w;
		
		w = Workbook.getWorkbook(inputWorkbook);
	    Sheet sheet = w.getSheet(0);
	    
	    SaccharinInfo si = new SaccharinInfo();
	    try {
			si.connect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    for (int i = 1; i < sheet.getRows(); i++) {
	    	si.ztc = sheet.getCell(0, i).getContents();
	    	si.sqxh = sheet.getCell(1, i).getContents();
	    	si.bb = sheet.getCell(7, i).getContents();
	    	si.bbsm = sheet.getCell(8, i).getContents();
	    	si.zd = sheet.getCell(9, i).getContents();
	    	si.rq = sheet.getCell(10, i).getContents();
	    	si.qxthxhdbcd = 
	    			Float.parseFloat(sheet.getCell(12, i).getContents());
	    	si.insert();
	    }
	}
	
	public static void doctorAdvice() throws IOException, BiffException {
		String file = path + "糖尿病数据库_医嘱信息表(436).xls";
		File inputWorkbook = new File(file);
		Workbook w;
		
		w = Workbook.getWorkbook(inputWorkbook);
	    Sheet sheet = w.getSheet(0);
	    
	    DoctorAdvice da = new DoctorAdvice();
	    try {
			da.connect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    for (int i = 1; i < sheet.getRows(); i++) {
	    	da.patient_id = Integer.parseInt(sheet.getCell(0, i).getContents());
	    	da.visit_id = Integer.parseInt(sheet.getCell(1, i).getContents());
	    	da.order_no = Integer.parseInt(sheet.getCell(2, i).getContents());
	    	da.order_sub_no = Integer.parseInt(sheet.getCell(3, i).getContents());
	    	da.repeat_indicator = Integer.parseInt(sheet.getCell(4, i).getContents());
	    	da.order_class = sheet.getCell(5, i).getContents();
	    	da.order_text = sheet.getCell(6, i).getContents();
	    	da.dosage = sheet.getCell(8, i).getContents();
	    	da.dosage_units = sheet.getCell(9, i).getContents();
	    	da.administration = sheet.getCell(10, i).getContents();
	    	da.duration = sheet.getCell(11, i).getContents();
	    	da.duration_units = sheet.getCell(12, i).getContents();
	    	da.start_date_time = sheet.getCell(13, i).getContents();
	    	da.stop_date_time = sheet.getCell(14, i).getContents();
	    	da.frequency = sheet.getCell(15, i).getContents();
	    	da.freq_counter = sheet.getCell(16, i).getContents();
	    	da.freq_interval = sheet.getCell(17, i).getContents();
	    	da.freq_interval_unit = sheet.getCell(18, i).getContents();
	    	da.freq_detail = sheet.getCell(19, i).getContents();
	    	da.perform_schedule = sheet.getCell(20, i).getContents();
//	    	da.ca_pregnancy_history = sheet.getCell(10, i).getContents();
	    	da.enter_date_time = sheet.getCell(24, i).getContents();
	    	da.order_status = Integer.parseInt(sheet.getCell(25, i).getContents());
	    	da.billing_attr = Integer.parseInt(sheet.getCell(26, i).getContents());
	    	da.last_accting_date_time = sheet.getCell(27, i).getContents();
	    	da.last_perform_date_time = sheet.getCell(28, i).getContents();
//	    	da.stop_nurse = sheet.getCell(10, i).getContents();
	    	da.stop_order_date_time = sheet.getCell(32, i).getContents();
	    	da.drug_billing_attr = sheet.getCell(33, i).getContents();
	    	if (da.last_perform_date_time.equals(""))
	    		da.last_perform_date_time = "0000-00-00 00:00:00";
	    	da.insert();
	    }
	}
	
	public static void diagnoseInfo() throws IOException, BiffException {
		String file = path + "糖尿病数据库_诊断信息表(200).xls";
		File inputWorkbook = new File(file);
		Workbook w;
		
		w = Workbook.getWorkbook(inputWorkbook);
	    Sheet sheet = w.getSheet(0);
	    
	    DiagnoseInfo di = new DiagnoseInfo();
	    try {
			di.connect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    for (int i = 1; i < sheet.getRows(); i++) {
	    	di.patient_id = Integer.parseInt(sheet.getCell(0, i).getContents());
	    	di.visit_id = Integer.parseInt(sheet.getCell(1, i).getContents());
	    	di.diagnosis_type = sheet.getCell(2, i).getContents();
	    	di.diagnosis_no = Integer.parseInt(sheet.getCell(3, i).getContents());
	    	di.diagnosis_desc = sheet.getCell(4, i).getContents();
	    	di.diagnosis_date = sheet.getCell(5, i).getContents();
	    	di.treat_days = Integer.parseInt(sheet.getCell(6, i).getContents());
	    	di.treat_result = sheet.getCell(7, i).getContents();
	    	di.oper_treat_indicator = sheet.getCell(8, i).getContents();
	    	di.code_version = sheet.getCell(9, i).getContents();
	    	di.insert();
	    }
	}
	
	public static void SymptonInfo() throws IOException, BiffException {
		String file = path + "糖尿病数据库_生命体征记录表(2468).xls";
		File inputWorkbook = new File(file);
		Workbook w;
		
		w = Workbook.getWorkbook(inputWorkbook);
	    Sheet sheet = w.getSheet(0);
	    
	    SymptomInfo si = new SymptomInfo();
	    try {
			si.connect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    for (int i = 1; i < sheet.getRows(); i++) {
	    	si.patient_id = Integer.parseInt(sheet.getCell(0, i).getContents());
	    	si.visit_id = Integer.parseInt(sheet.getCell(1, i).getContents());
	    	si.recording_date = sheet.getCell(2, i).getContents();
	    	si.time_point = sheet.getCell(3, i).getContents();
	    	si.vital_signs = sheet.getCell(4, i).getContents();
	    	si.vital_signs_values = Float.parseFloat(
	    			sheet.getCell(5, i).getContents());
	    	si.units = sheet.getCell(6, i).getContents();
	    	si.insert();
	    }
	}
}
