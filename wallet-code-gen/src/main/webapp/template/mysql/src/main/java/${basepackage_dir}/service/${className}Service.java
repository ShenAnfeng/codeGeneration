<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.service;

import java.util.List;

import ${basepackage}.pojo.${className};
import ${basepackage}.pojo.${className}Page;
import ${basepackage}.pojo.${className}Query;

public interface ${className}Service {

	/**
	 * 保存 ${className}
	 * @param ${classNameLower}
	 * @return
	 */
	public boolean save${className}(${className} ${classNameLower});

	/**
	 * 修改 ${className}
	 * @param ${classNameLower}
	 * @return
	 */
	public boolean update${className}(${className} ${classNameLower});
	
	/**
	 * 修改 ${className}Selective(只修改不为null的数据)
	 * @param ${classNameLower}
	 * @return
	 */
	public boolean update${className}Selective(${className} ${classNameLower});

	/**
	 * 新增 ${className}
	 * @param ${classNameLower}
	 * @return
	 */
	public boolean insert${className}(${className} ${classNameLower});
	
	/**
	 * 新增 ${className}Selective（只插入不为null的字段，不影响有默认值的字段）
	 * @param ${classNameLower}
	 * @return
	 */
	public boolean insert${className}Selective(${className} ${classNameLower});
	
	/**
	 * 批量 新增${className}
	 * @param ${classNameLower}
	 * @return
	 */
	public boolean insert${className}Batch(List<${className}> list);

	/**
	 * 根据ID获取 ${className}
	 * @param id
	 * @return
	 */
	public ${className} find${className}ById(int id);
	
	/**
	 * 根据条件获取 ${className}
	 * @param query
	 * @return
	 */
	public ${className} find${className}ByQueryContion(${className}Query query);

	/**
	 * 根据条件获取 ${className}List
	 * @param query
	 * @return
	 */
	public List<${className}> query${className}List(${className}Query query);

	/**
	 * 根据ID获取 ${className}
	 * @param id
	 * @return
	 */
	public boolean delete${className}ById(int id);

	/**
	 * 根据条件删除 ${className}
	 * @param query
	 * @return
	 */
	public boolean delete${className}(${className}Query query);

	/**
	 * 根据条件分页获取 ${className}List
	 * @param query
	 * @return
	 */
	public ${className}Page query${className}PageList(${className}Query query);

}
