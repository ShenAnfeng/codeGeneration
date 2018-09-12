<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import ${basepackage}.dao.${className}Dao;
import ${basepackage}.pojo.${className};
import ${basepackage}.pojo.${className}Query;
import com.rkylin.wallet.wps.dao.BaseDao;

@Repository("${classNameLower}Dao")
public class ${className}Dao extends BaseDao {

	public int countByExample(${className}Query example) {
		return super.getSqlSession().selectOne("${className}Mapper.countByExample", example);
	}

	public int deleteByExample(${className}Query example) {
		return super.getSqlSession().delete("${className}Mapper.deleteByExample", example);
	}

	public int deleteByPrimaryKey(String id) {
		return super.getSqlSession().delete("${className}Mapper.deleteByPrimaryKey", id);
	}

	public int insert(${className} record) {
		return super.getSqlSession().insert("${className}Mapper.insert", record);
	}

	public int insertSelective(${className} record) {
		return super.getSqlSession().insert("${className}Mapper.insertSelective", record);
	}
	
	public int batchInsert(List<${className}> list) {
		return super.batchInsert("${className}Mapper.insertSelective", list);
	}

	public List<${className}> selectByExample(${className}Query example) {
		return super.getSqlSession().selectList("${className}Mapper.selectByExample", example);
	}

	public List<${className}> selectPageByExample(${className}Query example) {
		return super.getSqlSession().selectList("${className}Mapper.selectPageByExample", example);
	}

	public ${className} selectByPrimaryKey(String id) {
		return super.getSqlSession().selectOne("${className}Mapper.selectByPrimaryKey", id);
	}

	public int updateByPrimaryKeySelective(${className} record) {
		return super.getSqlSession().update("${className}Mapper.updateByPrimaryKeySelective", record);
	}

	public int updateByPrimaryKey(${className} record) {
		return super.getSqlSession().update("${className}Mapper.updateByPrimaryKey", record);
	}

}
