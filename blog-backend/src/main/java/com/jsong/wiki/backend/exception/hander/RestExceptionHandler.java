package com.jsong.wiki.backend.exception.hander;

import com.jsong.wiki.backend.DataResult;
import com.jsong.wiki.backend.exception.BusinessException;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: Jsong
 * @Date: 2020/3/19 21:40
 * @Description: 异常处理
 * @RestControllerAdvice和@ControllerAdice 相当于@RestController和Controller的区别
 */

@RestControllerAdvice
@Log4j2
public class RestExceptionHandler {
    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        log.info((binder));
    }

    /***
     * 把值绑定到org.springframework.ui.Model;
     * 可以在RequestMapping注解方法可以获取该值
     * @date 2020/3/19 22:32
     * @author Jsong
     * @param model
     * @return void
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "jsong");
    }

    /***
     * 全局的异常
     * @date 2020/3/19 22:21
     * @author Jsong
     * @param e
     * @return com.jsong.wiki.backend.DataResult
     */
    @ExceptionHandler(value = BusinessException.class)
    public <T> DataResult<T> handleException(BusinessException e) {
        log.error("Exception exception:{}", e);
        DataResult<T> dataResult = new DataResult<>(e.getMessageCode(), e.getMessage());
        return dataResult;
    }

//    @ExceptionHandler(value = BusinessException.class)
//    public String handleException(BusinessException e) {
//        log.error("Exception exception:{}", e);
//        return "111";
//    }
}
