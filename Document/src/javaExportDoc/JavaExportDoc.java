package javaExportDoc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class JavaExportDoc {
	private Configuration configuration = null;

	public JavaExportDoc() {
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
	}

	// 2个参数分别是：模板的名称，导出文件的路径
	public void createDoc(String modelName, String exportFilePath) {

		Template t = null;
		// 1、导入模板
		configuration.setClassForTemplateLoading(this.getClass(), "/modelPath");

		try {
			// test.ftl为要装载的模板
			t = configuration.getTemplate(modelName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 2、打包数据--每次导出的word文件模板不同，打包数据的方法要单独写
		Map<String, Object> dataMap = new HashMap<String, Object>();
		getData(dataMap);

		// 3、导出文件
		// 输出文档路径及名称
		File outFile = new File(exportFilePath);
		Writer out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			t.process(dataMap, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//把数据打包成map;dataMap里存放的数据Key值要与模板中的参数相对应
	private void getData(Map<String, Object> dataMap) {
		dataMap.put("company", "张三12");//说明：模板文件中有${company}与之对应
		dataMap.put("year", "2017");
		dataMap.put("itemNo", "0401");
		dataMap.put("yjyear", "2017");
		dataMap.put("yjmonth", "04");
		dataMap.put("yjday", "01");
		dataMap.put("yjhour", "12");
		dataMap.put("cpmc", "2017");
		dataMap.put("ggxh", "2017");
		dataMap.put("zxlb", "2017");

		

	}

	public static void main(String[] args) {
		JavaExportDoc dh = new JavaExportDoc();
		String filePath = "G:/outFile.doc";//导出doc文件的路径
		String modelName = "test.xml";//模板名称
		dh.createDoc(modelName, filePath);
		System.out.println("	导出成功");
	}

}
