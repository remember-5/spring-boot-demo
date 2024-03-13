/**
* Copyright [2022] [remember5]
* <p>
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* <p>
* http://www.apache.org/licenses/LICENSE-2.0
* <p>
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
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
