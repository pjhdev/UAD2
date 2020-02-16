/*
 * @USER zkdlwnfm
 * @DATE 16/02/2020
 */
package com.uad2.application.exception;

import ch.qos.logback.classic.LoggerContext;
import io.sentry.Sentry;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@link HandlerExceptionResolver} implementation that will record any exception that a
 * Spring {@link org.springframework.web.servlet.mvc.Controller} throws to Sentry. It then
 * returns null, which will let the other (default or custom) exception resolvers handle
 * the actual error.
 */
public class SentryExceptionResolver implements HandlerExceptionResolver, Ordered {

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse res, Object o, Exception e) {

        Sentry.init("http://4f74bcd0757442efadc1cc5f7f373018@pjhdev.com:8090/2"); //여기서 센트리를 초기화시킨다.
        LoggerContext context = (LoggerContext)LoggerFactory.getILoggerFactory();
        context.putProperty("environment", "development");
        Sentry.capture(e); //여기서 Exception을 센트리로 보내버린다.

        return null;
    }
}
