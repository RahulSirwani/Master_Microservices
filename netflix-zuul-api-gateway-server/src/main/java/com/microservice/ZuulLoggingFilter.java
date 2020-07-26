package com.microservice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulLoggingFilter extends ZuulFilter
{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 *shouldFilter
	 *
	 *should this filter be executed or not.
	 *we can implement Business logics on the 
	 *request and decide we want to execute the filter or not
	 */
	@Override
	public boolean shouldFilter() 
	{
		return true;
	}

	/**
	 *run()
	 *
	 *Real logic of Interception written here
	 */
	@Override
	
	public Object run() throws ZuulException
	{
		HttpServletRequest request = 
				RequestContext.getCurrentContext().getRequest();
		logger.info("request ->{} request uri ->{}",
				request , request.getRequestURI());
		return null;
	}

	
	/**
	 *filterType
	 *
	 *It decide when should the filtering happen
	 *should filtering be happen before the request is executed or
	 *should filtering be happen after the request is executed  or
	 *you want to filter only error request that cause an exception
	 */
	@Override
	public String filterType() 
	{
		return "pre";
	}

	/**
	 *filterOrder
	 *
	 *If you have Multiple Filters like :
	 *ZuulFilter  ,  ZuulSecurityFilter etc
	 *then we can set priority order of filters
	 */
	@Override
	public int filterOrder() 
	{
		return 1;
	}
	
}
