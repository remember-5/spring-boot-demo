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

    private final ${table.serviceName} ${controllerMappingHyphen}Service;

    @GetMapping("/{id}")
    public R<${entity}> get${entity}(@PathVariable Long id) {
        return R.success(${controllerMappingHyphen}Service.getById(id));
    }

    @GetMapping
    public List<${entity}> getAll${entity}() {
        return R.success(${controllerMappingHyphen}Service.list());
    }

    @PostMapping
    public R<Boolean> add${entity}(@RequestBody ${entity} ${controllerMappingHyphen}) {
        return R.success(${controllerMappingHyphen}Service.save(cms));
    }

    @PutMapping("/{id}")
    public R<Boolean> update${entity}(@PathVariable Long id, @RequestBody ${entity} ${controllerMappingHyphen}) {
        ${controllerMappingHyphen}.setId(id);
        return R.success(${controllerMappingHyphen}Service.updateById(cms));
    }

    @DeleteMapping("/{id}")
    public R<Boolean> delete${entity}(@PathVariable Long id) {
        return R.success(${controllerMappingHyphen}Service.removeById(id));
    }

}
</#if>
