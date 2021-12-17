package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import lombok.AllArgsConstructor;
import ${package.Service}.${table.serviceName};
import cn.imadc.application.base.common.response.ResponseW;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import ${package.Parent}.dto.request.${entity}FindReqDTO;
import ${package.Entity}.${entity};

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@AllArgsConstructor
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("${package.ModuleName}")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    private final ${table.serviceName} ${entity?uncap_first}Service;

    /**
     * 查询
     *
     * @param reqDTO 参数
     * @return 结果
     */
    @RequestMapping(value = "find", method = RequestMethod.POST)
    public ResponseW find(@RequestBody ${entity}FindReqDTO reqDTO) {
        return ${entity?uncap_first}Service.find(reqDTO);
    }

    /**
     * 添加
     *
     * @param ${entity?uncap_first} 参数
     * @return 结果
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseW add(@RequestBody ${entity} ${entity?uncap_first}) {
        return ${entity?uncap_first}Service.add(${entity?uncap_first});
    }

    /**
     * 修改
     *
     * @param ${entity?uncap_first} 参数
     * @return 结果
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ResponseW edit(@RequestBody ${entity} ${entity?uncap_first}) {
        return ${entity?uncap_first}Service.edit(${entity?uncap_first});
    }

    /**
     * 删除
     *
     * @param ${entity?uncap_first} 参数
     * @return 结果
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ResponseW delete(@RequestBody ${entity} ${entity?uncap_first}) {
        return ${entity?uncap_first}Service.delete(${entity?uncap_first});
    }
}
</#if>
