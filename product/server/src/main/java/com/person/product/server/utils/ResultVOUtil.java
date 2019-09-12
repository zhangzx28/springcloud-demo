package com.person.product.server.utils;

import com.person.product.server.vo.ResultVO;

/**
 * @program: product
 * @description:
 * @author: zhangzx
 * @create: 2019-08-31 10:45
 */
public class ResultVOUtil {
    public static ResultVO success(Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(data);
        return resultVO;
    }
}