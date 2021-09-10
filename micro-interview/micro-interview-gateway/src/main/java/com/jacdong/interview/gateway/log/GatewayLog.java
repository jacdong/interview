package com.jacdong.interview.gateway.log;

import java.util.Date;

import lombok.Data;


@Data
public class GatewayLog {
     
    private String schema;
    
    private String requestMethod;
    
    private String requestPath;
    
    private String targetServer;
    
    private String requestBody;
    
    private Date responseTime;
    
    private Long executeTime;
    
    private Date requestTime;
    
    private String ip;
    
    private String responseData;
    
}
