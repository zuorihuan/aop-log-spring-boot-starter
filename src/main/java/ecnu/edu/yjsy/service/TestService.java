package ecnu.edu.yjsy.service;

/**
 * @date 2023/8/12  23:55
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private final static Logger logger = LoggerFactory.getLogger(TestService.class);


    public void say(){
        System.out.println("service say....");
    }
}
