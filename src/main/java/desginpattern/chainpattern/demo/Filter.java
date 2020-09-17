package desginpattern.chainpattern.demo;

/**
 * @author dihua.wu
 * @description
 * @create 2020/9/17
 */
public interface Filter {

    public void doFilter(Request request, Response response, FilterChain filterChain);

}
