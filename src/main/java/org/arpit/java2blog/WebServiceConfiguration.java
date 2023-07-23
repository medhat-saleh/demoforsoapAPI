package org.arpit.java2blog;



import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


@EnableWs
@Configuration
public class WebServiceConfiguration extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "booksWsdl")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema booksSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("BooksPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("https://www.java2blog.com/xml/book");
        wsdl11Definition.setSchema(booksSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema booksSchema() {
        return new SimpleXsdSchema(new ClassPathResource("book.xsd"));
    }

//    @Bean
//    public XsdSchema bookSchema() {
//        return new SimpleXsdSchema(new ClassPathResource("book.xsd"));
//    }
//
//    @Bean
//    public XsdSchemaCollection schemaCollection(XsdSchema bookSchema) {
//        return new XsdSchemaCollection() {
//            @Override
//            public XsdSchema[] getXsdSchemas() {
//                return new XsdSchema[]{bookSchema};
//            }
//
//            @Override
//            public XmlValidator createValidator() {
//                XmlValidatorFactory factory = XmlValidatorFactory.newInstance();
//                return factory.createValidator();
//            }
//        };
//    }
//
//    @Bean(name = "books")
//    public DefaultWsdl11Definition booksWsdl(XsdSchemaCollection schemaCollection) {
//        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
//        wsdl11Definition.setWsdl(new ClassPathResource("books.wsdl"));
//        wsdl11Definition.setSchemaCollection(schemaCollection);
//        return wsdl11Definition;
//    }
//
//    @Bean(name = "bookService")
//    public HttpTransportServlet httpTransportServlet() {
//        return new HttpTransportServlet();
//    }
//
//    @Bean
//    public BookService bookService() {
//        return new BookService();
//    }
}
