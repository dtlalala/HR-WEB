package cc.dt.hrweb.web.busi.service.impl;

import cc.dt.hrweb.common.utils.SortUtil;
import cc.dt.hrweb.web.busi.entity.DtTest;
import cc.dt.hrweb.web.busi.mapper.DtTestMapper;
import cc.dt.hrweb.common.domain.QueryRequest;
import cc.dt.hrweb.web.busi.service.IDtTestService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dtlalala
 */
@Slf4j
@Service("dtTestService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DtTestServiceImpl extends ServiceImpl<DtTestMapper, DtTest> implements IDtTestService {


    @Override
    public IPage<DtTest> getDttest(QueryRequest request, DtTest dtTest) {
        try {
            LambdaQueryWrapper<DtTest> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(DtTest::getName, "dt");
            Page<DtTest> page = new Page<>();
            SortUtil.handlePageSort(request, page, true);
            return this.page(page, wrapper);
        }catch (Exception e){
            log.error("获取字典信息失败", e);
            return null;
        }
    }

    @Override
    public void insertDt(DtTest dtTest) {
        this.save(dtTest);
    }
}
