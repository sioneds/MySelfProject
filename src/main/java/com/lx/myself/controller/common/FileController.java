package com.lx.myself.controller.common;

import com.lx.myself.tools.ArrayTools;
import com.lx.myself.tools.http.ResponseData;
import com.lx.myself.tools.http.ResultCode;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @date 2021/05/08 14:56
 **/
@Slf4j
@RequestMapping("/file")
@RestController
public class FileController {
    BASE64Decoder base64Decoder=new BASE64Decoder();
    /**
     * @author sioned
     * @date 2021/05/08 14:59
     * @Description encrypt
     */
    @RequestMapping("/encrypt")
        public ResponseData encrypt(@RequestParam("file") MultipartFile file){
        HashMap map =new HashMap();
        try {
            byte[] originArray = file.getBytes();//原始数组
            byte[] bytes = ArrayTools.FlashBackBytes(originArray);
            map.put("state","success");
            map.put("name",file.getName());
            map.put("originalFilename",file.getOriginalFilename());
            map.put("bytes",bytes);
            map.put("contentType",file.getContentType());
        }catch(Exception ioException){
            log.debug(ioException.getMessage());
            map.put("state","error");
            map.put("error",ioException.getMessage());
            return ResponseData.custom(ResultCode.FILE_EXCEPTION,map);
        }
        return ResponseData.success(map);
    }

    /**
     * @author sioned
     * @date 2021/05/08 14:59
     * @Description encrypt
     */
    @RequestMapping("/decode")
    public ResponseData decode(@RequestParam("file") MultipartFile file){
        HashMap map =new HashMap();
        try {
            String content = new String(file.getBytes());
            byte[] originArray = base64Decoder.decodeBuffer(content);//原始数组
            byte[] bytes = ArrayTools.FlashBackBytes(originArray);
            String endContent=new String(bytes);
            map.put("state","success");
            map.put("name",file.getName());
            map.put("originalFilename",file.getOriginalFilename());
            map.put("bytes",endContent);
            map.put("contentType",file.getContentType());
        }catch(Exception ioException){
            log.debug(ioException.getMessage());
            map.put("state","error");
            map.put("error",ioException.getMessage());
            return ResponseData.custom(ResultCode.FILE_EXCEPTION,map);
        }
        return ResponseData.success(map);
    }
}
