import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.*;
import java.lang.annotation.*;
import java.util.*;
import testdome.service.BookSearchService;

/*@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@*/interface ServiceCallback {
    public void callback(String methodName);
}

interface SearchCallback {
    public void callback(String searchedBook);
}

interface RepositoryCallback {
    public void callback();
}

@Aspect
@Component
public class ServiceAOP {
    @Autowired ServiceCallback serviceCallback;
    @Autowired SearchCallback searchCallback;
    @Autowired RepositoryCallback repositoryCallback;

    @Pointcut("execution(* testdome.service..*(..))")
    protected void loggingOperation()
    {
    }


    @Pointcut("execution(public boolean testdome.service.BookSearchService.bookExists(String))")
    protected void loggingOperation2()
    {
    }

    @Pointcut("execution(public List<String> testdome.service.BookRepository.getBooks())")
    protected void loggingOperation3()
    {
    }


    @Before("loggingOperation()")
    //@Before(value="execution(* com.testdome.service..*(..))")
    public void serviceAdvice(JoinPoint jp) {
        serviceCallback.callback(jp.getSignature().getName());
    }

    @Around("loggingOperation3()")
    public void repositoryAdvice(JoinPoint jp) {
        repositoryCallback.callback();
    }

    @Around("loggingOperation2()")
    public void searchServiceAdvice(JoinPoint joinPoint) throws Throwable {
        searchCallback.callback((String) joinPoint.getArgs()[0]);
    }
}

@Configuration
@EnableAspectJAutoProxy
@Import(ServiceAOP.class)
@ComponentScan(basePackages="testdome.service")
class Config3 {
    @Bean
    public ServiceCallback serviceCallback() {
        return (methodName) -> System.out.println(methodName);
    }
    @Bean
    public SearchCallback searchCallback() {
        return (book) -> System.out.println(book);
    }
    @Bean
    public RepositoryCallback repositoryCallback() {
        return () -> System.out.println("Repository called!");
    }
}