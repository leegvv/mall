package net.arver.mall.dto;

import lombok.Getter;
import lombok.Setter;
import net.arver.mall.model.UmsMenu;

import java.util.List;

/**
 * 后台菜单节点封装
 */
@Getter
@Setter
public class UmsMenuNode extends UmsMenu {
    private List<UmsMenuNode> children;
}
