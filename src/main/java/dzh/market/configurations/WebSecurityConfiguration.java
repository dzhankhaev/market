package dzh.market.configurations;

import dzh.market.BaseRoutes;
import dzh.market.filter.AuthUserRedirectFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration
{
    @Autowired
    private AuthUserRedirectFilter authUserRedirectFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .addFilterAfter(authUserRedirectFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((authz) -> {
                    try
                    {
                        authz
                                .requestMatchers(BaseRoutes.ADMIN + "/**").hasRole("ADMIN")
                                .requestMatchers(BaseRoutes.USER + "/**").hasRole("USER")
                                .requestMatchers(BaseRoutes.ROOT + "**").permitAll()
                                .and()
                                .formLogin()
                                .loginPage(BaseRoutes.LOGIN)
                                .defaultSuccessUrl(BaseRoutes.ROOT)
                                .permitAll()
                                .and()
                                .logout().permitAll().logoutSuccessUrl(BaseRoutes.ROOT);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                });
        return httpSecurity.build();
    }
}
