package org.amazing.framework.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by john on 2017/9/22.
 */
public class Request {

    private String requestMethod;
    private String requestPath;

    public Request( String requestMethod,String requestPath)
    {
        this.requestMethod=requestMethod;
        this.requestPath=requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }


    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }



}
