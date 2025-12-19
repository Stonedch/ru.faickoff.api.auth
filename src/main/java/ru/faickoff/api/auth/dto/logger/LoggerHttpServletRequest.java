package ru.faickoff.api.auth.dto.logger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoggerHttpServletRequest {

    private String remoteHost;

    private String headerUserAgent;

    private String headerReferer;

    private String requestURL;

    private String requestURI;

    private String queryString;

    private String method;

    private String requestBody;
}
