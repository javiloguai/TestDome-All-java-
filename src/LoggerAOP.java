import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.*;
import java.lang.annotation.*;
import java.util.*;

@Aspect
@Component
public class LoggerAOP {
	@Autowired private LoggerA logger;

	@Around("@annotation(LogExecution)")
	public Object loggingAdvice(ProceedingJoinPoint pjp, LogExecution logExecution) {
		logger.log("Before method call :"+pjp.getSignature());
		Object obj = null;
		try {
			obj = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			logger.log("On error during method call :"+ pjp.getSignature());
		}
		logger.log("After method call :"+pjp.getSignature());
		return obj;
	}
}

@Component
class NameRepository {
	@LogExecution
	public List<String> getNames() {
		List<String> names = new ArrayList<>();
		names.add("John");
		names.add("Mary");
		return names;
	}
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface LogExecution {}

interface LoggerA {
	public void log(String data);
}

@Configuration
@EnableAspectJAutoProxy
@Import({LoggerAOP.class, NameRepository.class})
class Config2 {
	@Bean
	public LoggerA logger() {
		return (message) -> System.out.println(message);
	}
}