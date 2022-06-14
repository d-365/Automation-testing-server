/*
 * author     : dujun
 * date       : 2022/6/10 17:50
 * description: 告诉大家我是干啥的
 */

package Test;

import com.dujun.springboot.SpringbootApplication;
import com.dujun.springboot.entity.Role;
import com.dujun.springboot.service.impl.RoleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = SpringbootApplication.class)
@RunWith(SpringRunner.class)
public class Test_temp {

    @Autowired
    RoleServiceImpl roleService;

    @Test
    public void Test1(){
        Role role = new Role();
        List<String> permissionUrl = new ArrayList<>();
        permissionUrl.add("/interface");
        permissionUrl.add("/interface/list");
        role.setRoleName("test");
        role.setPermissionUrl(permissionUrl);
        roleService.addRole(role);
    }

}
