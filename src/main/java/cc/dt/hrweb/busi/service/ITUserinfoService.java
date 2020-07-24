package cc.dt.hrweb.busi.service;

import cc.dt.hrweb.busi.entity.TUserinfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author dtlalala
 */
public interface ITUserinfoService extends IService<TUserinfo> {

    TUserinfo findPersonnelById(Long personnelId);

    /**
     * 修改员工的personnelInfo资料
     * @param tUserinfo
     * @throws Exception
     */
    void updatePersonnel(TUserinfo tUserinfo) throws Exception;

    void insertPersonnel(TUserinfo tUserinfo) throws Exception;

    void deleteByIds(List<String> list);
}
