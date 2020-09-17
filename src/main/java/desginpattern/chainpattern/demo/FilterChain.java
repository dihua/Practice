package desginpattern.chainpattern.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dihua.wu
 * @description
 * @create 2020/9/17
 */
public class FilterChain implements Filter {

    private List<Filter> list = new ArrayList<>();

    private int index = 0;

    public FilterChain add(Filter filter) {
        list.add(filter);
        return this;
    }


    /**
     * ***关键***
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        if (index == list.size()) {
            return;
        }
        Filter f = list.get(index);
        index++;
        f.doFilter(request, response, filterChain);
    }
}
