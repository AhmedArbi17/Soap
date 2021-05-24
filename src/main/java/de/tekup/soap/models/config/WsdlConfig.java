package de.tekup.soap.models.config;

import de.tekup.soap.models.Endpoint.ExamEndpoint;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WsdlConfig {


    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messagedipatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformSchemaLocations(true);
        return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/ws/*");

    }

    @Bean
    public XsdSchema schema() {
        return new SimpleXsdSchema(new ClassPathResource("WhiteTest.xsd"));
    }

    @Bean(name = "whiteTest")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
        defaultWsdl11Definition.setPortTypeName("whiteTestIndicator");
        defaultWsdl11Definition.setLocationUri("http://localhost:8080/ws");
        defaultWsdl11Definition.setSchema(schema);
        defaultWsdl11Definition.setTargetNamespace(ExamEndpoint.NAMESPACE);

        return defaultWsdl11Definition;

    }
}

