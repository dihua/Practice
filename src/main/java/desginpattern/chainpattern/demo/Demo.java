package desginpattern.chainpattern.demo;

import java.util.AbstractMap;

/**
 * @author dihua.wu
 * @description
 * @create 2020/9/17
 */
public class Demo {

    public static void main(String[] args) {
        String msg = "大家好 :), <script>haha</script> 我要说超级敏感的话";
        System.out.println(msg);
        Request request = new Request();
        request.setRequestStr(msg);

        Response response = new Response();

        FilterChain filterChain = new FilterChain();
        HtmlFilter htmlFilter = new HtmlFilter();
        SensitiveFilter sensitiveFilter = new SensitiveFilter();
        ExpressionFilter expressionFilter = new ExpressionFilter();
        filterChain.add(htmlFilter);
        filterChain.add(sensitiveFilter);
        filterChain.add(expressionFilter);

        filterChain.doFilter(request, response, filterChain);
        System.out.println(request.getRequestStr());
    }
}
