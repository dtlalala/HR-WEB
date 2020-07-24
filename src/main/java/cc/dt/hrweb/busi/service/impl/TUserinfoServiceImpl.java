package cc.dt.hrweb.busi.service.impl;

import cc.dt.hrweb.busi.entity.TUserinfo;
import cc.dt.hrweb.busi.mapper.TUserinfoMapper;
import cc.dt.hrweb.busi.service.ITUserinfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author dtlalala
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TUserinfoServiceImpl extends ServiceImpl<TUserinfoMapper, TUserinfo> implements ITUserinfoService {

    @Override
    public TUserinfo findPersonnelById(Long personnelId) {
        try {
            return baseMapper.selectOne(new LambdaQueryWrapper<TUserinfo>().eq(TUserinfo::getPersonnelId, personnelId));
        }catch (Exception e){
            log.error("通过用户ID获取用户其他资料失败", e);
            return null;
        }
    }

    @Override
    public void updatePersonnel(TUserinfo tUserinfo) throws Exception {
        updateById(tUserinfo);
    }

    @Override
    public void insertPersonnel(TUserinfo tUserinfo) throws Exception {
        baseMapper.insert(tUserinfo);
    }

    @Override
    public void deleteByIds(List<String> list) {
        removeByIds(list);
    }
}
