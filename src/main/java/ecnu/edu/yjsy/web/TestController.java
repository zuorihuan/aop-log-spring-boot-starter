package ecnu.edu.yjsy.web;

import ecnu.edu.yjsy.annotations.AopLog;
import ecnu.edu.yjsy.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date 2023/8/12  23:54
 */
@RestController
public class TestController {

    private final static Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private TestService testService;

    @GetMapping("/test")
    @AopLog(enableTimeRecord = true)
    public void say(@RequestParam(value = "i", defaultValue = "10") int i){
        System.out.println("say......");
        testService.say();
    }
}
