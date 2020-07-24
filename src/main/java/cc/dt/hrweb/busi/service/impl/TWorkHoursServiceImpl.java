package cc.dt.hrweb.busi.service.impl;

import cc.dt.hrweb.busi.entity.TWorkHours;
import cc.dt.hrweb.busi.mapper.TWorkHoursMapper;
import cc.dt.hrweb.busi.service.ITWorkHoursService;
import cc.dt.hrweb.common.domain.HrWebConstant;
import cc.dt.hrweb.common.domain.QueryRequest;
import cc.dt.hrweb.common.utils.SortUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dtlalala
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TWorkHoursServiceImpl extends ServiceImpl<TWorkHoursMapper, TWorkHours> implements ITWorkHoursService {
    @Override
    public List<TWorkHours> findById(Long userId) {
        try {
            return baseMapper.selectList(new LambdaQueryWrapper<TWorkHours>().eq(TWorkHours::getUserId, userId).eq(TWorkHours::getStatus, '0').eq(TWorkHours::getCategory, '0').orderByDesc(TWorkHours::getWorkDate));
        }catch (Exception e) {
            log.error("通过ID获取未填写工时失败", e);
            return null;
        }
    }

    @Override
    public List<TWorkHours> findPassById(Long userId, String status) {
        try {
            List<TWorkHours> tWorkHours = new ArrayList<>();
            if (status.equals("1")) {
                tWorkHours = baseMapper.selectList(new LambdaQueryWrapper<TWorkHours>().eq(TWorkHours::getUserId, userId).ne(TWorkHours::getStatus, "0").eq(TWorkHours::getCategory, '0').orderByDesc(TWorkHours::getWorkDate));
            } else {
                tWorkHours = baseMapper.selectList(new LambdaQueryWrapper<TWorkHours>().eq(TWorkHours::getUserId, userId).eq(TWorkHours::getStatus, status).eq(TWorkHours::getCategory, '0').orderByDesc(TWorkHours::getWorkDate));
            }
            return tWorkHours;
        }catch (Exception e) {
            log.error("通过ID获取未审核工时失败", e);
            return null;
        }
    }

    @Override
    @Transactional
    public void updateByKey(TWorkHours tWorkHours) throws Exception {
        tWorkHours.setStatus("1");
        updateById(tWorkHours);
    }

    @Override
    public void check(String key, String status) throws Exception {
        // TODO
    }

    @Override
    public List<TWorkHours> findApproval(QueryRequest request, TWorkHours tWorkHours, Long deptId) {
        QueryWrapper<TWorkHours> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(tWorkHours.getUserName())) {
            queryWrapper.lambda().eq(TWorkHours::getUserName, tWorkHours.getUserName());
        }
        if (!org.springframework.util.StringUtils.isEmpty(deptId)) {
            queryWrapper.lambda().eq(TWorkHours::getDeptId, deptId);
        }
        if (StringUtils.isNotBlank(tWorkHours.getWorkDateFrom()) && StringUtils.isNotBlank(tWorkHours.getWorkDateTo())) {
            queryWrapper.lambda()
                    .ge(TWorkHours::getWorkDate, tWorkHours.getWorkDateFrom())
                    .le(TWorkHours::getWorkDate, tWorkHours.getWorkDateTo());
        }
        queryWrapper.lambda().eq(TWorkHours::getStatus, "1").eq(TWorkHours::getCategory, '0');
        SortUtil.handleWrapperSort(request, queryWrapper, "workDate", HrWebConstant.ORDER_DESC, true);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void approval(String[] keyIds, String state) throws Exception {
        List<String> list = Arrays.asList(keyIds);
        TWorkHours tWorkHours = new TWorkHours();
        if (state.equals("1")) {
            tWorkHours.setStatus("2");
        } else if (state.equals("2")){
            tWorkHours.setStatus("3");
        }
        UpdateWrapper<TWorkHours> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().in(TWorkHours::getKey1, list);
        update(tWorkHours, updateWrapper);
    }

}
