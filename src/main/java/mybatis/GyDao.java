package mybatis;

public interface GyDao {
    @Select("update table")
    void update();
}
