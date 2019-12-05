package com.ual.blog.Mapper;

import com.ual.blog.model.Guestbook;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuestbookMapper extends BaseMapper<Guestbook>{
    /**
     * 获取留言列表
     * @param delStatus
     * @param type
     * @return
     */
    List<Guestbook> getList(@Param("delStatus") Integer delStatus, @Param("type") Integer type);

    /**
     * 留言总数
     * @param delStatus
     * @return
     */
    Integer getTotalCount(int delStatus);

    Guestbook getByNickname(String nickname);
}
