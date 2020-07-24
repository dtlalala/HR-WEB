package cc.dt.hrweb.web.controller;

import cc.dt.hrweb.common.domain.HrWebConstant;
import cc.dt.hrweb.common.domain.HrWebResponse;
import cc.dt.hrweb.common.exception.HrwebException;
import cc.dt.hrweb.common.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@Slf4j
@Validated
//@RestController
@RequestMapping("movie")
public class MovieController {

    private String message;

    @GetMapping("hot")
    public HrWebResponse getMoiveHot() throws HrwebException {
        try {
            String data = HttpUtil.sendSSLPost(HrWebConstant.TIME_MOVIE_HOT_URL, "locationId=328");
            return new HrWebResponse().data(data);
        } catch (Exception e) {
            message = "获取热映影片信息失败";
            log.error(message, e);
            throw new HrwebException(message);
        }
    }

    @GetMapping("coming")
    public HrWebResponse getMovieComing() throws HrwebException {
        try {
            String data = HttpUtil.sendSSLPost(HrWebConstant.TIME_MOVIE_COMING_URL, "locationId=328");
            return new HrWebResponse().data(data);
        } catch (Exception e) {
            message = "获取即将上映影片信息失败";
            log.error(message, e);
            throw new HrwebException(message);
        }
    }

    @GetMapping("detail")
    public HrWebResponse getDetail(@NotBlank(message = "{required}") String id) throws HrwebException {
        try {
            String data = HttpUtil.sendSSLPost(HrWebConstant.TIME_MOVIE_DETAIL_URL, "locationId=328&movieId=" + id);
            return new HrWebResponse().data(data);
        } catch (Exception e) {
            message = "获取影片详情失败";
            log.error(message, e);
            throw new HrwebException(message);
        }
    }

    @GetMapping("comments")
    public HrWebResponse getComments(@NotBlank(message = "{required}") String id) throws HrwebException {
        try {
            String data = HttpUtil.sendSSLPost(HrWebConstant.TIME_MOVIE_COMMENTS_URL, "movieId=" + id);
            return new HrWebResponse().data(data);
        } catch (Exception e) {
            message = "获取影片评论失败";
            log.error(message, e);
            throw new HrwebException(message);
        }
    }
}
