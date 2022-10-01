package com.store.controller;

import com.store.Util.JsonResult;
import com.store.controller.ex.FileEmptyException;
import com.store.controller.ex.FileSizeException;
import com.store.controller.ex.FileTypeException;
import com.store.controller.ex.FileUploadIOException;
import com.store.pojo.Address;
import com.store.pojo.User;
import com.store.service.IAddressService;
import com.store.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController extends BaseController{

    @Autowired
    private IAddressService addressService;

    @RequestMapping("/add_new_address")
    public JsonResult<Void> addNewAddress(Address address,HttpSession session){

        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);

        addressService.addNewAddress(uid,username,address);

        return new JsonResult<>(OK);


    }

    @RequestMapping({"","/"})
    public JsonResult<List<Address>> getAddressByUid(HttpSession session){

        Integer uid = getUidFromSession(session);

        List<Address> data = addressService.getByUid(uid);

        return new JsonResult<>(OK,data);

    }

    //Restful 风格
    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid,HttpSession session){

        addressService.setDefaultAddresss(aid,getUidFromSession(session),getUsernameFromSession(session));

        return new JsonResult<>(OK);

    }

    @RequestMapping("{aid}/delete")
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid,HttpSession session){

        addressService.delete(aid,getUidFromSession(session),getUsernameFromSession(session));

        return new JsonResult<>(OK);


    }





}
