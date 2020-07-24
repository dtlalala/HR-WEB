package cc.dt.hrweb.web.busi.service;

import cc.dt.hrweb.web.busi.entity.DtTest;
import cc.dt.hrweb.common.domain.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author dtlalala
 */
public interface IDtTestService extends IService<DtTest> {

    IPage<DtTest> getDttest(QueryRequest request, DtTest dtTest);

    void insertDt(DtTest dtTest);

}
