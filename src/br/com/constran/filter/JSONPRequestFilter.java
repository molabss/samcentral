package br.com.constran.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter("/JSONPRequestFilter")
public class JSONPRequestFilter implements Filter  {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		
		if (!(request instanceof HttpServletRequest))
		       throw new ServletException("This filter can only process HttpServletRequest requests");
	
	    HttpServletRequest httpRequest = (HttpServletRequest)request;
	      
	    if(isJSONPRequest(httpRequest))
	    {
	      ServletOutputStream out = response.getOutputStream();
	      out.println(getCallbackMethod(httpRequest) + "(");
	      chain.doFilter(request, response);
	      out.println(");");
	      
	      response.setContentType("text/javascript;charset=UTF-8");
	      
	      out.close();
	      
	    }
	    else
	    {
	    	
	    	chain.doFilter(request, response);
	    }
	}

	private String getCallbackMethod(HttpServletRequest httpRequest){
    
	  return httpRequest.getParameter("callback");
	}
	  
	  
	private boolean isJSONPRequest(HttpServletRequest httpRequest){
	  
		String callbackMethod = getCallbackMethod(httpRequest);
	    return (callbackMethod != null && callbackMethod.length() > 0);
	}
	
	
		
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
			
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	  // TODO Auto-generated method stub
			
	}

}
