package desginpattern.chainpattern.demo;

/**
 * @author dihua.wu
 * @description
 * @create 2020/9/17
 */
public class ExpressionFilter implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        request.setRequestStr(request.getRequestStr().replace(":)", "^_^"));
        System.out.println("在ExpressionFilter中处理request");
        filterChain.doFilter(request, response, filterChain);
        System.out.println("在ExpressionFilter中处理response");
    }
}
