package cc.dt.hrweb.job.service;

import cc.dt.hrweb.common.domain.QueryRequest;
import cc.dt.hrweb.job.domain.Job;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


public interface JobService extends IService<Job> {

    Job findJob(Long jobId);

    IPage<Job> findJobs(QueryRequest request, Job job);

    void createJob(Job job);

    void updateJob(Job job);

    void deleteJobs(String[] jobIds);

    int updateBatch(String jobIds, String status);

    void run(String jobIds);

    void pause(String jobIds);

    void resume(String jobIds);

}
