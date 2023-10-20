package com.example.payments.highnote;

import com.example.payments.commom.BaseServiceProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "highnote")
public class HighnoteProperties extends BaseServiceProperties {
}
