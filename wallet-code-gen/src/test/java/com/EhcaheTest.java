package com;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.manager.pojo.Testtable;
import com.manager.pojo.TesttablePage;
import com.manager.pojo.TesttableQuery;
import com.manager.service.TesttableService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
public class EhcaheTest {

	@Autowired
	private TesttableService testtableService;

	@Test
	public void testBatchSave() {
		try {
			System.out.println("----------------------批量插入开始--------------------------------");
			List<Testtable> list = new ArrayList<Testtable>();
			for (int i = 1; i <= 100; i++) {
				Testtable testtable = new Testtable();
				testtable.setLength(i);
				testtable.setName("郝帅测试--" + i);

				list.add(testtable);
			}
			this.testtableService.insertTesttableBatch(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testQuery() {
		try {
			TesttableQuery query = new TesttableQuery();
			List<Integer> statusList = new ArrayList<Integer>();
			statusList.add(5);
			// query.setName("郝帅-1");
			// List<Testtable> list =
			// testtableService.queryTesttableList(query);
			query.setPageNo(1);
			query.setPageSize(5);
			TesttablePage page = testtableService.queryTesttablePageList(query);
			for (Testtable testtable : page.getValues()) {
				System.out.println(testtable.getName());
			}
			// System.out.println(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSave() {
		try {
			System.out.println("----------------------插入测试开始--------------------------------");
			Testtable testtable = new Testtable();
			testtable.setLength(1);
			testtable.setName("郝帅测试");
			boolean isSaveSuccess = testtableService.saveTesttable(testtable);
			if (isSaveSuccess) {
				System.out.println("----------------------插入测试结束，保存成功--------------------------------");
			} else {
				System.out.println("----------------------插入测试结束，保存失败--------------------------------");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {

		System.out.println("----------------------修改测试开始--------------------------------");
		Testtable testtable = new Testtable();
		testtable.setName("22222222222222");
		testtable.setId("20171017-152434-100000000022");

		boolean isUpdateSuccess = this.testtableService.updateTesttableSelective(testtable);
		if (isUpdateSuccess) {
			System.out.println("----------------------修改测试结束，保存成功--------------------------------");
		} else {
			System.out.println("----------------------修改测试结束，保存失败--------------------------------");

		}
	}

	@Test
	public void testDelete() {

		System.out.println("----------------------删除测试开始--------------------------------");
		TesttableQuery query = new TesttableQuery();
		String id = "20171017-152434-100000000022";
		String transCode = "1";
		query.setName("郝帅-1");

		boolean isUpdateSuccess = this.testtableService.deleteTesttableById(id);
		boolean isUpdateSuccess1 = this.testtableService.deleteTesttable(query);
		if (isUpdateSuccess) {
			System.out.println("----------------------删除测试结束，保存成功--------------------------------");
		} else {
			System.out.println("----------------------删除测试结束，保存失败--------------------------------");

		}
	}

}
