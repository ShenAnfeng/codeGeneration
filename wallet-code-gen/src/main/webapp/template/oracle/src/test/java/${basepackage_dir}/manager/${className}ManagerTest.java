<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ${basepackage}.manager.${className}Manager;
import ${basepackage}.pojo.${className};
import ${basepackage}.pojo.${className}Page;
import ${basepackage}.pojo.${className}Query;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
@WebAppConfiguration
public class ${className}ManagerTest {
	
	@Autowired
	@Qualifier("${classNameLower}Manager")
	private ${className}Manager ${classNameLower}Manager;

	@Test
	public void testQueryPage${className}(){
		${className}Query query = new ${className}Query();
		query.setPageNo(2);
		
		${className}Page result = ${classNameLower}Manager.queryPageList(query);
		
		System.out.println("page no:" + result.getPagenation().getPageNo() + " page size:" + result.getPagenation().getPageSize() + 
				" total:" + result.getPagenation().getItemCount() + " pageCount:" + result.getPagenation().getPageCount());
		
		System.out.println(result.getValues().size());
	}

	public void testNew${className}() {
		${className} ${className} = new ${className}();
		${classNameLower}Manager.save(${className});
	}


	public void testUpdate${className}(){
		${className} ${className} = new ${className}();
		${className}.setId("21");
	}
	

	public void testDelete${className}(){
		${classNameLower}Manager.deleteBy_id("21");
	}
	

	public void testDelete${className}ByQuery(){
		${className}Query query = new ${className}Query();
		${classNameLower}Manager.delete(query);
	}


	public void testFind${className}ById(){
		${className}Query query = new ${className}Query();
		int size = ${classNameLower}Manager.queryList(query).size();
		System.out.println(size);
	}
}
