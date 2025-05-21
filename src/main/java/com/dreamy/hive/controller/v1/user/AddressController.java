package com.dreamy.hive.controller.v1.user;

import com.dreamy.hive.common.Result;
import com.dreamy.hive.controller.v1.base.BaseController;
import com.dreamy.hive.dto.request.AddressRequestDTO;
import com.dreamy.hive.dto.response.AddressResponseDTO;
import com.dreamy.hive.service.AddressService;
import com.dreamy.hive.annotation.RequiresLogin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 地址控制器
 * 处理用户收货地址的增删改查
 */
@Tag(name = "地址模块", description = "用户收货地址相关接口")
@RestController
@RequestMapping("/api/v1/address")
public class AddressController extends BaseController {

    @Autowired
    private AddressService addressService;

    /**
     * 添加收货地址
     * @param dto 地址信息
     * @return 添加结果
     */
    @Operation(summary = "添加收货地址", description = "添加新的收货地址")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "添加成功"),
        @ApiResponse(responseCode = "400", description = "添加失败，参数不合法"),
        @ApiResponse(responseCode = "401", description = "未登录")
    })
    @PostMapping("/add")
    @RequiresLogin
    public Result<?> add(@Validated @RequestBody AddressRequestDTO dto) {
        return addressService.add(dto);
    }

    /**
     * 获取地址列表
     * @return 地址列表
     */
    @Operation(summary = "获取地址列表", description = "获取当前用户的所有收货地址")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "获取成功", 
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AddressResponseDTO.class)))),
        @ApiResponse(responseCode = "401", description = "未登录")
    })
    @GetMapping("/list")
    @RequiresLogin
    public Result<?> list() {
        return addressService.getAddressList();
    }

    /**
     * 获取地址详情
     * @param id 地址ID
     * @return 地址详情
     */
    @Operation(summary = "获取地址详情", description = "根据ID获取收货地址详情")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "获取成功", 
                    content = @Content(schema = @Schema(implementation = AddressResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "地址不存在或无权访问"),
        @ApiResponse(responseCode = "401", description = "未登录")
    })
    @GetMapping("/detail/{id}")
    @RequiresLogin
    public Result<?> getDetail(
            @Parameter(description = "地址ID", required = true) 
            @PathVariable("id") Long id) {
        return addressService.getDetail(id);
    }

    /**
     * 更新地址
     * @param dto 地址信息
     * @return 更新结果
     */
    @Operation(summary = "更新地址", description = "更新现有的收货地址")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "更新成功"),
        @ApiResponse(responseCode = "400", description = "更新失败，地址不存在或无权操作"),
        @ApiResponse(responseCode = "401", description = "未登录")
    })
    @PutMapping("/update")
    @RequiresLogin
    public Result<?> update(@Validated @RequestBody AddressRequestDTO dto) {
        return addressService.update(dto);
    }

    /**
     * 删除地址
     * @param id 地址ID
     * @return 删除结果
     */
    @Operation(summary = "删除地址", description = "删除指定的收货地址")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "删除成功"),
        @ApiResponse(responseCode = "400", description = "删除失败，地址不存在或无权操作"),
        @ApiResponse(responseCode = "401", description = "未登录")
    })
    @DeleteMapping("/delete/{id}")
    @RequiresLogin
    public Result<?> delete(
            @Parameter(description = "地址ID", required = true) 
            @PathVariable("id") Long id) {
        return addressService.delete(id);
    }

    /**
     * 设置默认地址
     * @param id 地址ID
     * @return 设置结果
     */
    @Operation(summary = "设置默认地址", description = "将指定地址设为默认收货地址")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "设置成功"),
        @ApiResponse(responseCode = "400", description = "设置失败，地址不存在或无权操作"),
        @ApiResponse(responseCode = "401", description = "未登录")
    })
    @PutMapping("/set-default/{id}")
    @RequiresLogin
    public Result<?> setDefault(
            @Parameter(description = "地址ID", required = true) 
            @PathVariable("id") Long id) {
        return addressService.setDefault(id);
    }

    /**
     * 获取默认地址
     * @return 默认地址
     */
    @Operation(summary = "获取默认地址", description = "获取当前用户的默认收货地址")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "获取成功", 
                    content = @Content(schema = @Schema(implementation = AddressResponseDTO.class))),
        @ApiResponse(responseCode = "401", description = "未登录")
    })
    @GetMapping("/default")
    @RequiresLogin
    public Result<?> getDefault() {
        return addressService.getDefault();
    }
}
