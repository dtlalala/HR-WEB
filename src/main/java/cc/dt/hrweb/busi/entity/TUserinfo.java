package cc.dt.hrweb.busi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TUserinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工ID--user_id
     */
    @TableId(value = "personnel_id")
    private Long personnelId;

    /**
     * 员工真实姓名
     */
    private String personnelName;

    /**
     * 性别
     */
    private String personnelSex;

    /**
     * 出生年月日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    /**
     * 身份证号
     */
    private String shenfenzhengId;

    /**
     * 婚姻状况
     */
    private String wedLock;

    /**
     * 民族
     */
    private String race;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 政治面貌
     */
    private String politic;

    /**
     * 地址
     */
    private String address;

    /**
     * 职位
     */
    private String job;

    /**
     * 聘用形式
     */
    private String engageForm;

    /**
     * 最高学历
     */
    private String tiptopDegree;

    /**
     * 所属专业
     */
    private String specialty;

    /**
     * 毕业院校
     */
    private String school;

    /**
     * 入职日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginWorkDate;

    /**
     * 在职状态
     */
    private String workState;

    /**
     * 工号
     */
    private String workId;

    /**
     * 合同期限
     */
    private String contractTerm;

    /**
     * 开始合同日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginContract;

    /**
     * 结束合同日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endContract;


}
