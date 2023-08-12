package ecnu.edu.yjsy.conf;

/**
 * @date 2023/8/12  00:57
 */
//@ConfigurationProperties(prefix = "aop.log")
public class LogProperties {

    private String basedir;

    private String errDir;

    private String warnDir;

    private String debugDir;

    private boolean enableAopLog;

}
