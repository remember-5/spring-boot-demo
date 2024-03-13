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
package ${package.Controller};

import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.remember.common.entity.R;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
@RequiredArgsConstructor
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    private final ${table.serviceName} ${lowerEntityName}Service;

    @GetMapping("/{id}")
    public R<${entity}> get${entity}(@PathVariable Integer id) {
        return R.success(${lowerEntityName}Service.getById(id));
    }

    @GetMapping
    public R<List<${entity}>> getAll${entity}() {
        return R.success(${lowerEntityName}Service.list());
    }

    @PostMapping
    public R<Boolean> add${entity}(@RequestBody ${entity} ${lowerEntityName}) {
        return R.success(${lowerEntityName}Service.save(${lowerEntityName}));
    }

    @PutMapping("/{id}")
    public R<Boolean> update${entity}(@PathVariable Integer id, @RequestBody ${entity} ${lowerEntityName}) {
        ${lowerEntityName}.setId(id);
        return R.success(${lowerEntityName}Service.updateById(${lowerEntityName}));
    }

    @DeleteMapping("/{id}")
    public R<Boolean> delete${entity}(@PathVariable Integer id) {
        return R.success(${lowerEntityName}Service.removeById(id));
    }

}
</#if>
