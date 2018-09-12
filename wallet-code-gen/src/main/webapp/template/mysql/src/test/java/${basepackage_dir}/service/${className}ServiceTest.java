<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ${basepackage}.service.${className}Service;
import ${basepackage}.pojo.${className};
import ${basepackage}.pojo.${className}Page;
import ${basepackage}.pojo.${className}Query;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
@WebAppConfiguration
public class ${className}ServiceTest {
	
	@Autowired
	@Qualifier("${classNameLower}Service")
	private ${className}Service ${classNameLower}Service;

	@Test
	public void testQueryPage${className}(){
		${className}Query query = new ${className}Query();
		query.setPageNo(2);
		
		${className}Page result = ${classNameLower}Service.query${className}PageList(query);
		
		System.out.println("page no:" + result.getPagenation().getPageNo() + " page size:" + result.getPagenation().getPageSize() + 
				" total:" + result.getPagenation().getItemCount() + " pageCount:" + result.getPagenation().getPageCount());
		
		System.out.println(result.getValues().size());
	}

	public void testNew${className}() {
		${className} ${className} = new ${className}();
		${classNameLower}Service.save${className}(${className});
	}


	public void testUpdate${className}(){
		${className} ${className} = new ${className}();
		${className}.setId("21");
	}
	

	public void testDelete${className}(){
		${classNameLower}Service.delete${className}ById("11");
	}
	

	public void testDelete${className}ByQuery(){
		${className}Query query = new ${className}Query();
		${classNameLower}Service.delete${className}(query);
	}


	public void testFind${className}ById(){
		${className}Query query = new ${className}Query();
		int size = ${classNameLower}Service.query${className}List(query).size();
		System.out.println(size);
	}
}
