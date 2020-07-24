package cc.dt.hrweb.busi.entity;

import cc.dt.hrweb.common.converter.TimeConverter;
import com.baomidou.mybatisplus.annotation.IdType;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
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
@TableName("t_salary")
@Excel("导入导出工资表")
public class TSalary implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 工资表ID
     */
//    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ExcelField(value = "姓名")
    private String userName;

    /**
     * 基本工资
     */
    @ExcelField(value = "基本工资", required = true, maxLength = 11, regularExp = "[0-9]+",
            regularExpMessage = "必须是数字", comment = "提示: 必填，只能填写数字，并且长度不能超过11位")
    private String basicWage;

    /**
     * 奖金
     */
    @ExcelField(value = "奖金", maxLength = 11, regularExp = "[0-9]+",
            regularExpMessage = "必须是数字", comment = "提示: 只能填写数字，并且长度不能超过11位")
    private String bonus;

    /**
     * 餐补
     */
    @ExcelField(value = "餐补", maxLength = 11, regularExp = "[0-9]+",
            regularExpMessage = "必须是数字", comment = "提示: 必填，只能填写数字，并且长度不能超过11位")
    private String mealSupplement;

    /**
     * 加班工资
     */
    @ExcelField(value = "加班工资", maxLength = 11, regularExp = "[0-9]+",
            regularExpMessage = "必须是数字", comment = "提示: 必填，只能填写数字，并且长度不能超过11位")
    private String overtimeSalary;

    /**
     * 其他补贴
     */
    @ExcelField(value = "其他补贴", maxLength = 11, regularExp = "[0-9]+",
            regularExpMessage = "必须是数字", comment = "提示: 必填，只能填写数字，并且长度不能超过11位")
    private String otherSubsidies;

    /**
     * 社保
     */
    @ExcelField(value = "社保", required = true, maxLength = 11, regularExp = "[0-9]+",
            regularExpMessage = "必须是数字", comment = "提示: 必填，只能填写数字，并且长度不能超过11位")
    private String socialSecurity;

    /**
     * 住房公积金
     */
    @ExcelField(value = "住房公积金", required = true, maxLength = 11, regularExp = "[0-9]+",
            regularExpMessage = "必须是数字", comment = "提示: 必填，只能填写数字，并且长度不能超过11位")
    private String providentFund;

    /**
     * 个税
     */
    @ExcelField(value = "个税", required = true, maxLength = 11, regularExp = "[0-9]+",
            regularExpMessage = "必须是数字", comment = "提示: 必填，只能填写数字，并且长度不能超过11位")
    private String tax;

    /**
     * 实发工资
     */
    @ExcelField(value = "实发工资", required = true, maxLength = 11, regularExp = "[0-9]+",
            regularExpMessage = "必须是数字", comment = "提示: 必填，只能填写数字，并且长度不能超过11位")
    private String actualSalary;

    /**
     * 发放日期
     */
    @ExcelField(value = "发放日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    /**
     * 工资日期
     */
    @ExcelField(value = "工资日期")
    private String payDate;


}
