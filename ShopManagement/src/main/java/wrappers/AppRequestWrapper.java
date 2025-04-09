package wrappers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class AppRequestWrapper extends HttpServletRequestWrapper {
    public AppRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        System.out.println("Get parameter logger..... key: " + name + ", value: " + value);
        return value;
    }
}
