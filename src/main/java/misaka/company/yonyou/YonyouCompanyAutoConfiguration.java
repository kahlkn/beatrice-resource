package misaka.company.yonyou;

import artoria.query.QueryUtils;
import artoria.util.Assert;
import misaka.company.CompanyQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "misaka.company.yonyou.enabled", havingValue = "true")
@EnableConfigurationProperties({YonyouCompanyProperties.class})
public class YonyouCompanyAutoConfiguration {
    private static Logger log = LoggerFactory.getLogger(YonyouCompanyAutoConfiguration.class);

    @Autowired
    public YonyouCompanyAutoConfiguration(YonyouCompanyProperties properties) {
        Assert.notNull(properties, "Parameter \"properties\" must not null. ");
        String baseInfoApiCode = properties.getBaseInfoApiCode();
        String searchApiCode = properties.getSearchApiCode();
        Integer timeout = properties.getTimeout();
        YonyouCompanyQueryHandler queryHandler =
                new YonyouCompanyQueryHandler(baseInfoApiCode, searchApiCode, timeout);
        QueryUtils.registerHandler(CompanyQuery.class, "yonyou", queryHandler);
    }

}