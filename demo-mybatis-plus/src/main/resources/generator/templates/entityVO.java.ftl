package ${package.Entity};

<#list table.importPackages as pkg>
import ${pkg};
</#list>
<#if springdoc>
import io.swagger.v3.oas.annotations.media.Schema;
<#elseif swagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
import lombok.Getter;
import lombok.Setter;
    <#if chainModel>
import lombok.experimental.Accessors;
    </#if>
</#if>

/**
* <p>
* ${table.comment!} VO
* </p>
*
* @author ${author}
* @since ${date}
*/
<#if entityLombokModel>
@Getter
@Setter
    <#if chainModel>
@Accessors(chain = true)
    </#if>
</#if>
<#if springdoc>
@Schema(name = "${entity}", description = "${table.comment!}")
<#elseif swagger>
@ApiModel(value = "${entity}对象", description = "${table.comment!}")
</#if>
public class ${entity}VO implements Serializable {

    private static final long serialVersionUID = 1L;

<#list table.fields as field>
    <#if field.comment!?length gt 0>
        <#if springdoc>
    @Schema(description = "${field.comment}")
        <#elseif swagger>
    @ApiModelProperty("${field.comment}")
        <#else>
    /**
    * ${field.comment}
    */
        </#if>
    </#if>
    private ${field.propertyType} ${field.propertyName};

</#list>
}
