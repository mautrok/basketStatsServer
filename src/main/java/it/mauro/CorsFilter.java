package it.mauro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CorsFilter implements Filter {
 
   public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
       HttpServletResponse response = (HttpServletResponse) res;
       HttpServletRequest request=(HttpServletRequest) req;
       response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
       response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
       response.setHeader("Access-Control-Allow-Credentials", "true");
       response.setHeader("Access-Control-Max-Age", "3600");
       response.setHeader("Access-Control-Allow-Headers", "x-api-version, x-requested-with, authorization");
       response.setHeader("Strict-Transport-Security", "max-age=0; includeSubDomains");
       if(request.getMethod().equals("OPTIONS")) {
           response.setHeader("Access-Control-Allow-Headers", "x-api-version, content-type, authorization");
           return;
       }
       chain.doFilter(req, res);
   }
 
   public void init(FilterConfig filterConfig) {}
 
   public void destroy() {}
 
}