package mybatis.newversion;

@FyxScan
public interface GyDao {
    @Select("update table")
    void update();
}
