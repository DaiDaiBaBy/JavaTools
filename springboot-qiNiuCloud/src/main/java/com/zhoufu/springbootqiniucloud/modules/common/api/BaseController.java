package com.zhoufu.springbootqiniucloud.modules.common.api;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> 基类 </p>
 */
@Slf4j
@RestController
public class BaseController {

    public Logger logger = LoggerFactory.getLogger(getClass());

}
