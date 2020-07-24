package cc.dt.hrweb.busi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 *
 * @author dtlalala
 */
@Data
@TableName("t_work_hours")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TWorkHours implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "KEYK", type = IdType.AUTO)
    private Long key1;

    /**
     * 进行中的工作项目
     */
    @TableField("WORK_PROJECT")
    private String workProject;

    /**
     * 工作日期
     */
    @TableField("WORK_DATE")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date workDate;

    /**
     * 工作类型
     */
    @TableField("WORK_SORT")
    private String workSort;

    /**
     * 工作量
     */
    @TableField("WORK_LOAD")
    private String workLoad;

    /**
     * 是否加班 0不加班 1加班
     */
    @TableField("OVERTIME")
    private String overtime;

    /**
     * 工作描述
     */
    @TableField("WORK_DESC")
    private String workDesc;

    /**
     * 状态 0未填写 1填写未审批 2审批通过 3审批未通过
     */
    @TableField("STATUS")
    private String status;

    /**
     * 用户表的ID
     */
    @TableField("USER_ID")
    private Long userId;

    /**
     * 部门表的ID
     */
    @TableField("DEPT_ID")
    private Long deptId;

    /**
     * 工时类别 0正常工时 1加班工时
     */
    @TableField("CATEGORY")
    private String category;

    @TableField("USER_NAME")
    private String userName;

    @TableField("DEPT_NAME")
    private String deptName;

    private transient String workDateFrom;

    private transient String workDateTo;

}
