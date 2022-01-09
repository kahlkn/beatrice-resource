package misaka.tencent.work.wx.robot;

import artoria.robot.MessageRobot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Work WeChat configuration.
 * @author Kahle
 */
@Configuration
@ConditionalOnProperty(name = "misaka.tencent.wx-work.robot.enabled", havingValue = "true")
public class MessageRobotAutoConfiguration {
    private static Logger log = LoggerFactory.getLogger(MessageRobotAutoConfiguration.class);

    @Value("${misaka.tencent.wx-work.robot.key}")
    private String key;

    @Bean
    public MessageRobot wxWorkMessageRobot() {
        MessageRobot messageRobot = new WxWorkMessageRobot(key);
        log.info("Wx-Work robot init success. ");
        return messageRobot;
    }

}