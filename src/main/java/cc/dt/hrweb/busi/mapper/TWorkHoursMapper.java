package cc.dt.hrweb.busi.mapper;

import cc.dt.hrweb.busi.entity.TWorkHours;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author dtlalala
 */
public interface TWorkHoursMapper extends BaseMapper<TWorkHours> {

    // 查当月的总工时
    @Select("select count(1) from t_work_hours ${ew.customSqlSegment}")
    Long countMonth(@Param(Constants.WRAPPER) LambdaQueryWrapper<TWorkHours> queryWrapper);

    // 查当月的总工时
    @Select("select count(1) from t_work_hours ${ew.customSqlSegment}")
    Long countAll(@Param(Constants.WRAPPER) LambdaQueryWrapper<TWorkHours> queryWrapper);

    // 查当月的总工时
    @Select("select count(1) from t_work_hours ${ew.customSqlSegment}")
    Long countSp(@Param(Constants.WRAPPER) LambdaQueryWrapper<TWorkHours> queryWrapper);
}
