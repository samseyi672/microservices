package com.activedge.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ETLResponse {
    private String responseMessage ;
    private String status ;
}
