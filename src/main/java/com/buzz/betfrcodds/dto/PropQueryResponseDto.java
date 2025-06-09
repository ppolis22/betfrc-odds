package com.buzz.betfrcodds.dto;

import java.util.List;

public class PropQueryResponseDto {
    private List<PropDto> props;

    public PropQueryResponseDto() {}

    public PropQueryResponseDto(List<PropDto> props) {
        this.props = props;
    }

    public List<PropDto> getProps() {
        return props;
    }

    public void setProps(List<PropDto> props) {
        this.props = props;
    }
}
