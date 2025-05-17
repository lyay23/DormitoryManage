package com.example.springboot.controller;

import cn.hutool.core.io.FileUtil;
import com.example.springboot.common.Result;
import com.example.springboot.common.UID;
import com.example.springboot.entity.Admin;
import com.example.springboot.entity.DormManager;
import com.example.springboot.entity.Student;
import com.example.springboot.service.AdminService;
import com.example.springboot.service.DormManagerService;
import com.example.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

@RestController
@RequestMapping("/files")
public class FileController {

    static String rootFilePath = System.getProperty("user.dir") + "/springboot/src/main/resources/files/";

    @Resource
    private StudentService studentService;

    @Resource
    private AdminService adminService;

    @Resource
    private DormManagerService dormManagerService;

    /**
     * 将上传的头像写入本地 rootFilePath 并返回生成的唯一文件名
     */
    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return Result.error("-1", "上传文件不能为空");
        }
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            return Result.error("-1", "文件名不能为空");
        }

        String fileType = "";
        int dotIndex = originalFilename.lastIndexOf(".");
        if (dotIndex > -1 && dotIndex < originalFilename.length() - 1) {
            fileType = originalFilename.substring(dotIndex);
        } else {
            // Handle files with no extension or invalid names if necessary
            // For now, let's allow it but it might cause issues for file type recognition
        }

        String uid = new UID().produceUID();
        String uniqueFilename = uid + fileType;

        String targetPath = rootFilePath + uniqueFilename;

        FileUtil.writeBytes(file.getBytes(), targetPath);

        return Result.success(uniqueFilename);
    }

    /**
     * 将上传的头像写入本地 rootFilePath 并返回生成的唯一文件名
     */
    @PostMapping("/uploadAvatar/stu/{filename}")
    public Result<?> uploadStuAvatar(@RequestBody Student student, @PathVariable String filename) {
        if (filename == null || filename.isEmpty()) {
            return Result.error("-1", "文件名不能为空");
        }
        // 确保 student 对象中包含 username，因为更新操作依赖 username
        if (student.getUsername() == null || student.getUsername().isEmpty()) {
            return Result.error("-1", "学生信息错误，缺少用户名");
        }
        student.setAvatar(filename); // 设置新的头像文件名
        // int i = studentService.updateStudent(student); // 旧的方法名
        int i = studentService.updateNewStudent(student); // 使用更新后的方法名
        if (i > 0) {
            return Result.success(filename);
        } else {
            return Result.error("-1", "设置学生头像失败");
        }
    }

    @PostMapping("/uploadAvatar/admin/{filename}")
    public Result<?> uploadAdminAvatar(@RequestBody Admin admin, @PathVariable String filename) {
        if (filename == null || filename.isEmpty()) {
            return Result.error("-1", "文件名不能为空");
        }
        // 确保 admin 对象中包含 username，因为更新操作依赖 username
        if (admin.getUsername() == null || admin.getUsername().isEmpty()) {
            return Result.error("-1", "管理员信息错误，缺少用户名");
        }
        admin.setAvatar(filename);
        int i = adminService.updateAdmin(admin);
        if (i > 0) {
            return Result.success(filename);
        } else {
            return Result.error("-1", "设置管理员头像失败，可能用户不存在或更新未生效");
        }
    }

    @PostMapping("/uploadAvatar/dormManager/{filename}")
    public Result<?> uploadDormManagerAvatar(@RequestBody DormManager dormManager, @PathVariable String filename) {
        if (filename == null || filename.isEmpty()) {
            return Result.error("-1", "文件名不能为空");
        }
        dormManager.setAvatar(filename);
        int i = dormManagerService.updateDormManager(dormManager);
        if (i > 0) {
            return Result.success(filename);
        } else {
            return Result.error("-1", "设置宿管头像失败");
        }
    }

    /**
     * 前端调用接口，后端查询存储与本地的头像，进行Base64编码 发送到前端
     */
    @GetMapping("/initAvatar/{filename}")
    public Result<?> initAvatar(@PathVariable String filename) {
        if (filename == null || filename.isEmpty()) {
            return Result.error("-1", "文件名不能为空");
        }
        String path = rootFilePath + filename;
        File file = new File(path);
        if (!file.exists()) {
            System.err.println("头像文件不存在: " + path);
            return Result.error("-1", "头像文件不存在");
        }
        try {
            return getImage(path);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("-1", "获取头像失败: " + e.getMessage());
        }
    }

    private Result<?> getImage(String path) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(path);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            byte[] b = new byte[1024];
            int len;
            while ((len = fileInputStream.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            byte[] fileByte = bos.toByteArray();

            String data = java.util.Base64.getEncoder().encodeToString(fileByte);
            return Result.success(data);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
