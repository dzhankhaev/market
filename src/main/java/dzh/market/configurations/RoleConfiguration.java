package dzh.market.configurations;

import dzh.market.entity.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RoleConfiguration
{
    @Bean
    public Map<String, Role> userRoleMap() {
        Map<String, Role> roleMap = new HashMap<>();

        roleMap.put("ADMIN_ROLE", new Role(1L, "ADMIN_ROLE"));
        roleMap.put("USER_ROLE", new Role(2L, "USER_ROLE"));

        return roleMap;
    }
}
