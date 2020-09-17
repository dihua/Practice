package desginpattern.chainpattern.demo;

/**
 * @author dihua.wu
 * @description
 * @create 2020/9/17
 */
public class HtmlFilter implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        request.setRequestStr(request.getRequestStr()
                .replace("<","[")
                .replace(">","]"));
        System.out.println("在HtmlFilter中处理request");
        filterChain.doFilter(request, response, filterChain);
        System.out.println("在HtmlFilter中处理response");
    }
}
