package autowiring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Fyx {
    /**
     * @Autowired 实际逻辑：调用无参构造方法，属性先按照类型找，再按照名字找，找到唯一的就直接注入，会覆盖byType，byName
     *
     * 做一个实验，证明@Autowired不是ByType
     * 注入一个I，有两个实现类X（ix），Y（iy），设置其注入模型为byType
     * 不加@Autowired时,无法注入（byType有两个实现类）
     * 加@Autowired，注入成功，说明@Autowired覆盖了byType的逻辑
     */
    @Autowired
    I ix;

    /**
     * 做一个实验，证明@Autowired不是byName
     * 注入一个Z(iiz)，设置其注入模型为byName
     * 不加@Autowired时,无法注入（byName名字都对不上）
     * 加@Autowired，注入成功，说明@Autowired覆盖了byName的逻辑
     */
    @Autowired
    Z z;

    A a;

    public void setIx(I i) {
        this.ix = i;
    }

    public void setZ(Z z) {
        this.z = z;
    }

    public void setA(A a) {
        this.a = a;
    }

    /**
     * Autowiring modes：constructor
     * 当注入模型是constructor时，会通过推断构造方法推断出使用哪一个构造方法
     */
    public Fyx(){
        System.out.println("default");
    }

    public Fyx(A a){
        this.a = a;
        System.out.println("A");
    }

    public void print(){
        System.out.println("A:" + a);
        System.out.println("I:" + ix);
        System.out.println("Z:" + z);
    }
}
