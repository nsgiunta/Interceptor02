package co.develhope.Interceptor02.interceptors;

import co.develhope.Interceptor02.entities.Month;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class MonthInterceptor implements HandlerInterceptor {

    private static List<Month> months = new ArrayList<>(Arrays.asList(
            new Month(1, "january", "gennaio", "enero"),
            new Month(2, "february", "febbraio", "febrero"),
            new Month(3, "march", "marzo", "marzo"),
            new Month(4, "april", "aprile", "abril"),
            new Month(5, "may", "maggio", "mayo"),
            new Month(6, "june", "giugno", "junio")
    ));

    //Quando vado su postman non mi carica nessun mese
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String monthString = request.getHeader("monthNumber");
        if(monthString == null || monthString.isEmpty()){
            response.setStatus(400);
            return true;
        }
        long monthsNumbers = Long.parseLong(monthString);
        Optional<Month> month = months.stream().filter(singleMonth -> {
            return singleMonth.getMonthNumber() == monthsNumbers;
        }).findAny();
        if(month.isPresent()){
            request.setAttribute("MonthInterceptor-month", month.get());
        }else {
            response.setStatus(200);
        }
        if (month.isEmpty()){
        request.setAttribute("MonthInterceptor-month", new Month(0, "nope", "nope", "nope"));
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
