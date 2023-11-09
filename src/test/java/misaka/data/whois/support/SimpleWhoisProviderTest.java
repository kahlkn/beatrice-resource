package misaka.data.whois.support;

import artoria.data.json.JsonUtils;
import artoria.data.json.support.FastJsonHandler;
import com.alibaba.fastjson.JSON;
import misaka.data.whois.WhoisObject;
import misaka.data.whois.WhoisProvider;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Boolean.TRUE;

@Ignore
public class SimpleWhoisProviderTest {
    private static Logger log = LoggerFactory.getLogger(SimpleWhoisProviderTest.class);
    private WhoisProvider whoisProvider = new SimpleWhoisProvider();

    @Test
    public void test1() {
        JsonUtils.registerHandler("default", new FastJsonHandler());
        WhoisObject whoisObject = whoisProvider.findByDomainName("aaaa.com");
        log.info("{}", JSON.toJSONString(whoisObject, TRUE));
        log.info("{}", whoisObject.rawData());
    }

}