package cc.dt.hrweb.busi.service.impl;

import cc.dt.hrweb.busi.entity.TSalary;
import cc.dt.hrweb.busi.mapper.TSalaryMapper;
import cc.dt.hrweb.busi.service.ITSalaryService;
import cc.dt.hrweb.system.domain.Test;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dtlalala
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TSalaryServiceImpl extends ServiceImpl<TSalaryMapper, TSalary> implements ITSalaryService {


    @Value("${febs.max.batch.insert.num}")
    private int batchInsertMaxNum;//application.yml里的,从配置文件读取值


    @Override
    public List<TSalary> findSalary() {
        try {
            return baseMapper.selectList(new QueryWrapper<TSalary>().orderByDesc("user_name"));
        } catch (Exception e) {
            log.error("获取信息失败", e);
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public void batchInsert(List<TSalary> list) {
        saveBatch(list, batchInsertMaxNum);
    }
}
