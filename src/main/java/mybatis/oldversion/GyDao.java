package mybatis.oldversion;

@FyxScan
public interface GyDao {
    @Select("update table")
    void update();
}
