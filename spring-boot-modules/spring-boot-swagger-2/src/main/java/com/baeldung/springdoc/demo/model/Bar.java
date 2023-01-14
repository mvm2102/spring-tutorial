package com.baeldung.springdoc.demo.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Bar {
    private long id;

    @ApiModelProperty(name = "name", dataType = "List", example = "[\"str1\", \"str2\", \"str3\"]")
    private List<String> name;

    public Bar() {
        super();
    }

    public Bar(final long id, final List<String> name) {
        super();

        this.id = id;
        this.name = name;
    }

    //

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(final List<String> name) {
        this.name = name;
    }

}