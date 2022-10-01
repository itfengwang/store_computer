package com.store.controller;

import com.store.Util.JsonResult;
import com.store.controller.ex.FileEmptyException;
import com.store.controller.ex.FileSizeException;
import com.store.controller.ex.FileTypeException;
import com.store.controller.ex.FileUploadIOException;
import com.store.pojo.User;
import com.store.service.IUserService;
import com.store.service.ex.InsertEException;
import com.store.service.ex.UsernameDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController  extends BaseController{
    @Autowired
    private IUserService userService;

    /*接受数据的方式：请求处理方法的参数列表设置为pojo类类型来接受前端的数据
    *  1 springboot 会将前端url地址中的参数和pojo类的属性名相比较，若
    * 两个名称值相同，sp会自动将值注入到pojo对应的属性上
    *  2 参数设置为String类型(非pojo类型)，直接将请求的参数名和方法的参数名直接进行比较
    * 如果名称相同，自动完成值的依赖1注入 */

    @RequestMapping("/reg")
    public JsonResult<Void> reg(User user){
        //创建响应结果对象
        JsonResult<Void> result = new JsonResult<>();
        userService.reg(user);

        return new JsonResult<>(OK);

    }

    @RequestMapping("/login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User data = userService.login(username,password);
        //向session对象中完成对象的绑定(全局的session)
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());

        System.out.println(getUidFromSession(session));

        return new JsonResult<User>(OK,data);

    }

    @RequestMapping("/changepwd")
    public JsonResult<Void> changePassword(String oldPassword,
                                           String newPassword,
                                           HttpSession session){


        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session){
        User data = userService.getByUid(getUidFromSession(session));

        return new JsonResult<>(OK,data);

    }

    @RequestMapping("/change_info")
    public JsonResult<Void> changeInFo(User user,HttpSession session){

        //user对象中有四部分的数据 username,phone,email,gender
        //uid 数据需要再次封装到user对象中
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInFo(uid,username,user);
        return new JsonResult<>(OK);
    }

    /**
     * MultipartFile接口是springMvc提供的一个接口，这个接口为我们包装了文件类型的数据（任何file类型的文件）
     * springboot整合了mvc，只需要在处理请求的方法参数列表上声明一个参数类型为MultipartFile类型的参数，然后springboot
     * 自动将传递给服务端的文件的数据赋值在这个参数上
     * @param session
     * @param file
     * @return
     */
    //设置上传文件的最大值
    public static final int AVATAR_MAX_SIZE = 10*1024*1024;
    //限制上传文件的类型
    public static final List<String> AVATAR_TYPE = new ArrayList<>();

    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }


    /**
     * 用户头像相关
     * @param session
     * @param， multipartFile file 把表单中name属性为file的整个数据传递给 我们这里定义的file
     * @return
     */
    @RequestMapping("/change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session,
                                           @RequestParam("file") MultipartFile file) {

        //判断文件是否为空
        if (file.isEmpty()) {
            throw new FileEmptyException("文件为空");
        }
        if (file.getSize() > AVATAR_MAX_SIZE) {   //getSize 是得到字节的大小
            throw new FileSizeException("文件大小超出了限制");
        }

        //判断文件的类型是否是我们规定的类型
        /*
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
        */

        String contentType = file.getContentType();  // .html...
        if (!AVATAR_TYPE.contains(contentType)) {
            //如果集合bu包含某个元素
            throw new FileTypeException("文件类型不支持");

        }


        //获取当前项目的一个绝对磁盘路径
        String parent = session.getServletContext().getRealPath("/upload");


        //File 对象指向这个路径，File是否存在
        File dir = new File(parent);
        if (!dir.exists()) { //检测目录是否存在
            dir.mkdirs();//创建目录
        }

        //获取这个文件的名称 UUID工具来将生成一个新的字符串作为文件名

        //文件的名称 例如avatar01.png
        String originalFilename = file.getOriginalFilename();
        //先找到.最后出现的位置
        int index = originalFilename.lastIndexOf(".");
        //结合index，进行截取，后面的,png会被截取到
        String suffix = originalFilename.substring(index);
        //通过uuid加上suffix生成随机的存放文件的额名字
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;

        //File dest = new File(dir, filename); //在dir目录下创建以filename命名的文件
        try {
            //把file文件中的数据写入到dest文件中，前提是这两个文件后缀一致
            // file to "new file(D://img/aaa.img)"
            file.transferTo(new File(dir,filename)); //将从前端接受的图片数据写入到目标文件中

        } catch (IOException e) {

            throw new FileUploadIOException("文件读写异常");
        }


        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);

        //返回头像的路径
        String avatar = "/upload/" + filename;
        userService.changeAvatar(uid, avatar, username);
        //返回用户头像的路径给前端页面将来用于头像的展示使用
        return new JsonResult<>(OK, avatar);

    }

    
}
