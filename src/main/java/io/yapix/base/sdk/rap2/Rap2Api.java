package io.yapix.base.sdk.rap2;

import feign.Feign;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.form.FormEncoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import io.yapix.base.sdk.rap2.dto.InterfacePropertiesUpdateRequest;
import io.yapix.base.sdk.rap2.dto.InterfaceUpdateRequest;
import io.yapix.base.sdk.rap2.dto.LoginRequest;
import io.yapix.base.sdk.rap2.dto.LoginResponse;
import io.yapix.base.sdk.rap2.model.Rap2Interface;
import io.yapix.base.sdk.rap2.model.Rap2InterfaceBase;
import io.yapix.base.sdk.rap2.model.Rap2Module;
import io.yapix.base.sdk.rap2.model.Rap2Repository;


@Headers("Content-Type: application/json")
public interface Rap2Api {

    static Feign.Builder feignBuilder() {
        return Feign.builder()
                .encoder(new FormEncoder(new GsonEncoder()))
                .decoder(new GsonDecoder());
    }

    /**
     * 获取当前登录用户信息
     */
    @RequestLine("GET /captcha")
    feign.Response getCaptcha();

    /**
     * 普通登录
     */
    @RequestLine("POST /account/login")
    @Headers("Cookie: {captchaCookie}")
    Response<LoginResponse> login(LoginRequest request, @Param("captchaCookie") String captchaCookie);


    /**
     * 获取当前登录用户信息
     */
    @RequestLine("GET /account/info")
    Response<?> getAccountInfo();

    /**
     * 获取仓库信息
     */
    @RequestLine("GET /repository/get?id={id}&excludeProperty=true")
    Response<Rap2Repository> getRepository(@Param("id") Long id);

    /**
     * 创建模块
     */
    @RequestLine("POST /module/create")
    Response<Rap2Module> createModule(Rap2Module module);

    /**
     * 获取接口信息
     */
    @RequestLine("GET /interface/get?id={id}")
    Response<Rap2Interface> getInterface(@Param("id") Long id);

    /**
     * 创建接口
     */
    @RequestLine("POST /interface/create")
    Response<Rap2InterfaceBase> createInterface(Rap2InterfaceBase request);

    /**
     * 更新接口
     */
    @RequestLine("POST /interface/update")
    Response<Rap2InterfaceBase> updateInterface(InterfaceUpdateRequest request);

    /**
     * 更新接口属性
     */
    @RequestLine("POST /properties/update?itf={interfaceId}")
    Response<Rap2InterfaceBase> updateInterfaceProperties(@Param("interfaceId") Long interfaceId, InterfacePropertiesUpdateRequest request);
}
