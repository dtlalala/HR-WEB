package cc.dt.hrweb.job.dao;


import cc.dt.hrweb.job.domain.Job;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface JobMapper extends BaseMapper<Job> {
	
	List<Job> queryList();
}