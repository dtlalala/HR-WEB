package cc.dt.hrweb.busi.service;

import cc.dt.hrweb.busi.entity.TSalary;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author dtlalala
 */
public interface ITSalaryService extends IService<TSalary> {

    List<TSalary> findSalary();

    /**
     * 批量插入
     * @param list List<TSalary>
     */
    void batchInsert(List<TSalary> list);
}
