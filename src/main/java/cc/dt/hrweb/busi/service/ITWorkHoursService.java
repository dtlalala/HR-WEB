package cc.dt.hrweb.busi.service;

import cc.dt.hrweb.busi.entity.TWorkHours;
import cc.dt.hrweb.common.domain.QueryRequest;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author dtlalala
 */
public interface ITWorkHoursService extends IService<TWorkHours> {
    List<TWorkHours> findById(Long userId);

    List<TWorkHours> findPassById(Long userId, String status);

    void updateByKey(TWorkHours tWorkHours) throws Exception;

    void check(String key, String status) throws Exception;

    List<TWorkHours> findApproval(QueryRequest request, TWorkHours tWorkHours, Long deptId);

    void approval(String[] keyIds, String state) throws Exception;

}
