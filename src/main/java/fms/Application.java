package fms;

import fms.presentation.view.TwigCustomFunctions;
import org.jtwig.Environment;
import org.jtwig.mvc.JtwigViewResolver;
import org.jtwig.services.api.assets.AssetResolver;
import org.jtwig.services.impl.assets.BaseAssetResolver;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.theme.FixedThemeResolver;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@ComponentScan
@EnableAutoConfiguration
@EnableWebSecurity
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }


    @Configuration
    protected static class ResourceView {


        private static JtwigViewResolver jtwigViewResolver;

        @Bean
        public TwigCustomFunctions userFunctions () {
            return new TwigCustomFunctions();
        }

        @Bean
        public Environment environment() {
            return  new Environment();
        }

        @Bean
        public JtwigViewResolver viewResolver() {
            if (jtwigViewResolver == null) {
                jtwigViewResolver = new JtwigViewResolver();
                jtwigViewResolver.setPrefix("/WEB-INF/views/");
                jtwigViewResolver.setSuffix(".twig");
                jtwigViewResolver.setCache(false); //Nefunguje :(
                jtwigViewResolver.includeFunctions(userFunctions());
            }

            return jtwigViewResolver;
        }

        @Bean
        public AssetResolver assetResolver () {
            BaseAssetResolver assetResolver = new BaseAssetResolver();
            assetResolver.setPrefix("/WEB-INF/public/");
            return assetResolver;
        }

    }

    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().permitAll();
            http.csrf().disable();
        }

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication().withUser("admin").password("admin")
                    .roles("ADMIN", "USER").and().withUser("user").password("user")
                    .roles("USER");
        }

    }
}

