package com.dreamy.hive.controller.v1.user;

import com.dreamy.hive.common.Result;
import com.dreamy.hive.controller.v1.base.BaseController;
import com.dreamy.hive.dto.request.CartAddRequestDTO;
import com.dreamy.hive.dto.request.CartUpdateRequestDTO;
import com.dreamy.hive.dto.response.CartItemResponseDTO;
import com.dreamy.hive.service.CartService;
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
 * 购物车控制器
 * 处理购物车的增删改查
 */
@Tag(name = "购物车模块", description = "购物车相关接口")
@RestController
@RequestMapping("/api/v1/cart")
public class CartController extends BaseController {

    @Autowired
    private CartService cartService;

    /**
     * 添加商品到购物车
     * @param dto 购物车商品信息
     * @return 添加结果
     */
    @Operation(summary = "添加商品到购物车", description = "添加商品到购物车，如果已存在则增加数量")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "添加成功"),
        @ApiResponse(responseCode = "400", description = "添加失败，商品不存在或参数不合法"),
        @ApiResponse(responseCode = "401", description = "未登录")
    })
    @PostMapping("/add")
    @RequiresLogin
    public Result<?> add(@Validated @RequestBody CartAddRequestDTO dto) {
        return cartService.add(dto);
    }

    /**
     * 获取购物车列表
     * @return 购物车商品列表
     */
    @Operation(summary = "获取购物车列表", description = "获取当前用户的购物车商品列表")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "获取成功", 
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CartItemResponseDTO.class)))),
        @ApiResponse(responseCode = "401", description = "未登录")
    })
    @GetMapping("/list")
    @RequiresLogin
    public Result<?> list() {
        return cartService.getCartList();
    }

    /**
     * 更新购物车商品数量
     * @param dto 更新信息
     * @return 更新结果
     */
    @Operation(summary = "更新购物车商品数量", description = "更新购物车中商品的数量")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "更新成功"),
        @ApiResponse(responseCode = "400", description = "更新失败，商品不存在或参数不合法"),
        @ApiResponse(responseCode = "401", description = "未登录")
    })
    @PutMapping("/update")
    @RequiresLogin
    public Result<?> update(@Validated @RequestBody CartUpdateRequestDTO dto) {
        return cartService.update(dto);
    }

    /**
     * 删除购物车商品
     * @param id 购物车项ID
     * @return 删除结果
     */
    @Operation(summary = "删除购物车商品", description = "从购物车中删除指定商品")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "删除成功"),
        @ApiResponse(responseCode = "400", description = "删除失败，商品不存在或无权操作"),
        @ApiResponse(responseCode = "401", description = "未登录")
    })
    @DeleteMapping("/delete/{id}")
    @RequiresLogin
    public Result<?> delete(
            @Parameter(description = "购物车项ID", required = true) 
            @PathVariable("id") Long id) {
        return cartService.delete(id);
    }

    /**
     * 选中/取消选中购物车商品
     * @param id 购物车项ID
     * @param checked 是否选中：1-选中，0-取消选中
     * @return 更新结果
     */
    @Operation(summary = "选中/取消选中购物车商品", description = "选中或取消选中购物车中的商品")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "操作成功"),
        @ApiResponse(responseCode = "400", description = "操作失败，商品不存在或无权操作"),
        @ApiResponse(responseCode = "401", description = "未登录")
    })
    @PutMapping("/check/{id}/{checked}")
    @RequiresLogin
    public Result<?> updateChecked(
            @Parameter(description = "购物车项ID", required = true) 
            @PathVariable("id") Long id,
            @Parameter(description = "是否选中：1-选中，0-取消选中", required = true) 
            @PathVariable("checked") Integer checked) {
        return cartService.updateChecked(id, checked);
    }
}
