package mybatis;

import org.springframework.context.annotation.Lazy;

@FyxScan
public interface GyDao {
    @Select("update table")
    void update();
}
