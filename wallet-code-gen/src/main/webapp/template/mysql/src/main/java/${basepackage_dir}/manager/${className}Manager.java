<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ${basepackage}.dao.${className}Dao;
import ${basepackage}.manager.${className}Manager;
import ${basepackage}.pojo.${className};
import ${basepackage}.pojo.${className}Page;
import ${basepackage}.pojo.${className}Query;
import com.hrsf.paging.Pagenation;

@Component("${classNameLower}Manager")
public class ${className}Manager {

	@Autowired
	@Qualifier("${classNameLower}Dao")
	private ${className}Dao ${classNameLower}Dao;

	public ${className}Dao getDao() {
		return this.${classNameLower}Dao;
	}

	public int save(${className} ${classNameLower}) {
		int cnt = 0;
		if (${classNameLower}.getId() == null) {
			cnt = ${classNameLower}Dao.insertSelective(${classNameLower});
		} else {
			cnt = ${classNameLower}Dao.updateByPrimaryKeySelective(${classNameLower});
		}
		return cnt;
	}
	
	public int update(${className} ${classNameLower}) {
		return ${classNameLower}Dao.updateByPrimaryKey(${classNameLower});
	}
	
	public int updateSelective(${className} ${classNameLower}) {
		return ${classNameLower}Dao.updateByPrimaryKeySelective(${classNameLower});
	}
	
	public int insert(${className} ${classNameLower}) {
		return ${classNameLower}Dao.insert(${classNameLower});
	}
	
	public int insertSelective(${className} ${classNameLower}) {
		return ${classNameLower}Dao.insertSelective(${classNameLower});
	}
	
	public int batchInsert(List<${className}> list){
		return ${classNameLower}Dao.batchInsert(list);
	}

	public ${className} findBy_id(int id) {
		return ${classNameLower}Dao.selectByPrimaryKey(id);
	}
	
	public ${className} findByQueryContion(${className}Query query) {
		List<${className}> list = ${classNameLower}Dao.selectByExample(query);
		if(null!=list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public List<${className}> queryList(${className}Query query) {
		return ${classNameLower}Dao.selectByExample(query);
	}

	public int deleteBy_id(int id) {
		return ${classNameLower}Dao.deleteByPrimaryKey(id);
	}

	public int delete(${className}Query query) {
		return ${classNameLower}Dao.deleteByExample(query);
	}

	public ${className}Page queryPageList(${className}Query query) {
		${className}Page ${classNameLower}Page = new ${className}Page();
		Integer itemCount = ${classNameLower}Dao.countByExample(query);
		query.setItemCount(itemCount);

		if (itemCount == 0) {
			${classNameLower}Page.setValues(null);
		} else {
			${classNameLower}Page.setValues(${classNameLower}Dao.selectPageByExample(query));
		}

		${classNameLower}Page.setPagenation(new Pagenation(query.getPageNo(), query.getPageSize(), query.getItemCount()));
		return ${classNameLower}Page;
	}

}
