package com.person.user.server.utils;

import com.person.user.server.enums.ResultEnum;
import com.person.user.server.vo.ResultVO;

/**
 * @program: order
 * @description:
 * @author: zhangzx
 * @create: 2019-09-05 16:18
 */
public class ResultVOUtil {

    public static ResultVO success() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        return resultVO;
    }

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO error(ResultEnum resultEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMessage(resultEnum.getMessage());
        return resultVO;
    }
}