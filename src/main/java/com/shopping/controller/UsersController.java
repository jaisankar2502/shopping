/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopping.controller;

import com.shopping.exception.BadRequestException;
import com.shopping.form.PasswordChangeForm;
import com.shopping.form.UserForm;
import com.shopping.form.ProfileUpdatesForm;
import com.shopping.service.UserService;
import com.shopping.util.LanguageUtil;
import com.shopping.util.Pager;
import com.shopping.util.UserExcelExporter;
import com.shopping.view.UserView;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nirmal
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private LanguageUtil languageUtil;
    
    @PostMapping
    public UserView add(@Valid @RequestBody UserForm form) {
        return userService.add(form);
    }

    @GetMapping
    public UserView currentUser() {
        return userService.currentUser();
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable("userId") Integer userId) {
        userService.delete(userId);
    }

    @PutMapping("/{userId}")
    public UserView update(
            @PathVariable("userId") Integer userId,
            @Valid @RequestBody ProfileUpdatesForm form
    ) {
        return userService.update(userId, form);
    }

    @GetMapping("/{userId}")
    public UserView get(@PathVariable("userId") Integer userId) {
        return userService.get(userId);
    }

    @PutMapping("/changepassword")
    public void changePassword(@Valid @RequestBody PasswordChangeForm form) {
        userService.changePassword(form);
    }

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response, HttpServletRequest request) throws IOException {

        String type = request.getHeader("Device-Type");
        System.out.println("content type=====>" + type);

        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
//        response.setHeader(headerKey, headerValue);

        response.setHeader("Content-Disposition", "attachment; filename=\"" + currentDateTime + "\".xlsx");
        response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("X-Download-Options", "noopen");

        UserExcelExporter excelExporter = new UserExcelExporter();

        excelExporter.export(response);
    }

    @GetMapping("/search")
    public Pager<UserView> SearchUser(
            @RequestParam(value = "page", required = false, defaultValue = "1") String page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") String limit,
            @RequestParam(value = "sort", required = false, defaultValue = "updateDate") String sort,
            @RequestParam(value = "type", required = false, defaultValue = "false") boolean type,
            @RequestParam(value = "lastId", required = false) String lastId,
            @RequestParam(value = "search", required = false) String search
    ) {

        Integer lmt = null;
        Integer pg = null;
        try {
            lmt = Integer.parseInt(limit);
        }catch(NumberFormatException e){
            throw new BadRequestException(languageUtil.getTranslatedText("invalid.limit.option", null, "en"));
        }
        
        try {
            pg = Integer.parseInt(page);
        }catch(NumberFormatException e){
            throw new BadRequestException(languageUtil.getTranslatedText("invalid.limit.option", null, "en"));
        }

        return userService.SearchUser(pg, lmt, sort, type, lastId, search);

    }

}
