package com.in28minutes.rest.webservices.restfulwebservices.thymeleaf;

import java.util.List;

public class AjaxResponseBody {
    String msg;
    List<Team> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Team> getResult() {
        return result;
    }

    public void setResult(List<Team> result) {
        this.result = result;
    }
}
