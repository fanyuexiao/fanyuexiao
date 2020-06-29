package mybatis;

public interface FyxDao {
    @Select("select count(*) from table")
    Integer count();
}
