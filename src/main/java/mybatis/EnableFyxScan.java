package mybatis;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Import(FyxBatisRegistrar.class)
public @interface EnableFyxScan {
    String value();
}
