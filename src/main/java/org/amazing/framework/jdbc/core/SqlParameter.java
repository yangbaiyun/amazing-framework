package org.amazing.framework.jdbc.core;

/**
 * Created by john on 2017/9/25.
 */
public class SqlParameter {

    private String name;
    private int type;

    public SqlParameter(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public SqlParameter(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }


    public int getType() {
        return type;
    }


}
