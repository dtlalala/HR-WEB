package cc.dt.hrweb.web.busi.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 *
 * @author dtlalala
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DtTest implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Integer id;

    @TableField("NAME")
    private String name;

    @TableField("AGE")
    private String age;

    @TableField("TEL")
    private String tel;

    @TableField("ADDRESS")
    private String address;


}
