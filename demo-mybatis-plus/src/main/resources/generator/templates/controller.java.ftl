package ${package.Controller};

import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.remember.common.entity.R;

import java.util.List;
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
    public ${entity} get${entity}(@PathVariable Integer id) {
        return ${lowerEntityName}Service.getById(id);
    }

    @GetMapping
    public List<${entity}> getAll${entity}() {
        return ${lowerEntityName}Service.list();
    }

    @PostMapping
    public void add${entity}(@RequestBody ${entity} ${lowerEntityName}) {
        ${lowerEntityName}Service.save(${lowerEntityName});
    }

    @PutMapping("/{id}")
    public void update${entity}(@PathVariable Integer id, @RequestBody ${entity} ${lowerEntityName}) {
        ${lowerEntityName}.setId(id);
        ${lowerEntityName}Service.updateById(${lowerEntityName});
    }

    @DeleteMapping("/{id}")
    public void delete${entity}(@PathVariable Integer id) {
        ${lowerEntityName}Service.removeById(id);
    }

}
</#if>
