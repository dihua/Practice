package desginpattern.chainpattern.demo;

/**
 * @author dihua.wu
 * @description
 * @create 2020/9/17
 */
public class SensitiveFilter implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        request.setRequestStr(request.getRequestStr().replace("敏感", "正常"));
        System.out.println("在SensitiveFilter中处理request");
        filterChain.doFilter(request, response, filterChain);
        System.out.println("在SensitiveFilter中处理response");
    }
}
