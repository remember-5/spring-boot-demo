package com.remember.dynamic.datasource.sqlite;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.remember.dynamic.datasource.SpringBootDemoDynamicDatasourceApplication;
import com.remember.dynamic.datasource.mybatisplus.entity.TMenu;
import com.remember.dynamic.datasource.mybatisplus.service.impl.TMenuServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjiahao
 * @date 2023/7/27 21:54
 */
@Slf4j
@DS("sqlite")
@SpringBootTest(classes = {SpringBootDemoDynamicDatasourceApplication.class})
public class MenuTest {

    @Autowired
    TMenuServiceImpl tMenuService;

    @Test
    @DS("sqlite")
    public void testQueryTree() {
        final List<TMenu> list = tMenuService.lambdaQuery().isNull(TMenu::getParentId).list();
        for (TMenu tMenu : list) {
            List<TMenu> menus = iterateMenus(tMenuService.lambdaQuery().list(), tMenu.getId());
            tMenu.setChildren(menus);
        }
        System.err.println(list);
    }


    private List<TMenu> iterateMenus(List<TMenu> list, Integer pid) {
        final ArrayList<TMenu> result = new ArrayList<>();
        for (TMenu tMenu : list) {
            final Integer id = tMenu.getId();
            final Integer parentId = tMenu.getParentId();
            if (null != parentId) {
                if (parentId.equals(pid)) {
                    // 递归
                    List<TMenu> iterateMenu = iterateMenus(list, id);
                    tMenu.setChildren(iterateMenu);
                    result.add(tMenu);
                }
            }
        }
        return result;
    }

}
