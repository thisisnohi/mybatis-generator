package com.xyrx.jcms.dao.sys;

import com.xyrx.jcms.entity.sys.SysCode;
import org.springframework.stereotype.Component;

@Component
public interface SysCodeDao {
    int deleteByPrimaryKey(String codeId);

    int insert(SysCode record);

    int insertSelective(SysCode record);

    SysCode selectByPrimaryKey(String codeId);
}