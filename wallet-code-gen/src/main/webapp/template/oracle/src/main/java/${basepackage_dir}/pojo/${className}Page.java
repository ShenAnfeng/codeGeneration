<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.pojo;

import com.rkylin.wallet.result.PageListResult;

/**
 * ${table.tableAlias}
 * @author code-generator
 */
public class ${className}Page  extends PageListResult<${className}> {

	private static final long serialVersionUID = 1L;
	
}