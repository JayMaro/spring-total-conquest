package hello.login.web.interceptor;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    public static final String LOG_ID = "logId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String logId = UUID.randomUUID().toString();

        request.setAttribute(LOG_ID, logId);

        // @RequestMapping: HandlerMethod
        // static resource: ResourceHttpRequestHandler
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler; // 호출할 컨트롤러의 메서드 정보가 모두 들어 있다.
            System.out.println("hm.getMethod() = " + hm.getMethod());
            System.out.println("hm.getBean() = " + hm.getBean());
            for (MethodParameter methodParameter : hm.getMethodParameters()) {
                System.out.println("methodParameter = " + methodParameter.toString());
            }
            System.out.println("hm.getReturnType() = " + hm.getReturnType());
        }

        log.info("REQUEST [{}][{}][{}]", logId, requestURI, handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle LogInterceptor");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        String logId = (String) request.getAttribute(LOG_ID);
        log.info("RESPONSE [{}][{}][{}]", logId, requestURI, handler);
        log.info("afterCompletion LogInterceptor");
        if (ex != null) {
            log.error("afterCompletion error!!", ex);
        }
    }
}
