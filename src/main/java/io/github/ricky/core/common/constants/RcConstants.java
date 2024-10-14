package io.github.ricky.core.common.constants;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/27
 * @className RcConstants
 * @desc 项目常量
 */
public interface RcConstants {

    String CHINA_TIME_ZONE = "Asia/Shanghai";
    String AUTH_COOKIE_NAME = "rctoken";
    String AUTHORIZATION = "Authorization";

    String RELIC_COLLECTION = "relics";
    String POLYNOMIAL_EXPRESSION_COLLECTION = "polynomial_expressions";
    String EVENT_COLLECTION = "events";

    String REDIS_DOMAIN_EVENT_CONSUMER_GROUP = "domain.event.group";
    String REDIS_WEBHOOK_CONSUMER_GROUP = "webhook.group";
    String REDIS_NOTIFICATION_CONSUMER_GROUP = "notification.group";

}
