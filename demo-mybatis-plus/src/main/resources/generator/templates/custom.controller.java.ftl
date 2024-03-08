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
