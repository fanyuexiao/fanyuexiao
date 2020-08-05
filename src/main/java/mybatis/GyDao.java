package mybatis;

@FyxScan
public interface GyDao {
    @Select("update table")
    void update();
}
