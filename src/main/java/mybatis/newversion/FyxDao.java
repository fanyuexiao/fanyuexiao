package mybatis.newversion;

@FyxScan
public interface FyxDao {
    @Select("select count(*) from table")
    void count(String s);

    @Select("select count(*) from table")
    int say(int a);
}
