package com.person.order.server.utils;

import com.person.order.server.vo.ResultVO;

/**
 * @program: order
 * @description:
 * @author: zhangzx
 * @create: 2019-09-05 16:18
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        resultVO.setData(object);
        return resultVO;
    }
}