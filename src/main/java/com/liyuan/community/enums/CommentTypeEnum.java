package com.liyuan.community.enums;

public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private int type;

    public static boolean isExist(int type) {
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
            if (value.getType() == type) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public int getType() {
        return type;
    }

    CommentTypeEnum(int type) {
        this.type = type;
    }
}
