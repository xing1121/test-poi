package com.wdx.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.wdx.domain.Cat;
import com.sf.wdx.domain.Person;
import com.sf.wdx.poi2003.POIUtils2003;
import com.sf.wdx.utils.DateUtils;

/**
 * 描述：测试POI
 * @author 80002888
 * @date   2018年9月10日
 */
public class TestPOI2003 {

	private static Logger logger = LoggerFactory.getLogger(TestPOI2003.class);
	
	private static String DATE_PATTERN = "yyyy-MM-dd";
	
	private static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	private static final String FILE_PATH = "D:/user/80002888/desktop/poi.xls";
	
	/**
	 * 测试导出大量数据
	 *	@ReturnType	void 
	 *	@Date	2018年9月11日	下午4:19:48
	 *  @Param
	 */
	@Test
	public void testExport3(){
		long start = System.currentTimeMillis();
		
		try {
			int size = 20000;
			System.out.println("数据大小：" + size);
			// 构造集合
			List<Cat> cats = new ArrayList<>(size);
			for (int i = 0; i < size; i++) {
				cats.add(new Cat((long)i, "汤姆" + i, 12.53F + i, '公'));
			}
			cats.add(new Cat(1L, "汤姆", 12.53F, '公'));
			cats.add(new Cat(2L, "兔子", 12F, '母'));
			cats.add(new Cat(3L, "大象", 153.000F, '公'));
			// 标题
			List<String> headers = new ArrayList<>();
			headers.add("ID");
			headers.add("猫的名");
			headers.add("重量");
			headers.add("SEX");
			POIUtils2003.list2Out(headers, cats, new FileOutputStream(new File(FILE_PATH)));
		} catch (Exception e) {
			logger.error("get error->", e);
		}
		
		long end = System.currentTimeMillis();
		System.out.println("耗时：" + (end - start)/1000 + "s");
	}
	
	/**
	 * 测试导入2
	 *	@ReturnType	void 
	 *	@Date	2018年9月11日	下午4:24:34
	 *  @Param
	 */
	@Test
	public void testImport2(){
		try {
			// xlsx中每一列顺序对应实体的属性名
			List<String> fieldNames = new ArrayList<>();
			fieldNames.add("id");
			fieldNames.add("name");
			fieldNames.add("weight");
			fieldNames.add("sex");
			List<Cat> list = POIUtils2003.input2List(fieldNames, Cat.class, new FileInputStream(new File(FILE_PATH)));
			list.forEach(System.out::println);
		} catch (Exception e) {
			logger.error("get error->", e);
		}
	}
	
	/**
	 * 测试导出2
	 *	@ReturnType	void 
	 *	@Date	2018年9月11日	下午4:19:48
	 *  @Param
	 */
	@Test
	public void testExport2(){
		try {
			// 构造集合
			List<Cat> cats = new ArrayList<>();
			cats.add(new Cat(1L, "汤姆", 12.53F, '公'));
			cats.add(new Cat(2L, "兔子", 12F, '母'));
			cats.add(new Cat(3L, "大象", 153.000F, '公'));
			// 标题
			List<String> headers = new ArrayList<>();
			headers.add("ID");
			headers.add("猫的名");
			headers.add("重量");
			headers.add("SEX");
			POIUtils2003.list2Out(headers, cats, new FileOutputStream(new File(FILE_PATH)));
			// 转为数组输出
			byte[] bs = POIUtils2003.list2Bytes(headers, cats);
			System.out.println(bs.length);
		} catch (Exception e) {
			logger.error("get error->", e);
		}
	}
	
	/**
	 * 测试导入
	 *	@ReturnType	void 
	 *	@Date	2018年9月10日	下午5:48:12
	 *  @Param
	 */
	@Test
	public void testImport1(){
		try {
			// xlsx中每一列顺序对应实体的属性名
			List<String> fieldNames = new ArrayList<>();
			fieldNames.add("id");
			fieldNames.add("name");
			fieldNames.add("age");
			fieldNames.add("birthDay");
			fieldNames.add("birthDayTime");
			fieldNames.add("workStatus");
			fieldNames.add("salary");
			List<Person> list = POIUtils2003.input2List(fieldNames, Person.class, new FileInputStream(new File(FILE_PATH)));
			list.forEach(System.out::println);
		} catch (Exception e) {
			logger.error("get error->", e);
		}
	}
	
	/**
	 * 测试导出
	 *	@ReturnType	void 
	 *	@Date	2018年9月10日	下午5:48:19
	 *  @Param
	 */
	@Test
	public void testExport1() {
		try {
			// 构造集合
			List<Person> persons = new ArrayList<>();
			Person p1 = new Person(null, "张三", 18000, DateUtils.str2Date("1998-08-25", DATE_PATTERN), 
					DateUtils.str2Date("1998-08-25 14:22:55", DATE_TIME_PATTERN), true, 5555.55);
			Person p2 = new Person(2L, null, 20000, DateUtils.str2Date("1997-05-08", DATE_PATTERN), 
					DateUtils.str2Date("1997-05-08 08:19:57", DATE_TIME_PATTERN), false, 8866.88);
			Person p3 = new Person(3L, "王五", null, DateUtils.str2Date("2000-03-25", DATE_PATTERN), 
					DateUtils.str2Date("2000-03-25 09:39:45", DATE_TIME_PATTERN), true, 3850.0);
			Person p4 = new Person(4L, "赵六", 33000, null, 
					DateUtils.str2Date("2018-12-15 16:10:33", DATE_TIME_PATTERN), true, 9888.15);
			Person p5 = new Person(5L, "田七", 37000, DateUtils.str2Date("2016-01-21", DATE_PATTERN), 
					DateUtils.str2Date("2016-01-21 21:39:15", DATE_TIME_PATTERN), null, 12255.01);
			Person p6 = new Person(6L, "康八", 87000, DateUtils.str2Date("2008-11-30", DATE_PATTERN), 
					DateUtils.str2Date("2008-11-30 05:55:28", DATE_TIME_PATTERN), true, null);
			persons.add(p1);
			persons.add(p2);
			persons.add(p3);
			persons.add(p4);
			persons.add(p5);
			persons.add(p6);
			// 标题
			List<String> headers = new ArrayList<>();
			headers.add("ID");
			headers.add("姓名");
			headers.add("年龄");
			headers.add("出生日期");
			headers.add("生日");
			headers.add("是否有工作");
			headers.add("工资");
			// 转为文件输出
			POIUtils2003.list2Out(headers, persons, new FileOutputStream(new File(FILE_PATH)));
			// 转为数组输出
			byte[] bs = POIUtils2003.list2Bytes(headers, persons);
			System.out.println(bs.length);
		} catch (Exception e) {
			logger.error("get error->", e);
		}
	}
	
}
