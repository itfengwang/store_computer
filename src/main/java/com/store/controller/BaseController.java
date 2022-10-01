package com.store.controller;

import com.store.Util.JsonResult;
import com.store.controller.ex.*;
import com.store.service.ex.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/*控制层的基类 做异常的捕获处理*/
public class BaseController {
    //操作成功的状态码
    public static final int OK = 200;

    //当项目中产生了异常，会被统一拦截到此方法中，此方法充当请求处理方法，方法的返回值直接给到前端
    @ExceptionHandler({ServiceException.class, FileUploadException.class}) //用于统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e){

        JsonResult<Void> result = new JsonResult<>(e);

        if(e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名已被占用");

        }else if(e instanceof InsertEException){
            result.setState(5000);
            result.setMessage("注册时产生未知的异常");

        }else if(e instanceof UserNotFoundException){
            result.setState(5001);
            result.setMessage("用户未找到异常");

        }else if(e instanceof PasswordNotMatchException){
            result.setState(5002);
            result.setMessage("密码不匹配异常");

        }else if(e instanceof AddressCountLimitException){
            result.setState(5003);
            result.setMessage("用户的收货地址超出极限");

        }else if(e instanceof UpdateException){
            result.setState(5002);
            result.setMessage("更新时产生未知异常");

        }else if(e instanceof FileEmptyException){
            result.setState(6000);

        }else if(e instanceof FileSizeException){
            result.setState(6001);

        }else if(e instanceof FileUploadException){
            result.setState(6002);

        }else if(e instanceof FileTypeException){
            result.setState(6003);

        }else if(e instanceof FileUploadIOException){
            result.setState(6004);

        }else if(e instanceof AddressNotFoundException){
            result.setMessage("用户的后果地址找不到异常");
            result.setState(6004);

        }else if(e instanceof AccessDeniedException){
            result.setState(6004);
            result.setMessage("收货数据地址非法访问");

        }else if(e instanceof DeleteException){
            result.setState(6004);
            result.setMessage("删除数据时产生异常");

        }else if(e instanceof ProductNotFoundException){
            result.setState(6005);
            result.setMessage("查找商品异常");


        }else if(e instanceof CartNotFoundException){
            result.setState(6005);
            result.setMessage("购物车数据不存在");


        }

        return result;
    }

    /**
     * 获取session对象中的uid
     * @param session
     * @return 当前登录用户的uid的值
     */
    protected final Integer getUidFromSession(HttpSession session){

        Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
        return uid;

    }

    /**
     *获取当前登录的用户的用户名
     * @return
     */
    protected final String getUsernameFromSession(HttpSession session){

        return session.getAttribute("username").toString();



    }
}
