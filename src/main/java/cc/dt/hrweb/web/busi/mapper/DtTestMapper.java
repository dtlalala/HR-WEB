package cc.dt.hrweb.web.busi.mapper;

import cc.dt.hrweb.web.busi.entity.DtTest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author dtlalala
 */
public interface DtTestMapper extends BaseMapper<DtTest> {
    List<DtTest> selectAll();
}
