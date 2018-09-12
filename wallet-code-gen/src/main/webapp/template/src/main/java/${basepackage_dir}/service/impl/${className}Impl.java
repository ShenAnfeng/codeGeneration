<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${basepackage}.manager.${className}Manager;
import ${basepackage}.service.${className}Service;
import ${basepackage}.pojo.${className};
import ${basepackage}.pojo.${className}Page;
import ${basepackage}.pojo.${className}Query;

@Service("${classNameLower}Service")
public class ${className}Impl implements ${className}Service{

	@Autowired
	private ${className}Manager ${classNameLower}Manager;

	@Override
	public boolean save${className}(${className} ${classNameLower}) {
		int cnt = ${classNameLower}Manager.save(${classNameLower});
		if(cnt == 0){
			return false;
		}
		return true;
	}

	@Override
	public boolean update${className}(${className} ${classNameLower}) {
		int cnt = ${classNameLower}Manager.update(${classNameLower});
		if(cnt == 0){
			return false;
		}
		return true;
	}
	
	@Override
	public boolean update${className}Selective(${className} ${classNameLower}) {
		int cnt = ${classNameLower}Manager.updateSelective(${classNameLower});
		if(cnt == 0){
			return false;
		}
		return true;
	}

	@Override
	public boolean insert${className}(${className} ${classNameLower}) {
		int cnt = ${classNameLower}Manager.insert(${classNameLower});
		if(cnt == 0){
			return false;
		}
		return true;
	}
	
	@Override
	public boolean insert${className}Selective(${className} ${classNameLower}) {
		int cnt = ${classNameLower}Manager.insertSelective(${classNameLower});
		if(cnt == 0){
			return false;
		}
		return true;
	}
	
	@Override
	public boolean insert${className}Batch(List<${className}> list) {
		if (list.isEmpty()) {
			return false;
		}
		int cnt = ${classNameLower}Manager.batchInsert(list);

		if (cnt == list.size()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ${className} find${className}ById(String id) {
		return ${classNameLower}Manager.findBy_id(id);
	}
	
	public ${className} find${className}ByQueryContion(${className}Query query) {
		return ${classNameLower}Manager.findByQueryContion(query);
	}

	@Override
	public List<${className}> query${className}List(${className}Query query) {
		return ${classNameLower}Manager.queryList(query);
	}

	@Override
	public boolean delete${className}ById(String id) {
		int cnt = ${classNameLower}Manager.deleteBy_id(id);
		if(cnt == 0){
			return false;
		}
		return true;
	}

	@Override
	public boolean delete${className}(${className}Query query) {
		int cnt = ${classNameLower}Manager.delete(query);
		if(cnt == 0){
			return false;
		}
		return true;
	}

	@Override
	public ${className}Page query${className}PageList(${className}Query query) {
		return ${classNameLower}Manager.queryPageList(query);
	}

}
