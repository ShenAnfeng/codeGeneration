<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.pojo;

import java.io.Serializable;

/**
 * ${table.tableAlias}
 * @author code-generator
 */
public class ${className} implements Serializable{
	private static final long serialVersionUID = 1L;
	
<#list table.columns as column>
<#if column.columnNameFirstLower == "id">
	/** 主键 */
	private String ${column.columnNameFirstLower};
<#else>
	/** ${column.remarks} */
	private ${column.javaType} ${column.columnNameFirstLower};
</#if>
</#list>

<#list table.columns as column>
<#if column.columnNameFirstLower == "id">
	public void set${column.columnName}(String ${column.columnNameFirstLower}) {
		this.${column.columnNameFirstLower} = ${column.columnNameFirstLower};
	}

	public String get${column.columnName}() {
		return this.${column.columnNameFirstLower};
	}
<#else>

	/** ${column.remarks} */
	public void set${column.columnName}(${column.javaType} ${column.columnNameFirstLower}) {
		this.${column.columnNameFirstLower} = ${column.columnNameFirstLower};
	}

	/** ${column.remarks} */
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameFirstLower};
	}
</#if>
</#list>
}