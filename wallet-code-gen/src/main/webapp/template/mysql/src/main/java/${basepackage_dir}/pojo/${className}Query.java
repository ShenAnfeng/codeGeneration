<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.pojo;

import org.apache.commons.lang3.StringUtils;
import com.hrsf.query.PagingQuery;

/**
 * ${table.tableAlias}Query
 * @author code-generator
 */
public class ${className}Query extends PagingQuery {
	private static final long serialVersionUID = 1L;

	public ${className}Query(){
		super(1, 10);
	}

	public ${className}Query(int pageNo, int pageSize){
		super(pageNo, pageSize);
	}

<#list table.columns as column>
<#if !column.isQueryReserve>
	/** ${column.remarks} */
	private ${column.javaType} ${column.columnNameFirstLower};
</#if>
</#list>

	/** 计算总记录数 */
	public int calcItemCount(Object t) {
		return 0;
	}

<#list table.columns as column>
<#if !column.isQueryReserve>
	/** ${column.remarks} */
	public void set${column.columnName}(${column.javaType} ${column.columnNameFirstLower}) {
<#if column.javaType == "java.lang.String">
		if (StringUtils.isNotBlank(${column.columnNameFirstLower})) {
			this.${column.columnNameFirstLower} = ${column.columnNameFirstLower};
		}
<#else>
		this.${column.columnNameFirstLower} = ${column.columnNameFirstLower};
</#if>
	}
	
	/** ${column.remarks} */
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameFirstLower};
	}
</#if>
</#list>
	

}