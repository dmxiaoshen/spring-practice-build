package com.xyz.service.demo.query;

import com.xyz.common.base.entity.AbstractPagination;

public class DictPagination extends AbstractPagination<DictPagination> {

    private String value;

    private String label;

    private String description;

    private String type;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
